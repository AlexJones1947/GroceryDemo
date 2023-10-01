package com.app.grocerydemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDataModel {

    @SerializedName("employeeId")
    @Expose
    private String employeeId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("weekDays")
    @Expose
    private String weekDays;
    @SerializedName("cellData")
    @Expose
    private String cellData;
    @SerializedName("workingHours")
    @Expose
    private String workingHours;
    @SerializedName("breakHours")
    @Expose
    private String breakHours;
    @SerializedName("actualHours")
    @Expose
    private String actualHours;
    @SerializedName("clockInImage")
    @Expose
    private String clockInImage;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private String middleName;

    public Boolean getFace() {
        return isFace;
    }

    public void setFace(Boolean face) {
        isFace = face;
    }

    @SerializedName("isFace")
    @Expose
    private Boolean isFace;

    public String getClockInImage() {
        return clockInImage;
    }

    public void setClockInImage(String clockInImage) {
        this.clockInImage = clockInImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("divisionName")
    @Expose
    private String divisionName;

    public String getActualHours() {
        return actualHours;
    }

    public void setActualHours(String actualHours) {
        this.actualHours = actualHours;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getBreakHours() {
        return breakHours;
    }

    public void setBreakHours(String breakHours) {
        this.breakHours = breakHours;
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }

    public String getCellData() {
        return cellData;
    }

    public void setCellData(String cellData) {
        this.cellData = cellData;
    }


}