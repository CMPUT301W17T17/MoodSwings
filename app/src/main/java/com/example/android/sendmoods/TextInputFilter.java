package com.example.android.sendmoods;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Etiennera on 2017-02-05.
 * Sourced from: http://stackoverflow.com/questions/5357455/limit-decimal-places-in-android-edittext/24632346#24632346
 * Credit to user: android_dev
 *
 * This class acts only as a filter which is used to ensure numeric fields resemble XY.Z.
 */

public class TextInputFilter implements InputFilter {
    private Pattern mPattern;

    public TextInputFilter() {
        mPattern = Pattern.compile("-?^(?=.{0,20}$)([A-z0-9]{1,20}[^\\S\\n]?){0,3}?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String replacement = source.subSequence(start, end).toString();
        String newVal = dest.subSequence(0, dstart).toString() + replacement
                + dest.subSequence(dend, dest.length()).toString();
        Matcher matcher = mPattern.matcher(newVal);
        if (matcher.matches())
            return null;

        if (TextUtils.isEmpty(source))
            return dest.subSequence(dstart, dend);
        else
            return "";
    }
}

