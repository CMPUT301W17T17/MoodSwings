package com.example.android.sendmoods;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Etiennera on 2017-03-20.
 */

public class DrawerFragment extends AppCompatActivity{
    private ImageView moodIcon;
    private TextView nameText;
    private View headerBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, null, R.string.open_drawer_string, R.string.close_drawer_string);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        moodIcon = (ImageView) findViewById(R.id.drawer_header_icon);
        nameText = (TextView) findViewById(R.id.drawer_header_username);
        headerBox = findViewById(R.id.header_box);
    }

    public void setStyle(int color, int shape, String text){
        moodIcon.setImageResource(shape);
        nameText.setText(text);
        headerBox.setBackground(ContextCompat.getDrawable(this, color));
    }
}