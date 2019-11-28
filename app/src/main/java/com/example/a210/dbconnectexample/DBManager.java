package com.example.a210.dbconnectexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context) { super(context,"myDB",null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table customers ( name text, gender text, phone text, location text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
