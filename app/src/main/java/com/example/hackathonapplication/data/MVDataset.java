package com.example.hackathonapplication.data;

public class MVDataset {
    private String exercise;
    private String content;
    private String videoPath;
    private Object thumbnailPath;


    public MVDataset(String exercise, String content, String videoPath, Object thumbnailPath) {
        this.exercise = exercise;
        this.content = content;
        this.videoPath = videoPath;
        this.thumbnailPath = thumbnailPath;
    }

    public String getExercise() {        return exercise;    }
    public String getContent() {        return content;    }
    public String getVideoPath() {        return videoPath;    }
    public Object getThumbnailPath() {        return thumbnailPath;    }

}
