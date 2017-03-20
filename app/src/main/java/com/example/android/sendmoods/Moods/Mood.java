package com.example.android.sendmoods.Moods;

import android.graphics.Color;

import static com.example.android.sendmoods.Constants.DEFAULT_COLOR;
import static com.example.android.sendmoods.Constants.DEFAULT_POPUP_BOX;
import static com.example.android.sendmoods.Constants.DEFAULT_WORD;
import static com.example.android.sendmoods.Constants.DEFAULT_ICON;

/**
 * Created by Etiennera on 2017-03-17.
 */

public class Mood{
    protected int color;
    protected int shape;
    protected String text;
    protected int icon;
    protected int icon_bw;

    public Mood(){
        color = Color.parseColor(DEFAULT_COLOR);
        shape = DEFAULT_POPUP_BOX;
        text = DEFAULT_WORD;
        icon = DEFAULT_ICON;
        icon_bw = DEFAULT_ICON; //No _bw for the default
    }

    public Mood(int color, int shape, String text, int icon, int icon_bw) {
        this.color = color;
        this.text = text;
        this.shape = shape;
        this.icon = icon;
        this.icon_bw = icon_bw;
    }

    public int getColor() {
        return color;
    }

    public String getText() {
        return text;
    }

    public int getShape() {
        return shape;
    }

    public int getIcon() {
        return icon;
    }

    public int getIcon_bw() {
        return icon_bw;
    }

    public Mood toMood(){
        return new Mood(this.color, this.shape, this.text, this.icon, this.icon_bw);
    }
}
