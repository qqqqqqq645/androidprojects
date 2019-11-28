package com.example.a210.dbconnectexample;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {

    SQLiteDatabase sqltedb;
    DBManager dbmanager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setTitle("등록");

        Intent it = getIntent();
        String str_name = it.getStringExtra("it_name");
        String str_gender = it.getStringExtra("it_gender");
        String str_phone = it.getStringExtra("it_phone");
        String str_location = it.getStringExtra("it_location");

        try {
            dbmanager = new DBManager(this);
            sqltedb = dbmanager.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name",str_name);
            values.put("name",str_gender);
            values.put("name",str_phone);
            values.put("name",str_location);
            long newRowId = sqltedb.insert("customers",null,values);
            sqltedb.close();
            dbmanager.close();

            if(newRowId != -1) {
                TextView tv_name = (TextView)findViewById(R.id.name);
                TextView tv_gender = (TextView)findViewById(R.id.gender);
                TextView tv_phone = (TextView)findViewById(R.id.r_phone);
                TextView tv_location = (TextView)findViewById(R.id.r_location);

            } else {
                Toast.makeText(this,"등록 실패!", Toast.LENGTH_LONG).show();
            }
        }   catch(SQLiteException e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG)show();
        }
    }
}
