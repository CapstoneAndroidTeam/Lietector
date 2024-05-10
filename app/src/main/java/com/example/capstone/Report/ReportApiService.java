package com.example.capstone.Report;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ReportApiService {
    @FormUrlEncoded
    @POST("/reports/")
    Call<getReportItems> report(
            @Header("Authorization") String Token,
            @Field("report_number") String report_number,
            @Field("report_type") String report_type,
            @Field("report_content") String report_content
    );

}
