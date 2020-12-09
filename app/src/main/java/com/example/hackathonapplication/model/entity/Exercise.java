package com.example.hackathonapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise {
    String prescription;
    String videopath;
    String contents;
    String thumbnailPath;

    public Exercise(String prescription, String videopath, String contents, String thumbnailPath) {
        this.prescription = prescription;
        this.videopath = videopath;
        this.contents = contents;
        this.thumbnailPath = thumbnailPath;
    }

    public String getPrescription() {
        return prescription;
    }

    public String getVideopath() {
        return videopath;
    }

    public String getContents() {
        return contents;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }
}
