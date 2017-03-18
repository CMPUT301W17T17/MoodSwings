package com.example.android.sendmoods;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * This class allows popup layout to show the edited information for an existing mood.
 * Shows the views similarly to edit_mood.
 */
public class MoodPopupActivity extends AppCompatActivity {

    private MoodEvent moodEvent;
    private TextView usernameText;
    private TextView moodText;
    private TextView dateText;
    private TextView timeText;
    private TextView reasonText;
    private TextView addressText;
    private View popupBox;
    private FloatingActionButton editButton;
    private RelativeLayout popupMargin;


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

        popupBox = findViewById(R.id.popup_box);
        popupMargin = (RelativeLayout) findViewById(R.id.popup);

        //OnClick for outside the popup to close that activity
        popupMargin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                MoodPopupActivity.this.finish();
            }
        });
    }
    /**
     * This method contains try/catch blocks to ensure correct input in the EditTexts and TextViews.
     */
    public void onStart(){
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");
        popupBox.setBackground(ContextCompat.getDrawable(this, moodEvent.getMood().getShape()));
        //Don't put try Except clauses everywhere.
        //Especially where errors should not occur.
        //This makes it hard to spot real errors.
        usernameText.setText(moodEvent.getUsername());
        moodText.setText(moodEvent.getMood().getText());
        dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        timeText.setText(String.format("At: %1$s", moodEvent.getTime()));
        reasonText.setText(moodEvent.getReason());
        addressText.setText(moodEvent.getAddress());
    }


}