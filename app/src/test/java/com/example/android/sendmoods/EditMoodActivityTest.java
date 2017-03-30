package com.example.android.sendmoods;

import android.app.Activity;

import android.test.ActivityInstrumentationTestCase;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;


/**
 *
 */


public class EditMoodActivityTest extends InstrumentationRegistry{

    private Solo solo;

    public EditMoodActivityTest() { super(EditMoodActivity.class);
    }


    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }


    public void testStart() throws Exception{
        Activity activity = getActivity();

    }


    public void testAddMood() {
        //add camera testing later?
        assertTrue(true);
        solo.assertCurrentActivity("Wrong Activity!", EditMoodActivity.class);



    }

    public void testHasMood() {
        assertFalse(true);

    }

    public void testDeleteMood() {
        assertTrue(false);

    }

    public void testAddReason() {
        assertFalse(false);


    }


}
