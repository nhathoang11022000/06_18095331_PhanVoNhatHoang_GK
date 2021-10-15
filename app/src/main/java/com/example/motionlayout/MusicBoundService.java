package com.example.motionlayout;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class MusicBoundService extends Service {
    private MyBinder myBinder = new MyBinder();
    private MediaPlayer mediaPlayer;
    public class MyBinder extends Binder {
        MusicBoundService getMusicBoundService() {
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public void StartMusic(){
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.filemusic);
        }
        mediaPlayer.start();
    }
}

