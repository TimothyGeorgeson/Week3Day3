package com.example.consultants.week3day3;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import static com.example.consultants.week3day3.App.CHANNEL_ID;

public class MusicService extends Service {
    public static final String NOTIFY_PLAY = "com.example.consultants.week3day3.play";
    public static final String NOTIFY_PAUSE = "com.example.consultants.week3day3.pause";
    public static final String NOTIFY_STOP = "com.example.consultants.week3day3.stop";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getAction())
        {
            case NOTIFY_PLAY:
                RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.notification);

                if (mediaPlayer == null)
                {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.frenchjazz);
                }
                mediaPlayer.start();

                Intent intentNotify = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this,
                        0, intentNotify, 0);

                Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("Music Player")
                        .setContentText("Play/Pause/Stop Music")
                        .setSmallIcon(R.drawable.ic_music_note)
                        .setContentIntent(pendingIntent)
                        .setCustomBigContentView(expandedView)
                        .build();

                setListeners(expandedView, this);

                startForeground(1, notification);

                break;
            case NOTIFY_PAUSE:
                mediaPlayer.pause();
                break;
            case NOTIFY_STOP:
                stopForeground(true);
                stopSelf();
                break;
        }

        return START_NOT_STICKY;
    }

    private static void setListeners(RemoteViews views, Context context)
    {
        Intent play = new Intent(NOTIFY_PLAY);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent stop = new Intent(NOTIFY_STOP);

        PendingIntent pPlay = PendingIntent.getBroadcast(context,0, play, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.btnPlay, pPlay);

        PendingIntent pPause = PendingIntent.getBroadcast(context,0, pause, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.btnPause, pPause);

        PendingIntent pStop = PendingIntent.getBroadcast(context,0, stop, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.btnStop, pStop);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
