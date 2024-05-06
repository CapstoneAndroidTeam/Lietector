package com.example.capstone.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LogInService {
    @FormUrlEncoded
    @POST("/login/")
    Call<LogInResponse> login(@Field("username") String username,
                              @Field("password") String password
    );
}


