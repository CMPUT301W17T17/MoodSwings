package com.example.android.sendmoods;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Matt on 2017-03-21.
 */

public class MoodMapsActivity extends FragmentActivity implements OnMapReadyCallback {

        private GoogleMap mMap;
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

            for (int i = 0; i < moodEventList.size(); i++) {
                LatLng mood = new LatLng(moodEventList.getMoodEvent(i).getLatitude(), moodEventList.getMoodEvent(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(mood).title(moodEventList.getMoodEvent(i).getMood().getText()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(mood));
            }



            try{
                mMap.setMyLocationEnabled(true);
                Context context2 = getApplicationContext();
                CharSequence text2 = "found location";
                int duration2 = Toast.LENGTH_SHORT;
                Toast toast2 = Toast.makeText(context2, text2, duration2);
                toast2.show();

            } catch (SecurityException e){
                Context context2 = getApplicationContext();
                CharSequence text2 = "cant find location";
                int duration2 = Toast.LENGTH_SHORT;
                Toast toast2 = Toast.makeText(context2, text2, duration2);
                toast2.show();

            }
        }
}
