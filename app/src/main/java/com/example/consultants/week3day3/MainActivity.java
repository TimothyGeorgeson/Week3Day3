package com.example.consultants.week3day3;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationReceiver notificationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notificationReceiver = new NotificationReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.NOTIFY_PLAY);
        intentFilter.addAction(MusicService.NOTIFY_PAUSE);
        intentFilter.addAction(MusicService.NOTIFY_STOP);
        registerReceiver(notificationReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(notificationReceiver);
    }

    public void clickMusic(View view) {

        Intent intent = new Intent(this, MusicService.class);

        switch (view.getId())
        {
            case R.id.btnStart:
                startService(intent);
                break;
            case R.id.btnStop:
                stopService(intent);
                break;
        }
    }
}
