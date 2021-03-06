package com.example.android.sendmoods;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * This class allows popup layout to show the edited information for an existing mood.
 * Shows the views similarly to edit_mood UI, no option to edit the mood from here.
 */
public class MoodPopupActivity extends AppCompatActivity {

    private MoodEvent moodEvent;
    private TextView usernameText;
    private TextView moodText;
    private TextView dateText;
    private TextView timeText;
    private TextView reasonText;
    private View popupBox;
    private ImageView popupIcon;
    private RelativeLayout popupMargin;
    private ImageView photo;
    private Integer people;
    private TextView social;

    /**
     *
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        usernameText = (TextView) findViewById(R.id.username_text_popup);
        moodText = (TextView) findViewById(R.id.mood_text_popup);
        dateText = (TextView) findViewById(R.id.date_text_popup);
        timeText = (TextView) findViewById(R.id.time_text_popup);
        reasonText = (TextView) findViewById(R.id.description_text_popup);

        popupBox = findViewById(R.id.popup_box);
        popupIcon = (ImageView) findViewById(R.id.popup_icon);
        popupMargin = (RelativeLayout) findViewById(R.id.popup);
        photo = (ImageView) findViewById(R.id.user_image_popup);
        social = (TextView) findViewById(R.id.socialDescription);

        //OnClick for outside the popup to close that activity
        popupMargin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoodPopupActivity.this.finish();
            }
        });

    }

    /**
     * Launches the popup with the edited information of the respective mood.
     */
    public void onStart() {
        super.onStart();
        moodEvent = getIntent().getParcelableExtra("MoodEvent");
        popupBox.setBackground(ContextCompat.getDrawable(this, moodEvent.getMood().getShape()));
        popupIcon.setImageResource(moodEvent.getMood().getIcon());
        usernameText.setText(moodEvent.getUsername());
        moodText.setText(moodEvent.getMood().getText());
        dateText.setText(String.format("On: %1$s", moodEvent.getDate()));
        timeText.setText(String.format("At: %1$s", moodEvent.getTime()));
        reasonText.setText(moodEvent.getReason());
        if (moodEvent.getPhoto() != null) {
            photo.setImageBitmap(moodEvent.getPhoto());
        }
        people = moodEvent.getSocial();

        if (people == 0) {
            social.setText("Not selected");
        } else if (people == 1) {
            social.setText("Alone");
        } else if (people == 2) {
            social.setText("One other person");
        } else if (people == 3) {
            social.setText("Several");
        } else if (people == 4) {
            social.setText("Crowd");
        }


    }
}


