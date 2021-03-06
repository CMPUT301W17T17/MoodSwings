package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

public class AfraidMood extends Mood {
    public AfraidMood(){
        color = Color.parseColor(AFRAID_COLOR);
        shape = AFRAID_POPUP_BOX;
        text = AFRAID_WORD;
        icon = AFRAID_ICON;
        icon_bw = AFRAID_ICON_BW;
    }
}
