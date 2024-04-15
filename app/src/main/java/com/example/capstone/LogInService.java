package com.example.capstone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.OPTIONS;
import retrofit2.http.Query;

public interface LogInService {
    @GET("/check/")
    Call<LogInResponse> login(@Query("username") String username,
                              @Query("password") String password
    );
}


