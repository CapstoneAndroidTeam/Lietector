package com.example.capstone.Report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MyReportApiService {
    @GET("/reports/myreport/")
    Call<List<getMyReportItems>> reportlist(
            @Header("Authorization") String Token,
            @Query("report_number") String report_number,
            @Query("report_type") String report_type,
            @Query("report_content") String report_content
    );
}
