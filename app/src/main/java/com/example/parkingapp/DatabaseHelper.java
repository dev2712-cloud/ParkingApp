package com.example.parkingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key, password TEXT, fullName TEXT, phone TEXT, role TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String email, String password, String fullName, String phone, String role){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("fullName", fullName);
        contentValues.put("phone", phone);
        contentValues.put("role", role);

        long result = MyDB.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    // UPDATED: Now checks 'fullName' instead of 'email'
    public String checkLogin(String fullName, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        // Query changed to look for fullName
        Cursor cursor = MyDB.rawQuery("Select * from users where fullName = ? and password = ?", new String[]{fullName, password});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int roleIndex = cursor.getColumnIndex("role");
            if (roleIndex != -1) {
                return cursor.getString(roleIndex);
            }
        }
        return null;
    }
}