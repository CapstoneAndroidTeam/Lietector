package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Reporting extends AppCompatActivity {
    EditText reportphonenum = findViewById(R.id.OptionalPhoneNumber);
    EditText reportreason = findViewById(R.id.StoryBox);
    Button reportButton = findViewById(R.id.EndWrite);
    String StringReportPhoneNum = reportphonenum.getText().toString();
    String StringReportReason = reportreason.getText().toString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportpage);
        if (StringReportPhoneNum != "" && StringReportReason != "") {
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
