package com.example.capstone.Diagnose;

import static com.example.capstone.Diagnose.DiagnoseAudio.intPercent;
import static com.example.capstone.Diagnose.DiagnoseAudio.suspiciousOrder;
import static com.example.capstone.Diagnose.DiagnoseAudio.suspiciousWord;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Chatbot.Chat;
import com.example.capstone.Community.CommunityWrite;
import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;
import com.example.capstone.Report.Reporting;

public class DiagnoseSerious extends AppCompatActivity {
    private ProgressBar progressBarCircle;
    protected void onCreate(Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_report);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button banBtn = findViewById(R.id.BanNumberButton);
        Button CommunityBtn = findViewById(R.id.WriteinGalleryButton);
        Button treatBtn = findViewById(R.id.PreventionButton);
        treatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goChat = new Intent(getApplicationContext(), Chat.class);
                startActivity(goChat);
            } //진단하기 버튼 누르면 진단하기 창으로
        });
        Button moreInfo = findViewById(R.id.MoreButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        TextView riskpercent = findViewById(R.id.riskyPercent);
        progressBarCircle = findViewById(R.id.progressBarCircle);
        riskpercent.setText(String.valueOf(intPercent));
        progressBarCircle.setProgress((int) intPercent);
        progressBarCircle.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.lietectorred)));



        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suspiciousOrder.clear();
                suspiciousWord.clear();
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
        //Go Report Page
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReportPage = new Intent(getApplicationContext(), Reporting.class);
                startActivity(goReportPage);
            }
        });
        //Go Community Page
        CommunityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCommunityPage = new Intent(getApplicationContext(), CommunityWrite.class);
                startActivity(goCommunityPage);
            }
        });
        //go Prevention Page
        treatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chat = new Intent(getApplicationContext(), Chat.class);
                startActivity(chat);
            }
        });


        //More Info Popup Page Calling
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popup = new Intent(getApplicationContext(), MoreInfoPopup.class);
                startActivity(popup);
            }
        });

    }
}
