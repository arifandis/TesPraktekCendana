package com.digietos.tespraktekcendana;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.digietos.tespraktekcendana.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE = "create table user (id integer primary key, name text, username text, email text);";
    private static final String GET_USERS_SQL = "select*from user";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (userid integer primary key, name text, username text, email text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        onCreate(db);
    }

    public void addDataUser(List<User> users){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (User user : users) {
            Log.d("User",user.toString());

            values.put("userid",user.getId());
            values.put("name",user.getName());
            values.put("username",user.getUsername());
            values.put("email",user.getEmail());
            db.insert("user",null,values);
        }
        db.close();
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_USERS_SQL,null);
        User user;
        while (cursor.moveToNext()){
            user = new User();
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("userid"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));

            user.setId(id);
            user.setName(name);
            user.setUsername(username);
            user.setEmail(email);
            users.add(user);
        }

        return users;
    }
}
