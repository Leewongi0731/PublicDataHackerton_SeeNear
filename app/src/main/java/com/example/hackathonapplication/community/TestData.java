package com.example.hackathonapplication.community;

import java.util.ArrayList;

public class TestData {

    private Post postData;
    private ArrayList<Post> postArrayList;

    public TestData() {
        postData.setWriter("김노인");
        postData.setDate("2020-12-05");
        postData.setContents("나훈아 개쩔탱;;");

        postArrayList.add(postData);
    }

    public Post getPostData() {
        return postData;
    }

    public void setPostData(Post postData) {
        this.postData = postData;
    }

    public ArrayList<Post> getPostArrayList() {
        return postArrayList;
    }

    public void setPostArrayList(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

//    public void makeTestData() {
//        //postData.setProfile();
//        postData.setWriter("김노인");
//        postData.setDate("2020-12-05");
//        postData.setContents("나훈아 개쩔탱;;");
//
//        postArrayList.add(postData);
//    }


}
