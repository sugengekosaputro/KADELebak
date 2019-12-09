package com.inspektorat.kadelebak.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RoleEntity implements Serializable {

    @SerializedName("roleId")
    @Expose
    private int roleId;
    @SerializedName("name")
    @Expose
    private String name;
    private final static long serialVersionUID = 6878194172079481627L;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
