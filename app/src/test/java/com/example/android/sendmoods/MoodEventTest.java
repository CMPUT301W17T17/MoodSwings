package com.example.android.sendmoods;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ahua on 3/9/17.
 */

public class MoodEventTest extends ActivityInstrumentationTestCase2{

    public MoodEventTest(){
        super(MoodListActivity.class);
    }

    public void testgetPopupShape() {
        MoodEvent testmood = new MoodEvent();
        testmood.setPopupShape(0);
        int shape = testmood.getPopupShape();
        assertEquals(shape, 0);
    }

    public void testsetPopupShape() {
        /*same as testgetPopupShape()?*/
    }

    public void testgetColor() {
        MoodEvent testmood = new MoodEvent();
        testmood.setColor("Green");
        assertEquals(testmood.getColor(),"Green");
    }

    public void testsetColor() {
        /*same as testgetColor()?*/
    }

}
