package com.example.android.sendmoods;


import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Moe on 2017-03-11.
 * This class contains the constants
 */

public class Constants {
    public static final String SAVEFILE_NAME = "local.sav";
    private static final String dateFormat = "MMMM dd, yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat(dateFormat, Locale.CANADA);
    private static final String timeFormat = "h:mm a";
    public static final SimpleDateFormat SIMPLE_TIME_FORMAT
            = new SimpleDateFormat(timeFormat, Locale.CANADA);

    /**
     * Constants for Empty Mood
     */

    public static final int DEFAULT_POPUP_BOX = R.drawable.popup_shape_gray;
    public static final int DEFAULT_ICON = R.drawable.neutral;
    public static final String DEFAULT_WORD = "No Mood Selected";
    public static final String DEFAULT_COLOR = "#E0E0E0";
    /**
     * Constants for Happy Mood
     */

    public static final int HAPPY_POPUP_BOX = R.drawable.popup_shape_pink;
    public static final int HAPPY_ICON = R.drawable.otherhappy;
    public static final int HAPPY_ICON_BW = R.drawable.otherhappy_bw;
    public static final String HAPPY_WORD = "Happy";
    public static final String HAPPY_COLOR = "#F06292";

    /**
     * Constants for Angry Mood
     */
    public static final int ANGRY_POPUP_BOX = R.drawable.popup_shape_red;
    public static final int ANGRY_ICON = R.drawable.angry;
    public static final int ANGRY_ICON_BW = R.drawable.angry_bw;
    public static final String ANGRY_WORD = "Angry";
    public static final String ANGRY_COLOR = "#E57373";

    /**
     * Constants for Confused Mood
     */
    public static final int CONFUSED_POPUP_BOX = R.drawable.popup_shape_purple;
    public static final int CONFUSED_ICON = R.drawable.confused;
    public static final int CONFUSED_ICON_BW = R.drawable.confused_bw;
    public static final String CONFUSED_WORD = "Confused";
    public static final String CONFUSED_COLOR = "#BA68C8";

    /**
     * Constants for Disgusted Mood
     */
    public static final int DISGUSTED_POPUP_BOX = R.drawable.popup_shape_green;
    public static final int DISGUSTED_ICON = R.drawable.disgusted;
    public static final int DISGUSTED_ICON_BW = R.drawable.disgusted_bw;
    public static final String DISGUSTED_WORD = "Disgusted";
    public static final String DISGUSTED_COLOR = "#81C784";

    /**
     * Constants for fear Mood
     */
    public static final int AFRAID_POPUP_BOX = R.drawable.popup_shape_cyan;
    public static final int AFRAID_ICON = R.drawable.afraid;
    public static final int AFRAID_ICON_BW = R.drawable.afraid_bw;
    public static final String AFRAID_WORD = "Afraid";
    public static final String AFRAID_COLOR = "#4DD0E1";

    /**
     * Constants for sadness Mood
     */
    public static final int SAD_POPUP_BOX = R.drawable.popup_shape_blue;
    public static final int SAD_ICON = R.drawable.sad;
    public static final int SAD_ICON_BW = R.drawable.sad_bw;
    public static final String SAD_WORD = "Sad";
    public static final String SAD_COLOR = "#64B5F6";

    /**
     * Constants for surprised Mood
     */
    public static final int SURPRISED_POPUP_BOX = R.drawable.popup_shape_yellow;
    public static final int SURPRISED_ICON = R.drawable.surprised;
    public static final int SURPRISED_ICON_BW = R.drawable.surprised_bw;
    public static final String SURPRISED_WORD = "Surprised";
    public static final String SURPRISED_COLOR = "#FFF176";

    /**
     * Constants for ASHAMED Mood
     */
    public static final int ASHAMED_POPUP_BOX = R.drawable.popup_shape_orange;
    public static final int ASHAMED_ICON = R.drawable.ashamed;
    public static final int ASHAMED_ICON_BW = R.drawable.ashamed_bw;
    public static final String ASHAMED_WORD = "Ashamed";
    public static final String ASHAMED_COLOR = "#FFB74D";

    public static final int REQ_CODE_POPUP = 1;
    public static final int REQ_CODE_NEW = 0;
    public static final int REQ_CODE_EDIT = 2;
    public static final int RES_CODE_DELETED = 0;
    public static final int RES_CODE_EDITED = 1;
    public static final int RES_CODE_NOCHANGE = 2;
    public static final int RES_CODE_NEW = 3;
}



