package com.example.consultants.week3day3.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.util.Random;

public class RequestHandler extends Handler {

    public RequestHandler(Context context) {

    }

    public void handleMessage(Message request) {
        //Store reply messenger
        final Messenger replyMessenger = request.replyTo;

        //create message, put into bundle
        Message reply = Message.obtain();
        Bundle data = new Bundle();
        int randNum = new Random().nextInt(100);
        data.putString(MyBoundService.ID, String.valueOf(randNum));
        reply.setData(data);

        //use messenger to send reply message
        try {
            replyMessenger.send(reply);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
