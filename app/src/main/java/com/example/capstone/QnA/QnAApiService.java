package com.example.capstone.QnA;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface QnAApiService {
    @FormUrlEncoded
    @POST("/ask/")
    Call<postItems> ask(@Field("title") String title,
                        @Field("content") String content,
                        @Field("userId") String userId
    );
}