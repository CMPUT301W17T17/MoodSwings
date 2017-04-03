package com.example.android.sendmoods;


import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Matt on 2017-04-03.
 */

public class MoodListActivityTest {

    //private Solo solo;
    private EmptyActivity activity;

    public MoodListActivityTest() {
        activity = new EmptyActivity();
    }

    @Test
    public void listSizeTest(){
        MoodList testList= new MoodList(activity);

        MoodEvent mood = new MoodEvent();
        testList.add(mood);

        Assert.assertEquals(testList.size(),1);
    }

    @Test
    public void testMoodList() throws Exception {
        MoodList testList = new MoodList(activity);
        assertEquals(testList.size(), 0);
        MoodEvent moodEvent = new MoodEvent();
        testList.add(moodEvent);
        assertEquals(testList.size(), 1);
        moodEvent.setDate("April 01, 2000");
        MoodEvent newMood = new MoodEvent();
        testList.add(newMood);

        assertEquals(newMood, testList.getMostRecent());
    }

    @Test
    public void addRecentMood(){
        MoodEvent mood = new MoodEvent();

        mood.setAddress("123 fake street");

        MoodList testList= new MoodList(activity);
        testList.add(mood);
        Assert.assertEquals(testList.size(),1);

        assertEquals("123 fake street",testList.getMostRecent().getAddress());

    }
    @Test
    public void removeRecentMood(){
        MoodEvent mood = new MoodEvent();

        MoodList testList= new MoodList(activity);
        testList.add(mood);
        testList.deleteLast();
        Assert.assertEquals(testList.size(),0);
    }

    @Test
    public void verifyList(){

        MoodList testList= new MoodList(activity);
        MoodEvent mood = new MoodEvent();
        MoodEvent mood2 = new MoodEvent();


        mood.setUsername("First");
        testList.add(mood);

        mood2.setUsername("Second");
        testList.add(mood2);

        Assert.assertEquals(testList.size(),2);


        assertEquals(testList.getUsernames().size(),2);


    }



}


