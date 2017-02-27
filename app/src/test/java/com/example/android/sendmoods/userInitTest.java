package com.example.android.sendmoods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 *
 */

public class userInitTest extends userInit {

    public userInitTest () {super(userInit.class);
    }

    public void testAddUserName() {
        UserName username = new username();
        UserNameList userList = new UserNameList("some username");
        userList.add(username);

        try {
            userList.add(username);
        }
        catch (IllegalArgumentException e){
            RuntimeException();

        }
        assertTrue(userList.hasUserName(username));
    }

    public void testHasUserName() {
        UserName username = new username();
        UserNameList userList = new userNameList("some username");

        assertFalse(userList.hasUserName(username));
        userList.add(username);

        assertTrue(userList.hasUserName(username));
    }
}
