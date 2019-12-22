package com.inspektorat.kadelebak.view.kade_complaint.model;

public class ComplaintCreateModel {

    private String content;
    private int employeeId;
    private int sectionId;
    private boolean anonymous;
    private boolean notify;
    private String dateTime;

    public ComplaintCreateModel(String content, int employeeId, int sectionId, boolean anonymous, boolean notify, String dateTime) {
        this.content = content;
        this.employeeId = employeeId;
        this.sectionId = sectionId;
        this.anonymous = anonymous;
        this.notify = notify;
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
