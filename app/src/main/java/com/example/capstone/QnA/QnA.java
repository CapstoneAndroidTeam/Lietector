package com.example.capstone.QnA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QnA extends AppCompatActivity {

    private QnAListApiService apiService;
    private int ID;
    private String Title ;
    private String Content ;
    private int Writer;
    private ListView listView;
    private QnAListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna);

        ImageButton addBtn = findViewById(R.id.AddBtn);
        listView = findViewById(R.id.QnAlistView);
        ImageButton backBtn = findViewById(R.id.BackButton);
        ImageButton homeBtn = findViewById(R.id.HomeButton);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        apiService = retrofit.create(QnAListApiService.class);

        // Make API call to fetch the list of questions
        Call<List<getItems>> call = apiService.asklist( Title, Content);
        call.enqueue(new Callback<List<getItems>>() {
            @Override
            public void onResponse(Call<List<getItems>> call, Response<List<getItems>> response) {
                if (response.isSuccessful()) {
                    List<getItems> items = response.body();
                    Toast.makeText(QnA.this, "Data Received Successfully", Toast.LENGTH_SHORT).show();
                    // Create lists to hold titles and contents
                    List<String> titles = new ArrayList<>();
                    List<String> contents = new ArrayList<>();
                    // Iterate through each QnAItem and add its title and content to the respective lists
                    for (getItems item : items) {
                        titles.add(item.getTitle());
                        contents.add(item.getContent());
                    }
                    adapter = new QnAListAdapter(QnA.this, titles, contents);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(QnA.this, "Failed to fetch data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<getItems>> call, Throwable t) {
                Toast.makeText(QnA.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddPage = new Intent(getApplicationContext(), QnAWrite.class);
                startActivity(goAddPage);
            }
        });
    }

}
