package com.example.motionlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView play ,pause;
    private MusicBoundService musicBoundService;
    private boolean isServiceConnected;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {

            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) iBinder;
            musicBoundService = myBinder.getMusicBoundService();
            musicBoundService.StartMusic();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceConnected = false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStopService();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickStartService();
            }
        });
    }
    private void onClickStartService() {
        Intent intent = new Intent(this, MusicBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private void onClickStopService() {
        if(isServiceConnected){
            unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }

}