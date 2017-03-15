package com.example.android.sendmoods;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditMoodActivity extends Activity {
    //Please pay attention to  proper class organization. Views must be declared
    //as private variables.
    private EditText ReasonEdit;
    private MoodEvent moodEvent;
    private Button saveButton;
    private Button deleteButton;
    private View backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        ReasonEdit = (EditText) findViewById(R.id.reason_text);//the convention for a view name is word_word not wordWord
        ReasonEdit.setFilters(new InputFilter[]{new TextInputFilter()});

        /*backgroundView = findViewById(R.id.edit_background);

        //Save button parcels the mood Event
        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedMood", moodEvent);
                setResult(RES_CODE_EDITED, resultIntent);
            }
        });

        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent resultIntent = new Intent();
                setResult(RES_CODE_DELETED, resultIntent);
            }
        });*/
    }
    public void onStart(){
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");
        //backgroundView.setBackground();
    }
}
