package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

/**
 * Created by Etiennera on 2017-03-17.
 */

public class AfraidMood extends Mood {
    public void AfraidMood(){
        color = Color.parseColor(AFRAID_COLOR);
        shape = AFRAID_POPUP_BOX;
        text = AFRAID_WORD;
        icon = AFRAID_ICON;
        icon_bw = AFRAID_ICON_BW;
    }
}
