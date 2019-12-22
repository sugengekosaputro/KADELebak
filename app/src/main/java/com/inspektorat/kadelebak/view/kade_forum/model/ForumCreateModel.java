package com.inspektorat.kadelebak.view.kade_forum.model;

public class ForumCreateModel {
    private String content;
    private int employeeId;
    private boolean notify;
    private String dateTime;

    public ForumCreateModel(String content, int employeeId, boolean notify, String dateTime) {
        this.content = content;
        this.employeeId = employeeId;
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
