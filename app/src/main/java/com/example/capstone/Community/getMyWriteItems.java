package com.example.capstone.Community;

import com.google.gson.annotations.SerializedName;

public class getMyWriteItems {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;
    @SerializedName("writer")
    public int writer;
    @SerializedName("user_nickname")
    public  String user_nickname;
}
