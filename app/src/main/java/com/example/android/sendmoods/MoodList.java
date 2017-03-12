package com.example.android.sendmoods;

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
    private ArrayList<MoodEvent> MoodEvents;

    public MoodList() {
        MoodEvents = new ArrayList<>();
    }

    public void add(MoodEvent MoodEvent) {
        MoodEvents.add(MoodEvent);
    }

    public int getCount(){
        return MoodEvents.size();
    }

    public ArrayList<MoodEvent> getMoodEvents(){
        return MoodEvents;
    }

    public MoodEvent getMoodEvent(int index){
        return MoodEvents.get(index);
    }

    public boolean hasMoodEvent(MoodEvent MoodEvent){
        return MoodEvents.contains(MoodEvent);
    }

    public void delete(MoodEvent MoodEvent) {
        MoodEvents.remove(MoodEvent);
    }

    public void clear(){
        MoodEvents.clear();
    }

    public void fromGson(BufferedReader in){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MoodEvent>>(){}.getType();
        MoodEvents = gson.fromJson(in, listType);
    }
}