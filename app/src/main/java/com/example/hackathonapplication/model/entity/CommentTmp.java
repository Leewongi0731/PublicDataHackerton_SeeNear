package com.example.hackathonapplication.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentTmp {
    String writeremail;
    String boardkey;
    String contents;
    String postdate;
    int like;

    public CommentTmp(String writeremail, String boardkey, String contents, String postdate, int like) {
        this.writeremail = writeremail;
        this.boardkey = boardkey;
        this.contents = contents;
        this.postdate = postdate;
        this.like = like;
    }

    public String getWriteremail() {
        return writeremail;
    }

    public String getBoardkey() {
        return boardkey;
    }

    public String getContents() {
        return contents;
    }

    public String getPostdate() {
        return postdate;
    }

    public int getLike() {
        return like;
    }
}
