package com.example.android.sendmoods;

/**
 *
 */

public class EditMoodActivityTest extends {

    public EditMoodActivityTest() { super(EditMoodActivity.class):
    }

    public void testAddMood() {
        MoodEvent moodEvent = new MoodEvent();
        MoodList moodList = new MoodList();
        moodList.add(moodEvent);

        try {
            moodList.add(moodEvent);
        }
        catch (IllegalArgumentException e){
            RuntimeException();

        }
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
