package com.example.android.sendmoods;

import android.os.AsyncTask;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import android.util.Log;

/**
 * referenced watts1 LonelyTwitter from lab
 */

public class ElasticSearchController {

    private static JestDroidClient client;

    public static class updateRecentTask extends AsyncTask<MoodEvent, Void, Void> {

        @Override
        protected Void doInBackground(MoodEvent... moodevent) {
            verifySettings();


            /*
            // check if user has mood uploaded already, if yes delete it
            for (MoodEvent mood : moodevent) {

                /*search for the most recent mood of that user
                * to see if one exists already
                * ##need to search only in username field


                Search search = new Search.Builder(mood.getUsername())
                        .addIndex("moods")
                        .addType("mood")
                        .build();

                try {
                    // TODO get the results of the query
                    SearchResult result = client.execute(search);
                    if (result.isSucceeded()) {
                        List<SearchResult.Hit<Map,Void>> hits = client.execute(search).getHits(Map.class);
                        SearchResult.Hit hit = hits.get(0);
                        Map source = (Map)hit.source;
                        String id = (String)source.get(JestResult.ES_METADATA_ID);
                        client.execute(new Delete.Builder(id).index("moods").type("mood").build());

                    }
                    else {

                        Log.i("irrelevant", "User does not have most recent mood yet");
                    }
                } catch (Exception e) {
                    Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }

            } */


            // upload most recent mood event from array
            for (MoodEvent mood : moodevent) {
                //If the index for the mood event already exists we need to either

                Index index = new Index.Builder(mood).index("moods").type("mood").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.i("Success", "Elastic search updated the mood");
                    } else {
                        Log.i("Error", "Elastic search was not able to update the mood");
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

                return null;


            }

        }

        public static class checkUserExistsTask extends AsyncTask<String, Void, Boolean> {

            @Override
            protected Boolean doInBackground(String...username) {
                verifySettings();


                return null;


            }
        }



        /*code from watts1 lonelytwitter */
        public static void verifySettings() {
            if (client == null) {
                DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/cmput301w17t17");
                DroidClientConfig config = builder.build();

                JestClientFactory factory = new JestClientFactory();
                factory.setDroidClientConfig(config);
                client = (JestDroidClient) factory.getObject();
            }
        }
    }

