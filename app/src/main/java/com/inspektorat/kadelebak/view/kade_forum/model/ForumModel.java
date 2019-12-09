package com.inspektorat.kadelebak.view.kade_forum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ForumModel implements Serializable {

    @SerializedName("forumId")
    @Expose
    private int forumId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("publisher")
    @Expose
    private Publisher publisher;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("notify")
    @Expose
    private boolean notify;
    @SerializedName("commentList")
    @Expose
    private List<CommentList> commentList = null;
    private final static long serialVersionUID = 2200255248506495791L;

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public List<CommentList> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentList> commentList) {
        this.commentList = commentList;
    }

}