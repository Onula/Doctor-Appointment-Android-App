package com.example.r3.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreatePatient extends SQLiteOpenHelper {
    public CreatePatient(Context c){
        super(c, "CreateP.db", null,1);

    }
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String sql = "CREATE TABLE selections ( name TEXT, lastname TEXT, address TEXT, AMKA PRIMARY KEY)";
        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS selections";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }


}
