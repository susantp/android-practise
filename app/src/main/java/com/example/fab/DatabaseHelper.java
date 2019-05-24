package com.example.fab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "class7am";
    static int version = 1;
    String CreateUserTableSql = "CREATE TABLE IF NOT EXISTS \"user\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"email\"\tTEXT,\n" +
            "\t\"address\"\tTEXT,\n" +
            "\t\"phone\"\tTEXT,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"image\"\tBLOB\n" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(CreateUserTableSql);
    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
    }

    public void updateUser(String id, ContentValues contentValues){
        /* getWritableDatabase().update("user", contentValues, "id="+id, null); */
        getWritableDatabase().update("user", contentValues, "id=?", new String[]{id});
    }

    public void deleteUser(String id){
        getWritableDatabase().delete("user", "id=?", new String[]{id});
    }

    public ArrayList<UserInfo> getUserList() {
        String sql = "Select * from user";

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        ArrayList<UserInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfo info = new UserInfo();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            info.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            info.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            info.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            info.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            info.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            info.setImage(cursor.getBlob(cursor.getColumnIndex("image")));

            list.add(info);
        }
        cursor.close();
        return list;
    }

    public UserInfo getUserInfo(String id) {
        String sql = "Select * from user where id=" + id;

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        UserInfo info = new UserInfo();
        while (cursor.moveToNext()) {
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            info.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            info.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            info.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            info.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            info.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            info.setImage(cursor.getBlob(cursor.getColumnIndex("image")));

        }
        cursor.close();
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
