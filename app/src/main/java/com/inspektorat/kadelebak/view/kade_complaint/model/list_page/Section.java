package com.inspektorat.kadelebak.view.kade_complaint.model.list_page;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Section implements Serializable {

    @SerializedName("sectionId")
    @Expose
    private int sectionId;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 1918944750618397237L;

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

}
