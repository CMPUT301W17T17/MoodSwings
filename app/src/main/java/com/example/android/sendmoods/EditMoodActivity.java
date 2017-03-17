package com.example.android.sendmoods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

import static com.example.android.sendmoods.Constants.AFRAID_WORD;
import static com.example.android.sendmoods.Constants.ANGRY_WORD;
import static com.example.android.sendmoods.Constants.CONFUSED_WORD;
import static com.example.android.sendmoods.Constants.DISGUSTED_WORD;
import static com.example.android.sendmoods.Constants.HAPPY_COLOR;
import static com.example.android.sendmoods.Constants.HAPPY_POPUP_BOX;
import static com.example.android.sendmoods.Constants.HAPPY_WORD;
import static com.example.android.sendmoods.Constants.RES_CODE_DELETED;
import static com.example.android.sendmoods.Constants.RES_CODE_EDITED;
import static com.example.android.sendmoods.Constants.RES_CODE_NEW;
import static com.example.android.sendmoods.Constants.SAD_WORD;
import static com.example.android.sendmoods.Constants.SHAME_WORD;
import static com.example.android.sendmoods.Constants.*;

public class EditMoodActivity extends Activity {
    //Please pay attention to  proper class organization. Views must be declared
    //as private variables.
    private EditText reasonText;


    private MoodEvent moodEvent;
    private ImageButton happyButton;
    private ImageButton angryButton;
    private ImageButton sadButton;
    private ImageButton confusedButton;
    private ImageButton ashamedButton;
    private ImageButton surprisedButton;
    private ImageButton disgustedButton;
    private ImageButton afraidButton;
    private ArrayList<ImageButton> buttonList = new ArrayList<>();
    private RelativeLayout editBackground;
    private LinearLayout.LayoutParams params;



    /**
     * @param savedInstanceState Opens edit_mood, and allows for the user to input the reason and mood.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        reasonText = (EditText) findViewById(R.id.reason_text);//the convention for a view name is word_word not wordWord
        reasonText.setFilters(new InputFilter[]{new TextInputFilter()});

        happyButton = (ImageButton) findViewById(R.id.happy);
        angryButton = (ImageButton) findViewById(R.id.angry);
        sadButton = (ImageButton) findViewById(R.id.sad);
        confusedButton = (ImageButton) findViewById(R.id.confused);
        ashamedButton = (ImageButton) findViewById(R.id.ashamed);
        surprisedButton = (ImageButton) findViewById(R.id.surprised);
        disgustedButton = (ImageButton) findViewById(R.id.disgusted);
        afraidButton = (ImageButton) findViewById(R.id.afraid);
        editBackground = (RelativeLayout) findViewById(R.id.edit_background);
        buttonList.add(happyButton);
        buttonList.add(angryButton);
        buttonList.add(sadButton);
        buttonList.add(confusedButton);
        buttonList.add(ashamedButton);
        buttonList.add(surprisedButton);
        buttonList.add(disgustedButton);
        buttonList.add(afraidButton);

        params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = 50;
        params.weight = 0.75f;
        params.leftMargin = 6;
        params.rightMargin = 6;
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;


        reasonText = (EditText) findViewById(R.id.reason_text);


        //final Context tempC = this;
        happyButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(HAPPY_WORD);
                moodEvent.setColor(HAPPY_COLOR);
                moodEvent.setPopupShape(HAPPY_POPUP_BOX);
                //moodEvent.setPopupShape( ContextCompat.getDrawable(tempC, HAPPY_POPUP_BOX));
                cycleStyle(happyButton);
            }
        });

        angryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(ANGRY_WORD);
                moodEvent.setColor(ANGRY_COLOR);
                moodEvent.setPopupShape(ANGRY_POPUP_BOX);
                cycleStyle(angryButton);
            }
        });

        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(SAD_WORD);
                moodEvent.setColor(SAD_COLOR);
                moodEvent.setPopupShape(SAD_POPUP_BOX);
                cycleStyle(sadButton);
            }
        });

        confusedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(CONFUSED_WORD);
                moodEvent.setColor(CONFUSED_COLOR);
                moodEvent.setPopupShape(CONFUSED_POPUP_BOX);
                cycleStyle(confusedButton);
            }
        });

        ashamedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(SHAME_WORD);
                moodEvent.setColor(SHAME_COLOR);
                moodEvent.setPopupShape(SHAME_POPUP_BOX);
                cycleStyle(ashamedButton);
            }
        });

        surprisedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(SURPRISED_WORD);
                moodEvent.setColor(SURPRISED_COLOR);
                moodEvent.setPopupShape(SURPRISED_POPUP_BOX);
                cycleStyle(surprisedButton);
            }
        });

        disgustedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(DISGUSTED_WORD);
                moodEvent.setColor(DISGUSTED_COLOR);
                moodEvent.setPopupShape(DISGUSTED_POPUP_BOX);
                cycleStyle(disgustedButton);
            }
        });

        afraidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setEmotion(AFRAID_WORD);
                moodEvent.setColor(AFRAID_COLOR);
                moodEvent.setPopupShape(AFRAID_POPUP_BOX);
                cycleStyle(afraidButton);
            }
        });

        //backgroundView = findViewById(R.id.edit_background);

        //Save button parcels the mood Event

    }

    private void cycleStyle(ImageButton myButton){
        editBackground.setBackgroundColor(Color.parseColor(moodEvent.getColor()));

        params.setMargins(10,10,10,10);
        for (int i = 0; i < buttonList.size(); i++){
            myButton.setLayoutParams(params);
        }

        params.setMargins(5,5,5,5);
        myButton.setLayoutParams(params);
    }

    /**
     * Successfully loads the already created mood status and the reason for the selected mood when accessing edit_mood
     * from either mood_list or popup.
     */
    public void onStart() {
        super.onStart();

        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        try {
            reasonText.setText(moodEvent.getReason());
        } catch (Exception e) {
            reasonText.setText("UNKNOWN");
        }



        /**
         * Allows for user to press the save button which successfully stores the values for the mood status,
         * mood reason, the date and location (optional), then redirects them to mood_list.
         */
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type;
                type = getIntent().getStringExtra("Type");

                moodEvent.setUsername("machung");

                try {
                    moodEvent.setReason(reasonText.getText().toString());
                } catch (Exception e) {
                    moodEvent.setReason("UNKNOWN");
                }



                moodEvent.setDate("February 02, 2017");

                moodEvent.setTime("11:11");
                moodEvent.setAddress("123 Fakestreet, WA");
                moodEvent.setColor(moodEvent.getColor());
                moodEvent.setPopupShape(moodEvent.getPopupShape());

                Intent resultIntent = new Intent(EditMoodActivity.this, MoodListActivity.class);
                resultIntent.putExtra("updatedMood", moodEvent);

                if (type.matches("OLD")) {
                    setResult(RES_CODE_EDITED, resultIntent);
                    finish();
                } else {
                    setResult(RES_CODE_NEW, resultIntent);
                    finish();
                }

            }
        });


        /**
         * Allows for the delete button in edit_mood to successfully remove current mood beind edited.
         */
        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent(EditMoodActivity.this, MoodListActivity.class);
                setResult(RES_CODE_DELETED, resultIntent);
                resultIntent.putExtra("updatedMood", moodEvent);
                finish();
            }
        });


    }
}
