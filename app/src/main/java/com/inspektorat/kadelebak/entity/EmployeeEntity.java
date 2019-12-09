package com.inspektorat.kadelebak.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EmployeeEntity implements Serializable {

    @SerializedName("employeeId")
    @Expose
    private int employeeId;
    @SerializedName("nip")
    @Expose
    private String nip;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("prefixTitle")
    @Expose
    private String prefixTitle;
    @SerializedName("suffixTitle")
    @Expose
    private String suffixTitle;
    @SerializedName("bornPlaces")
    @Expose
    private String bornPlaces;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("institution")
    @Expose
    private InstitutionEntity institution;
    @SerializedName("position")
    @Expose
    private PositionEntity position;
    @SerializedName("role")
    @Expose
    private RoleEntity role;
    @SerializedName("section")
    @Expose
    private SectionEntity section;
    private final static long serialVersionUID = -1570470978303137875L;

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

    public String getPrefixTitle() {
        return prefixTitle;
    }

    public void setPrefixTitle(String prefixTitle) {
        this.prefixTitle = prefixTitle;
    }

    public String getSuffixTitle() {
        return suffixTitle;
    }

    public void setSuffixTitle(String suffixTitle) {
        this.suffixTitle = suffixTitle;
    }

    public String getBornPlaces() {
        return bornPlaces;
    }

    public void setBornPlaces(String bornPlaces) {
        this.bornPlaces = bornPlaces;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstitutionEntity getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionEntity institution) {
        this.institution = institution;
    }

    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public SectionEntity getSection() {
        return section;
    }

    public void setSection(SectionEntity section) {
        this.section = section;
    }
}
