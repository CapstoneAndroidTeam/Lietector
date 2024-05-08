package com.example.capstone.Chatbot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface chatApiService {
    @FormUrlEncoded
    @POST("/chatbot/")
     Call<Message> chat(@Field("question") String question
    );
}
