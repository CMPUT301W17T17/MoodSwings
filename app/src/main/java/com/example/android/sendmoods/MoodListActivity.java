package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.android.sendmoods.Moods.AfraidMood;
import com.example.android.sendmoods.Moods.AngryMood;
import com.example.android.sendmoods.Moods.AshamedMood;
import com.example.android.sendmoods.Moods.ConfusedMood;
import com.example.android.sendmoods.Moods.DisgustedMood;
import com.example.android.sendmoods.Moods.HappyMood;

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
    private FloatingActionButton addButton, mapButton;
    private NavigationView navigationView;
    private Spinner nameSpinner, moodSpinner;
    private Switch recentSwitch;
    private ArrayAdapter<String> nameAdapter, moodAdapter;
    private ArrayList<String> nameArray;
    private ArrayList<String> moodArray;

    private Boolean filterDate = SORT_ALL_TIME;
    private String filterMood = "All Moods";
    private String filterName = "All Users";

    private ImageView moodIcon;
    private TextView nameText;
    private View headerBox;

    private int pos;

    private MoodEvent newMoodEvent;
    private ElasticSearchController elasticSearchController;

    private Intent changeIntent, mapIntent;


    private MoodEvent testMoodEvent1 = new MoodEvent();
    private MoodEvent testMoodEvent2 = new MoodEvent();
    private MoodEvent testMoodEvent3 = new MoodEvent();
    private MoodEvent testMoodEvent4 = new MoodEvent();
    private MoodEvent testMoodEvent5 = new MoodEvent();
    private MoodEvent testMoodEvent6 = new MoodEvent();

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

        elasticSearchController = new ElasticSearchController();

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

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_add:
                        newMoodEvent = new MoodEvent();
                        changeIntent = new Intent(MoodListActivity.this, EditMoodActivity.class);
                        changeIntent.putExtra("MoodEvent", newMoodEvent);
                        startActivityForResult(changeIntent, REQ_CODE_NEW);
                        break;
                }
                return false;
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

        View hView =  navigationView.getHeaderView(0);
        moodIcon = (ImageView) hView.findViewById(R.id.drawer_header_icon);
        nameText = (TextView) hView.findViewById(R.id.drawer_header_username);
        headerBox = hView.findViewById(R.id.header_box);

        mapButton = (FloatingActionButton) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mapIntent = new Intent(MoodListActivity.this, MoodMapsActivity.class);
                Bundle bundle = new Bundle();


                if (filterMood.matches("All Moods")) {
                    bundle.putParcelable("myList",moodEventList);
                    mapIntent.putExtra("mapBundle", bundle);

                }
                else{
                    bundle.putParcelable("myList",moodEventList);
                    mapIntent.putExtra("mapBundle", bundle);
                    String x="s";
                }
                startActivity(mapIntent);
            }
        });

        /**
         * Initialize a few test mood events *
         **/

        testMoodEvent1.setMood(new AngryMood().toMood());
        testMoodEvent1.setUsername("machung");
        testMoodEvent1.setLongitude(51.440270);
        testMoodEvent1.setLatitude(-114.062019);
        moodEventList.add(testMoodEvent1);

        testMoodEvent2.setMood(new DisgustedMood().toMood());
        testMoodEvent2.setUsername("machung");
        testMoodEvent2.setLongitude(56.305);
        testMoodEvent2.setLatitude(-113.6256);
        moodEventList.add(testMoodEvent2);

        testMoodEvent3.setMood(new HappyMood().toMood());
        testMoodEvent3.setUsername("machung");
        testMoodEvent3.setLongitude(52.5444);
        testMoodEvent3.setLatitude(-113.323975);
        moodEventList.add(testMoodEvent3);

        testMoodEvent4.setMood(new AfraidMood().toMood());
        testMoodEvent4.setUsername("machung");
        testMoodEvent4.setLongitude(52.681);
        testMoodEvent4.setLatitude(-113.8112);
        moodEventList.add(testMoodEvent4);

        testMoodEvent5.setMood(new ConfusedMood().toMood());
        testMoodEvent5.setUsername("machung");
        testMoodEvent5.setLongitude(56.305);
        testMoodEvent5.setLatitude(-113.6256);
        moodEventList.add(testMoodEvent5);

        testMoodEvent6.setMood(new AshamedMood().toMood());
        testMoodEvent6.setUsername("machung");
        testMoodEvent6.setLongitude(51.180202);
        testMoodEvent6.setLatitude(-115.565704);
        moodEventList.add(testMoodEvent6);

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


        newMoodEvent = moodEventList.getMostRecent();

        ElasticSearchController.updateRecentTask updateRecent
                = new ElasticSearchController.updateRecentTask();
        updateRecent.execute(newMoodEvent);


        moodIcon.setImageResource(newMoodEvent.getMood().getIcon());
        nameText.setText(newMoodEvent.getMood().getText());
        headerBox.setBackground(ContextCompat.getDrawable(this, newMoodEvent.getMood().getShape()));

    }
}
