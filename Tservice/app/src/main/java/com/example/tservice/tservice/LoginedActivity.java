package com.example.tservice.tservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginedActivity extends AppCompatActivity
{
    //로그인 된 상태일 때 실행되는 activity

    private BackPressCloseHandler backPressCloseHandler;

    Intent intent;
    Intent fixUserIntent;

    Button fixUserBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logined);

        intent = new Intent(this.getIntent());
        fixUserIntent = new Intent(LoginedActivity.this, FixUserActivity.class);
        backPressCloseHandler = new BackPressCloseHandler(this);

        fixUserBtn = (Button)findViewById(R.id.fixUserBtn);

        fixUserBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent fixUserIntent = new Intent(LoginedActivity.this, FixUserActivity.class);
                startActivity(fixUserIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
