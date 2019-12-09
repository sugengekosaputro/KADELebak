package com.inspektorat.kadelebak.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegionEntity implements Serializable {

    @SerializedName("regionId")
    @Expose
    private int regionId;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 7911445351295003957L;

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
