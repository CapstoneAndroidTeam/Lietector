package com.example.capstone.Report;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ReportApiService {
    @POST("/reports/")
    Call<ReportPost> report(
            @Body ReportPost report
    );
    /*
    @POST("/reports/")
    Call<Void> report(
            @Field("report_number") String report_number,
            @Field("report_type") String report_type,
            @Field("report_content") String report_content,
            @Field("reporter") int reporter,
            @Field("voice_phishing_record") int voice_phishing_record
    );

     */
}
