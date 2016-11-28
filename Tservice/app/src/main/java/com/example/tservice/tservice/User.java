package com.example.tservice.tservice;

import android.app.Application;

public class User extends Application
{
    public static boolean loginState = false;
    public static String userPk = "";

    @Override
    public void onCreate() {
        super.onCreate();
    }
}