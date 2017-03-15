package com.example.android.sendmoods;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 *
 */

public class EditMoodActivityTest extends ActivityInstrumentationTestCase2<EditMoodActivity> {

    public EditMoodActivityTest() { super(EditMoodActivity.class):
    }

    public void testAddMood() {
        Location location = new Location("defaultlocation");

        MoodEvent moodEvent = new MoodEvent("default", "default", "default", "default", location, "default", "default", 0, "default");
        ArrayList moodList = new ArrayList<MoodEvent>();
        moodList.add(moodEvent);


        moodList.add(moodEvent);
        assertTrue(moodList.hasMood(moodEvent));
    }

    public void testHasMood() {
        MoodEvent moodEvent = new MoodEvent();
        MoodList moodList = new MoodList();

        assertFalse(moodList.hasMood(moodEvent));
        moodList.add(moodEvent);

        assertTrue(moodList.hasUserName(moodEvent));
    }

    public void testDeleteMood() {
        MoodEvent moodEvent = new MoodEvent();
        MoodList moodList = new MoodList();
        moodList.add(moodEvent);
        moodList.delete(moodEvent);
        assertFalse(moodList.hasTweet(moodEvent));
    }

    public void testAddReason() {
        MoodReason moodReason = new MoodReason();

        try {
            MoodList.add(moodReason);
        }
        catch (IllegalArgumentException e){
            RuntimeException();

        }
        assertTrue(MoodList.hasMoodReason(moodReason));
    }


}
