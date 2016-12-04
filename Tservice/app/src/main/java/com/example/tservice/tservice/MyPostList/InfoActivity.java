package com.example.tservice.tservice.MyPostList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.tservice.tservice.CallBackListener;
import com.example.tservice.tservice.Query;
import com.example.tservice.tservice.R;
import com.example.tservice.tservice.User;

import java.util.ArrayList;

//내 정보 (보기, 수정)
public class InfoActivity extends AppCompatActivity {

    private TextView tviewId, tviewName, tviewContact, tviewAddress;

    private ArrayList<Post> myposts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tviewId = (TextView) findViewById(R.id.tviewId);
        tviewName = (TextView) findViewById(R.id.tviewName);
        tviewContact = (TextView) findViewById(R.id.tviewContact);
        tviewAddress = (TextView) findViewById(R.id.tviewAddress);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myPostList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llManager = new LinearLayoutManager(this);
        llManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llManager);


        myposts = new ArrayList<Post>();
        Query postListQuery = new Query(new CallBackListener<String>() {
            @Override
            public void onSuccess(String value) {
                String[] flag = value.split(";");
                if (flag[0].equals("success")) {
                    int i = 0, j = 0;
                    while (j < flag.length){
                        myposts.add(new Post(flag[4 +(13*i)], // 글 제목
                                flag[5 +(13*i)], // 공연 이름
                                flag[6 +(13*i)], // 공연 날짜
                                flag[7 +(13*i)], // 공연 장소
                                Integer.parseInt(flag[8 +(13*i)]), // 가격
                                flag[9 +(13*i)], // 링크
                                flag[10 +(13*i)], // 메모
                                flag[11 +(13*i)], // sellout
                                flag[12 +(13*i)])); // 글 작성한 날짜)
                        i++;
                        j = 14 +(13*i);
                    }
                } else {
                    Log.v("TESToooooooooooooo", "실패");
                }
            }

        }, "select", "select * from `post_open` where user_pk='" + User.userPk + "';");

        RecyclerView.Adapter adapter = new MyPostAdapter(myposts);
        recyclerView.setAdapter(adapter);
//        recyclerView.smoothScrollToPosition(myposts.size() -1);


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
//                    User.userPk = "";
//                    User.loginState= false;
//                    startActivity(mainIntent);
                }
            }

        }, "select", "select * from `user_open` where id='" + User.userPk + "';");

    }
}
