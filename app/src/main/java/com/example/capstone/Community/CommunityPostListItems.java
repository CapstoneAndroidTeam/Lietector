package com.example.capstone.Community;

import com.google.gson.annotations.SerializedName;

public class CommunityPostListItems {
    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("user_nickname")
    public String user_nickname;
    @SerializedName("report_number")
    public String report_number;
}
