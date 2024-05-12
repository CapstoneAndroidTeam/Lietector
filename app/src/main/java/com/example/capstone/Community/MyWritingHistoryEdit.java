package com.example.capstone.Community;

import static android.content.ContentValues.TAG;
import static com.example.capstone.Community.MyWritingHistoryAdapter.communityExistContent;
import static com.example.capstone.Community.MyWritingHistoryAdapter.communityExistTitle;
import static com.example.capstone.Community.MyWritingHistoryAdapter.editpostId;
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
import com.example.capstone.Report.ReportTokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyWritingHistoryEdit  extends AppCompatActivity {
    private MyWritingHistoryEditApiService apiService;
    EditText phonenumber, content, title;

    String type;
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.community_writing);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        phonenumber = findViewById(R.id.phoneNumber);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        title.setText(communityExistTitle);
        content.setText(communityExistContent);

        ReportTokenInterceptor interceptor = new ReportTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(MyWritingHistoryEditApiService.class);


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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });

    }

    private void edit() {
        String Number = phonenumber.getText().toString().trim();
        String Content = content.getText().toString().trim();
        String Title = title.getText().toString().trim();


        if (TextUtils.isEmpty(Content)) {
            content.setError("Enter contents");
            content.requestFocus();
            return;
        }



        Call<getMyCommunitytItems> call = apiService.edit(editpostId, Title ,Content, Number, userToken);
        call.enqueue(new Callback<getMyCommunitytItems>() {

            @Override
            public void onResponse(Call<getMyCommunitytItems> call, Response<getMyCommunitytItems> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), MyWritingHistory.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MyWritingHistoryEdit.this, response.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "response data " + response.message());
                }
            }

            @Override
            public void onFailure( Call<getMyCommunitytItems> call, Throwable t) {
                // Network error
                Toast.makeText(MyWritingHistoryEdit.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
