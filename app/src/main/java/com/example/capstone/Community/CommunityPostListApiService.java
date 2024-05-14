package com.example.capstone.Community;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommunityPostListApiService {
    @GET("/posts/{post_id}/")
    Call<CommunityPostListItems> communityPost(
            @Path("post_id") int post_id,
            @Query("title") String title,
            @Query("content") String content,
            @Query("user_nickname") String user_nickname,
            @Query("report_number") String report_number
    );
}
