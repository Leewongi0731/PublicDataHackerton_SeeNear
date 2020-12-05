package com.example.hackathonapplication.data;

public class MVDataset {
    private String exercise;
    private String content;
    private Object thumbnailPath;

    public MVDataset(String exercise, String content, Object thumbnailPath) {
        this.exercise = exercise;
        this.content = content;
        this.thumbnailPath = thumbnailPath;
    }

    public String getExercise() {        return exercise;    }
    public String getContent() {        return content;    }
    public Object getThumbnailPath() {        return thumbnailPath;    }

}
