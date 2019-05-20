package com.example.fab;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userinfo.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "email";
    public static final String COL_4 = "password";
    public static final String COL_5 = "address";
    public static final int VERSION = 1;

    Context mContext;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
       /* this.mContext = context;
        SQLiteDatabase db = this.getWritableDatabase();*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ( id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, email, TEXT, password TEXT, address TEXT  )");
//        db.execSQL("CREATE TABLE \"users\" (\n" +
//                "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                "\t\"name\"\tTEXT,\n" +
//                "\t\"email\"\tTEXT,\n" +
//                "\t\"password\"\tTEXT,\n" +
//                "\t\"address\"\tTEXT\n" +
//                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void insertRow() {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COL_1, "1");
        contentValues.put(COL_2, "RAM");
        contentValues.put(COL_3, "ram@gmail.com");
        contentValues.put(COL_4, "1234");
        contentValues.put(COL_5, "Address");

        try {
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        sqLiteDatabase.close();
       // Toast.makeText(mContext, "Inserted", Toast.LENGTH_SHORT).show();
        Log.d("data inserted :","");

    }


    public int DatabaseListSize(){

        ArrayList<DatabaseDTO> dataList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                DatabaseDTO databaseDTO = new DatabaseDTO();
                databaseDTO.setId(cursor.getString(cursor.getColumnIndex(COL_1)));
                databaseDTO.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
                dataList.add(databaseDTO);

            }while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        dataList.size();
        return dataList.size();
    }
}
