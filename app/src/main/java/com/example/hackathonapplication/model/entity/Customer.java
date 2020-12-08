package com.example.hackathonapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    long id;
    String writeremail;
    String boardkey;
    String contents;
    String postdate;
    int like;
}
