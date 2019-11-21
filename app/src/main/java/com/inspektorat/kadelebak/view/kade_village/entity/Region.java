package com.inspektorat.kadelebak.view.kade_village.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Region implements Serializable {

    @SerializedName("regionId")
    @Expose
    private String regionId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
