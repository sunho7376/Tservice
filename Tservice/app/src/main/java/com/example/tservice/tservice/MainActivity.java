package com.example.tservice.tservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    //로그인 안 됐을 때 메인 페이지

    private BackPressCloseHandler backPressCloseHandler;

    Intent intent;
    Intent loginedIntent;
    InputMethodManager imm;

    EditText idEditText;
    EditText pwEditText;
    Button loginBtn;
    Button registerBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this.getIntent());
        loginedIntent = new Intent(MainActivity.this, LoginedActivity.class);
        imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        backPressCloseHandler = new BackPressCloseHandler(this);

        if (User.loginState) {
            startActivity(loginedIntent);
        }

        idEditText = (EditText)findViewById(R.id.idEditText);
        pwEditText = (EditText)findViewById(R.id.pwEditText);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        registerBtn = (Button)findViewById(R.id.registerBtn);

        //로그인 버튼 클릭
        loginBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                Query mmQuery = new Query(new CallBackListener<String>(){
                    @Override
                    public void onSuccess(String value) {
                        String[] flag = value.split(";");
                        if (flag[0].equals("success")) {
                            User.loginState = true;
                            User.userPk = flag[2];

                            startActivity(loginedIntent);
                            //로그인 성공시 User클래스의
                            //loginState = true로, userPk에 해당하는 user의 primary key를 설정

                        } else {
                            User.loginState = false;
                            User.userPk = "";

                            imm.hideSoftInputFromWindow(idEditText.getWindowToken(),0);
                            Toast.makeText(getApplicationContext(), "회원정보를 확인하세요.",
                                    Toast.LENGTH_SHORT).show();
                            //로그인 실패시 토스트메시지 출력
                        }
                    }

                }, "select", "select * from `user_open` where user_id='"
                    + idEditText.getText().toString()
                    + "' and pw='" + pwEditText.getText().toString() + "';");
            }
        });

        registerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    //App Task 끄지 않는 이상 로그인 되어있는 상태면 loginedActivity로 넘어가도록
    public void onStart() {
        super.onStart();
        if (User.loginState) {
            startActivity(loginedIntent);
        }
    }
    public void onResume() {
        super.onResume();
        if (User.loginState) {
            startActivity(loginedIntent);
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}