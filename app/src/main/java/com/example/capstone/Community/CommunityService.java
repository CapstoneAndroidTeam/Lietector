package com.example.capstone.Community;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommunityService {
    @GET("/posts/alllist/")
    Call<communitypost_backend> getCommunityPosts(
            @Query("title") String title,
            @Query("content") String content,
            @Query("report_number") String report_number,
            @Query("writer") String writer
    );
}
