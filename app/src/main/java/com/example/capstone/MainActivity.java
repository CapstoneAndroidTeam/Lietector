package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button diagnoseBtn = findViewById(R.id.DiagnoseButton);
        ImageButton communityBtn = findViewById(R.id.CommunityBtn);
        ImageButton reportBtn = findViewById(R.id.ReportBtn);
        ImageButton treatBtn = findViewById(R.id.TreatBtn);
        ImageButton myPageBtn = findViewById(R.id.MyPageBtn);


        SearchView searchview = findViewById(R.id.search);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("01012345678")) {
                    Intent goNumSearch = new Intent(getApplicationContext(), NumSearch.class);
                    startActivity(goNumSearch);
                } else {
                    Intent goNumNotSearch = new Intent(getApplicationContext(), NumNotSearch.class);
                    startActivity(goNumNotSearch);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text change if needed
                return false;
            }
        });

        //Go Diagnose page
        diagnoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnose = new Intent(getApplicationContext(), DiagnoseMain.class);
                startActivity(goDiagnose);
            } //진단하기 버튼 누르면 진단하기 창으로
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

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gomyPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(gomyPage);
            }
        });

        //PreventPage have not been finished.
    }
}