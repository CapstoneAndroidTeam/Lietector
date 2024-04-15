package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/*
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

 */

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private static final int RC_SIGN_IN = 9001;
    /*

    // Google api 클라이언트
    private GoogleSignInClient mGooglesSignInClient;

    // 구글계정
    private GoogleSignInAccount gsa;
    // 파이어베이스 인증 객체 생성
    private FirebaseAuth mAuth;

    // 구글 로그인 버튼 및 로그아웃버튼
    private SignInButton btnGoogleLogin;

     */
    EditText idEditText, passwordEditText;
    LogInService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //FirebaseApp.initializeApp(this);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        idEditText = findViewById(R.id.IDEditText);
        passwordEditText = findViewById(R.id.PassWordEditText);
        Button signButton = findViewById(R.id.signButton);
        Button logInButton = findViewById(R.id.LogInButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.213.170/") // Adjust the base URL to include login endpoint
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        loginService = retrofit.create(LogInService.class);


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logining();
            }
        });
    }


    private void logining() {
        String username = idEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        // Call the login API
        Call<LogInResponse> call = loginService.login(username, password);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogInResponse> call, @NonNull Response<LogInResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Login successful
                    String token = response.body().getToken();
                    Toast.makeText(LogIn.this, "Login successful! Token: " + token, Toast.LENGTH_SHORT).show();
                    // Navigate to another activity or perform any other action after successful login
                } else {
                    // Login failed
                    Toast.makeText(LogIn.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogInResponse> call, @NonNull Throwable t) {
                // Network error
                Toast.makeText(LogIn.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
