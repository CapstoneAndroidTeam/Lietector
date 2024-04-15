package com.example.capstone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MypageMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_main);

        //edit, Inquire 왜이러는지..?
        Button editBtn = findViewById(R.id.myedit);
        ImageButton BanBtn = findViewById(R.id.banhistoryBtn);
        ImageButton ReportBtn = findViewById(R.id.reporthistoryBtn);
        ImageButton PostBtn = findViewById(R.id.mypost);
        ImageButton InquireBtn = findViewById(R.id.inquire);

        ImageButton SettingBtn = findViewById(R.id.SetButton);


        ImageButton BackBtn = findViewById(R.id.BackButton);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goEditPage = new Intent(getApplicationContext(), MypageEdit.class);
                startActivity(goEditPage);
            }
        });

        BanBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent BanPage = new Intent(getApplicationContext(), BanHistory.class);
                startActivity(BanPage);
            }
        });
        PostBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent PostPage = new Intent(getApplicationContext(), MyWritingHistory.class);
                startActivity(PostPage);
            }
        });

        InquireBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent QnAPage = new Intent(getApplicationContext(), QnA.class);
                startActivity(QnAPage);
            }
        });

        ReportBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ReportPage = new Intent(getApplicationContext(), ReportHistory.class);
                startActivity(ReportPage);
            }
        });

        SettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSettingPage = new Intent(getApplicationContext(), Settings.class);
                startActivity(goSettingPage);
            }
        });
    }
}