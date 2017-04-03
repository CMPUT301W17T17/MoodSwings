package com.example.android.sendmoods;

import android.os.AsyncTask;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;

import android.util.Log;

/**
 * referenced watts1 LonelyTwitter from lab
 */

public class ElasticSearchController {

    private static JestDroidClient client;
    private static final String index = "cmput301w17t17";

    public static class updateRecentTask extends AsyncTask<MoodEvent, Void, Void> {
        @Override
        protected Void doInBackground(MoodEvent... moodEvent) {
            ElasticSearchController.verifySettings();
            ElasticSearchController.initializeIndex();

            for (MoodEvent mood : moodEvent) {
                try {
                    Index index;
                    if (mood.hasId()){
                        index = new Index
                                .Builder(mood.toString())
                                .index(ElasticSearchController.index)
                                .type("mood")
                                .id(mood.getId())
                                .build();
                    } else {
                        index = new Index
                                .Builder(mood.toString())
                                .index(ElasticSearchController.index)
                                .type("mood")
                                .build();
                    }
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        mood.setId(result.getId());
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

    private static void initializeIndex(){
        IndicesExists indicesExists = new IndicesExists
                .Builder(index)
                .build();
        try {
            boolean indexExists = ElasticSearchController.client.execute(indicesExists)
                    .isSucceeded();
            if (!indexExists) {
                CreateIndex createIndex = new CreateIndex
                        .Builder(index)
                        .build();
                client.execute(createIndex);
            }
        }
        catch (IOException e) {
            Log.i("Error", "Could not make index.");
        }
    }

    /*code from watts1 lonelytwitter */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder
                    = new DroidClientConfig.Builder(
                            "http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}

