package com.example.user.listns;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

//글 올리기
//branch - listActivity_by_ns

public class MainActivityList extends AppCompatActivity {

    EditText etextTitle, etextPerName, etextPerDate, etextCost, etextPerAddress, etextLink, etextMemo;
    Button btnCreatePost, btnRemovePost,  list_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        MyOpenHelper helper = new MyOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();

        etextTitle = (EditText) findViewById(R.id.etextTitle);
        etextPerName = (EditText) findViewById(R.id.etextPerName);
        etextPerDate = (EditText) findViewById(R.id.etextPerDate);
        etextCost = (EditText) findViewById(R.id.etextCost);
        etextPerAddress = (EditText) findViewById(R.id.etextPerAddress);
        etextLink = (EditText) findViewById(R.id.etextLink);
        etextMemo = (EditText) findViewById(R.id.etextLink);

        btnCreatePost = (Button) findViewById(R.id.btnCreatePost);
        btnRemovePost = (Button) findViewById(R.id.btnRemovePost);
        list_btn = (Button) findViewById(R.id.list_btn);

        //글 올리기, db에 삽입
        btnCreatePost.setOnClickListener(new OnClickListener() {
            //@Override
            public void onClick(View v) {

                String title = etextTitle.getText().toString();
                String perName = etextPerName.getText().toString();
                String perDate = etextPerDate.getText().toString();
                String cost = etextCost.getText().toString();
                String perAddress = etextPerAddress.getText().toString();
                String link = etextLink.getText().toString();
                String memo = etextMemo.getText().toString();
                Date nowDate = new Date();
                String post_datetime = nowDate.toString();
                String sellout = "false"; //:팜매중/ true:판매완료

                ContentValues insertValues = new ContentValues();

                insertValues.put("user_id", title); /////
                insertValues.put("title", title);
                insertValues.put("perName", perName);
                insertValues.put("perDate", perDate);
                insertValues.put("perAddress", perAddress);
                insertValues.put("cost", cost);
                insertValues.put("link", link);
                insertValues.put("memo", memo);
                insertValues.put("post_datetime", post_datetime);
                insertValues.put("sellout", sellout);

                long id = db.insert("Post",null,insertValues);

                Toast.makeText(getApplicationContext(), "글 등록이 되었습니다.",
                        Toast.LENGTH_SHORT).show();
                /*
                Intent mainIntent
                        = new Intent(MainActivityList.this, MainActivityList.class);
                startActivity(mainIntent);
                */
            }
        });

        // 글목록
        list_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityList.this, ListActivity.class);
                startActivity(intent);
            }
        });

    }
}
