package com.example.android.sendmoods;

import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Created by ahua on 3/9/17.
 */

//update for string date and time
public class MoodEventTest{

        @Test
        public void createMoodTest() {
            MoodEvent testmood = new MoodEvent();
            testmood.setReason("test reason");
            assertEquals(testmood.getReason(), "test reason");

        }


    /*add testing for parcelable functionality*/



}
