package com.app.grocerydemo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginMainModel {

    @SerializedName("resultStatus")
    @Expose
    private Boolean resultStatus;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private LoginDataModel loginDataModel;
    @SerializedName("errors")
    @Expose
    private Object errors;

    public Boolean getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Boolean resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginDataModel getData() {
        return loginDataModel;
    }

    public void setData(LoginDataModel loginDataModel) {
        this.loginDataModel = loginDataModel;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

}