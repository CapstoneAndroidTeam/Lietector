package com.example.capstone.Report;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportsEditApiService {
    @FormUrlEncoded
    @PUT("/reports/update/{report_id}/")
    Call<getMyReportItems> edit(@Path("report_id") int report_id,
                         @Field("report_number") String report_number,
                         @Field("report_type") String report_type,
                         @Field("report_content") String report_content,
                         @Header("Authorization") String Token
    );
}
