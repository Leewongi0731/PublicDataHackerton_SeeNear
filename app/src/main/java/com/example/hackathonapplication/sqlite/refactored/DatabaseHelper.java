package com.example.hackathonapplication.sqlite.refactored;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "refactoredDb";
    private static final int VERSION = 1;
    Scheme scheme;

    public DatabaseHelper(@Nullable Context context, Scheme scheme) {
        super(context, DB_NAME, null, VERSION);
        this.scheme = scheme;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(scheme.createTableIfNotExistQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(scheme.dropTableIfExistQuery());
        onCreate(db);
    }
}
