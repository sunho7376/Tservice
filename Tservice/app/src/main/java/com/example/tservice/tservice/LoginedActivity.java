package com.example.tservice.tservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoginedActivity extends AppCompatActivity
{
    //로그인 된 상태일 때 실행되는 activity
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logined);
    }
}
