package com.example.hackathonapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BoardDbOpenHelper {
    private String DATABASE_NAME = "Board.db";
    private int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(BoardDB.CreateDB._CREATE_BOARD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + BoardDB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public BoardDbOpenHelper(Context context){
        this.context = context;
    }

    public BoardDbOpenHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        databaseHelper.onCreate(sqLiteDatabase);
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public long insertColumn(String location, String writeremail, String category, String title, String contents, String postdate, String modifydate, Integer like, Integer comment) {
        ContentValues values = new ContentValues();
        values.put(BoardDB.CreateDB.LOCATION, location);
        values.put(BoardDB.CreateDB.WRITEREMAIL, writeremail);
        values.put(BoardDB.CreateDB.CATEGORY, category);
        values.put(BoardDB.CreateDB.TITLE, title);
        values.put(BoardDB.CreateDB.LOCATION, location);
        values.put(BoardDB.CreateDB.CONTENTS, contents);
        values.put(BoardDB.CreateDB.POSTDATE, postdate);
        values.put(BoardDB.CreateDB.MODIFYDATE, modifydate);
        values.put(BoardDB.CreateDB.LIKE, like);
        values.put(BoardDB.CreateDB.COMMENT, comment);
        return sqLiteDatabase.insert(BoardDB.CreateDB._TABLENAME, null, values);
    }

    public Cursor sortColumn(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + " ORDER BY " + sort + ";", null);
        return cursor;
    }

    public Cursor selectColumns() {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + ";", null);
        return cursor;
    }

    public void deleteAllColumns() {
        sqLiteDatabase.delete(BoardDB.CreateDB._TABLENAME, null, null);
    }

    public void deleteColumn(Integer id) {
        sqLiteDatabase.execSQL("DELETE FROM '" + BoardDB.CreateDB._TABLENAME + "' WHERE _id = '" + id + "';");
    }
}
