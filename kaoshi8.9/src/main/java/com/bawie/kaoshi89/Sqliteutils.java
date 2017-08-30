package com.bawie.kaoshi89;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/8/99:22
 */

public class Sqliteutils {
    private static SQLiteDatabase db;
    public Sqliteutils(Context context){
        Sqliteopenhelper helper = new Sqliteopenhelper(context);
        db = helper.getReadableDatabase();
    }
    public void add(String Imtro){
        ContentValues values = new ContentValues();
        values.put("Imtro",Imtro);
        db.insert("person",null,values);
    }
}
