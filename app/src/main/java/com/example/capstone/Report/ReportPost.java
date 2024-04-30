package com.example.capstone.Report;

public class ReportPost {
    private String report_number;
    private String report_type;
    private String report_content;
    private Integer reporter;
    private Integer voice_phishing_record;
    public ReportPost(String report_number, String report_type , String report_content, Integer reporter, Integer voice_phishing_record) {
        this.report_number = report_number;
        this.report_type = report_type;
        this.report_content = report_content;
        this.reporter = reporter;
        this.voice_phishing_record = voice_phishing_record;
    }

    /*
    @Field("report_number") String report_number,
            @Field("report_type") String report_type,
            @Field("report_content") String report_content,
            @Field("reporter") int reporter,
            @Field("voice_phishing_record") int voice_phishing_record
     */
}
