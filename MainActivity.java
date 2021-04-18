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


public class MainActivity extends AppCompatActivity {
    NotificationManager man;
    NotificationCompat.Builder bui;
    private static String CHANNEL_ID = "cha";
    private static String CHANEL_NAME = "Cha";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView image = (ImageView) findViewById(R.id.target);
        SeekBar seekbar = (SeekBar) findViewById(R.id.changer);
        Intent intent = getIntent();
        Integer name = intent.getIntExtra("name",0 );
        if(name!=null){
            seekbar.setProgress(name);
        }
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekbar){}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar){}

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int a = Integer.valueOf(progress);
                if (a >= 50) {
                    image.setImageResource(R.drawable.change);
                    alter(a);
                } else {
                    image.setImageResource(R.drawable.main);
                }
            }

        });
    }
    public void alter(Integer name){
        bui = null;
        man = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            man.createNotificationChannel(new NotificationChannel(CHANNEL_ID,CHANEL_NAME,NotificationManager.IMPORTANCE_DEFAULT));
        bui = new NotificationCompat.Builder(this,CHANNEL_ID);
        }else{
            bui = new NotificationCompat.Builder(this);
        }
    Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name",name);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 101,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        bui.setContentTitle("알림");
        bui.setContentText("50%이상입니다.");
        bui.setSmallIcon(R.drawable.ic_launcher_foreground);
        bui.setAutoCancel(true);
        bui.setContentIntent(pendingIntent);
        Notification notification = bui.build();
        man.notify(1,notification);
    }
}