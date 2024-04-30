package com.example.capstone.QnA;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface QnAApiService {

    @POST("/ask/")
    Call<QnAPost> ask(
            @Body QnAPost qnapost
            );
}
