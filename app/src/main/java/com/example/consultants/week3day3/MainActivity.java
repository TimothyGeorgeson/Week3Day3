package com.example.consultants.week3day3;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private NotificationReceiver notificationReceiver;
    private RecyclerView rvList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = findViewById(R.id.rvList);
        layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
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
                intent.setAction(MusicService.NOTIFY_PLAY);
                startService(intent);
                break;
            case R.id.btnPause:
                intent.setAction(MusicService.NOTIFY_PAUSE);
                startService(intent);
                break;
            case R.id.btnStop:
                intent.setAction(MusicService.NOTIFY_STOP);
                startService(intent);
                break;
        }
    }

    public void showList(View view) {

    }
}
