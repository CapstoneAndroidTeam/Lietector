package com.example.capstone;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.LogInService;
import com.example.capstone.LogInSrv;
import com.example.capstone.MainActivity;
import com.example.capstone.R;
import com.example.capstone.SignIn;

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
                .baseUrl("http://54.180.213.170/login/") // Adjust the base URL as per your backend endpoint
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        LogInService loginService = retrofit.create(LogInService.class);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = idEditText.getText().toString();
                String PassWord = passwordEditText.getText().toString();

                Call<LogInSrv> call = loginService.requestLogin(ID, PassWord);

                call.enqueue(new Callback<LogInSrv>() {
                    @Override
                    public void onResponse(Call<LogInSrv> call, Response<LogInSrv> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "계정 확인 완료용!!" + response.toString());
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();

                            // 로그인. Main Activity2 를 호출한다. (갤러리와 이미지 처리 버튼이 나오는 부분이다)
                            // text view 내의 값들이 db에 있는 경우
                            Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                            goHome.putExtra("firstKeyName", ID); // Verify된 경우 userId 다음 액티비티로 전달하기
                            startActivity(goHome);
                        } else {
                            Log.d(TAG, "Post Status Code ㅠㅠ : " + response.code());
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                            Log.d(TAG, response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<LogInSrv> call, Throwable t) {
                        Log.d(TAG, "Fail msg : " + t.getMessage());
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
