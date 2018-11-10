package com.example.consultants.week3day3.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.consultants.week3day3.model.Person;
import com.example.consultants.week3day3.model.PersonGenerator;

import java.util.ArrayList;

public class MyIntentService extends IntentService {

    public static final String TAG = MyIntentService.class.getSimpleName() + "_TAG";
    public static final String PERSON_LIST = "personList";

    public MyIntentService()
    {
        super("IntentService");
    }

    //handles intent - gets data for recycleview from PersonGenerator
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent: ");
        ArrayList<Person> personList = PersonGenerator.generate(20);

        //sends broadcast
        Intent returnIntent = new Intent();
        returnIntent.setAction(PERSON_LIST);
        returnIntent.putParcelableArrayListExtra(PERSON_LIST, personList);
        sendBroadcast(returnIntent);
    }
}
