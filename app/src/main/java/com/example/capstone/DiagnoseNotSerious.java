package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DiagnoseNotSerious extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_report_notserious);
        Button HomeBtn = findViewById(R.id.HomeButton);
        Button reinputBtn = findViewById(R.id.ReInputButton);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });
        reinputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnoseMainPage = new Intent(getApplicationContext(), DiagnoseMain.class);
                startActivity(goDiagnoseMainPage);
            }
        });
    }
}
