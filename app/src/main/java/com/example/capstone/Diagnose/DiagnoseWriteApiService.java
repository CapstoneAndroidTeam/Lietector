package com.example.capstone.Diagnose;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DiagnoseWriteApiService {
    @FormUrlEncoded
    @POST("/voice/")
    Call<DWritegetItems> diagnosisWrite(
            @Field("diagnosis_type") String diagnosis_type,
            @Field("call_details") String call_details
    );
}
