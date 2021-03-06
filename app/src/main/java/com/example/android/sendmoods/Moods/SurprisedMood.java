package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

public class SurprisedMood extends Mood {
    public SurprisedMood(){
        color = Color.parseColor(SURPRISED_COLOR);
        shape = SURPRISED_POPUP_BOX;
        text = SURPRISED_WORD;
        icon = SURPRISED_ICON;
        icon_bw = SURPRISED_ICON_BW;
    }
}