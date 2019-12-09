package com.inspektorat.kadelebak.view.kade_forum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sender implements Serializable {

    @SerializedName("employeeId")
    @Expose
    private int employeeId;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("section")
    @Expose
    private final static long serialVersionUID = -3033585376383635848L;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
