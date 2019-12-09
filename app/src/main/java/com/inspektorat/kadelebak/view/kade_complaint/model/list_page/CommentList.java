package com.inspektorat.kadelebak.view.kade_complaint.model.list_page;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CommentList implements Serializable {

    @SerializedName("idComplaintDetail")
    @Expose
    private int idComplaintDetail;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    private final static long serialVersionUID = 8229190155833416800L;

    public int getIdComplaintDetail() {
        return idComplaintDetail;
    }

    public void setIdComplaintDetail(int idComplaintDetail) {
        this.idComplaintDetail = idComplaintDetail;
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
