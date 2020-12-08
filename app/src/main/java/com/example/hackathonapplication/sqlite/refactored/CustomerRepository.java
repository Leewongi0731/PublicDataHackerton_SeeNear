package com.example.hackathonapplication.sqlite.refactored;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hackathonapplication.model.entity.Customer;

import lombok.Data;

@Data
public class CustomerRepository {

    Context context;
    SqliteTableScheme.CustomerScheme scheme;
    SQLiteDatabase database;

    public CustomerRepository(Context context) {
        this.context = context;
        this.scheme = new SqliteTableScheme.CustomerScheme();
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

    public long insert(String email, Integer age, Integer height, String gender, String location, String nickname, String medallist, String medaltitle) {
        ContentValues values = new ContentValues();

        values.put(scheme.EMAIL, email);
        values.put(scheme.AGE, age);
        values.put(scheme.HEIGHT, height);
        values.put(scheme.GENDER, gender);
        values.put(scheme.LOCATION, location);
        values.put(scheme.NICKNAME, nickname);
        values.put(scheme.MEDALLIST, medallist);
        values.put(scheme.MEDALTITLE, medaltitle);

        return database.insert(scheme.TABLE_NAME, null, values);
    }

    public Customer findByEmail(String email) {
        Cursor c = database.rawQuery(
                    " SELECT * " +
                        " FROM " + scheme.TABLE_NAME +
                        " WHERE " + scheme.EMAIL + " = " + email,
                null);

        Customer result = null;
        while (c.moveToNext()) {
            // todo conver model here
            int age = c.getInt(c.getColumnIndex("age"));
            int height = c.getInt(c.getColumnIndex("height"));
            String gender = c.getString(c.getColumnIndex("gender"));
            String location = c.getString(c.getColumnIndex("location"));
            String nickname = c.getString(c.getColumnIndex("nickname"));
            String medallist = c.getString(c.getColumnIndex("medallist"));
            String medaltitle = c.getString(c.getColumnIndex("medaltitle"));

            result = new Customer( email, age, height, gender, location, nickname, medallist, medaltitle );
        }

        c.close();

        return result;
    }
}
