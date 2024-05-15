package com.example.capstone.Diagnose;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DgetItems {
    @SerializedName("suspicion_percentage")
    public Number suspicion_percentage;

    @SerializedName("보이스피싱 의심 상위 10단어")
    public List<String> suspicion_word;

    @SerializedName("message")
    public String message;

}
