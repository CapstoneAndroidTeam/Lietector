package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Reporting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportpage);
        EditText reportphonenum = findViewById(R.id.OptionalPhoneNumber);
        EditText reportreason = findViewById(R.id.StoryBox);
        Button HomeBtn = findViewById(R.id.HomeButton);
        Button reportButton = findViewById(R.id.EndWriteButton);
        String StringReportPhoneNum = reportphonenum.getText().toString();
        String StringReportReason = reportreason.getText().toString();
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });
        if (!StringReportPhoneNum.isEmpty() && !StringReportReason.isEmpty()) {
            reportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent (getApplicationContext(), ReportingEnd.class);
                    startActivity(intent);
                }
            });
        }
    }


}
