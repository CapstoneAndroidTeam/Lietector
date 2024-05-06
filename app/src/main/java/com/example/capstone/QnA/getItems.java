package com.example.capstone.QnA;

import com.google.gson.annotations.SerializedName;

public class getItems {
    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
