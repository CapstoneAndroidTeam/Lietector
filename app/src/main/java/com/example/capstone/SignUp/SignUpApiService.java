package com.example.capstone.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SignUpApiService {
    @FormUrlEncoded
    @POST("/signup/")
    Call<SignUpGetItem> signup(
            @Field("username") String username,
            @Field("password") String password,
            @Field("last_name") String last_name,
            @Field("first_name") String first_name,
            @Field("nickname") String nickname,
            @Field("number") String number
    );
}
