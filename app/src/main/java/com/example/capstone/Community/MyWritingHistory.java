package com.example.capstone.Community;

import static com.example.capstone.Login.LogIn.userToken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.QnA.AskTokenInterceptor;
import com.example.capstone.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyWritingHistory extends AppCompatActivity {
    ListView listView;
    List<Integer> writer = new ArrayList<>();
    List<String> title = new ArrayList<>();
    List<String> content = new ArrayList<>();
    List<Integer> PostId = new ArrayList<>();
    int writers;
    MyWriteApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.writinghistory);

        ListView listView = findViewById(R.id.listView);

        ImageButton BackBtn = findViewById(R.id.BackButton);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        listView.getEmptyView();
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

        AskTokenInterceptor interceptor = new AskTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(MyWriteApiService.class);

        Call<List<getMyWriteItems>> call = apiService.postlist(userToken,"", "", writers);
        call.enqueue(new Callback<List<getMyWriteItems>>() {

            @Override
            public void onResponse(Call<List<getMyWriteItems>> call, Response<List<getMyWriteItems>> response) {
                if (response.isSuccessful()) {
                    List<getMyWriteItems> items = response.body();
                    // Create lists to hold titles and contents
                    List<String> titles = new ArrayList<>();
                    List<String> contents = new ArrayList<>();
                    // Iterate through each QnAItem and add its title and content to the respective lists
                    for (getMyWriteItems item : items) {
                        titles.add(item.title);
                        contents.add(item.content);
                        PostId.add(item.id);
                    }
                    Collections.reverse(titles);
                    Collections.reverse(contents);
                    Collections.reverse(PostId);

                    MyWritingHistoryAdapter listadapter = new MyWritingHistoryAdapter(MyWritingHistory.this, PostId, titles, contents);
                    listView.setAdapter(listadapter);

                    /*
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter arrayAdapter = new ArrayAdapter(titles, contents);

                        }
                    });

                     */
                } else {
                    listView.getEmptyView();
                    Toast.makeText(MyWritingHistory.this, "내 작성 내역이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<getMyWriteItems>> call, Throwable t) {
                Toast.makeText(MyWritingHistory.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}