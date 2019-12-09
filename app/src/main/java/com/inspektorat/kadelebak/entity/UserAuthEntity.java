package com.inspektorat.kadelebak.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserAuthEntity implements Serializable {

    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("employee")
    @Expose
    private EmployeeEntity employee;
    @SerializedName("active")
    @Expose
    private boolean active;
    private final static long serialVersionUID = -1361876509107903206L;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
