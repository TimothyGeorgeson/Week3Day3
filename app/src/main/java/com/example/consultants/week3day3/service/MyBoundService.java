package com.example.consultants.week3day3.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyBoundService extends Service {
    public static final String TAG = MyBoundService.class.getSimpleName() + "_TAG";
    public final static String ID = "ID";

    private RequestHandler requestHandler;
    private Messenger messenger;

    @Override
    public void onCreate() {
        super.onCreate();

        requestHandler = new RequestHandler(this);
        messenger = new Messenger(requestHandler);

        Log.d(TAG, "onCreate: MyBoundService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return messenger.getBinder();
    }

}
