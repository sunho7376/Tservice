package com.example.user.listns;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by User on 2016/12/04.
 */

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        MyOpenHelper helper = new MyOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        final Cursor c;
        try {
            // query method 실행 (rowQqueue method)
            ///*
            c = db.query("Post", new String[]{"title", "cost", "sellout"}, null,
                    null, null, null, null);
            //*/

            boolean mov = c.moveToFirst();
            while (mov) {
                TextView textView = new TextView(this);
                textView.setText(String.format("%s : %s원 :판매완료여부 %s ", c.getString(0),
                        c.getString(1), c.getString(2)));
                mov = c.moveToNext();
                layout.addView(textView);
                c.close();
            }
        }finally {
            db.close();
        }
    }
}
