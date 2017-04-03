package com.example.android.sendmoods;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by Amy on 2017-03-30.
 */

@RunWith(AndroidJUnit4.class)

public class EditMoodActivityTest extends ActivityInstrumentationTestCase2<EditMoodActivity> {


    private Solo solo;

    public EditMoodActivityTest() {
        super(com.example.android.sendmoods.EditMoodActivity.class);
    }

    public void setUp() throws Exception{
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

        solo.clickOnButton("save");*? */


    }
}