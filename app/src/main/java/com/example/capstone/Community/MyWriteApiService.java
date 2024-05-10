package com.example.capstone.Community;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MyWriteApiService {
    @GET("/posts/mylist/")
    Call<List<getMyWriteItems>> postlist(
            @Header("Authorization") String Token,
            @Query("title") String title,
            @Query("content") String content
    );

}
