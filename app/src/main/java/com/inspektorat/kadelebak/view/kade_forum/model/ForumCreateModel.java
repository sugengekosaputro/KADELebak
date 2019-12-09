package com.inspektorat.kadelebak.view.kade_forum.model;

public class ForumCreateModel {
    private String content;
    private int employeeId;
    private int notify;

    public ForumCreateModel(String content, int employeeId, int notify) {
        this.content = content;
        this.employeeId = employeeId;
        this.notify = notify;
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

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }
}
