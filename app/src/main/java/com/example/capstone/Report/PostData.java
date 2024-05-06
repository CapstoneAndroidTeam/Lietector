package com.example.capstone.Report;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostData {
    @Expose
    @SerializedName("report_number") private String report_number;
    @SerializedName("report_type") private String report_type;
    @SerializedName("report_content") private String report_content;

    public void setReport_number(String report_number) {
        this.report_number = report_number;
    }

    public void setReport_type(String report_type) {
        this.report_type =  report_type;
    }
    public void setReport_content(String report_content) {
        this.report_content =  report_content;
    }

}
