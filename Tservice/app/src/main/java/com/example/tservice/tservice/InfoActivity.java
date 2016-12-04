package com.example.tservice.tservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//내 정보 (보기, 수정)
public class InfoActivity extends AppCompatActivity {

    private TextView tviewId, tviewName, tviewContact, tviewAddress;
    private Button btnMyPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tviewId = (TextView) findViewById(R.id.tviewId);
        tviewName = (TextView) findViewById(R.id.tviewName);
        tviewContact = (TextView) findViewById(R.id.tviewContact);
        tviewAddress = (TextView) findViewById(R.id.tviewAddress);
        btnMyPost = (Button) findViewById(R.id.btnMyPost);

        //내 정보 설정
        Query mQuery = new Query(new CallBackListener<String>() {
            @Override
            public void onSuccess(String value) {
                String[] flag = value.split(";");
                if (flag[0].equals("success")) {
                    tviewId.setText("ID: " + flag[3]);
                    tviewName.setText("이름: " + flag[5]);
                    tviewContact.setText("연락처: " + flag[6]);
                    tviewAddress.setText("주소: " + flag[7]);
                } else {
//                    user primary key 문제시 user정보 초기화 + mainActivity로 돌려버림
                    User.userPk = "";
                    User.loginState= false;
//                    startActivity(mainIntent);
                }
            }
        }, "select", "select * from `user_open` where id='" + User.userPk + "';");

        // 버튼을 누르면 자기가 쓴 글만 나오는 Activity로 이동
        btnMyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
