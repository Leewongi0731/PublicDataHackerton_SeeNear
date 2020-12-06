package com.example.hackathonapplication.sqlite;

import android.provider.BaseColumns;

public class ExercisePrescriptionDB {
    public class CreateDB implements BaseColumns {
        public static final String PRESCRIPTION = "prescription";
        public static final String VIDEOPATH = "videopath";
        public static final String CONTENTS = "contents";

        public static final String _TABLENAME = "exerciseprescription";
        public static final String _CREATE_EXERCISEPRESCRIPTION =
                "create table if not exists " + _TABLENAME + "("
                        + PRESCRIPTION     + " text not null, "
                        + VIDEOPATH        + " text not null, "
                        + CONTENTS         + " text not null);";

    }
}
