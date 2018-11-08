package com.example.consultants.week3day3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
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
        }

    }
}
