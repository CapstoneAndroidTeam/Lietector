package com.example.capstone;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        EditText idEditText = findViewById(R.id.IDEditText);
        EditText passwordEditText = findViewById(R.id.PassWordEditText);
        Button signInButton = findViewById(R.id.signinButton);
        Button logInButton = findViewById(R.id.LogInButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.213.170/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        LogInService loginService = retrofit.create(LogInService.class);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = idEditText.getText().toString();
                String PassWord = passwordEditText.getText().toString();

                loginService.requestLogin(ID, PassWord).enqueue(new Callback<LogInSrv>() {
                    @Override
                    public void onResponse(Call<LogInSrv> call, Response<LogInSrv> response) {
                        if (response.isSuccessful()) {
                            LogInSrv loginResponse = response.body();
                            Log.d("LOGIN", "msg : " + loginResponse.getMsg());
                            Log.d("LOGIN", "code : " + loginResponse.getCode());

                            AlertDialog.Builder dialog = new AlertDialog.Builder(LogIn.this);
                            dialog.setTitle(loginResponse.getMsg());
                            dialog.setMessage(loginResponse.getCode());
                            dialog.show();
                        } else {
                            Log.e("LOGIN", "Unsuccessful response: " + response.message());
                            AlertDialog.Builder dialog = new AlertDialog.Builder(LogIn.this);
                            dialog.setTitle("에러");
                            dialog.setMessage(response.message());
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LogInSrv> call, Throwable t) {
                        Log.e("LOGIN", "Failed to connect: " + t.getMessage());
                        AlertDialog.Builder dialog = new AlertDialog.Builder(LogIn.this);
                        dialog.setTitle("에러");
                        dialog.setMessage("서버와의 연결에 실패했습니다.");
                        dialog.show();
                    }
                });
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignInPage = new Intent(getApplicationContext(), SignIn.class);
                startActivity(goSignInPage);
            }
        });
    }
}
