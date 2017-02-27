package com.example.android.sendmoods;

import java.security.cert.CertPathValidatorException;

/**
 *
 */

public class EditMoodActivityTest extends {

    public EditMoodActivityTest() { super(EditMoodActivity.class):
    }

    public void testAddMood() {
        Mood mood = new Mood();
        MoodList moodList = new MoodList();
        moodList.add(mood);

        try {
            moodList.add(mood);
        }
        catch (IllegalArgumentException e){
            RuntimeException();

        }
        assertTrue(moodList.hasMood(mood));
    }

    public void testHasMood() {
        Mood mood = new Mood();
        MoodList moodList = new MoodList();

        assertFalse(moodList.hasMood(mood));
        moodList.add(mood);

        assertTrue(moodList.hasUserName(mood));
    }

    public void testDeleteMood() {
        Mood mood = new Mood();
        MoodList moodList = new MoodList();
        moodList.add(mood);
        moodList.delete(mood);
        assertFalse(moodList.hasTweet(mood));
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
