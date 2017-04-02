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

    public static class updateRecentTask extends AsyncTask<MoodEvent, Void, Void> {

        @Override
        protected Void doInBackground(MoodEvent... moodevents) {
            verifySettings();

            //but we will probably only be updating one mood event at a time
            for (MoodEvent mood : moodevents) {
                //If the index for the mood event already exists we need to either
                //update it or make a new one and delete the old one
                Index index = new Index.Builder(mood).index(mood.getUsername()).type("mood").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        /*make a dirty flag for the mood event so
                        that it can be reset here?*/
                        result.getId();
                    } else {
                        Log.i("Error", "Elasticsearch was not able to update the mood");
                    }

                } catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the mood");
                }
            }
            return null;
        }


        }


        /*takes an array list of usernames (strings)*/
        public static class getFollowedMoodsTask extends AsyncTask<ArrayList<String>, Void, ArrayList<MoodEvent>> {

            @Override
            protected ArrayList<MoodEvent> doInBackground(ArrayList<String>... username) {
                verifySettings();

                ArrayList<MoodEvent> moodevents = new ArrayList<MoodEvent>();

                for (String user : username) {
                /*get the most recent mood event of all the users in the arraylist*/
                }

            }

        }



        /*code from watts1 lonelytwitter */
        public static void verifySettings() {
            if (client == null) {
                DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
                DroidClientConfig config = builder.build();

                JestClientFactory factory = new JestClientFactory();
                factory.setDroidClientConfig(config);
                client = (JestDroidClient) factory.getObject();
            }
        }
    }
}
