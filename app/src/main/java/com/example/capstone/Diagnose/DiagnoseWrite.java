package com.example.capstone.Diagnose;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.QnA.AskTokenInterceptor;
import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiagnoseWrite extends AppCompatActivity{
    DiagnoseWriteApiService apiService;
    EditText Story;
    public static int intWritePercent;
    public static Number writepercent;
    public static List<String> WsuspiciousWord = new ArrayList<>();
    public static List<Integer> WsuspiciousOrder = new ArrayList<>();
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_writing);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Story = findViewById(R.id.CallStory);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        AskTokenInterceptor interceptor = new AskTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(DiagnoseWriteApiService.class);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diagnosis();
            }
        });
    }

    private void diagnosis() {
        String content = Story.getText().toString();
        if (Story.length() < 80) {
            Toast.makeText(DiagnoseWrite.this, "80자 이상 작성해야 합니다.", Toast.LENGTH_SHORT).show();
        } else {
            Call<DWritegetItems> call = apiService.diagnosisWrite("직접 통화 내용 입력하기", content);
            call.enqueue(new Callback<DWritegetItems>() {
                @Override
                public void onResponse(@NonNull Call<DWritegetItems> call, @NonNull Response<DWritegetItems> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(DiagnoseWrite.this, "Diagnosis successful", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "write data : " + response.body().suspicion_percentage);
                        writepercent = response.body().suspicion_percentage;
                        String stringPercent = "";
                        for (char x : writepercent.toString().toCharArray()) {
                            if (Character.isDigit(x)) {

                                stringPercent += x;
                                Log.d(TAG, "x data : " + x);
                            }
                        }
                        intWritePercent = Integer.parseInt(stringPercent);
                        Log.d(TAG, "percent : " + intWritePercent);
                        for(String i : response.body().suspicious_word) {
                            WsuspiciousWord.add(i);
                        }
                        for (int i = 1; i <= WsuspiciousWord.size(); i ++) {
                            WsuspiciousOrder.add(i);
                        }

                        if (intWritePercent > 55) {
                            Intent intent = new Intent(getApplicationContext(), DiagnoseWriteSerious.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), DiagnoseNotSerious.class);
                            startActivity(intent);
                        }



                    } else {
                        Toast.makeText(DiagnoseWrite.this, "Diagnosis failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(@NonNull Call<DWritegetItems> call, @NonNull Throwable t) {
                    Toast.makeText(DiagnoseWrite.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
