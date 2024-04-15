package com.example.capstone;

import com.google.gson.annotations.SerializedName;

public class LogInResponse {
    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}