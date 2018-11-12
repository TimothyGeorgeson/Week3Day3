package com.example.consultants.week3day3.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;

public class MyBoundService extends Service {

    public final static String ID = "ID";

    private RequestHandler requestHandler;
    private Messenger messenger;

    @Override
    public void onCreate() {
        super.onCreate();

        requestHandler = new RequestHandler(this);
        messenger = new Messenger(requestHandler);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return messenger.getBinder();
    }

}
