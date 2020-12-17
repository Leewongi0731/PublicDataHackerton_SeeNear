package com.example.hackathonapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    String email;
    int age;
    int height;
    String gender;
    String location;
    String nickname;
    String medallist;
    String medaltitle;

    public Customer(String email, int age, int height, String gender, String location, String nickname, String medallist, String medaltitle) {
        this.email = email;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.location = location;
        this.nickname = nickname;
        this.medallist = medallist;
        this.medaltitle = medaltitle;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMedallist() {
        return medallist;
    }

    public String getMedaltitle() {
        return medaltitle;
    }


}
