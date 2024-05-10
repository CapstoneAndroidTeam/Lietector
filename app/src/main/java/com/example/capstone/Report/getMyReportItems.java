package com.example.capstone.Report;

import com.google.gson.annotations.SerializedName;

public class getMyReportItems {
    @SerializedName("id")
    public int id;
    
    @SerializedName("report_number")
    public String report_number;

    @SerializedName("report_type")
    public String report_type;

    @SerializedName("report_content")
    public String report_content;
}
