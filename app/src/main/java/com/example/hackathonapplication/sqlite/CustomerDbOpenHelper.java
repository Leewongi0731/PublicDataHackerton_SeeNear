package com.example.hackathonapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomerDbOpenHelper {
    private String DATABASE_NAME = "Customer.db";
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
            db.execSQL(CustomerDB.CreateDB._CREATE_CUSTOMER);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + CustomerDB.CreateDB._TABLENAME);
            onCreate(db);
        }
    }

    public CustomerDbOpenHelper(Context context){
        this.context = context;
    }

    public CustomerDbOpenHelper open() throws SQLException {
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

    public long insertColumn(String email, Integer age, Integer height, String gender, String location, String nickname, String medallist, String medaltitle) {
        ContentValues values = new ContentValues();
        values.put(CustomerDB.CreateDB.EMAIL, email);
        values.put(CustomerDB.CreateDB.AGE, age);
        values.put(CustomerDB.CreateDB.HEIGHT, height);
        values.put(CustomerDB.CreateDB.GENDER, gender);
        values.put(CustomerDB.CreateDB.LOCATION, location);
        values.put(CustomerDB.CreateDB.NICKNAME, nickname);
        values.put(CustomerDB.CreateDB.MEDALLIST, medallist);
        values.put(CustomerDB.CreateDB.MEDALTITLE, medaltitle);
        return sqLiteDatabase.insert(CustomerDB.CreateDB._TABLENAME, null, values);
    }

    public Cursor selectColumns() {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + CustomerDB.CreateDB._TABLENAME + ";", null);
        return cursor;
    }

    public Cursor sortColumn(String sort) {
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + CustomerDB.CreateDB._TABLENAME + " ORDER BY " + sort + ";", null);
        return cursor;
    }

    public void updateColumn(String email, Integer age, Integer height, String gender, String location, String nickname, String medallist, String medaltitle) {
        sqLiteDatabase.execSQL("UPDATE customer SET age = '" + age + "', height = '" + height + "', gender = '" + gender + "', location = '" + location + "', nickname = '" + nickname + "', medallist = '" + medallist + "', medaltitle = '" + medaltitle + "'  WHERE email = '" + email + "';");
    }

    public void deleteAllColumns() {
        sqLiteDatabase.delete(CustomerDB.CreateDB._TABLENAME, null, null);
    }

    public void deleteColumn(String email) {
        sqLiteDatabase.execSQL("DELETE FROM '" + CustomerDB.CreateDB._TABLENAME + "' WHERE email = '" + email + "';");
    }
}
