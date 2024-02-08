package com.example.capstone;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LogInService {
    @FormUrlEncoded
    @POST("/signup/")
    Call<LogInSrv> requestLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}