package com.example.ali.iqbreaker3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserDb extends SQLiteOpenHelper {

    public static final String DBName = "myDb";
    public static final int DBVersion = 1;

    public UserDb(Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "Create TABLE Student(name VARCHAR, uname VARCHAR, pass VARCHAR);";
        db.execSQL(userTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
