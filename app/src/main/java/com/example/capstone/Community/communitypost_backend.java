package com.example.capstone.Community;

import com.google.gson.annotations.SerializedName;

public class communitypost_backend {
    @SerializedName("id")
    public int id;
    @SerializedName("title")
    private String  title;
    @SerializedName("content")
    private String content;
    @SerializedName("user_nickname")
    public String user_nickname;

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }

    public String getWriter() { return user_nickname; }

}
