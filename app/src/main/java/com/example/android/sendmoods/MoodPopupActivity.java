package com.example.android.sendmoods;

/**
 * Created by erasseli on 3/4/17.
 */

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MoodPopupActivity extends AppCompatActivity {

    private MoodEvent moodEvent;
    private TextView usernameText;
    private TextView moodText;
    private TextView dateText;
    private TextView timeText;
    private TextView reasonText;
    private TextView addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        usernameText = (TextView) findViewById(R.id.username_text_popup);
        moodText = (TextView) findViewById(R.id.mood_text_popup);
        dateText = (TextView) findViewById(R.id.date_text_popup);
        timeText = (TextView) findViewById(R.id.time_text_popup);
        reasonText = (TextView) findViewById(R.id.description_text_popup);
        addressText = (TextView) findViewById(R.id.address_text_popup);
    }

    public void onStart(){
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        View view = findViewById(R.id.popup_box);
        view.setBackground(ContextCompat.getDrawable(this, moodEvent.getPopupShape()));

        usernameText.setText(moodEvent.getUsername());
        moodText.setText(moodEvent.getEmotion());
        dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        timeText.setText(String.format("At: %1$s", moodEvent.getTime()));
        reasonText.setText(moodEvent.getReason());
        addressText.setText(moodEvent.getAddress());
    }

    public void editMood(View view) {
        Intent intent = new Intent(this, EditMoodActivity.class);
        intent.putExtra("MoodEvent",moodEvent);
        startActivity(intent);
    }
}
