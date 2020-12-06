package com.example.hackathonapplication.sqlite;

import android.provider.BaseColumns;

public class BoardDB {
    public class CreateDB implements BaseColumns {
        public static final String LOCATION = "location";
        public static final String WRITEREMAIL = "writeremail";
        public static final String CATEGORY = "category";
        public static final String CONTENTS = "contents";
        public static final String POSTDATE = "postdate";
        public static final String LIKE = "like";
        public static final String COMMENT = "comment";

        public static final String _TABLENAME = "board";
        public static final String _CREATE_BOARD =
                "create table if not exists " + _TABLENAME + "("
                        + _ID             + " integer primary key autoincrement, "
                        + LOCATION        + " text not null, "
                        + WRITEREMAIL     + " text not null, "
                        + CATEGORY        + " text not null, "
                        + CONTENTS        + " text not null, "
                        + POSTDATE        + " text not null, "
                        + LIKE            + " integer not null, "
                        + COMMENT         + " integer not null);";

    }
}
