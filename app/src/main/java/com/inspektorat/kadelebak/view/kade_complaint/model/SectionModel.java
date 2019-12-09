package com.inspektorat.kadelebak.view.kade_complaint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SectionModel implements Serializable {

    @SerializedName("sectionId")
    @Expose
    private int sectionId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("employeeList")
    @Expose
    private List<EmployeeList> employeeList = null;
    private final static long serialVersionUID = 3305570212775754250L;

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeList> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeList> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return name;
    }
}
