package com.inspektorat.kadelebak.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PositionEntity implements Serializable {

    @SerializedName("positionId")
    @Expose
    private int positionId;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = -8949665805910321341L;

    public PositionEntity(int positionId, String name) {
        this.positionId = positionId;
        this.name = name;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
