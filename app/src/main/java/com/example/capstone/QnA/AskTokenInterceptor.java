package com.example.capstone.QnA;



import static com.example.capstone.Login.LogIn.userToken;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AskTokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        //rewrite the request to add bearer token
        Request newRequest=chain.request().newBuilder()
                .header("Authorization","Token "+ userToken)
                .build();

        return chain.proceed(newRequest);
    }
}