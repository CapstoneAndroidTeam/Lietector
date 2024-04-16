package com.example.capstone;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class kakaoApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        KakaoSdk.init(this, "{d1ccf91d3f67f62c53f4a4025df7b4b8}");
    }
}
