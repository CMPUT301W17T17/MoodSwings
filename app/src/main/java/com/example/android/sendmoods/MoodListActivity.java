package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static com.example.android.sendmoods.Constants.*;

/**
 * Created by erasseli on 3/4/17.
 */

public class MoodListActivity extends AppCompatActivity{

    private ListView moodListView;
    private MoodList moodEventList;
    private MoodListAdapter adapter;
    private String FILENAME = "local.sav";
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

        //moodEventList.add(testMoodEvent);
        //adapter.notifyDataSetChanged();

        moodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                MoodEvent moodEvent = (MoodEvent) moodListView.getItemAtPosition(position);
                pos = position;
                Intent myIntent = new Intent(MoodListActivity.this, MoodPopupActivity.class);
                myIntent.putExtra("MoodEvent", moodEvent);
                startActivityForResult(myIntent, REQ_CODE_POPUP);
            }
        });

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

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
        if(requestCode == REQ_CODE_NEW && resultCode == RES_CODE_NEW){//The whole purpose of static imports it to not need to use "Constants."
            //Get the new MoodEvent and append it to the list
            newMoodEvent = data.getExtras().getParcelable("updatedMood");
            moodEventList.add(newMoodEvent);
            adapter.notifyDataSetChanged();
        }
        if(requestCode == REQ_CODE_NEW && resultCode == RES_CODE_DELETED){
            //Delete the candidate MoodEvent
            moodEventList.deleteLast();
            adapter.notifyDataSetChanged();
        }
        if(requestCode == REQ_CODE_POPUP && resultCode == RES_CODE_DELETED){
            //Delete the viewed MoodEvent
            moodEventList.delete(pos);
        }
        if(requestCode == REQ_CODE_POPUP && resultCode == RES_CODE_EDITED){
            //Update the viewed MoodEvent
            MoodEvent newMoodEvent = data.getExtras().getParcelable("updatedMood");
            moodEventList.set(pos, newMoodEvent);
            adapter.notifyDataSetChanged();
        }
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            moodEventList.fromGson(in);
        } catch (FileNotFoundException e) {
            moodEventList = new MoodList(this);
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(moodEventList, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}

