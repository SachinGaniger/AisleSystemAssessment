package com.sachin.aislesystemassessment.models;

public class OtpResponse {

    private String token;

    public OtpResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
