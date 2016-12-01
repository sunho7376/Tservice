package com.example.tservice.tservice;

// 글 생성, 수정, 삭제는 한 페이지로 만듬
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class RegPostActivity extends AppCompatActivity {

    EditText etextTitle, etextPerName, etextPerDate, etextCost, etextPerAddress, etextLink, etextMemo;
    Button btnCreatePost, btnRemovePost;
    Query mQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_post);

        etextTitle = (EditText) findViewById(R.id.etextTitle);
        etextPerName = (EditText) findViewById(R.id.etextPerName);
        etextPerDate = (EditText) findViewById(R.id.etextPerDate);
        etextCost = (EditText) findViewById(R.id.etextCost);
        etextPerAddress = (EditText) findViewById(R.id.etextPerAddress);
        etextLink = (EditText) findViewById(R.id.etextLink);
        etextMemo = (EditText) findViewById(R.id.etextLink);

        btnCreatePost = (Button) findViewById(R.id.btnCreatePost);
        btnRemovePost = (Button) findViewById(R.id.btnRemovePost);

        Intent intent = getIntent();
        boolean isCreate = intent.getBooleanExtra("isCreate", true); // true값이면 세로 글 만들고 false값이면 글 수정
        if (isCreate){
            // 글 새로 만들기
            btnRemovePost.setVisibility(View.INVISIBLE); // 삭제 버튼 숨기기

            btnCreatePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = etextTitle.getText().toString();
                    String perName = etextPerName.getText().toString();
                    String perDate = etextPerDate.getText().toString();
                    String cost = etextCost.getText().toString();
                    String perAddress = etextPerAddress.getText().toString();
                    String link = etextLink.getText().toString();
                    String memo = etextMemo.getText().toString();
                    Date nowDate = new Date();
                    String dateStr = nowDate.toString();

                    mQuery = new Query(new CallBackListener<String>(){
                        @Override
                        public void onSuccess(String value) {

                        }
                    }, "insert", "insert into post_open (title, name, show_datetime, place, cost, link, memo, sellout, post_datetime) " +
                            "values (" + title + "," + perName + "," + perDate + "," + perAddress + "," + cost + "," + link + "," + memo + "," + false + "," + dateStr + ")"
                    );

                    finish();
                }
            });
        } else {
            // 글 수정, 삭제
            btnRemovePost.setVisibility(View.VISIBLE); // 삭제 버튼 숨기기
            final Post post = (Post) intent.getSerializableExtra("exectPost");

            btnCreatePost.setText("수정");

            // 존재하는 글 표시
            Query mQuery = new Query(new CallBackListener<String>() {
                @Override
                public void onSuccess(String value) {
                    String[] flag = value.split(";");
                    if (flag[0].equals("success")) {
                        etextTitle.setText(flag[4]);
                        etextPerName.setText(flag[5]);
                        etextPerDate.setText(flag[6]);
                        etextPerAddress.setText(flag[7]);
                        etextCost.setText(flag[8]);
                        etextLink.setText(flag[9]);
                        etextMemo.setText(flag[10]);
                    } else {
//                        //user primary key 문제시 user정보 초기화 + mainActivity로 돌려버림
//                        User.userPk = "";
//                        User.loginState= false;
//                        startActivity(mainIntent);
                    }
                }

            }, "select", "select * from `post_open` where id='" + post.getPost_pk() + "';");

            // 글 수정하는 부분
            btnCreatePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = etextTitle.getText().toString();
                    String perName = etextPerName.getText().toString();
                    String perDate = etextPerDate.getText().toString();
                    String cost = etextCost.getText().toString();
                    String perAddress = etextPerAddress.getText().toString();
                    String link = etextLink.getText().toString();
                    String memo = etextMemo.getText().toString();
                    Date nowDate = new Date();
                    String dateStr = nowDate.toString();

                    Query updateQuery = new Query(new CallBackListener<String>(){
                        @Override
                        public void onSuccess(String value) {
                            Toast.makeText(RegPostActivity.this,"글이 정상적으로 수정되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }, "update", "update `post_open` set title='" + title + "', name='" + perName
                            + "', show_datetime='" + perDate + "', place='" + perAddress
                            + "', cost='" + cost + "', link='" + link + "', memo='" + memo
                            + "' where id='" + post.getPost_pk() + "';"
                    );

                    finish();
                }
            });

            // 글 삭제하는 부분
            btnRemovePost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Query updateQuery = new Query(new CallBackListener<String>(){
                        @Override
                        public void onSuccess(String value) {
                            Toast.makeText(RegPostActivity.this,"글이 정상적으로 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }, "delete", "delete from `post_open`" + "where id='" + post.getPost_pk() + "';"
                    );

                    finish();
                }
            });
        }
    }
}
