package com.example.android.sendmoods;

/**
 *
 */

public class userTest extends User {

    public userTest() {super(User.class);
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
