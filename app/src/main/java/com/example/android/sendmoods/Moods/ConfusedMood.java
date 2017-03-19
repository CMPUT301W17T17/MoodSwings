package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

/**
 * Created by Etiennera on 2017-03-17.
 */

public class ConfusedMood extends Mood {
    public ConfusedMood(){
        color = Color.parseColor(CONFUSED_COLOR);
        shape = CONFUSED_POPUP_BOX;
        text = CONFUSED_WORD;
        icon = CONFUSED_ICON;
        icon_bw = CONFUSED_ICON_BW;
    }
}
