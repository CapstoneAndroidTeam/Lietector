package com.example.capstone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderApi {
    @GET("tests")
    Call<List<Post>> getPosts();
}
