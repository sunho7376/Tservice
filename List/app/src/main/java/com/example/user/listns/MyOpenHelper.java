package com.example.user.listns;


import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**Db 생
 * Created by User on 2016/12/04.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "PostDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Post("
                + "user_id text" + " title text not null,"
                + "perName text not null,"+ "perDate text not null,"
                + "perAddress text,"+ "cost text not null,"+ "link text," +"memo text"
                + "sellout text,"+ "post_datetime text"
                + ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
