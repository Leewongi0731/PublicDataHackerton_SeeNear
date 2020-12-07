package com.example.hackathonapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExercisePrescriptionDbOpenHelper {
    private String DATABASE_NAME = "ExercisePrescription.db";
    private int DATABASE_VERSION = 1;
    private SQLiteDatabase sqLiteDatabase;
    private ExercisePrescriptionDbOpenHelper.DatabaseHelper databaseHelper;
    private Context context;

    private class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ExercisePrescriptionDB.CreateDB._CREATE_EXERCISEPRESCRIPTION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + ExercisePrescriptionDB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public ExercisePrescriptionDbOpenHelper(Context context){
        this.context = context;
    }

    public ExercisePrescriptionDbOpenHelper open() throws SQLException {
        databaseHelper = new ExercisePrescriptionDbOpenHelper.DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        databaseHelper.onCreate(sqLiteDatabase);
    }

    public void close(){
        sqLiteDatabase.close();
    }

    public long insertRow(String prescription, String videopath, String contents) {
        ContentValues values = new ContentValues();
        values.put(ExercisePrescriptionDB.CreateDB.PRESCRIPTION, prescription);
        values.put(ExercisePrescriptionDB.CreateDB.VIDEOPATH, videopath);
        values.put(ExercisePrescriptionDB.CreateDB.CONTENTS, contents);
        return sqLiteDatabase.insert(ExercisePrescriptionDB.CreateDB._TABLENAME, null, values);
    }

    public Cursor selectAllRows() {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + ExercisePrescriptionDB.CreateDB._TABLENAME + ";", null);
        return cursor;
    }

    public Cursor selectRow(String column, String value) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM '" + ExercisePrescriptionDB.CreateDB._TABLENAME + "' WHERE " + column + " = '" + value +  "';", null);
        return cursor;
    }

    public Cursor orderByColumn(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + ExercisePrescriptionDB.CreateDB._TABLENAME + " ORDER BY " + sort + ";", null);
        return cursor;
    }

    public void deleteAllRows() {
        sqLiteDatabase.delete(ExercisePrescriptionDB.CreateDB._TABLENAME, null, null);
    }

    public void deleteRow(String prescription) {
        sqLiteDatabase.execSQL("DELETE FROM '" + ExercisePrescriptionDB.CreateDB._TABLENAME + "' WHERE prescription = '" + prescription + "';");
    }
}
