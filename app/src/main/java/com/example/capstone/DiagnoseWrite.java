package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DiagnoseWrite extends AppCompatActivity{
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_writing);
        int RiskPercent = 55;
        Button HomeBtn = findViewById(R.id.HomeButton);
        EditText Story = findViewById(R.id.CallStory);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        String StoryString = Story.getText().toString();
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });
        if (StoryString != "") {
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RiskPercent > 39) {
                        Intent goSeriousPage = new Intent(getApplicationContext(), DiagnoseSerious.class);
                        startActivity(goSeriousPage);
                    }
                    else {
                        Intent goNotSeriousPage = new Intent(getApplicationContext(), DiagnoseNotSerious.class);
                        startActivity(goNotSeriousPage);
                    }
                }
            });
        }
    }
}
