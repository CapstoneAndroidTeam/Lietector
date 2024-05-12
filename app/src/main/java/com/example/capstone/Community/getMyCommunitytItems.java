package com.example.capstone.Community;

import com.google.gson.annotations.SerializedName;

public class getMyCommunitytItems {

    @SerializedName("title")
    public int title;

    @SerializedName("content")
    public String content;

    @SerializedName("report_number")
    public String report_number;

    @SerializedName("writer")
    public int writer;
}
