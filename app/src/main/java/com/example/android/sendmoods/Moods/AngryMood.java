package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

/**
 * Created by Etiennera on 2017-03-17.
 */

public class AngryMood extends Mood {
    public AngryMood(){
        color = Color.parseColor(ANGRY_COLOR);
        shape = ANGRY_POPUP_BOX;
        text = ANGRY_WORD;
        icon = ANGRY_ICON;
        icon_bw = ANGRY_ICON_BW;
    }
}
