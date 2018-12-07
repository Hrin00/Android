package com.example.mashiro.experiment4.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_RECORD = "create table RECORD ("
            + "rid integer primary key autoincrement, "
            + "typeid integer, "
            + "time integer, "
            + "money real) ";

    public static final String CREATE_TYPE = "create table TYPE ("
            + "typeid integer primary key autoincrement, "
            + "name text, "
            + "img integer) ";


    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECORD);
        db.execSQL(CREATE_TYPE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}