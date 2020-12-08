package com.example.hackathonapplication.sqlite.refactored;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hackathonapplication.model.entity.Customer;
import com.example.hackathonapplication.model.entity.Medal;

public class MedalRepository {
    Context context;
    SqliteTableScheme.MedalScheme scheme;
    SQLiteDatabase database;

    public MedalRepository(Context context) {
        this.context = context;
        this.scheme = new SqliteTableScheme.MedalScheme();
    }

    public void connect() {
        DatabaseHelper databaseHelper = new DatabaseHelper(context, scheme);
        SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
        databaseHelper.onCreate(writableDatabase);
        this.database = writableDatabase;
    }

    public void close() {
        this.database.close();
    }

    public long insert(String title, String imagepath) {
        ContentValues values = new ContentValues();

        values.put(scheme.TITLE, title);
        values.put(scheme.IMAGEPATH, imagepath);

        return database.insert(scheme.TABLE_NAME, null, values);
    }

    public Medal findByTitle(String title) {
        Cursor c = database.rawQuery(
                " SELECT * " +
                        " FROM " + scheme.TABLE_NAME +
                        " WHERE " + scheme.TITLE + " = " + title,
                null);

        Medal result = null;
        while (c.moveToNext()) {
            // todo conver model here
            String imagepath = c.getString(c.getColumnIndex("imagepath"));
            result = new Medal( title, imagepath );
        }

        c.close();

        return result;
    }
}
