package com.inspektorat.kadelebak.view.kade_forum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentList implements Serializable {
    @SerializedName("idForumDetail")
    @Expose
    private int idForumDetail;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    private final static long serialVersionUID = -152262001524179059L;

    public int getIdForumDetail() {
        return idForumDetail;
    }

    public void setIdForumDetail(int idForumDetail) {
        this.idForumDetail = idForumDetail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

}
