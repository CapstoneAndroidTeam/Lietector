package com.example.capstone.Home;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NSApiService {
    @GET("/reports/allreport/")
    Call<List<NSgetItems>> reportList(
            @Query("report_number") String report_number,
            @Query("report_type") String report_type
    );
}
