package com.example.capstone.Community;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface CommunityPostApiService {
    @FormUrlEncoded
    @POST("/posts/")
    Call<Void> ask(
            @Field("title") String title,
            @Field("content") String content
    );
}