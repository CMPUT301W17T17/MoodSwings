package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

public class SadMood extends Mood {
    public SadMood(){
        color = Color.parseColor(SAD_COLOR);
        shape = SAD_POPUP_BOX;
        text = SAD_WORD;
        icon = SAD_ICON;
        icon_bw = SAD_ICON_BW;
    }
}
