package com.example.capstone.Community;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;


public interface CommunityPostApiService {
    @FormUrlEncoded
    @POST("/posts/")
    Call<Void> post(
            @Header("Authorization") String Token,
            @Field("title") String title,
            @Field("content") String content,
            @Field("report_number") String report_number
    );
}