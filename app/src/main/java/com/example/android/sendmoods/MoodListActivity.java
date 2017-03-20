package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.android.sendmoods.Constants.REQ_CODE_EDIT;
import static com.example.android.sendmoods.Constants.REQ_CODE_NEW;
import static com.example.android.sendmoods.Constants.RES_CODE_DELETED;
import static com.example.android.sendmoods.Constants.RES_CODE_EDITED;
import static com.example.android.sendmoods.Constants.SORT_ALL_TIME;


public class MoodListActivity extends AppCompatActivity{

    private ListView moodListView;
    private MoodList moodEventList;
    private MoodListAdapter adapter;
    private FloatingActionButton addButton;
    private Spinner nameSpinner, moodSpinner;
    private Switch recentSwitch;
    private ArrayAdapter<String> nameAdapter, moodAdapter;
    private ArrayList<String> nameArray;
    private ArrayList<String> moodArray;

    private Boolean filterDate = SORT_ALL_TIME;
    private String filterMood = "All Moods";
    private String filterName = "All Users";

    private int pos;

    //Location location = new Location("defaultLocation");
    //private MoodEvent testMoodEvent = new MoodEvent("February 02, 2017", "11:11", "Harder Better Faster", "Mohamad", "123 Fakestreet, WA", HAPPY_WORD, HAPPY_POPUP_BOX, HAPPY_COLOR);

    private MoodEvent newMoodEvent;
    private Intent changeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_drawer_string, R.string.close_drawer_string);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Prepare list and adapter
        moodEventList = new MoodList(this);
        moodListView = (ListView) findViewById(R.id.mood_list);
        moodEventList.loadFromFile();
        adapter = moodEventList.getAdapter();
        moodListView.setAdapter(adapter);

        /**
         * Mood spinner
         **/
        moodSpinner = (Spinner) findViewById(R.id.mood_spinner);
        moodArray = new ArrayList<>();
        moodArray.addAll(
                Arrays.asList("All Moods"
                        , "Afraid"
                        , "Angry"
                        , "Ashamed"
                        , "Confused"
                        , "Disgusted"
                        , "Happy"
                        , "Sad"
                        , "Surprised"));
        moodAdapter = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , moodArray);
        moodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        moodSpinner.setAdapter(moodAdapter);
        moodAdapter.notifyDataSetChanged();

        moodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
                filterMood = moodSpinner.getSelectedItem().toString();
                moodEventList.filterEvents(filterName, filterMood, filterDate);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /**
         * Name spinner
         **/
        nameSpinner = (Spinner) findViewById(R.id.name_spinner);
        nameArray = moodEventList.getUsernames();
        nameArray.add(0, "All Users");
        nameAdapter
                = new ArrayAdapter<>(this
                , android.R.layout.simple_spinner_item
                , nameArray);
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nameSpinner.setAdapter(nameAdapter);
        nameAdapter.notifyDataSetChanged();

        nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
                filterName = nameSpinner.getSelectedItem().toString();
                moodEventList.filterEvents(filterName, filterMood, filterDate);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /**
         * Date toggle
         */
        recentSwitch = (Switch) findViewById(R.id.recent_switch);

        addButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        recentSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterDate = isChecked;
                moodEventList.filterEvents(filterName, filterMood, filterDate);
            }
        });

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
                newMoodEvent = (MoodEvent) moodListView.getItemAtPosition(position);
                pos = position;
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
            newMoodEvent = data.getExtras().getParcelable("updatedMood");
            moodEventList.set(pos, newMoodEvent);
        }
        moodEventList.filterEvents(filterName, filterMood, filterDate);
        moodEventList.saveInFile();
    }
    //Don't quote a source that wasn't used. Type I Error is just as much a sin as type II
}
