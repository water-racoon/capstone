package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
        import android.os.Handler;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    ImageView target,target2,target3,target4;
    ProgressBar progress,progress2,progress3,progress4;
    TextView text,text2,text3,text4;
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

        target = (ImageView)findViewById(R.id.target);
        target2 = (ImageView)findViewById(R.id.target2);
        target3 = (ImageView)findViewById(R.id.target3);
        target4 = (ImageView)findViewById(R.id.target4);
        progress = (ProgressBar) findViewById(R.id.progressBar2);
        progress2 = (ProgressBar)findViewById(R.id.progressBar3);
        progress3 = (ProgressBar)findViewById(R.id.progressBar4);
        progress4 = (ProgressBar)findViewById(R.id.progressBar5);
        text = (TextView)findViewById(R.id.text1);
        text2 = (TextView)findViewById(R.id.text2);
        text3 = (TextView)findViewById(R.id.text3);
        text4 = (TextView)findViewById(R.id.text4);
        text.setText(progress.getProgress()+"%");
        text2.setText(progress2.getProgress()+"%");
        text3.setText(progress3.getProgress()+"%");
        text4.setText(progress4.getProgress()+"%");
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
                                    progress.setProgress(part2[0]);
                                    text.setText(part2[0]+"%");
                                    break;
                                case 1:  //캔일때
                                    progress2.setProgress(part2[1]);
                                    text2.setText(part2[1]+"%");
                                    break;
                                case 2:  //종이일때
                                    progress3.setProgress(part2[2]);
                                    text3.setText(part2[2]+"%");
                                    break;
                                case 3:  //일반일때
                                    progress4.setProgress(part2[3]);
                                    text4.setText(part2[3]+"%");
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
