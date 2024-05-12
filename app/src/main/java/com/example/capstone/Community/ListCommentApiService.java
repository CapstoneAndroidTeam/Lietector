package com.example.capstone.Community;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ListCommentApiService {
    @GET("/posts/comment/allcomment/")
    Call<List<getListComment>> listComment(
            @Query("content") List<String> content,
            @Query("user") List<Integer> user
    );
}
