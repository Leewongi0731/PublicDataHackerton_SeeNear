package com.example.hackathonapplication.data;

public class CertificatesDataset {
    String userName;
    String eduTitle;
    String startDay;
    String ednDay;
    String giveDay;

    public CertificatesDataset(String userName, String eduTitle, String startDay, String ednDay, String giveDay) {
        this.userName = userName;
        this.eduTitle = eduTitle;
        this.startDay = startDay;
        this.ednDay = ednDay;
        this.giveDay = giveDay;
    }

    public String getUserName() {
        return userName;
    }

    public String getEduTitle() {
        return eduTitle;
    }

    public String getStartDay() {
        return startDay;
    }

    public String getEdnDay() {
        return ednDay;
    }

    public String getGiveDay() {
        return giveDay;
    }
}
