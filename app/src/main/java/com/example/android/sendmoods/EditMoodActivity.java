package com.example.android.sendmoods;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
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
import android.util.Base64;

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

import java.io.ByteArrayOutputStream;
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

/**
 * This class allows the user to edit a mood object.
 * Allows user to attach all mandatory and optional characteristics of the moodEvent object.
 */
public class EditMoodActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private EditText reasonText;
    private TextView dateText;
    private MoodEvent moodEvent;
    private ImageButton happyButton, angryButton, sadButton, confusedButton, ashamedButton, surprisedButton, disgustedButton, afraidButton;
    private ImageView addPhoto;
    private Bitmap photo;
    private static final int REQUEST_CODE = 123;
    private RelativeLayout editBackground;
    private GoogleApiClient mGoogleApiClient;
    private Boolean connection = false;
    private TextView socialDescription;
    private ImageButton onePerson;
    private ImageButton twoPerson;
    private ImageButton threePerson;
    private ImageButton fourperson;
    private Integer number;


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

    /**
     * Prepares the current time for a calendar time picker.
     */
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
     * @param savedInstanceState
     *
     * Method for launching and initializing the variables to their respective attributes.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_mood);


        reasonText = (EditText) findViewById(R.id.reason_text);
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

        dateText = (TextView) findViewById(R.id.edit_date);

        onePerson = (ImageButton) findViewById(R.id.one_person);
        twoPerson = (ImageButton) findViewById(R.id.two_person);
        threePerson = (ImageButton) findViewById(R.id.three_person);
        fourperson = (ImageButton) findViewById(R.id.four_person);
        socialDescription = (TextView) findViewById(R.id.socialDescription);


        /**
         * Enables responsiveness for when the emoticons are clicked, and allows for the background to changes
         * according to their respective colours.
         */

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
         * mood reason, photo, the date and location (optional), then closes current mood.
         *
         * Works for new or already created mood.
         */
        FloatingActionButton saveButton = (FloatingActionButton) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodEvent.setUsername("machung");
                moodEvent.setReason(reasonText.getText().toString());
                moodEvent.setAddress("123 Fakestreet, WA");
                moodEvent.setSocial(number);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedMood", moodEvent);
                setResult(RES_CODE_EDITED, resultIntent);

                Toast.makeText(getApplicationContext(),
                        "Reaches savebutton",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        /**
         * Creates functionality for the delete button to successfully remove current mood, new or
         * already existent.
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

        /**
         * The add location button allows for current mood being edited to pin location.
         */
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

    /**
     * Checks to see if the user has given permission to use their location.
     *
     * If the user chooses to never get permission popup again, then a toast with relevant information is displayed.
     */
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


    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     *
     * Checks if permission is given or not.
     */
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
        connection = false;
    }

    /**
     *
     * @param connectionResult
     *
     * If connection to location fails then a toast message is displayed.
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        connection = false;

        Toast toast = Toast.makeText(getApplicationContext(),
                "ERROR: Unsuccessful Connection with Google play service. Location cannot be attached.",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     *
     * Method is called once photo is snapped during use of camera. Photo is then scaled to a
     * 256x192 resolution.
     *
     * The try and catch block is for when the user decides to cancel use of camera without taking
     * a photo.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            photo = scaleDown((Bitmap) data.getExtras().get("data"), 8, true);
        } catch (Exception e) {
            photo = null;
        }

        if (photo != null) {

            //stores photo.
            addPhoto.setImageBitmap(photo);

            moodEvent.setPhoto(photo);
        }
    }

    /**
     * Loads the already created mood with whatever was added beforehand, whether it is a photo,
     * reason, social status, or mood.
     *
     * Sets background for the mood added previously, or keeps background if no mood was added.
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

        /**
         * Calls setVisibilility method depending on the social status, from nobody to several people.
         */

        number = moodEvent.getSocial();

        switch (number) {
            case 0:
                onePerson.setVisibility(View.INVISIBLE);
                twoPerson.setVisibility(View.INVISIBLE);
                threePerson.setVisibility(View.INVISIBLE);
                fourperson.setVisibility(View.INVISIBLE);
                socialDescription.setText("Select number of people by clicking on Social");
                break;

            case 1:
                onePerson.setVisibility(View.VISIBLE);
                twoPerson.setVisibility(View.INVISIBLE);
                threePerson.setVisibility(View.INVISIBLE);
                fourperson.setVisibility(View.INVISIBLE);
                socialDescription.setText("Event occured Alone");
                break;

            case 2:
                onePerson.setVisibility(View.VISIBLE);
                twoPerson.setVisibility(View.VISIBLE);
                threePerson.setVisibility(View.INVISIBLE);
                fourperson.setVisibility(View.INVISIBLE);
                socialDescription.setText("Event occured with one person");
                break;
            case 3:
                onePerson.setVisibility(View.VISIBLE);
                twoPerson.setVisibility(View.VISIBLE);
                threePerson.setVisibility(View.VISIBLE);
                fourperson.setVisibility(View.INVISIBLE);
                socialDescription.setText("Event occured in presence of several people");
                break;
            case 4:
                onePerson.setVisibility(View.VISIBLE);
                twoPerson.setVisibility(View.VISIBLE);
                threePerson.setVisibility(View.VISIBLE);
                fourperson.setVisibility(View.VISIBLE);
                socialDescription.setText("Event occured in presence of a crowd of people");
                break;

        }

        /**
         * returns no photo if no photo was saved previously, or returns photo saved previously.
         */
        if (moodEvent.getPhoto() != null) {
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

    /**
     *
     * @param mood
     *
     * Method for selecting background of the mood with respect to the mood selected.
     */
    private void cycleStyle(Mood mood) {
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

    /**
     *
     * @param v
     *
     * Checks to see if permission is granted by user to launch the camera.
     * if permission is granted, launches camera and calls startActivityForResult.
     */
    public void addPhotoMethod(View v) {
        if (checkSelfPermission(Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.CAMERA},
                    REQUEST_CODE);
        } else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, REQUEST_CODE);
        }
    }

    /**
     *
     * @param v
     *
     *
     */
    public void person(View v) {
        number = number + 1;

        if (number == 5) {
            number = 0;
            onePerson.setVisibility(View.INVISIBLE);
            twoPerson.setVisibility(View.INVISIBLE);
            threePerson.setVisibility(View.INVISIBLE);
            fourperson.setVisibility(View.INVISIBLE);
            socialDescription.setText("Select number of people by clicking on Social");
        }


        if (number == 1) {
            onePerson.setVisibility(View.VISIBLE);
            twoPerson.setVisibility(View.INVISIBLE);
            threePerson.setVisibility(View.INVISIBLE);
            fourperson.setVisibility(View.INVISIBLE);
            socialDescription.setText("Event occured Alone");
        } else if (number == 2) {
            onePerson.setVisibility(View.VISIBLE);
            twoPerson.setVisibility(View.VISIBLE);
            threePerson.setVisibility(View.INVISIBLE);
            fourperson.setVisibility(View.INVISIBLE);
            socialDescription.setText("Event occured with one person");
        } else if (number == 3) {
            onePerson.setVisibility(View.VISIBLE);
            twoPerson.setVisibility(View.VISIBLE);
            threePerson.setVisibility(View.VISIBLE);
            fourperson.setVisibility(View.INVISIBLE);
            socialDescription.setText("Event occured in presence of several people");
        } else if (number == 4) {
            onePerson.setVisibility(View.VISIBLE);
            twoPerson.setVisibility(View.VISIBLE);
            threePerson.setVisibility(View.VISIBLE);
            fourperson.setVisibility(View.VISIBLE);
            socialDescription.setText("Event occured in presence of a crowd of people");
        }
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        double ratio = Math.min(
                (double) maxImageSize / realImage.getWidth(),
                (double) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }


}