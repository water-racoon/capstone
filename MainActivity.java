package com.example.bulid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    NotificationManager man;
    NotificationCompat.Builder bui;
    private static String CHANNEL_ID = "cha";
    private static String CHANEL_NAME = "Cha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ImageView와 seekBar 객체 선언
        ImageView image = (ImageView) findViewById(R.id.target);
        ImageView image2 = (ImageView) findViewById(R.id.target2);
        ImageView image3 = (ImageView) findViewById(R.id.target3);
        ImageView image4 = (ImageView) findViewById(R.id.target4);
        SeekBar seekbar = (SeekBar) findViewById(R.id.changer);
        SeekBar seekbar2 = (SeekBar) findViewById(R.id.changer2);
        SeekBar seekbar3 = (SeekBar) findViewById(R.id.changer3);
        SeekBar seekbar4 = (SeekBar) findViewById(R.id.changer4);
        // Intent에 값을 보낼 리스트배열 list 선언
        ArrayList<Integer> list = new ArrayList<Integer>();
        // Intent값 받아오기
        Intent intent = getIntent();
        // 리스트배열 name에 받아온 Intent값 집어넣기
        ArrayList<Integer> name = (ArrayList<Integer>) intent.getSerializableExtra("name");

        if(name!=null){
            //name값이 있다면 seekbar값 셋팅 및 seekbar값에 맞춘 이미지셋팅
            seekbar.setProgress(name.get(0));
            seekbar2.setProgress(name.get(1));
            seekbar3.setProgress(name.get(2));
            seekbar4.setProgress(name.get(3));
            changeimage(name.get(0),image);
            changeimage(name.get(1),image2);
            changeimage(name.get(2),image3);
            changeimage(name.get(3),image4);
            list.clear();
        }
        // seekbar각 객체에 따른 이벤트리스너 셋팅 조건은 seekBar의 값을 기준으로 25씩 사진변경, 75이상시 알림
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = Integer.valueOf(progress);
                if (a >= 75) {
                    image.setImageResource(R.drawable.burn);
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            list.add(0,new Integer(seekbar.getProgress()));
                            list.add(1,new Integer(seekbar2.getProgress()));
                            list.add(2,new Integer(seekbar3.getProgress()));
                            list.add(3,new Integer(seekbar4.getProgress()));
                            alter(list,"종이");
                        }
                    };
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(task,0,1000);
                }else if(a>=50) {
                    image.setImageResource(R.drawable.happy);
                }
                 else if(a>=25){
                     image.setImageResource(R.drawable.change);
                    } else {
                    image.setImageResource(R.drawable.main);
                }
            }
        });
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = Integer.valueOf(progress);
                if (a >= 75) {
                    image2.setImageResource(R.drawable.burn);
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            list.add(0,new Integer(seekbar.getProgress()));
                            list.add(1,new Integer(seekbar2.getProgress()));
                            list.add(2,new Integer(seekbar3.getProgress()));
                            list.add(3,new Integer(seekbar4.getProgress()));
                            alter(list,"플라스틱");
                        }
                    };
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(task,0,1000);
                }else if(a>=50) {
                    image2.setImageResource(R.drawable.happy);
                }
                else if(a>=25){
                    image2.setImageResource(R.drawable.change);
                } else {
                    image2.setImageResource(R.drawable.main);
                }
            }
        });
        seekbar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = Integer.valueOf(progress);
                if (a >= 75) {
                    image3.setImageResource(R.drawable.burn);

                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            list.add(0,new Integer(seekbar.getProgress()));
                            list.add(1,new Integer(seekbar2.getProgress()));
                            list.add(2,new Integer(seekbar3.getProgress()));
                            list.add(3,new Integer(seekbar4.getProgress()));
                            alter(list,"캔");
                        }
                    };
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(task,0,1000);
                }else if(a>=50) {
                    image3.setImageResource(R.drawable.happy);
                }
                else if(a>=25){
                    image3.setImageResource(R.drawable.change);
                } else {
                    image3.setImageResource(R.drawable.main);
                }
            }
        });
        seekbar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar){}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = Integer.valueOf(progress);
                if (a >= 75) {
                    image4.setImageResource(R.drawable.burn);
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            list.add(0,new Integer(seekbar.getProgress()));
                            list.add(1,new Integer(seekbar2.getProgress()));
                            list.add(2,new Integer(seekbar3.getProgress()));
                            list.add(3,new Integer(seekbar4.getProgress()));
                            alter(list,"일반");
                        }
                    };
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(task,0,1000);
                }else if(a>=50) {
                    image4.setImageResource(R.drawable.happy);
                }
                else if(a>=25){
                    image4.setImageResource(R.drawable.change);
                } else {
                    image4.setImageResource(R.drawable.main);
                }
            }
        });
    }
    // 알림 함수
    public void alter(ArrayList name, String val){
        // builder와 manager선언
        bui = null;
        man = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 핸드폰 OS버전에 따른 manager체널 개설
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //버전 오레오 이상일때
            man.createNotificationChannel(new NotificationChannel(CHANNEL_ID,CHANEL_NAME,NotificationManager.IMPORTANCE_DEFAULT));
        bui = new NotificationCompat.Builder(this,CHANNEL_ID);
        }else{
            //하위버전일때
            bui = new NotificationCompat.Builder(this);
        }
    Intent intent = new Intent(this, MainActivity.class);
        // intent에 전달할 값 name 삽입
        intent.putExtra("name",name);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        // 알림제목
        bui.setContentTitle("알림");
        // 알림내용
        bui.setContentText(val+"의 용량이 75%이상입니다.");
        // 알림 아이콘(24*24 사이즈)
        bui.setSmallIcon(R.drawable.ic_launcher_foreground);
        // 알림 터치시 자동 알림삭제
        bui.setAutoCancel(true);
        // 알림창 터치시 Intent가 전달될수 있도록 해줌
        bui.setContentIntent(pendingIntent);
        Notification notification = bui.build();
        // 알림 실행
        man.notify(1,notification);
    }
    public void changeimage(int a,ImageView image){
        if (a >= 75) {
            image.setImageResource(R.drawable.burn);
        }else if(a>=50) {
            image.setImageResource(R.drawable.happy);
        }
        else if(a>=25){
            image.setImageResource(R.drawable.change);
        } else {
            image.setImageResource(R.drawable.main);
        }
    }
}
