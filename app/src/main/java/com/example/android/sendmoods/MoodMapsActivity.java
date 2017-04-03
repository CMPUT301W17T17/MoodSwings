package com.example.android.sendmoods;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.android.sendmoods.Constants.AFRAID_WORD;
import static com.example.android.sendmoods.Constants.ANGRY_WORD;
import static com.example.android.sendmoods.Constants.ASHAMED_WORD;
import static com.example.android.sendmoods.Constants.CONFUSED_WORD;
import static com.example.android.sendmoods.Constants.DISGUSTED_WORD;
import static com.example.android.sendmoods.Constants.HAPPY_WORD;
import static com.example.android.sendmoods.Constants.SAD_WORD;
import static com.example.android.sendmoods.Constants.SURPRISED_WORD;

/**
 * Created by Matt on 2017-03-21.
 */

public class MoodMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    /**
     *
     * @param savedInstanceState
     *
     * Launches the Map UI
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Context context = getApplicationContext();
        CharSequence text = "onCreate part";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Context context = getApplicationContext();
        CharSequence text = "onMapReady part";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        mMap = googleMap;

        // Add a marker in Edmonton and move the camera
            /*LatLng edmonton = new LatLng(53.5, -113.4);
            mMap.addMarker(new MarkerOptions().position(edmonton).title("Marker in Edmonton"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(edmonton));*/


        //ArrayList<MoodEvent> moodEventList = this.getIntent().getParcelableArrayListExtra("MapEvents");
        //Bundle bundle = new Bundle();

        Bundle bundle = getIntent().getParcelableExtra("mapBundle");
        MoodList moodEventList = bundle.getParcelable("myList");


        /**
         * Generates the respective mood as the pin for the map UI.
         */
        for (int i = 0; i < moodEventList.size(); i++) {
            String icon = moodEventList.getMoodEvent(i).getMood().getText();

            // initialize the marker so it exists
            Bitmap smallMarker = BitmapFactory.decodeResource(context.getResources(), R.drawable.ashamed);
            int height = 120;
            int width = 120;

            if (icon.equals(ASHAMED_WORD)) {

                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.ashamed);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

            }
            if (icon.equals(ANGRY_WORD)) {

                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.angry);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

            }
            if (icon.equals(HAPPY_WORD)) {
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.otherhappy);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            }
            if (icon.equals(SAD_WORD)) {
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.sad);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            }
            if (icon.equals(DISGUSTED_WORD)) {
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.disgusted);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            }
            if (icon.equals(CONFUSED_WORD)) {
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.confused);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            }
            if (icon.equals(SURPRISED_WORD)) {
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.surprised);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            }
            if (icon.equals(AFRAID_WORD)) {
                BitmapDrawable bitmapdraw = (BitmapDrawable) getResources().getDrawable(R.drawable.afraid);
                Bitmap b = bitmapdraw.getBitmap();
                smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
            }


            LatLng mood = new LatLng(moodEventList.getMoodEvent(i).getLatitude(), moodEventList.getMoodEvent(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(mood).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).title(moodEventList.getMoodEvent(i).getMood().getText()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(mood));
        }


        /**
         * Try Catch blocks to verify that location is found.
         */
        try {
            mMap.setMyLocationEnabled(true);
            Context context2 = getApplicationContext();
            CharSequence text2 = "found location";
            int duration2 = Toast.LENGTH_SHORT;
            Toast toast2 = Toast.makeText(context2, text2, duration2);
            toast2.show();

        } catch (SecurityException e) {
            Context context2 = getApplicationContext();
            CharSequence text2 = "cant find location";
            int duration2 = Toast.LENGTH_SHORT;
            Toast toast2 = Toast.makeText(context2, text2, duration2);
            toast2.show();

        }


    }

    /*public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }*/
}
