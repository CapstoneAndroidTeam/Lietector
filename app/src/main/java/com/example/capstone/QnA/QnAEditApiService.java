package com.example.capstone.QnA;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface QnAEditApiService {
    @FormUrlEncoded
    @PUT("/ask/update/{post_id}/")
    Call<postItems> edit(@Path("post_id") int post_id,
                         @Field("title") String title,
                         @Field("content") String content,
                         @Header("Authorization") String Token
    );
}
