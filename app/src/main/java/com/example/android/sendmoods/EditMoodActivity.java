package com.example.android.sendmoods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.sendmoods.Constants.HAPPY_COLOR;
import static com.example.android.sendmoods.Constants.HAPPY_POPUP_BOX;
import static com.example.android.sendmoods.Constants.RES_CODE_DELETED;
import static com.example.android.sendmoods.Constants.RES_CODE_EDITED;
import static com.example.android.sendmoods.Constants.RES_CODE_NEW;

public class EditMoodActivity extends Activity {
    //Please pay attention to  proper class organization. Views must be declared
    //as private variables.
    private EditText reasonText;
    private EditText moodText;
    private TextView userName;

    private MoodEvent moodEvent;

    private Button saveButton;
    private Button deleteButton;
    private View backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        reasonText = (EditText) findViewById(R.id.reason_text);//the convention for a view name is word_word not wordWord
        reasonText.setFilters(new InputFilter[]{new TextInputFilter()});

        reasonText = (EditText) findViewById(R.id.reason_text);
        moodText = (EditText) findViewById(R.id.mood_text);
        userName = (TextView) findViewById(R.id.username);


        //backgroundView = findViewById(R.id.edit_background);

        //Save button parcels the mood Event
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                moodEvent.setUsername("machung");
                moodEvent.setReason(reasonText.getText().toString());
                moodEvent.setEmotion(moodText.getText().toString());
                moodEvent.setDate("February 02, 2017");
                moodEvent.setTime("11:11");
                moodEvent.setAddress("123 Fakestreet, WA");
                moodEvent.setColor(HAPPY_COLOR);
                moodEvent.setPopupShape(HAPPY_POPUP_BOX);

                Intent resultIntent = new Intent(EditMoodActivity.this, MoodListActivity.class);
                resultIntent.putExtra("updatedMood", moodEvent);


                setResult(RES_CODE_NEW, resultIntent);
                finish();
            }
        });

        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent resultIntent = new Intent(EditMoodActivity.this, MoodListActivity.class);
                setResult(RES_CODE_DELETED, resultIntent);
                finish();
            }
        });
    }
    public void onStart(){
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        //backgroundView.setBackground();

        String nullString = "";

        try {
            moodText.setText(moodEvent.getEmotion());
        } catch (Exception e) {
            moodText.setText(nullString);
        }

        try {
            reasonText.setText(moodEvent.getReason());
        } catch (Exception e) {
            reasonText.setText(nullString);
        }

        try {
            userName.setText(moodEvent.getUsername());
        } catch (Exception e) {
            userName.setText("Username");
        }
    }
}
