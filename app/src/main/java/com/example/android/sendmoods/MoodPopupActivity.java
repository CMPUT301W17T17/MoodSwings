package com.example.android.sendmoods;

/**
 * Created by erasseli on 3/4/17.
 */

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
                Intent myIntent = new Intent(MoodPopupActivity.this, EditMoodActivity.class);
                myIntent.putExtra("MoodEvent", moodEvent);
                startActivityForResult(myIntent, REQ_CODE_EDIT);
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

    public void onStart(){
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");

        //Prepare the default intent
        resultIntent = new Intent();
        setResult(RES_CODE_NOCHANGE, resultIntent);

        popupBox.setBackground(ContextCompat.getDrawable(this, moodEvent.getPopupShape()));

        updateFields();
    }

    //When returned from EditMoodActivity
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        //If the mood was deleted we immediately leave the popup
        if (resultCode == RES_CODE_DELETED) {
            setResult(RES_CODE_DELETED, resultIntent);
            MoodPopupActivity.this.finish();
        }
        //If the mood was changed, we retrieve the parcelable, update the popup fields
        //and prepare to send the same parcelable back to the MoodListActivity
        if (resultCode == RES_CODE_EDITED) {
            moodEvent = data.getExtras().getParcelable("updatedMood");
            updateFields();
            resultIntent.putExtra("updatedMood", moodEvent);
            setResult(RES_CODE_EDITED, resultIntent);
        }
    }

    //Since we are now using this code twice, I have made a function for it
    protected void updateFields(){
        usernameText.setText(moodEvent.getUsername());
        moodText.setText(moodEvent.getEmotion());
        dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        timeText.setText(String.format("At: %1$s", moodEvent.getTime()));
        reasonText.setText(moodEvent.getReason());
        addressText.setText(moodEvent.getAddress());
    }
}
