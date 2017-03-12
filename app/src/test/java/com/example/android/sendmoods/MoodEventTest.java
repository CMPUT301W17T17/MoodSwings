package com.example.android.sendmoods;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

/**
 * Created by ahua on 3/9/17.
 */

public class MoodEventTest extends ActivityInstrumentationTestCase2{

    public MoodEventTest(){
        super(MoodListActivity.class);
    }

    public void testMoodEventCreate(){
        /*test on create of mood event*/
        Location location = new Location("eventlocation");
        location.setLatitude(53.5443d);
        location.setLongitude(-113.4909d);

        Date date = new Date();

        MoodEvent moodevent = new MoodEvent(date, "bought an ice cream", "hobiehobie", location, "Happy");

        Date mooddate = moodevent.getDateTime();
        String moodreason = moodevent.getReason();
        String mooduser = moodevent.getUsername();
        Location moodlocation = moodevent.getAddress();
        String moodmood = moodevent.getEmotion();

        assertEquals(mooddate, date);
        assertEquals(moodreason, "bought an ice cream");
        assertEquals(mooduser, "hobiehobie");
        assertEquals(moodlocation, location);
        assertEquals(moodmood, "Happy");
    }

    /*add testing for parcelable functionality*/



}
