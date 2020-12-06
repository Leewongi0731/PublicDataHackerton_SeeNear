package com.example.hackathonapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MedalDbOpenHelper {
    private String DATABASE_NAME = "Medal.db";
    private int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private MedalDbOpenHelper.DatabaseHelper databaseHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(MedalDB.CreateDB._CREATE_MEDAL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + MedalDB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public MedalDbOpenHelper(Context context){
        this.context = context;
    }

    public MedalDbOpenHelper open() throws SQLException {
        databaseHelper = new MedalDbOpenHelper.DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        databaseHelper.onCreate(sqLiteDatabase);
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public long insertColumn(String title, String imagepath) {
        ContentValues values = new ContentValues();
        values.put(MedalDB.CreateDB.TITLE, title);
        values.put(MedalDB.CreateDB.IMAGEPATH, imagepath);
        return sqLiteDatabase.insert(MedalDB.CreateDB._TABLENAME, null, values);
    }

    public Cursor sortColumn(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + MedalDB.CreateDB._TABLENAME + " ORDER BY " + sort + ";", null);
        return cursor;
    }

    public Cursor selectColumns() {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + MedalDB.CreateDB._TABLENAME + ";", null);
        return cursor;
    }

    public void deleteAllColumns() {
        sqLiteDatabase.delete(MedalDB.CreateDB._TABLENAME, null, null);
    }

    public void deleteColumn(String title) {
        sqLiteDatabase.execSQL("DELETE FROM '" + MedalDB.CreateDB._TABLENAME + "' WHERE title = '" + title + "';");
    }
}
