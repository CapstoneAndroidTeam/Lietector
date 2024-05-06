package com.example.capstone.Diagnose;

import com.google.gson.annotations.SerializedName;

public class DgetItems {
    @SerializedName("suspicion_percentage")
    public Number suspicion_percentage;

    public Number getPercent() {
        return suspicion_percentage;
    }

}
