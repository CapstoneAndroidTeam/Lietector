package com.example.capstone.Community;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommunityPostListApiService {
    @GET("/posts/{post_id}")
    Call<CommunityPostListItems> communityPost(
            @Path("post_id") int post_id,
            @Query("title") String title,
            @Query("content") String content,
            @Query("writer") int writer,
            @Query("report_number") TextView report_number
    );
}
