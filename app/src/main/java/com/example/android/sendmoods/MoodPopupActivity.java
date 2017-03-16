package com.example.android.sendmoods;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.android.sendmoods.Constants.REQ_CODE_EDIT;
import static com.example.android.sendmoods.Constants.RES_CODE_DELETED;
import static com.example.android.sendmoods.Constants.RES_CODE_EDITED;
import static com.example.android.sendmoods.Constants.RES_CODE_NOCHANGE;


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
    private Intent resultIntent;

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

        editButton = (FloatingActionButton) findViewById(R.id.edit_mood_button);
        popupBox = findViewById(R.id.popup_box);
        popupMargin = (RelativeLayout) findViewById(R.id.popup);


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                resultIntent = new Intent(MoodPopupActivity.this, EditMoodActivity.class);
                resultIntent.putExtra("MoodEvent", moodEvent);
                startActivityForResult(resultIntent, REQ_CODE_EDIT);
            }
        });

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
        popupBox.setBackground(ContextCompat.getDrawable(this, moodEvent.getPopupShape()));


        usernameText.setText(moodEvent.getUsername());

        try{
            moodText.setText(moodEvent.getEmotion());
        } catch (Exception e){
            moodText.setText("UNKNOWN");
        }

        try{
            dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        } catch (Exception e){
            dateText.setText("UNKNOWN");
        }

        try{
            timeText.setText(String.format("At: %1$s", moodEvent.getTime()));
        }
        catch (Exception e){
            timeText.setText("UNKNOWN");
        }

        try {
            reasonText.setText(moodEvent.getReason());
        } catch (Exception e) {
            reasonText.setText("UNKNOWN");
        }

        try{
            addressText.setText(moodEvent.getAddress());
        } catch(Exception e){
            addressText.setText("UNKNOWN");
        }


    }


}