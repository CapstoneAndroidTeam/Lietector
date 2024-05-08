package com.example.capstone.Community;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
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

public class CommunityMain extends AppCompatActivity {
    ListView listView;

    private CommunityService apiService;



    /*
    String[] nickname = {"I", "sample", "sample", "sample", "sample", "sample", "sample", "sample"};
    int[] profileimg = {R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg};
    String[] storytext = {"sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample PM"};

     */
    String title, content;
    String titles;
    String contents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        apiService = retrofit.create(CommunityService.class);

        posting();
        listView = findViewById(R.id.listView);

        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        ImageButton AddBtn = findViewById(R.id.AddStoryButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goWritePage = new Intent(getApplicationContext(), CommunityWrite.class);
                startActivity(goWritePage);
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });
    }


    private void posting () {


        Call<List<communitypost_backend>> call = apiService.getCommunityPosts(title, content);
        call.enqueue(new Callback<List<communitypost_backend>>() {
            @Override
            public void onResponse(Call<List<communitypost_backend>> call, Response<List<communitypost_backend>> response) {
                if (response.isSuccessful()) {

                    List<communitypost_backend> items = response.body();
                    Toast.makeText(CommunityMain.this, "Data Received Successfully", Toast.LENGTH_SHORT).show();
                    List<String> titles = new ArrayList<>();
                    List<String> contents = new ArrayList<>();
                    // Iterate through each QnAItem and add its title and content to the respective lists
                    for (communitypost_backend item : items) {
                        titles.add(item.getTitle());
                        contents.add(item.getContent());
                    }
                    ListAdapter adapter = new CommunityListAdapter(CommunityMain.this, titles, contents);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(CommunityMain.this, "Failed to fetch data: " + response.body().toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "response data " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<communitypost_backend>> call, Throwable t) {
                Toast.makeText(CommunityMain.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}