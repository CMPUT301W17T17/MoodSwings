package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

public class HappyMood extends Mood {
    public HappyMood(){
        color = Color.parseColor(HAPPY_COLOR);
        shape = HAPPY_POPUP_BOX;
        text = HAPPY_WORD;
        icon = HAPPY_ICON;
        icon_bw = HAPPY_ICON_BW;
    }
}
