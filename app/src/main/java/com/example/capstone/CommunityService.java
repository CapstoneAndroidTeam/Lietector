package com.example.capstone;

import com.example.capstone.communitypost_backend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CommunityService {
    @GET("/posts/")
    Call<List<communitypost_backend>> getCommunityPosts();
}
