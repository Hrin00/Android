package com.example.mashiro.experiment4.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.mashiro.experiment4.R;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_RECORD = "create table RECORD ("
            + "rid integer primary key autoincrement, "
            + "typeid integer, "
            + "time text, "
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
        ContentValues values = new ContentValues();

        values.put("typeid",1);values.put("name","餐饮食品");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",2);values.put("name","衣服饰品");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",3);values.put("name","居家生活");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",4);values.put("name","行车交通");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",5);values.put("name","文化教育");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",6);values.put("name","健康医疗");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",7);values.put("name","投资支出");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",8);values.put("name","其他支出");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",9);values.put("name","职业收入");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        values.put("typeid",10);values.put("name","业余收入");values.put("img",R.mipmap.ic_launcher_round);db.insert("TYPE",null,values);
        Toast.makeText(mContext,"Yijidb has been created",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}