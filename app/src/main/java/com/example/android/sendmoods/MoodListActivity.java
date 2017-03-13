package com.example.android.sendmoods;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import static com.example.android.sendmoods.Constants.*;


/**
 * Created by erasseli on 3/4/17.
 */

public class MoodListActivity extends AppCompatActivity{

    private ListView moodListView;
    private ArrayList<MoodEvent> moodEventList = new ArrayList<>();
    private MoodListAdapter adapter;

    Location location = new Location("defaultlocation");
    //private MoodEvent testMoodEvent = new MoodEvent("default", "default", "default", "default", location, "default", "default", 0, "default");
    private MoodEvent testMoodEvent = new MoodEvent("February 02, 2017", "11:11", "Harder Better Faster", "Mohamad", "123 Fakestreet, WA", HAPPY_WORD, HAPPY_POPUP_BOX, HAPPY_COLOR);
    private MoodEvent newMoodEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_list);

        moodListView = (ListView) findViewById(R.id.mood_list);
        adapter = new MoodListAdapter(this, moodEventList);
        moodListView.setAdapter(adapter);

        /*testMoodEvent.setUsername("Mohamad");
        testMoodEvent.setEmotion(HAPPY_WORD);
        testMoodEvent.setDate("February 02, 2017");
        testMoodEvent.setTime("11:11");
        testMoodEvent.setReason("Harder Better Faster");
        testMoodEvent.setAddress("123 Fakestreet, WA");
        testMoodEvent.setColor(HAPPY_COLOR);
        testMoodEvent.setPopupShape(HAPPY_POPUP_BOX);*/

        moodEventList.add(testMoodEvent);
        adapter.notifyDataSetChanged();

        moodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                MoodEvent moodEvent = (MoodEvent) moodListView.getItemAtPosition(position);
                Intent myIntent = new Intent(MoodListActivity.this, MoodPopupActivity.class);
                myIntent.putExtra("MoodEvent", moodEvent);
                startActivityForResult(myIntent, 0);
            }
        });
    }

    public void editMood(View view) {
        Intent intent = new Intent(this, EditMoodActivity.class);
        startActivityForResult(intent, Constants.INTENT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Constants.INTENT_REQUEST_CODE){
            Intent intent = getIntent(); /*might not be necessary*/
            Bundle extras = intent.getExtras();

            String date = extras.getString("EXTRA_DATE");
            String time = extras.getString("EXTRA_TIME");
            String reason = extras.getString("EXTRA_REASON");
            String username = extras.getString("EXTRA_USER");
            String address = extras.getString("EXTRA_ADDRESS");
            String emotion = extras.getString("EXTRA_EMOTION");
            int popupshape = extras.getInt("EXTRA_POPUP");
            String color = extras.getString("EXTRA_COLOR");

            newMoodEvent = new MoodEvent(date, time, reason, username, address, emotion, popupshape, color);
            moodEventList.add(newMoodEvent);
            adapter.notifyDataSetChanged();

        }
    }

    public void addMood(MoodEvent moodevent){

    }




}

