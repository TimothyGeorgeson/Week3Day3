package com.example.consultants.week3day3.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.consultants.week3day3.NotificationReceiver;
import com.example.consultants.week3day3.R;
import com.example.consultants.week3day3.model.PersonGenerator;
import com.example.consultants.week3day3.service.MusicService;
import com.example.consultants.week3day3.service.MyIntentService;

public class MainActivity extends AppCompatActivity {

    private NotificationReceiver notificationReceiver;
    private RecyclerView rvList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

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

//        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
//        intent.putExtra()

        rvList = findViewById(R.id.rvList);
        layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        adapter = new MyAdapter(PersonGenerator.generate(20));
        rvList.setAdapter(adapter);
    }
}
