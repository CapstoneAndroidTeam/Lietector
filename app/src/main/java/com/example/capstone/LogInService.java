package com.example.capstone;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LogInService {
    @FormUrlEncoded
    @POST("/login/")
    Call<LogInSrv> requestLogin(
            @Field("username") String username,
            @Field("password") String password
    );
}
