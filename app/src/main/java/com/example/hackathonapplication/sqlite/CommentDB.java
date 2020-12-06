package com.example.hackathonapplication.sqlite;

import android.provider.BaseColumns;

public class CommentDB {
    public class CreateDB implements BaseColumns {
        public static final String WRITEREMAIL = "writeremail";
        public static final String BOARDKEY = "boardkey";
        public static final String CONTENTS = "contents";
        public static final String POSTDATE = "postdate";
        public static final String LIKE = "like";

        public static final String _TABLENAME = "comment";
        public static final String _CREATE_COMMENT =
                "create table if not exists " + _TABLENAME + "("
                        + _ID               + " integer primary key autoincrement, "
                        + WRITEREMAIL       + " text not null, "
                        + BOARDKEY          + " text not null, "
                        + CONTENTS          + " text not null, "
                        + POSTDATE          + " text not null, "
                        + LIKE              + " integer not null);";

    }
}
