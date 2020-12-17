package com.example.hackathonapplication.data;

public class JobDataset {
    private Boolean isRecommended;
    private String title;
    private String businessName;
    private String location;
    private String phoneNumber;
    private String startDate;
    private String endDate;
    private String numOfPeople;
    private String isGathering;
    private String pageUrl;

    public JobDataset(Boolean isRecommended, String title, String businessName, String location, String phoneNumber, String startDate, String endDate, String numOfPeople, String isGathering, String pageUrl) {
        this.isRecommended = isRecommended;
        this.title = title;
        this.businessName = businessName;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfPeople = numOfPeople;
        this.isGathering = isGathering;
        this.pageUrl = pageUrl;
    }

    public Boolean getRecommended() {
        return isRecommended;
    }

    public String getTitle() {
        return title;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getLocation() {
        return location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getNumOfPeople() {
        return numOfPeople;
    }

    public String getIsGathering() {
        return isGathering;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
