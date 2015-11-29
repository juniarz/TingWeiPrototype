package com.ezsofe.tingweiprototype;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Jia Rong on 11/29/2015.
 */
public class AppActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "9wfdcHDEwMLOsOrrST5u0TpvGHfErwdOxuhOtRg2", "RW7xCQRGvQs7UOxCAHK7FvLyum8ldGUz9VKfllh3");
    }
}
