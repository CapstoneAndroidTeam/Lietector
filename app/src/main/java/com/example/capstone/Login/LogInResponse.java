package com.example.capstone.Login;

import com.google.gson.annotations.SerializedName;

public class LogInResponse {
    @SerializedName("message")
    private  String message;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
   private  String password;

    @SerializedName("token")
    public  String token;

    @SerializedName("nickname")
    public String nickname;


    public  String getMessage() {return message;}
    public String getUsername() {
        return username;
    }
    public String getPassword() { return password;}
}