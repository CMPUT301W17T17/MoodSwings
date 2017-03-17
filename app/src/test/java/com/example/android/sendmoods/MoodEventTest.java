package com.example.android.sendmoods;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ahua on 3/9/17.
 */

//update for string date and time
public class MoodEventTest extends ActivityInstrumentationTestCase2{

    public MoodEventTest(){
        super(MoodListActivity.class);
    }

    public void testMoodEventCreate(){
        /*test on create of mood event*/
        Location location = new Location("eventlocation");
        location.setLatitude(53.5443d);
        location.setLongitude(-113.4909d);


        MoodEvent moodevent = new MoodEvent("2017-03-12", "17:54:20", "bought an ice cream", "hobiehobie", location, "address", "Happy", 7, "Green");

        String mooddate = moodevent.getDate();
        String moodtime = moodevent.getTime();
        String moodreason = moodevent.getReason();
        String mooduser = moodevent.getUsername();
        Location moodlocation = moodevent.getAddressGPS();
        String moodaddress = moodevent.getAddress();
        String moodmood = moodevent.getEmotion();
        int moodpopupshape = moodevent.getPopupShape();
        String moodcolor = moodevent.getColor();

        assertEquals(mooddate, "2017-03-12");
        assertEquals(moodtime, "17:54:20");
        assertEquals(moodreason, "bought an ice cream");
        assertEquals(mooduser, "hobiehobie");
        assertEquals(moodlocation, location);
        assertEquals(moodaddress, "address");
        assertEquals(moodmood, "Happy");
        assertEquals(moodpopupshape, 7);
        assertEquals(moodcolor, "Green");
    }

    /*add testing for parcelable functionality*/



}
