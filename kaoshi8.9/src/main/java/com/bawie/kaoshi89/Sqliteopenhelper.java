package com.bawie.kaoshi89;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/99:18
 */

public class Sqliteopenhelper extends SQLiteOpenHelper {
    public Sqliteopenhelper(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(id integer primary key autoincrement,Imtro varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
