package com.example.android.sendmoods;

import android.os.AsyncTask;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;

import android.util.Log;

/**
 * referenced watts1 LonelyTwitter from lab
 */

public class ElasticSearchController {

    private static JestDroidClient client;

    public static class updateRecent extends AsyncTask<MoodEvent, Void, Void>{

        @Override
        protected Void doInBackground(MoodEvent... moodevents) {
            verifySettings();

            for (MoodEvent mood : moodevents) {
                Index index = new Index.Builder(mood).index(mood.getUsername()).type("mood").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        /*make a dirty flag for the mood event so
                        that it can be reset here?*/
                    }
                    else {
                        Log.i("Error", "Elasticsearch was not able to update the mood");
                    }

                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the mood");
                }
            }
            return null;



    }


/*takes an array list of usernames (strings)*/
    public static class getFollowedMoods extends AsyncTask<ArrayList<String>, Void, ArrayList<MoodEvent>>{

        @Override
        protected ArrayList<MoodEvent> doInBackground(ArrayList<String>... usernames) {
            verifySettings();

            ArrayList<MoodEvent> moodevents = new ArrayList<MoodEvent>();

            for (MoodEvent event : moodevents) {
                /*get the most recent mood event of all the users in the arraylist*/
            }

        }

    }


    /*public void getFollowedMoods() {

    }

    public void setRecentMood(MoodEvent) {

    }

    public void setRecentMood(MoodEvent) {

    }*/

    /*code from watts1 lonelytwitter */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);client = (JestDroidClient) factory.getObject();
        }
    }
}
