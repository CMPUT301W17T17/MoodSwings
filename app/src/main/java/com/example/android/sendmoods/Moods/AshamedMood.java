package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

/**
 * Created by Etiennera on 2017-03-17.
 */

public class AshamedMood extends Mood {
    public void AshamedMood(){
        color = Color.parseColor(ASHAMED_COLOR);
        shape = ASHAMED_POPUP_BOX;
        text = ASHAMED_WORD;
        icon = ASHAMED_ICON;
        icon_bw = ASHAMED_ICON_BW;
    }
}
