package com.example.tservice.tservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class RegPostActivity extends AppCompatActivity {

    EditText etextTitle, etextPerName, etextPerDate, etextCost, etextPerAddress, etextLink, etextMemo;
    Button btnCreatePost;
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
            }
        });
    }
}
