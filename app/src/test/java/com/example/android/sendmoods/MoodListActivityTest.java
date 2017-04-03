package com.example.android.sendmoods;


import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by Matt on 2017-04-03.
 */

public class MoodListActivityTest extends ActivityInstrumentationTestCase2<MoodListActivity> {

    //private Solo solo;
    private EmptyActivity activity;

    public MoodListActivityTest() {
        super(com.example.android.sendmoods.MoodListActivity.class);

        activity = new EmptyActivity();
    }

    public void testMoodList(){
        MoodList testList = new MoodList(activity);
        assertEquals(testList.size(), 0);
        testList.add(new MoodEvent());
        assertEquals(testList.size(), 1);
    }

    /*public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    public void testNewMood(){
        assertTrue("not true", true);

        /*
        solo.assertCurrentActivity("Wrong Activity!", EditMoodActivity.class);
        solo.enterText((EditText) solo.getView(R.id.reason_text), "im sad");

        solo.clickOnButton("sad");

        solo.clickOnButton("save");*?


    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }*/

}


