package com.example.capstone.Setting;

import static com.example.capstone.Login.LogIn.preferences;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Login.LogIn;
import com.example.capstone.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import androidx.preference.PreferenceFragmentCompat;


public class Settings extends AppCompatActivity {

    private logout apiService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        Button logout = findViewById(R.id.logout);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        apiService = retrofit.create(com.example.capstone.Setting.logout.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //일반 로그인

                Call<Void> call = apiService.logout(preferences.getString("userid", ""), preferences.getString("userpwd", ""));
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if (response.isSuccessful()) {
                            // Signup successful
                            Toast.makeText(Settings.this, "Logout successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LogIn.class);
                            startActivity(intent);
                            // Navigate to another activity or perform any other action
                        } else {
                            // Signup failed
                            Toast.makeText(Settings.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        // Network error
                        Toast.makeText(Settings.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                /* kakao 로그인 시
                UserApiClient.getInstance().logout(error -> {
                    if (error != null) {
                        Log.e(TAG, "로그아웃 실패, SDK에서 토큰 삭제됨", error);
                    }else{
                        Log.e(TAG, "로그아웃 성공, SDK에서 토큰 삭제됨");
                        Intent intent = new Intent (getApplicationContext(), LogIn.class);
                        startActivity(intent);
                    }
                    return null;
                });

                 */
            }
        });

        SettingFragment SettingFragment = new SettingFragment();
        Intent intent = getIntent();

        if(intent!=null){
            String rootKey = intent.getStringExtra("target");
            if(rootKey!=null){
                Bundle bundle = new Bundle();
                /*
                bundle.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT,rootKey);
                SettingFragment.setArguments(bundle);

                 */
            }
        }
//
//        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,SettingFragment,null).commit();

    }

}
