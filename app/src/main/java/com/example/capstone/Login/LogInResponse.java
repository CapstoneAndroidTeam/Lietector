package com.example.capstone.Login;

import com.google.gson.annotations.SerializedName;

public class LogInResponse {
    @SerializedName("message")
    private  String message;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
   private  String password;


    public  String getMessage() {return message;}
    public String getUsername() {
        return username;
    }
    public String getPassword() { return password;}
}