package com.example.android.sendmoods;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Etiennera on 2017-03-12.
 */

public class MoodList {
    private ArrayList<MoodEvent> moodEvents, moodEventList;
    private MoodListAdapter adapter;

    public MoodList(Context context) {
        moodEvents = new ArrayList<>();
        adapter = new MoodListAdapter(context, moodEvents);
    }

    public MoodListAdapter getAdapter(){
        return adapter;
    }

    public void add(MoodEvent MoodEvent) {
        moodEvents.add(MoodEvent);
    }

    public int getCount(){
        return moodEvents.size();
    }

    public ArrayList<MoodEvent> getmoodEvents(){
        return moodEvents;
    }

    public MoodEvent getMoodEvent(int index){
        return moodEvents.get(index);
    }

    public boolean hasMoodEvent(MoodEvent MoodEvent){
        return moodEvents.contains(MoodEvent);
    }

    public void delete(MoodEvent MoodEvent) {
        moodEvents.remove(MoodEvent);
    }

    public void clear(){
        moodEvents.clear();
    }

    public void fromGson(BufferedReader in){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MoodEvent>>(){}.getType();
        moodEvents = gson.fromJson(in, listType);
    }
}