package com.example.android.sendmoods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private MoodEvent moodEvent;

    private Button saveButton;
    private Button deleteButton;
    private View backgroundView;


    /**
     * @param savedInstanceState Opens edit_mood, and allows for the user to input the reason and mood.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        reasonText = (EditText) findViewById(R.id.reason_text);//the convention for a view name is word_word not wordWord
        reasonText.setFilters(new InputFilter[]{new TextInputFilter()});

        reasonText = (EditText) findViewById(R.id.reason_text);
        moodText = (EditText) findViewById(R.id.mood_text);


        //backgroundView = findViewById(R.id.edit_background);

        //Save button parcels the mood Event

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
        try {
            moodText.setText(moodEvent.getEmotion());
        } catch (Exception e) {
            moodText.setText("UNKNOWN");
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

                try {
                    moodEvent.setEmotion(moodText.getText().toString());
                } catch (Exception e) {
                    moodEvent.setEmotion("UNKNOWN");
                }

                moodEvent.setDate("February 02, 2017");

                moodEvent.setTime("11:11");
                moodEvent.setAddress("123 Fakestreet, WA");
                moodEvent.setColor(HAPPY_COLOR);
                moodEvent.setPopupShape(HAPPY_POPUP_BOX);

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
