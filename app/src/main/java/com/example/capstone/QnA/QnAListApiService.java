package com.example.capstone.QnA;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface QnAListApiService {
    @GET("/ask/allask/")
    Call<List<getItems>> asklist(
            @Query("title") String title,
            @Query("content") String content
    );

}
