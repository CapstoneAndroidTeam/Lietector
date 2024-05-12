package com.example.capstone.Community;

import com.google.gson.annotations.SerializedName;

public class CommunityPostListItems {
    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("writer")
    public int writer;
    @SerializedName("report_number")
    public String report_number;
}
