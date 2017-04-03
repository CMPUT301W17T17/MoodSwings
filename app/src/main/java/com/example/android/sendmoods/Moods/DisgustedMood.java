package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.*;

public class DisgustedMood extends Mood {
    public DisgustedMood(){
        color = Color.parseColor(DISGUSTED_COLOR);
        shape = DISGUSTED_POPUP_BOX;
        text = DISGUSTED_WORD;
        icon = DISGUSTED_ICON;
        icon_bw = DISGUSTED_ICON_BW;
    }
}