package com.example.capstone.Community;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentApiService {
    @FormUrlEncoded
    @POST("/posts/{post_id}/comment/")
    Call<Void> reply(
            @Header("Authorization") String Token,
            @Path("post_id") int post_id,
            @Field("content") String content
    );
}
