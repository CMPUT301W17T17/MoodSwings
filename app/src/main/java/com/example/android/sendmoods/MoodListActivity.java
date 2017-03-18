package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.example.android.sendmoods.Constants.REQ_CODE_EDIT;
import static com.example.android.sendmoods.Constants.REQ_CODE_NEW;
import static com.example.android.sendmoods.Constants.RES_CODE_DELETED;
import static com.example.android.sendmoods.Constants.RES_CODE_EDITED;


public class MoodListActivity extends AppCompatActivity{

    private ListView moodListView;
    private MoodList moodEventList;
    private MoodListAdapter adapter;
    private FloatingActionButton addButton;
    private int pos;

    //Location location = new Location("defaultLocation");
    //private MoodEvent testMoodEvent = new MoodEvent("February 02, 2017", "11:11", "Harder Better Faster", "Mohamad", "123 Fakestreet, WA", HAPPY_WORD, HAPPY_POPUP_BOX, HAPPY_COLOR);

    private MoodEvent newMoodEvent;
    private Intent changeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_list);

        moodEventList = new MoodList(this);
        adapter = moodEventList.getAdapter();
        moodListView = (ListView) findViewById(R.id.mood_list);
        moodListView.setAdapter(adapter);

        addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        moodEventList.loadFromFile();

        /**
         * Allowing both edit buttons, one from popup and one from mood_list, to redirect the user to edit_mood.
         */
        moodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                MoodEvent moodEvent = (MoodEvent) moodListView.getItemAtPosition(position);
                pos = position;
                Intent myIntent = new Intent(MoodListActivity.this, MoodPopupActivity.class);
                myIntent.putExtra("MoodEvent", moodEvent);
                startActivity(myIntent);
            }
        });

        moodListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                changeIntent = new Intent(MoodListActivity.this, EditMoodActivity.class);
                newMoodEvent= (MoodEvent) moodListView.getItemAtPosition(position);
                changeIntent.putExtra("MoodEvent", newMoodEvent);
                startActivityForResult(changeIntent, REQ_CODE_EDIT);
                return true;
            }
        });

        /**
         * Allows the + button to redirect user to edit_mood from mood_list.
         */
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                newMoodEvent = new MoodEvent();
                changeIntent = new Intent(MoodListActivity.this, EditMoodActivity.class);
                changeIntent.putExtra("MoodEvent", newMoodEvent);
                startActivityForResult(changeIntent, REQ_CODE_NEW);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE_NEW && resultCode == RES_CODE_EDITED){
            //Get the new MoodEvent and append it to the list
            newMoodEvent = data.getExtras().getParcelable("updatedMood");
            moodEventList.add(newMoodEvent);
        }
        if(requestCode == REQ_CODE_EDIT && resultCode == RES_CODE_DELETED){
            //Delete the viewed MoodEvent
            moodEventList.delete(pos);
        }
        if(requestCode == REQ_CODE_EDIT && resultCode == RES_CODE_EDITED){
            //Update the viewed MoodEvent
            MoodEvent newMoodEvent = data.getExtras().getParcelable("updatedMood");
            moodEventList.set(pos, newMoodEvent);
        }
        adapter.notifyDataSetChanged();
        moodEventList.saveInFile();
    }
    //Don't quote a source that wasn't used. Type I Error is just as much a sin as type II
}
