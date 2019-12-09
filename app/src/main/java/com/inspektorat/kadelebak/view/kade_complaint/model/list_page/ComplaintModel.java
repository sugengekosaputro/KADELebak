package com.inspektorat.kadelebak.view.kade_complaint.model.list_page;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ComplaintModel implements Serializable {

    @SerializedName("complaintId")
    @Expose
    private int complaintId;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("publisher")
    @Expose
    private Publisher publisher;
    @SerializedName("sectionId")
    @Expose
    private SectionId sectionId;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("anonymous")
    @Expose
    private boolean anonymous;
    @SerializedName("notify")
    @Expose
    private boolean notify;
    @SerializedName("commentList")
    @Expose
    private List<CommentList> commentList = null;
    private final static long serialVersionUID = 5266886715362713108L;

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
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

    public SectionId getSectionId() {
        return sectionId;
    }

    public void setSectionId(SectionId sectionId) {
        this.sectionId = sectionId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
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
