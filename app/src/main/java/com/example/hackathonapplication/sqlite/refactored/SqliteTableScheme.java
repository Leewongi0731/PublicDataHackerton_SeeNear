package com.example.hackathonapplication.sqlite.refactored;

import android.provider.BaseColumns;

/**
 * SqLite Database Schema Class
 *
 * 안드로이드 내부에서 사용할 db scheme 정보들
 *
 * @author UiSeok
 * @since 2020.12.08
 */
public class SqliteTableScheme {

    /**
     * Customer Table
     *
     */
    public static class CustomerScheme implements BaseColumns, Scheme {
        public static final String TABLE_NAME = "Customer";
        public static final String EMAIL = "email";
        public static final String AGE = "age";
        public static final String HEIGHT = "height";
        public static final String GENDER = "gender";
        public static final String LOCATION = "location";
        public static final String NICKNAME = "nickname";
        public static final String MEDALLIST = "medallist";
        public static final String MEDALTITLE = "medaltitle";

        @Override
        public String createTableIfNotExistQuery() {
            return "CREATE TABLE IF NOT EXISTS " + CustomerScheme.TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY,"
                    + EMAIL + " text not null, "
                    + AGE + " integer not null, "
                    + HEIGHT + " integer not null, "
                    + GENDER + " text not null, "
                    + LOCATION + " text not null, "
                    + NICKNAME + " text not null, "
                    + MEDALLIST + " text not null, "
                    + MEDALTITLE + " text not null);";
        }

        @Override
        public String dropTableIfExistQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
    }

    /**
     * Comment Table
     *
     */
    public static class CommentScheme implements BaseColumns, Scheme {
        public static final String TABLE_NAME = "Comment";
        public static final String WRITER_EMAIL = "writeremail";
        public static final String BOARD_KEY = "boardkey";
        public static final String CONTENTS = "contents";
        public static final String POSTDATE = "postdate";
        public static final String LIKE = "like";

        @Override
        public String createTableIfNotExistQuery() {
            return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID               + " integer primary key autoincrement, "
                    + WRITER_EMAIL + " text not null, "
                    + BOARD_KEY + " text not null, "
                    + CONTENTS          + " text not null, "
                    + POSTDATE          + " text not null, "
                    + LIKE              + " integer not null);";
        }

        @Override
        public String dropTableIfExistQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
    }


    /**
     * Exercise Table
     *
     */
    public static class ExerciseScheme implements BaseColumns, Scheme {
        public static final String TABLE_NAME = "Exercise";
        public static final String PRESCRIPTION = "prescription";
        public static final String VIDEOPATH = "videopath";
        public static final String CONTENTS = "contents";
        public static final String THUMBNAILPATH = "thumbnailPath";

        @Override
        public String createTableIfNotExistQuery() {
            return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID               + " integer primary key autoincrement, "
                    + PRESCRIPTION + " text not null, "
                    + VIDEOPATH + " text not null, "
                    + CONTENTS          + " text not null, "
                    + THUMBNAILPATH    + " integer not null);";
        }

        @Override
        public String dropTableIfExistQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
    }


    /**
     * Medal Table
     *
     */
    public static class MedalScheme implements BaseColumns, Scheme {
        public static final String TABLE_NAME = "Medal";
        public static final String TITLE = "title";
        public static final String IMAGEPATH = "imagepath";

        @Override
        public String createTableIfNotExistQuery() {
            return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID               + " integer primary key autoincrement, "
                    + TITLE + " text not null, "
                    + IMAGEPATH + " text not null); ";
        }

        @Override
        public String dropTableIfExistQuery() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
    }






}
