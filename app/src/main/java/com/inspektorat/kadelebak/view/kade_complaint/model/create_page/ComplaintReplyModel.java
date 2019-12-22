package com.inspektorat.kadelebak.view.kade_complaint.model.create_page;

public class ComplaintReplyModel {

    private int complaintId;
    private String comment;
    private int employeeId;
    private String dateTime;

    public ComplaintReplyModel(int complaintId, String comment, int employeeId, String dateTime) {
        this.complaintId = complaintId;
        this.comment = comment;
        this.employeeId = employeeId;
        this.dateTime = dateTime;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
