package com.example.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CommunityMain extends AppCompatActivity {
    private List<String> StoryList;
    private ArrayAdapter<String> StoryAdapter;
    private static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community);

        ListView listView = findViewById(R.id.listView);

        // Retrieve stored stories from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        StoryList = new ArrayList<>(prefs.getStringSet("stories", new HashSet<>()));

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("PhoneNum") && intent.hasExtra("Story")) {
            String phoneNum = intent.getStringExtra("PhoneNum");
            String storyContent = intent.getStringExtra("Story");

            // Add the new story with phone number to the list
            StoryList.add("전화번호: " + phoneNum + ",   내용: " + storyContent);
        }

        // Save the updated list to SharedPreferences
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("stories", new HashSet<>(StoryList));
        editor.apply();

        StoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, StoryList);
        listView.setAdapter(StoryAdapter);

        Button HomeBtn = findViewById(R.id.HomeButton);
        Button AddBtn = findViewById(R.id.AddStoryButton);
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
    }
}
