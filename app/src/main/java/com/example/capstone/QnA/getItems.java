package com.example.capstone.QnA;

import com.google.gson.annotations.SerializedName;

public class getItems {
    @SerializedName("id")
    public int id;
    @SerializedName("user_nickname")
    public String user_nickname;

    @SerializedName("writer")
    public int writer;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
