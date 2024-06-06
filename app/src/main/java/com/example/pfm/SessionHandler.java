package com.example.pfm;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

public class SessionHandler {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EXPIRES = "expires";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_EMPTY = "";
    private Context mcontext;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPreferences;


    public SessionHandler(Context mcontext){
        this.mcontext = mcontext;
        mPreferences = mcontext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.mEditor = mPreferences.edit();
    }

    //login the user by saving their username and password
//
//    @param username
//    @param fullName
    public void loginUser(String username, String fullName){
        mEditor.putString(KEY_USERNAME, username);
        mEditor.putString(KEY_FULL_NAME, fullName);
        Date date = new Date();

        //set usersession for next 7 days
        long millis = date.getTime() + (7 * 24 * 60 * 60 * 1000);
        mEditor.putLong(KEY_EXPIRES, millis);
        mEditor.commit();

    }


    //check whether the user is logged in or not
    public boolean isLoggedIn(){
        Date currentDate = new Date();
        long millis = mPreferences.getLong(KEY_EXPIRES, 0);

        //is shared preferences is not have value then user
        //is not logged in

        if (millis == 0){
            return false;
        }
        Date expiryDate = new Date(millis);

//        check if session is expired by comparing the current date and session expiry date

        return currentDate.before(expiryDate);

    }

    //fetch and return the userdetails
    public User getUserDetails() {
        //Check if user is logged in first
        if (!isLoggedIn()) {
            return null;
        }
        User user = new User();
        user.setUsername(mPreferences.getString(KEY_USERNAME, KEY_EMPTY));
        user.setFullName(mPreferences.getString(KEY_FULL_NAME, KEY_EMPTY));
        user.setSessionExpiryDate(new Date(mPreferences.getLong(KEY_EXPIRES, 0)));

        return user;
    }
    //logout session by clearing session
    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();
    }
}
