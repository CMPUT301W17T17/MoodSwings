package com.example.android.sendmoods;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by ahua on 3/9/17.
 */

//update for string date and time
public class MoodEventTest{

    private EmptyActivity activity;

    public MoodEventTest() {
        activity = new EmptyActivity();
    }

    @Test
    public void addressTest(){
        MoodEvent mood = new MoodEvent();
        MoodEvent mood2 = new MoodEvent();

        mood.setAddress("123 fake street");
        mood2.setAddress("fake street 2");

        assertEquals(mood.getAddress(),"123 fake street");
        assertEquals(mood2.getAddress(),"fake street 2");

    }

    @Test
    public void usernameTest(){
        MoodEvent mood = new MoodEvent();
        MoodEvent mood2 = new MoodEvent();

        mood.setUsername("First");
        mood2.setUsername("Second");

        assertEquals(mood.getUsername(),"First");
        assertEquals(mood2.getUsername(),"Second");
    }

    @Test
    public void reasonTest(){
        MoodEvent mood = new MoodEvent();
        MoodEvent mood2 = new MoodEvent();

        mood.setReason("I dont know");
        mood2.setReason("I know");

        assertEquals(mood.getReason(),"I dont know");
        assertEquals(mood2.getReason(),"I know");
    }

    @Test
    public void photoTest(){
        MoodEvent mood = new MoodEvent();
        MoodEvent mood2 = new MoodEvent();

        mood.setReason("I dont know");
        mood.setAddress("123 fake street");

        mood2.setReason("I know");
        mood2.setAddress("Fake street 2");

        assertEquals(mood.getPhoto(),null);
        assertEquals(mood2.getPhoto(),null);
    }




}
