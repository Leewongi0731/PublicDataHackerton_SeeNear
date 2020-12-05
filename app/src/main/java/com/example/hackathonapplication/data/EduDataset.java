package com.example.hackathonapplication.data;

public class EduDataset {
    private String campus;
    private String isRegister;
    private String content;
    private String gatherDate;
    private String eduDate;
    private String plusUrl;

    public EduDataset(String campus, String isRegister, String content, String gatherDate, String eduDate, String plusUrl) {
        this.campus = campus;
        this.isRegister = isRegister;
        this.content = content;
        this.gatherDate = gatherDate;
        this.eduDate = eduDate;
        this.plusUrl = plusUrl;
    }

    public String getIsRegister() {
        return isRegister;
    }

    public String getContent() {
        return content;
    }

    public String getCampus() {
        return campus;
    }

    public String getPlusUrl() {
        return plusUrl;
    }

    public String getGatherDate() {
        return gatherDate;
    }

    public String getEduDate() {
        return eduDate;
    }
}
