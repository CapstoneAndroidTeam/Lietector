package com.example.capstone.NumberSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NSPApiService {
    @GET("/reports/allreport/")
    Call<List<NSPgetItems>> reportList(
            @Query("report_number") String report_number,
            @Query("report_type") String report_type
    );
}
