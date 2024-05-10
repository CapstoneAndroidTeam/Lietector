package com.example.capstone.SignUp;

import com.google.gson.annotations.SerializedName;

public class SignUpGetItem {
    @SerializedName("username")
    public String username;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("number")
    public String number;

    public String getUsername() {return username;}
    public String getNickname() {return nickname;}
    public String getNumber() {return number;}


}
