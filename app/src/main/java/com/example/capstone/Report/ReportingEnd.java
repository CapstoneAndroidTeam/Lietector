package com.example.capstone.Report;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Chatbot.Chat;
import com.example.capstone.Community.CommunityWrite;
import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

public class ReportingEnd extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endreport);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button BanNumberButton = findViewById(R.id.BanNumberButton);
        Button WriteInGalleryButton = findViewById(R.id.WriteinGalleryButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        Button treatBtn = findViewById(R.id.PreventionButton);
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
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });
        /*
        BanNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go To Ban Number List Page;
            }
        });
        */
        WriteInGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCommunity = new Intent(getApplicationContext(), CommunityWrite.class);
                startActivity(goCommunity);
            }
        });
    }
}
