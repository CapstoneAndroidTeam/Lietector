package com.example.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunityMain extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> profileimg = new ArrayList<>();
    ArrayList<String> nickname = new ArrayList<>();
    ArrayList<String> storytext = new ArrayList<>();



    /*
    String[] nickname = {"I", "sample", "sample", "sample", "sample", "sample", "sample", "sample"};
    int[] profileimg = {R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg, R.drawable.profileimg};
    String[] storytext = {"sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample AM", "sample PM"};

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        ListView listView = findViewById(R.id.listView);
        if(storytext.size() == 0) {
            listView.getEmptyView();
        }

        for (int i = 0; i < 5; i ++) {
            profileimg.add(R.drawable.profileimg);
            nickname.add("닉네임");
            storytext.add("게시물 내용");
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.213.170/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommunityService service = retrofit.create(CommunityService.class);

        Call<List<communitypost_backend>> call = service.getCommunityPosts();
        call.enqueue(new Callback<List<communitypost_backend>>() {
            @Override
            public void onResponse(Call<List<communitypost_backend>> call, Response<List<communitypost_backend>> response) {
                if (!response.isSuccessful()) {
                    List<communitypost_backend> posts = response.body();
                    Toast.makeText(CommunityMain.this, response.message(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CommunityMain.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<communitypost_backend>> call, Throwable t) {
                Toast.makeText(CommunityMain.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });




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
        //CommunityListAdapter listadapter = new CommunityListAdapter(CommunityMain.this);
        //listView.setAdapter(listadapter);
    }


}