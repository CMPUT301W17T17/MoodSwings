package com.example.android.sendmoods;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.sendmoods.Moods.AfraidMood;
import com.example.android.sendmoods.Moods.AngryMood;
import com.example.android.sendmoods.Moods.AshamedMood;
import com.example.android.sendmoods.Moods.ConfusedMood;
import com.example.android.sendmoods.Moods.DisgustedMood;
import com.example.android.sendmoods.Moods.HappyMood;
import com.example.android.sendmoods.Moods.Mood;
import com.example.android.sendmoods.Moods.SadMood;
import com.example.android.sendmoods.Moods.SurprisedMood;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import static com.example.android.sendmoods.Constants.AFRAID_ICON;
import static com.example.android.sendmoods.Constants.AFRAID_ICON_BW;
import static com.example.android.sendmoods.Constants.AFRAID_WORD;
import static com.example.android.sendmoods.Constants.ANGRY_ICON;
import static com.example.android.sendmoods.Constants.ANGRY_ICON_BW;
import static com.example.android.sendmoods.Constants.ANGRY_WORD;
import static com.example.android.sendmoods.Constants.ASHAMED_ICON;
import static com.example.android.sendmoods.Constants.ASHAMED_ICON_BW;
import static com.example.android.sendmoods.Constants.ASHAMED_WORD;
import static com.example.android.sendmoods.Constants.CONFUSED_ICON;
import static com.example.android.sendmoods.Constants.CONFUSED_ICON_BW;
import static com.example.android.sendmoods.Constants.CONFUSED_WORD;
import static com.example.android.sendmoods.Constants.DISGUSTED_ICON;
import static com.example.android.sendmoods.Constants.DISGUSTED_ICON_BW;
import static com.example.android.sendmoods.Constants.DISGUSTED_WORD;
import static com.example.android.sendmoods.Constants.HAPPY_ICON;
import static com.example.android.sendmoods.Constants.HAPPY_ICON_BW;
import static com.example.android.sendmoods.Constants.HAPPY_WORD;
import static com.example.android.sendmoods.Constants.RES_CODE_DELETED;
import static com.example.android.sendmoods.Constants.RES_CODE_EDITED;
import static com.example.android.sendmoods.Constants.SAD_ICON;
import static com.example.android.sendmoods.Constants.SAD_ICON_BW;
import static com.example.android.sendmoods.Constants.SAD_WORD;
import static com.example.android.sendmoods.Constants.SIMPLE_DATE_FORMAT;
import static com.example.android.sendmoods.Constants.SIMPLE_TIME_FORMAT;
import static com.example.android.sendmoods.Constants.SURPRISED_ICON;
import static com.example.android.sendmoods.Constants.SURPRISED_ICON_BW;
import static com.example.android.sendmoods.Constants.SURPRISED_WORD;

public class EditMoodActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private EditText reasonText;
    private TextView dateText;
    private MoodEvent moodEvent;
    private ImageButton happyButton
            , angryButton
            , sadButton
            , confusedButton
            , ashamedButton
            , surprisedButton
            , disgustedButton
            , afraidButton;
    private ImageView addPhoto;
    private Bitmap photo;
    private static final int REQUEST_CODE = 123;
    private RelativeLayout editBackground;
    private GoogleApiClient mGoogleApiClient;
    private Boolean connection = false;

    /**
     * A calendar for date picking. Definitely the fastest and most intuitive way.
     */
    final Calendar myCalendar = Calendar.getInstance();

    /**
     * Prepares the current date for a calendar date picker.
     */
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            moodEvent.setDate(SIMPLE_DATE_FORMAT.format(myCalendar.getTime()));

            new TimePickerDialog(EditMoodActivity.this, time, myCalendar
                    .get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false).show();
        }
    };
    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hour);
            myCalendar.set(Calendar.MINUTE, minute);
            moodEvent.setTime(SIMPLE_TIME_FORMAT.format(myCalendar.getTime()));
            dateText.setText(
                    String.format(
                            "%1$s %2$s"
                            , moodEvent.getDate()
                            , moodEvent.getTime()));
        }
    };

    /**
     * @param savedInstanceState Opens edit_mood, and allows for the user to input the reason and mood.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);

        reasonText = (EditText) findViewById(R.id.reason_text);//the convention for a view name is word_word not wordWord
        reasonText.setFilters(new InputFilter[]{new TextInputFilter(this)});

        happyButton = (ImageButton) findViewById(R.id.otherhappy);
        angryButton = (ImageButton) findViewById(R.id.angry);
        sadButton = (ImageButton) findViewById(R.id.sad);
        confusedButton = (ImageButton) findViewById(R.id.confused);
        ashamedButton = (ImageButton) findViewById(R.id.ashamed);
        surprisedButton = (ImageButton) findViewById(R.id.surprised);
        disgustedButton = (ImageButton) findViewById(R.id.disgusted);
        afraidButton = (ImageButton) findViewById(R.id.afraid);
        editBackground = (RelativeLayout) findViewById(R.id.edit_background);
        addPhoto = (ImageView) findViewById(R.id.add_photo);

        reasonText = (EditText) findViewById(R.id.reason_text);
        dateText = (TextView) findViewById(R.id.edit_date);

        happyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cycleStyle((new HappyMood()).toMood());
                        happyButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, HAPPY_ICON));
                    }
                });

        angryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new AngryMood()).toMood());
                angryButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, ANGRY_ICON));
            }
        });

        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new SadMood()).toMood());
                sadButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, SAD_ICON));
            }
        });

        confusedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new ConfusedMood()).toMood());
                confusedButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, CONFUSED_ICON));
            }
        });

        ashamedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new AshamedMood()).toMood());
                ashamedButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, ASHAMED_ICON));
            }
        });

        surprisedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new SurprisedMood()).toMood());
                surprisedButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, SURPRISED_ICON));
            }
        });

        disgustedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new DisgustedMood()).toMood());
                disgustedButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, DISGUSTED_ICON));
            }
        });

        afraidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycleStyle((new AfraidMood()).toMood());
                afraidButton.setBackground(ContextCompat.getDrawable(EditMoodActivity.this, AFRAID_ICON));
            }
        });

        /**
         * Allows for user to press the save button which successfully stores the values for the mood status,
         * mood reason, the date and location (optional), then redirects them to mood_list.
         */
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setUsername("machung");
                moodEvent.setReason(reasonText.getText().toString());
                moodEvent.setAddress("123 Fakestreet, WA");

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedMood", moodEvent);
                setResult(RES_CODE_EDITED, resultIntent);
                finish();
            }
        });

        /**
         * Allows for the delete button in edit_mood to successfully remove current mood being edited.
         */
        FloatingActionButton deleteButton = (FloatingActionButton) findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RES_CODE_DELETED, resultIntent);
                finish();
            }
        });
        dateText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View nt) {
                new DatePickerDialog(EditMoodActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // Location retrieval code starts here
        FloatingActionButton locationButton = (FloatingActionButton) findViewById(R.id.location);

        locationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(EditMoodActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(EditMoodActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Location service needs to be enabled to detect location",
                            Toast.LENGTH_SHORT);
                    toast.show();

                } else {
                    Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

                    if (mLastLocation != null) {
                        moodEvent.setLatitude(mLastLocation.getLatitude());
                        moodEvent.setLongitude(mLastLocation.getLongitude());

                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Location successfully detected",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Unexpected error: Detected location is NULL",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
            }
        });

        checkPermission();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGoogleApiClient.connect();
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Location should be enabled to attach location information to mood changes",
                        Toast.LENGTH_LONG);
                toast.show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    connection = false;
                    onResume();
                }
                return;
            }
        }
    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {


    }

    @Override
    public void onConnectionSuspended(int i) {
        connection=false;


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        connection = false;

        Toast toast = Toast.makeText(getApplicationContext(),
                "ERROR: Unsuccessful Connection with Google play service. Location cannot be attached.",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            photo = (Bitmap) data.getExtras().get("data");
        }
        catch(Exception e){
            photo = null;
        }

        if (photo != null) {
            moodEvent.updatePhoto(this, photo);
        }
    }

    /**
     * Successfully loads the already created mood status and the reason for the selected mood when accessing edit_mood
     * from either mood_list or popup.
     */

    public void onStart() {
        super.onStart();

        moodEvent = getIntent().getParcelableExtra("MoodEvent");
        reasonText.setText(moodEvent.getReason());
        dateText.setText(
                String.format(
                        "%1$s %2$s"
                        , moodEvent.getDate()
                        , moodEvent.getTime()));
        editBackground.setBackgroundColor(moodEvent.getMood().getColor());

        switch (moodEvent.getMood().getText()) {
            case HAPPY_WORD:
                happyButton.setBackground(ContextCompat.getDrawable(this, HAPPY_ICON));
                break;
            case ANGRY_WORD:
                angryButton.setBackground(ContextCompat.getDrawable(this, ANGRY_ICON));
                break;
            case SAD_WORD:
                sadButton.setBackground(ContextCompat.getDrawable(this, SAD_ICON));
                break;
            case CONFUSED_WORD:
                confusedButton.setBackground(ContextCompat.getDrawable(this, CONFUSED_ICON));
                break;
            case ASHAMED_WORD:
                ashamedButton.setBackground(ContextCompat.getDrawable(this, ASHAMED_ICON));
                break;
            case SURPRISED_WORD:
                surprisedButton.setBackground(ContextCompat.getDrawable(this, SURPRISED_ICON));
                break;
            case DISGUSTED_WORD:
                disgustedButton.setBackground(ContextCompat.getDrawable(this, DISGUSTED_ICON));
                break;
            case AFRAID_WORD:
                afraidButton.setBackground(ContextCompat.getDrawable(this, AFRAID_ICON));
                break;
        }

        if (moodEvent.getPhoto()!=null){
            addPhoto.setImageBitmap(moodEvent.getPhoto());
        }

        mGoogleApiClient.connect();
    }
    @Override
    protected void onResume() {
        super.onResume();

        checkPermission();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    public void onLocationChanged(Location location) {
        Toast toast = Toast.makeText(getApplicationContext(),
                Double.toString(location.getLatitude()),
                Toast.LENGTH_SHORT);
        toast.show();
    }

    private void cycleStyle(Mood mood){
        moodEvent.setMood(mood);
        editBackground.setBackgroundColor(moodEvent.getMood().getColor());

        happyButton.setBackground(ContextCompat.getDrawable(this, HAPPY_ICON_BW));
        angryButton.setBackground(ContextCompat.getDrawable(this, ANGRY_ICON_BW));
        sadButton.setBackground(ContextCompat.getDrawable(this, SAD_ICON_BW));
        confusedButton.setBackground(ContextCompat.getDrawable(this, CONFUSED_ICON_BW));
        ashamedButton.setBackground(ContextCompat.getDrawable(this, ASHAMED_ICON_BW));
        surprisedButton.setBackground(ContextCompat.getDrawable(this, SURPRISED_ICON_BW));
        disgustedButton.setBackground(ContextCompat.getDrawable(this, DISGUSTED_ICON_BW));
        afraidButton.setBackground(ContextCompat.getDrawable(this, AFRAID_ICON_BW));
    }

    public void addPhotoMethod (View v){
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE);
        }
        else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CODE);
        }
    }
}
