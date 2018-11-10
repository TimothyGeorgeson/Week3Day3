package com.example.consultants.week3day3.model;

import android.media.Image;

import com.example.consultants.week3day3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    public static ArrayList<Person> generate(int count){
        ArrayList<Person> personList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            personList.add(new Person(getRandomImage(), getRandomName(), getRandomAge(), getRandomGender()));
        }
        return personList;
    }

    private static Integer getRandomImage(){
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.person1);
        images.add(R.drawable.person2);
        images.add(R.drawable.person3);
        images.add(R.drawable.person4);

        return images.get(new Random().nextInt(images.size()));
    }

    private static String getRandomName(){
        List<String> names = new ArrayList<>();
        names.add("Josh");
        names.add("Nathan");
        names.add("Keenan");
        names.add("Tim");
        names.add("Michael");
        names.add("Bernardo");

        return names.get(new Random().nextInt(names.size()));
    }

    private static String getRandomAge(){

        return String.valueOf(new Random().nextInt(30)+10);
    }

    private static String getRandomGender(){
        List<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        return genders.get(new Random().nextInt(2));
    }
}
