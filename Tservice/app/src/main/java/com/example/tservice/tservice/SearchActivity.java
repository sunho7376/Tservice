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


    //SQLiteDatabase  db;
    //MySQLiteOpenHelper helper; //db연동 클래스가 뭐냐 정훈아
    Button btn;
    EditText edt;
    //String searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn = (Button)findViewById(R.id.search_btn);
        edt = (EditText)findViewById(R.id.edtSearch);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //searchTitle = edt.getText().toString();
                //searchTable(searchTitle);

                String searchWord = edt.getText().toString();
                String[] result;

                Query mQuery = new Query(new CallBackListener<String>(){
                    @Override
                    public void onSuccess(String value) {
                        String[] arr = value.split(";");
                        if (arr[0].equals("success")) {
                            edt.setText(arr[1]);
                            Log.v("LOG", "" + value);
                        }
                    }
                }, "select", "select * from `post_open` where title='" + searchWord + "';");
            }
        });
    }
/**
 private void searchTable(String searchTitle){
 //database 객체를 읽기를 해주는 선언문
 db = helper.getReadableDatabase();

 Cursor c = db.rawQuery("Select * from " + searchTitle, null );

 while (c.moveToNext()){
 String title = c.getString(c.getColumnIndex("title"));
 String number = c.getString(c.getColumnIndex("number"));
 int price = c.getInt(c.getColumnIndex("price"));

 println(searchTitle + "제목:\n" + title + "티켓번호:\n" + number + "가격:\n" + price);
 }
 }
 **/
}
