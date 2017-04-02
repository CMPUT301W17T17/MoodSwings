package com.example.android.sendmoods;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static com.example.android.sendmoods.Constants.COMBINED_DATE_FORMAT;
import static com.example.android.sendmoods.Constants.SAVEFILE_NAME;
import static com.example.android.sendmoods.Constants.SORT_ALL_TIME;
import static com.example.android.sendmoods.Constants.WEEK_IN_MSEC;

/**
 * Created by Etiennera on 2017-03-12.
 */

public class MoodList implements Parcelable{
    private ArrayList<MoodEvent> moodEvents;
    private ArrayList<MoodEvent> moodEventList;
    private MoodListAdapter adapter;
    private Context context;

    public ArrayList<MoodEvent> getMoodEventList() {
        return moodEventList;
    }

    public MoodList(Context context) {
        this.context = context;
        moodEvents = new ArrayList<>();
        moodEventList = new ArrayList<>();
        adapter = new MoodListAdapter(context, moodEventList);

    }

    public static final Creator<MoodList> CREATOR = new Creator<MoodList>() {
        @Override
        public MoodList createFromParcel(Parcel in) {
            return new MoodList(in);
        }

        @Override
        public MoodList[] newArray(int size) {
            return new MoodList[size];
        }
    };

    public MoodListAdapter getAdapter(){
        return adapter;
    }

    public void add(MoodEvent moodEvent) {
        moodEvents.add(moodEvent);
    }

    public void set(int pos,MoodEvent moodEvent){
        moodEvents.set(pos, moodEvent);
    }

    public int size(){
        return moodEvents.size();
    }

    public void filterEvents(String username, String mood, Boolean date){
        moodEventList.clear();
        for (int i = 0; i < moodEvents.size(); i++){
            MoodEvent mood_i = moodEvents.get(i);
            if (mood_i.getUsername().equals(username) || username.equals("All Users")){
                if (mood_i.getMood().getText().equals(mood) || mood.equals("All Moods")){
                    try{
                        if(new Date().getTime() - COMBINED_DATE_FORMAT.parse(
                                String.format("%1$s %2$s", mood_i.getDate(), mood_i.getTime()))
                                .getTime()
                                < WEEK_IN_MSEC
                                        || date == SORT_ALL_TIME) {
                            moodEventList.add(moodEvents.get(i));
                        }
                    } catch (ParseException e){
                        moodEventList.add(moodEvents.get(i));
                    }
                }
            }
        }

        Collections.sort(moodEventList, new Comparator<MoodEvent>() {
            public int compare(MoodEvent mood1, MoodEvent mood2) {
                long t1 = 0;
                long t2 = 0;
                try {
                    t1 = COMBINED_DATE_FORMAT.parse(
                            String.format("%1$s %2$s", mood1.getDate(), mood1.getTime()))
                            .getTime();
                    t2 = COMBINED_DATE_FORMAT.parse(
                            String.format("%1$s %2$s", mood2.getDate(), mood2.getTime()))
                            .getTime();
                } catch (ParseException e){
                    return 1;
                }
                return Long.valueOf(t1).compareTo(t2);
            }
        });
        adapter.notifyDataSetChanged();
    }

    public ArrayList<String> getUsernames() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < moodEvents.size(); i++){
            if (!names.contains(moodEvents.get(i).getUsername())){
                names.add(moodEvents.get(i).getUsername());
            }
        }

        Collections.sort(names, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        return names;
    }

    public MoodEvent getMoodEvent(int index){
        return moodEvents.get(index);
    }

    public MoodEvent getMostRecent() { return getMoodEvent(moodEvents.size() - 1); }

    public boolean hasMoodEvent(MoodEvent moodEvent){
        return moodEvents.contains(moodEvent);
    }

    public void delete(int pos) {
        moodEvents.remove(pos);
    }

    public void deleteLast() {
        moodEvents.remove(moodEvents.size() - 1);
    }

    public void clear(){
        moodEvents.clear();
    }


    public void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(SAVEFILE_NAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            moodEvents = gson.fromJson(in, new TypeToken<ArrayList<MoodEvent>>(){}.getType());
        } catch (FileNotFoundException e) {
            moodEvents = new ArrayList<>();
        }
        filterEvents("All Users", "All Moods", SORT_ALL_TIME);
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = context.openFileOutput(SAVEFILE_NAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(moodEvents, writer);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeTypedList(moodEvents);
        dest.writeTypedList(moodEventList);

    }

    protected MoodList(Parcel in) {
        moodEvents = in.createTypedArrayList(MoodEvent.CREATOR);
        moodEventList = in.createTypedArrayList(MoodEvent.CREATOR);
    }

}