package com.example.consultants.week3day3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction())
        {
            case MusicService.NOTIFY_PLAY:
                Toast.makeText(context, "Play", Toast.LENGTH_SHORT).show();
                break;
            case MusicService.NOTIFY_PAUSE:
                Toast.makeText(context, "Pause", Toast.LENGTH_SHORT).show();
                break;
            case MusicService.NOTIFY_STOP:
                context.stopService(intent);
                break;
        }

    }
}
