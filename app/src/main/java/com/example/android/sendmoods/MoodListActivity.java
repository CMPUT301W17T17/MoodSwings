package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import static com.example.android.sendmoods.Constants.*;

/**
 * Created by erasseli on 3/4/17.
 */

public class MoodListActivity extends AppCompatActivity{

    private ListView moodListView;
    private ArrayList<MoodEvent> moodEventList = new ArrayList<>();
    private MoodListAdapter adapter;

    private MoodEvent testMoodEvent = new MoodEvent("February 02, 2017","11:11","Harder", "Etienne", "1 Infinite Loop",
            "SUPER SAD", 35, "Blue");
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
        startActivity(intent);
    }

    public void resetFilter(View v){

    }

}