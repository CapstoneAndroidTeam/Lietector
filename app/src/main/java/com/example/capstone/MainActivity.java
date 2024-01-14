package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button diagnoseBtn = findViewById(R.id.DiagnoseButton);
        Button communityBtn = findViewById(R.id.CommunityBtn);
        Button preventBtn = findViewById(R.id.PreventBtn);
        Button reportBtn = findViewById(R.id.ReportBtn);
        Button myPageBtn = findViewById(R.id.MyPageBtn);

        //Go Diagnose page
        diagnoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnose = new Intent(getApplicationContext(), DiagnoseMain.class);
                startActivity(goDiagnose);
            }
        });
        //Go Community Page
        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCommunity = new Intent(getApplicationContext(), CommunityMain.class);
                startActivity(goCommunity);
            }
        });
        //Go Report Page
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReport = new Intent(getApplicationContext(), Reporting.class);
                startActivity(goReport);
            }
        });

        //PreventPage, myPage have not been finished.
    }
}