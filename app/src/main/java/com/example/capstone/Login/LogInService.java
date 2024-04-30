package com.example.capstone.Login;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LogInService {
    @GET("/login/")
    Call<LogInResponse> login(@Query("username") String username,
                              @Query("password") String password
    );
}


