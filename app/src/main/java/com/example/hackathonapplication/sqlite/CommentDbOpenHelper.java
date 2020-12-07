package com.example.hackathonapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CommentDbOpenHelper {
    private String DATABASE_NAME = "Comment.db";
    private int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private CommentDbOpenHelper.DatabaseHelper databaseHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CommentDB.CreateDB._CREATE_COMMENT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CommentDB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public CommentDbOpenHelper(Context context){
        this.context = context;
    }

    public CommentDbOpenHelper open() throws SQLException {
        databaseHelper = new CommentDbOpenHelper.DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        databaseHelper.onCreate(sqLiteDatabase);
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public long insertRow(String writeremail, String boardkey, String contents, String postdate, Integer like) {
        ContentValues values = new ContentValues();
        values.put(CommentDB.CreateDB.WRITEREMAIL, writeremail);
        values.put(CommentDB.CreateDB.BOARDKEY, boardkey);
        values.put(CommentDB.CreateDB.CONTENTS, contents);
        values.put(CommentDB.CreateDB.POSTDATE, postdate);
        values.put(CommentDB.CreateDB.LIKE, like);
        return sqLiteDatabase.insert(CommentDB.CreateDB._TABLENAME, null, values);
    }

    public Cursor selectAllRows() {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + CommentDB.CreateDB._TABLENAME + ";", null);
        return cursor;
    }

    public Cursor selectRow(String column, String value) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM '" + CommentDB.CreateDB._TABLENAME + "' WHERE " + column + " = '" + value +  "';", null);
        return cursor;
    }

    public Cursor orderByColumn(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + CommentDB.CreateDB._TABLENAME + " ORDER BY " + sort + ";", null);
        return cursor;
    }

    public void deleteAllRows() {
        sqLiteDatabase.delete(CommentDB.CreateDB._TABLENAME, null, null);
    }

    public void deleteRow(Integer id) {
        sqLiteDatabase.execSQL("DELETE FROM '" + CommentDB.CreateDB._TABLENAME + "' WHERE _id = '" + id + "';");
    }
}
