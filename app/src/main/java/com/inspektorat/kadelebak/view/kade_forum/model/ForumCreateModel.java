package com.inspektorat.kadelebak.view.kade_forum.model;

public class ForumCreateModel {
    private String content;
    private int employeeId;
    private boolean notify;

    public ForumCreateModel(String content, int employeeId, boolean notify) {
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

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
