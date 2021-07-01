package com.sachin.aislesystemassessment.models;

public class LoginResponse {

    private Boolean status;

    public LoginResponse(Boolean status) {
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }
}
