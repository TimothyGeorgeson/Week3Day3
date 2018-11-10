package com.example.consultants.week3day3.view;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.consultants.week3day3.R;
import com.example.consultants.week3day3.model.Person;
import com.example.consultants.week3day3.service.MusicService;
import com.example.consultants.week3day3.service.MyIntentService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    private MyBroadcastReceiver myBroadcastReceiver;
    private RecyclerView rvList;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rvList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.NOTIFY_PLAY);
        intentFilter.addAction(MusicService.NOTIFY_PAUSE);
        intentFilter.addAction(MusicService.NOTIFY_STOP);
        intentFilter.addAction(MyIntentService.PERSON_LIST);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
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
        //start Intent service
        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
        startService(intent);
    }

    //declare broadcast receiver
    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive: ");
            Intent returnIntent = new Intent(context, MusicService.class);
            switch (intent.getAction())
            {
                case MusicService.NOTIFY_PLAY:
                    returnIntent.setAction(MusicService.NOTIFY_PLAY);
                    context.startService(returnIntent);
                    break;
                case MusicService.NOTIFY_PAUSE:
                    returnIntent.setAction(MusicService.NOTIFY_PAUSE);
                    context.startService(returnIntent);
                    break;
                case MusicService.NOTIFY_STOP:
                    returnIntent.setAction(MusicService.NOTIFY_STOP);
                    context.startService(returnIntent);
                    break;
                case MyIntentService.PERSON_LIST:
                    //get personList from intentservice
                    ArrayList<Person> personList = intent.getParcelableArrayListExtra(MyIntentService.PERSON_LIST);

                    //setup recycleview/adapter with personlist data
                    layoutManager = new LinearLayoutManager(context);
                    rvList.setLayoutManager(layoutManager);
                    adapter = new MyAdapter(personList);
                    rvList.setAdapter(adapter);
            }
        }
    }
}
