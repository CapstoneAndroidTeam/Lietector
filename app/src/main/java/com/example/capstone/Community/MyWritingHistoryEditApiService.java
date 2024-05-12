package com.example.capstone.Community;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyWritingHistoryEditApiService {
    @FormUrlEncoded
    @PUT("/posts/update/{post_id}/")
    Call<getMyCommunitytItems> edit(@Path("post_id") int post_id,
                                @Field("title") String title,
                                @Field("content") String content,
                                @Field("report_number") String report_number,
                                @Header("Authorization") String Token
    );
}
