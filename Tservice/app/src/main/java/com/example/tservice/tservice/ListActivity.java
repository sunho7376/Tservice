package com.example.tservice.tservice;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tservice.tservice.MyPostList.MyPostAdapter;
import com.example.tservice.tservice.MyPostList.Post;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashMap;
import java.net.URL;

public class ListActivity extends AppCompatActivity {

    private TextView tviewId, tviewName, tviewContact, tviewAddress;
    private RecyclerView.Adapter adapter;
    private ArrayList<Post> myposts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
                    while (j < flag.length) {
                        myposts.add(new Post(flag[4 + (13 * i)], // 글 제목
                                flag[5 + (13 * i)], // 공연 이름
                                flag[6 + (13 * i)], // 공연 날짜
                                flag[7 + (13 * i)], // 공연 장소
                                Integer.parseInt(flag[8 + (13 * i)]), // 가격
                                flag[9 + (13 * i)], // 링크
                                flag[10 + (13 * i)], // 메모
                                flag[11 + (13 * i)], // sellout
                                flag[12 + (13 * i)])); // 글 작성한 날짜)
                        i++;
                        j = 13 + (13 * i);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.v("TESToooooooooooooo", "실패");
                }
            }

        }, "select", "select * from `post_open` where user_pk='" + User.userPk + "';");

        adapter = new MyPostAdapter(myposts);
        recyclerView.setAdapter(adapter);
    }
}