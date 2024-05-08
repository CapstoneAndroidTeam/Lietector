package com.example.capstone.Setting;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface logout {
    @FormUrlEncoded
    @POST("/logout/")
    Call<Void> logout (
            @Field("username") String username,
            @Field("password") String password
    );

}
