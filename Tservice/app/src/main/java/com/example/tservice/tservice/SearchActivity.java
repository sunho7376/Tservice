package com.example.tservice.tservice;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Reader;

import static java.sql.DriverManager.println;

public class SearchActivity extends AppCompatActivity {


    Button btn;
    EditText edt;
    Query mQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn = (Button) findViewById(R.id.search_btn);
        edt = (EditText) findViewById(R.id.edtSearch);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchWord = edt.getText().toString();

                mQuery = new Query(new CallBackListener<String>() {
                    @Override
                    public void onSuccess(String value) {
                        Log.v("INSERT SEARCH::::::::", value);
                    }
                }, "insert", "insert into post_open (title) " +
                        "values ('" + searchWord + "');"
                );

                finish();
            }
        });
    }
}