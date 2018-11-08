package com.example.consultants.week3day3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
