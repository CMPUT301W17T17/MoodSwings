package com.example.android.sendmoods;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.android.sendmoods.Constants.SAVEFILE_NAME;

/**
 * Created by Etiennera on 2017-03-12.
 */

public class MoodList {
    private ArrayList<MoodEvent> moodEvents, moodEventList;
    private MoodListAdapter adapter;
    private Context context;

    public MoodList(Context context) {
        this.context = context;
        moodEvents = new ArrayList<>();
        adapter = new MoodListAdapter(context, moodEvents);
    }

    public MoodListAdapter getAdapter(){
        return adapter;
    }

    public void add(MoodEvent moodEvent) {
        moodEvents.add(moodEvent);
    }

    public void set(int pos,MoodEvent moodEvent){ moodEvents.set(pos, moodEvent); }

    public int getCount(){
        return moodEvents.size();
    }

    public ArrayList<MoodEvent> getmoodEvents(){
        return moodEvents;
    }

    public MoodEvent getMoodEvent(int index){
        return moodEvents.get(index);
    }

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

    public void fromGson(BufferedReader in){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MoodEvent>>(){}.getType();
        moodEvents = gson.fromJson(in, listType);
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
}