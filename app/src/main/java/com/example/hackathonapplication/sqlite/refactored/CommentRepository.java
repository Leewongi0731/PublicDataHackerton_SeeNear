package com.example.hackathonapplication.sqlite.refactored;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hackathonapplication.model.entity.CommentTmp;

import java.util.ArrayList;
import java.util.List;

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

    public List<CommentTmp> findByEmail(String writeremail) {
        Cursor c = database.rawQuery(
                    " SELECT * " +
                        " FROM " + scheme.TABLE_NAME +
                        " WHERE " + scheme.WRITER_EMAIL + " = " + writeremail,
                null);

        ArrayList<CommentTmp> result = null;
        String boardkey;
        String contents;
        String postdate;
        int like;

        while (c.moveToNext()) {
            // todo conver model here
            boardkey = c.getString(c.getColumnIndex("boardkey"));
            contents = c.getString(c.getColumnIndex("contents"));
            postdate = c.getString(c.getColumnIndex("postdate"));
            like = c.getInt(c.getColumnIndex("like"));
            result.add( new CommentTmp( writeremail, boardkey, contents, postdate,like ) );
        }

        c.close();

        return result;
    }

    public List<CommentTmp> findByBoardkey(String boardkey) {
        Cursor c = database.rawQuery(
                " SELECT * " +
                        " FROM " + scheme.TABLE_NAME +
                        " WHERE " + scheme.BOARD_KEY + " = " + boardkey,
                null);

        ArrayList<CommentTmp> result = null;
        String writeremail;
        String contents;
        String postdate;
        int like;

        while (c.moveToNext()) {
            // todo conver model here
            writeremail = c.getString(c.getColumnIndex("writeremail"));
            contents = c.getString(c.getColumnIndex("contents"));
            postdate = c.getString(c.getColumnIndex("postdate"));
            like = c.getInt(c.getColumnIndex("like"));
            result.add( new CommentTmp( writeremail, boardkey, contents, postdate,like ) );
        }

        c.close();

        return result;
    }
}
