package com.example.capstone.Diagnose;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface DiagnoseVoiceApiService {
    @Multipart
    @POST("/voice/")
    Call<DgetItems> diagnoseVoice(
            @PartMap Map<String, RequestBody> diagnosis_type,
            @Part MultipartBody.Part audoi_file);

    /*
    @GET("/voice/")
    Call<List<DiagnosisResponse>> getDiagnose(@Query("suspicion_percentage") Number suspicion_percentage);

     */
}
