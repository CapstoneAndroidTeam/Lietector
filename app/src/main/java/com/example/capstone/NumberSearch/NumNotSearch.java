package com.example.capstone.NumberSearch;

import static com.example.capstone.Home.MainActivity.phonenumList;
import static com.example.capstone.Home.MainActivity.reportTypeList;
import static com.example.capstone.Home.MainActivity.searchNumber;
import static com.example.capstone.Home.MainActivity.searchReportType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.capstone.Chatbot.Chat;
import com.example.capstone.Community.CommunityMain;
import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;
import com.example.capstone.Report.Reporting;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NumNotSearch extends AppCompatActivity {
    NSPApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_notsearch);

        Button bannumBtn = findViewById(R.id.BanNumberBtn);
        Button communityBtn = findViewById(R.id.GalleryBtn);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        Button treatBtn = findViewById(R.id.PreventionBtn);
        treatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goChat = new Intent(getApplicationContext(), Chat.class);
                startActivity(goChat);
            } //진단하기 버튼 누르면 진단하기 창으로
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });


        bannumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReporting = new Intent(getApplicationContext(), Reporting.class);
                startActivity(goReporting);
            }
        });
        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCommunity = new Intent(getApplicationContext(), CommunityMain.class);
                startActivity(goCommunity);
            }
        });

        ImageButton HomeBtn = findViewById(R.id.HomeButton);

        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        SearchView searchview = findViewById(R.id.searchView);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(NSPApiService.class);


        // Make API call to fetch the list of questions
        Call<List<NSPgetItems>> call = apiService.reportList("", "");

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<List<NSPgetItems>> call = apiService.reportList("", "");
                call.enqueue(new Callback<List<NSPgetItems>>() {

                    @Override
                    public void onResponse(Call<List<NSPgetItems>> call, Response<List<NSPgetItems>> response) {
                        if (response.isSuccessful()) {
                            if (phonenumList.contains(query)) {
                                int index = phonenumList.indexOf(query);
                                searchNumber = query;
                                searchReportType = reportTypeList.get(index);
                                Intent intent = new Intent(getApplicationContext(), NumSearch.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), NumNotSearch.class);
                                startActivity(intent);
                            }
                            Toast.makeText(NumNotSearch.this, "Data Received Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(NumNotSearch.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NSPgetItems>> call, Throwable t) {
                        Toast.makeText(NumNotSearch.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}