package com.example.hackathonapplication.sqlite.refactored;

public interface Scheme {
    String createTableIfNotExistQuery();
    String dropTableIfExistQuery();
}
