package com.example.capstone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MypageMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_main);

        //edit, Inquire 왜이러는지..?
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button editBtn = findViewById(R.id.myedit);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button BanBtn = findViewById(R.id.banhistoryBtn);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button ReportBtn = findViewById(R.id.reporthistoryBtn);
        Button PostBtn = findViewById(R.id.mypost);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button InquireBtn = findViewById(R.id.inquire);

        Button BackBtn = findViewById(R.id.BackButton);
        Button SettingBtn = findViewById(R.id.SetButton);


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
    }
}