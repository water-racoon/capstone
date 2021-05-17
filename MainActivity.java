package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
        import android.os.Handler;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.io.PrintWriter;
        import java.net.Socket;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button connect_btn;                 // ip 받아오는 버튼

    TextView show_text;             // 서버에서온거 보여주는 에디트
    ImageView target;
    // 소켓통신에 필요한것
    private String html = "";
    private Handler mHandler;

    private Socket socket;

    private BufferedReader networkReader;
    private PrintWriter networkWriter;

    private DataOutputStream dos;
    private DataInputStream dis;

    private String ip = "192.168.43.89";            // IP 번호
    private int port = 9999;                          // port 번호

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connect_btn = (Button)findViewById(R.id.connect_btn);
        

        show_text = (TextView)findViewById(R.id.show_text);
        target = (ImageView)findViewById(R.id.target);

    }

    @Override
    public void onStart() {
        super.onStart();
        connect();
    }

    // 로그인 정보 db에 넣어주고 연결시켜야 함.
    void connect(){
        mHandler = new Handler();
        Log.w("connect","연결 하는중");
        // 받아오는거
        Thread checkUpdate = new Thread() {
            public void run() {
                // 서버 접속
                try {
                    socket = new Socket(ip, port);
                    Log.w("서버 접속됨", "서버 접속됨");
                } catch (IOException e1) {
                    Log.w("서버접속못함", "서버접속못함");
                    e1.printStackTrace();
                }

                Log.w("edit 넘어가야 할 값 : ","안드로이드에서 서버로 연결요청");

                // Buffered가 잘못된듯.
                try {
                    dos = new DataOutputStream(socket.getOutputStream());   // output에 보낼꺼 넣음
                    dis = new DataInputStream(socket.getInputStream());     // input에 받을꺼 넣어짐
                    dos.writeUTF("안드로이드에서 서버로 연결요청");

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("버퍼", "버퍼생성 잘못됨");
                }
                Log.w("버퍼","버퍼생성 잘됨");

                while(true) {
                    // 서버에서 받아옴
                    try {
                        String line2;
                        while (true) {
                            //line = (String) dis.readUTF();
                            line2 = (String) dis.readLine();
                            String [] part = line2.split(";");
                            int [] part2 = Arrays.stream(part).mapToInt(Integer::parseInt).toArray();
                            for (int i = 0; i < 4; i++) {
                                //Log.w("서버에서 받아온 값 ", "" + line);
                                //Log.w("서버에서 받아온 값 ", "" + line2);

                            switch (i){
                                case 0:  //플라스틱일때
                                    if(part2[0]>25){  //플라스틱 25%찼을때
                                        target.setImageResource(R.drawable.ic_launcher_foreground);
                                    }
                                    break;
                                case 1:  //캔일때
                                    break;
                                case 2:  //종이일때
                                    break;
                                case 3:  //일반일때
                                    break;
                                }
                            }
                        }
                    } catch (Exception e) {

                    }
                }

            }
        };
        // 소켓 접속 시도, 버퍼생성
        checkUpdate.start();
    }
}
