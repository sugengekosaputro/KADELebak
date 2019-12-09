package com.inspektorat.kadelebak.view.kade_forum.model;

public class ForumReplyModel {
    private int forumId;
    private String comment;
    private int employeeId;

    public ForumReplyModel(int forumId, String comment, int employeeId) {
        this.forumId = forumId;
        this.comment = comment;
        this.employeeId = employeeId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
