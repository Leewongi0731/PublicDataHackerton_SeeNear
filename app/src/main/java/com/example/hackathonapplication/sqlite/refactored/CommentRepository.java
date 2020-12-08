package com.example.hackathonapplication.sqlite.refactored;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import lombok.Data;

@Data
public class CommentRepository {

    Context context;
    SqliteTableScheme.CommentScheme scheme;
    SQLiteDatabase database;

    public CommentRepository(Context context) {
        this.context = context;
        this.scheme = new SqliteTableScheme.CommentScheme();
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

    public long insert(String writeremail, String boardkey, String contents, String postdate, Integer like) {
        ContentValues values = new ContentValues();

        values.put(scheme.WRITER_EMAIL, writeremail);
        values.put(scheme.BOARD_KEY, boardkey);
        values.put(scheme.CONTENTS, contents);
        values.put(scheme.POSTDATE, postdate);
        values.put(scheme.LIKE, like);

        return database.insert(scheme.TABLE_NAME, null, values);
    }

    public Object findByEmail(String email) {
        Cursor c = database.rawQuery(
                    " SELECT * " +
                        " FROM " + scheme.TABLE_NAME +
                        " WHERE " + scheme.WRITER_EMAIL + " = " + email,
                null);

        Object result = null;
        while (c.moveToNext()) {
            // todo conver model here
        }

        c.close();

        return result;
    }
}
