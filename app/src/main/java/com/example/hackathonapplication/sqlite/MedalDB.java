package com.example.hackathonapplication.sqlite;

import android.provider.BaseColumns;

public class MedalDB {
    public class CreateDB implements BaseColumns {
        public static final String TITLE = "title";
        public static final String IMAGEPATH = "imagepath";

        public static final String _TABLENAME = "medal";
        public static final String _CREATE_MEDAL =
                "create table if not exists " + _TABLENAME + "("
                        + TITLE      + " text not null, "
                        + IMAGEPATH + " text not null);";
    }
}
