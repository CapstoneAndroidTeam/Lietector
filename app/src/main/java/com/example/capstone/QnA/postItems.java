package com.example.capstone.QnA;

import com.google.gson.annotations.SerializedName;

public class postItems {
    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;


    public void getTitle() {
        this.title = title;
    }

    public void getContent() {

        this.content = content;
    }

}
