package com.example.consultants.week3day3;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_ID = "chan01";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID, "Music Player", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(serviceChannel);
        }
    }
}
