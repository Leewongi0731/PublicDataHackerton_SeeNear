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

    public long insertColumn(String location, String writeremail, String profile, String writer, String category, String contents, String postdate, Integer like, Integer comment) {
        ContentValues values = new ContentValues();
        values.put(BoardDB.CreateDB.LOCATION, location);
        values.put(BoardDB.CreateDB.WRITEREMAIL, writeremail);
        values.put(BoardDB.CreateDB.PROFILE,profile);
        values.put(BoardDB.CreateDB.WRITER,writer);
        values.put(BoardDB.CreateDB.CATEGORY, category);
        values.put(BoardDB.CreateDB.LOCATION, location);
        values.put(BoardDB.CreateDB.CONTENTS, contents);
        values.put(BoardDB.CreateDB.POSTDATE, postdate);
        values.put(BoardDB.CreateDB.LIKE, like);
        values.put(BoardDB.CreateDB.COMMENT, comment);
        return sqLiteDatabase.insert(BoardDB.CreateDB._TABLENAME, null, values);
    }



    public Cursor sortColumn(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + " ORDER BY " + sort + ";", null);
        return cursor;
    }

    public Cursor sortColumnDesc(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + " ORDER BY " + sort + " DESC;", null);
        return cursor;
    }

    public Cursor selectColumns() {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + ";", null);
        return cursor;
    }

    public Cursor searchColumns(String columnName, String search) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + " WHERE " + columnName + " = " + search + ";", null);
        return cursor;
    }

    public Cursor searchColumnsDesc(String columnName, String search, String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + BoardDB.CreateDB._TABLENAME + " WHERE " + columnName + " = " + search + " ORDER BY " + sort + " DESC;", null);
        return cursor;
    }

    public void deleteAllColumns() {
        sqLiteDatabase.delete(BoardDB.CreateDB._TABLENAME, null, null);
    }

    public void updateColumn(String conditionColumn, String conditionValue, String updateColumn, String columnValue) {
        sqLiteDatabase.execSQL("UPDATE " + BoardDB.CreateDB._TABLENAME +" SET " + updateColumn + " = "+columnValue + " WHERE " + conditionColumn + " = " + conditionValue + " ; ");
    }

    public void updateColumnInt(String conditionColumn, String conditionValue, String updateColumn, Integer columnValue) {
        sqLiteDatabase.execSQL("UPDATE " + BoardDB.CreateDB._TABLENAME +" SET " + updateColumn + " = "+columnValue + " WHERE " + conditionColumn + " = " + conditionValue + " ; ");
    }

    public void deleteColumn(Integer id) {
        sqLiteDatabase.execSQL("DELETE FROM '" + BoardDB.CreateDB._TABLENAME + "' WHERE _id = '" + id + "';");
    }
}
