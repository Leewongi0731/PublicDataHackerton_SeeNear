package com.example.hackathonapplication.sqlite;

import android.provider.BaseColumns;

public class CustomerDB {
       public class CreateDB implements BaseColumns {
           public static final String EMAIL = "email";
           public static final String AGE = "age";
           public static final String HEIGHT = "height";
           public static final String GENDER = "gender";
           public static final String LOCATION = "location";
           public static final String NICKNAME = "nickname";
           public static final String MEDALLIST = "medallist";
           public static final String MEDALTITLE = "medaltitle";

           public static final String _TABLENAME = "customer";
           public static final String _CREATE_CUSTOMER =
                   "create table if not exists " + _TABLENAME + "("
                           + EMAIL      + " text primary key, "
                           + AGE        + " integer not null, "
                           + HEIGHT     + " integer not null, "
                           + GENDER     + " text not null, "
                           + LOCATION   + " text not null, "
                           + NICKNAME   + " text not null, "
                           + MEDALLIST  + " text not null, "
                           + MEDALTITLE + " text not null);";

       }
}
