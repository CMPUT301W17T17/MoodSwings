package com.example.android.sendmoods;

/**
 * Created by erasseli on 3/4/17.
 */

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MoodPopupActivity extends AppCompatActivity {

    private MoodEvent moodEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
    }

    public void onStart(){
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        View view = findViewById(R.id.popup_box);
        view.setBackground(ContextCompat.getDrawable(this, moodEvent.getPopupShape()));
    }
}
