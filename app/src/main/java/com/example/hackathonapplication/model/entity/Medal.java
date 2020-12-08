package com.example.hackathonapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Medal {
    String title;
    String imagepath;

    public Medal(String title, String imagepath) {
        this.title = title;
        this.imagepath = imagepath;
    }

    public String getTitle() {
        return title;
    }

    public String getImagepath() {
        return imagepath;
    }
}
