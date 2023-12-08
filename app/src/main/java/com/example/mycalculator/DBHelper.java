package com.example.mycalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBname="register.db";
    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(userName TEXT primary Key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");

    }
    public boolean insertData(String userName,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("userName",userName);
        contentValues.put("password",password);
        long result =myDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else return true;
    }

    public boolean checkUserName(String userName){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor= myDB.rawQuery("select * from users where userName=?",new String[]{userName});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkUser(String userName,String psw){
        SQLiteDatabase myDB= this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from users where userName=? and password=?",new String[]{userName,psw});
        if(cursor.getCount()>0){
            return true;
        }
        else return false;
    }
}
