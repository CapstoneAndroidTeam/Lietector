package com.example.capstone.Diagnose;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface DiagnoseResultApiService {
    @GET("/diagnosis/")
    Call<List<DgetItems>> diagnoseList(
            @Query("suspicion_percentage") Number suspicion_percentage
            );

}
