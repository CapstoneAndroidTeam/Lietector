package com.example.capstone.Community;

import static android.service.controls.ControlsProviderService.TAG;
import static com.example.capstone.Login.LogIn.userToken;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityWrite extends AppCompatActivity {
    CommunityPostApiService apiService;
    EditText title, content, Report_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_writing);

        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        Report_number = findViewById(R.id.phoneNumber);
        Button SaveBtn = findViewById(R.id.EndWriteButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);


        TokenInterceptor interceptor=new TokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(CommunityPostApiService.class);
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
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushCommunity();
            }
        });
    }

    private void pushCommunity() {
        String Title = title.getText().toString().trim();
        String Content = content.getText().toString().trim();
        String report_number = Report_number.getText().toString().trim();

        if (TextUtils.isEmpty(Title)) {
            title.setError("Enter title");
            title.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Content)) {
            content.setError("Enter contents");
            content.requestFocus();
            return;
        }


        Call<Void> call = apiService.post(userToken, Title, Content, report_number);
        Log.d(TAG, "community Token : " + userToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), CommunityMain.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CommunityWrite.this, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( Call<Void> call, Throwable t) {
                // Network error
                Toast.makeText(CommunityWrite.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
