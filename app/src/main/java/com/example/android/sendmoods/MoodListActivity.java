package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
    private Intent myIntent;

    private MoodEvent testMoodEvent = new MoodEvent();
    //private MoodEvent newMoodEvent = new MoodEvent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mood_list);

        moodEventList = new MoodList(this);
        adapter = moodEventList.getAdapter();
        moodListView = (ListView) findViewById(R.id.mood_list);
        moodListView.setAdapter(adapter);

        /*testMoodEvent.setUsername("Mohamad");
        testMoodEvent.setEmotion(HAPPY_WORD);
        testMoodEvent.setDate("February 02, 2017");//http://stackoverflow.com/questions/9945072/convert-string-to-date-in-java This will help sort by date
        testMoodEvent.setTime("11:11");
        testMoodEvent.setReason("Harder Better Faster");
        testMoodEvent.setAddress("123 Fakestreet, WA");
        testMoodEvent.setColor(HAPPY_COLOR);
        testMoodEvent.setPopupShape(HAPPY_POPUP_BOX);

        moodEventList.add(testMoodEvent);
        adapter.notifyDataSetChanged();*/

        moodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                MoodEvent moodEvent = (MoodEvent) moodListView.getItemAtPosition(position);
                Intent myIntent = new Intent(MoodListActivity.this, MoodPopupActivity.class);
                myIntent.putExtra("MoodEvent", moodEvent);
                startActivityForResult(myIntent, REQ_CODE_EXISTING);
            }
        });

        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                moodEventList.add(testMoodEvent);

                myIntent = new Intent(MoodListActivity.this, EditMoodActivity.class);
                myIntent.putExtra("MoodEvent", testMoodEvent);
                startActivityForResult(myIntent, REQ_CODE_NEW);
            }
        });
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_EXISTING && resultCode == 1) {
            testMoodEvent = data.getExtras().getParcelable("MoodEvent");
            moodEventList.set(pos, testMoodEvent);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "resultcode1reqcodeexisting", Toast.LENGTH_LONG).show();
        }
        if (requestCode == REQ_CODE_EXISTING && resultCode == 0) {
            moodEventList.remove(pos);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "resultcode0reqcodeexisting", Toast.LENGTH_LONG).show();
        }
        if (requestCode == REQ_CODE_NEW && resultCode == 1) {
            testMoodEvent = data.getExtras().getParcelable("MoodEvent");
            //moodEventList.set(moodEventList.size() - 1, testMoodEvent);
            moodEventList.add(testMoodEvent);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "resultcode1reqcodenew", Toast.LENGTH_LONG).show();

        }
        if (requestCode == REQ_CODE_NEW && resultCode == 0) {
            //moodEventList.remove(moodEventList.size() - 1);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "resultcode1reqcodenew", Toast.LENGTH_LONG).show();
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
