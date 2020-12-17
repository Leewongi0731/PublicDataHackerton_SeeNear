package com.example.hackathonapplication.community.board;

public class Comment {
    private String id;
    private String boardkey;
    private String profile;
    private String writer;
    private String badge;
    private String contents;
    private String commentdate;
    private String like;

    public String getBoardkey() {
        return boardkey;
    }

    public void setBoardkey(String boardkey) {
        this.boardkey = boardkey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public Comment(String id, String boardkey, String profile, String writer, String badge, String contents, String commentdate, String like) {
        this.id = id;
        this.profile = profile;
        this.boardkey = boardkey;
        this.writer = writer;
        this.badge = badge;
        this.contents = contents;
        this.commentdate = commentdate;
        this.like = like;
    }
}
