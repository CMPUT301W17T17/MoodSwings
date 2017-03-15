package com.example.android.sendmoods;

/**
 * Created by Moe on 2017-03-11.
 */

public class Constants {
    public static final int HAPPY_POPUP_BOX = R.drawable.popup_shape_pink;
    public static final String HAPPY_WORD = "Happy";
    public static final String HAPPY_COLOR = "#F06292";

    //Changed because both constants were the same, defeating the purpose of request codes.
    //Please read up on Request codes.
    public static final int REQ_CODE_POPUP = 1;
    public static final int REQ_CODE_NEW = 0;
    public static final int REQ_CODE_EDIT = 2;
    public static final int RES_CODE_DELETED = 0;
    public static final int RES_CODE_EDITED = 1;
    public static final int RES_CODE_NOCHANGE = 2;
    public static final int RES_CODE_NEW = 3;

}



