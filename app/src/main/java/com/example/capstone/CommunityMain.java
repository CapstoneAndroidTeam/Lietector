package com.example.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        CommunityListAdapter listadapter = new CommunityListAdapter(CommunityMain.this,  profileimg, nickname, storytext);
        listView.setAdapter(listadapter);
    }
}
