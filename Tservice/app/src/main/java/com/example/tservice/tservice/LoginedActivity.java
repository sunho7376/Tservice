package com.example.tservice.tservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tservice.tservice.MyPostList.InfoActivity;

public class LoginedActivity extends AppCompatActivity
{
    //로그인 된 상태일 때 실행되는 activity

    private BackPressCloseHandler backPressCloseHandler;

    Intent intent;
    Intent fixUserIntent;

    Button listBtn; //글목록
    Button btnCreatePost; // 글 올리기
    Button searchBtn; //글검색
    Button infoBtn; //내정보보기 수정
    Button logoutBtn; //로그아웃
    Button fixUserBtn; //회원정보 수정

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logined);

        intent = new Intent(this.getIntent());
        fixUserIntent = new Intent(LoginedActivity.this, FixUserActivity.class);
        backPressCloseHandler = new BackPressCloseHandler(this);

        fixUserBtn = (Button)findViewById(R.id.fixUserBtn);
        listBtn = (Button)findViewById(R.id.list_btn);
        searchBtn = (Button)findViewById(R.id.search_btn);
        infoBtn = (Button)findViewById(R.id.user_Info);
        logoutBtn = (Button)findViewById(R.id.logout_btn);
        btnCreatePost = (Button) findViewById(R.id.btnCreatePost);


        fixUserBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent fixUserIntent = new Intent(LoginedActivity.this, FixUserActivity.class);
                startActivity(fixUserIntent);
            }
        });

        listBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginedActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginedActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        infoBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginedActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginedActivity.this, MainActivity.class);
                //finish();
                User.loginState = false;
                User.userPk = "";
                startActivity(intent);
            }
        });

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginedActivity.this, RegPostActivity.class);
                intent.putExtra("isCreate", true);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
