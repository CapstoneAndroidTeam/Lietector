package com.example.capstone.Diagnose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

public class DiagnoseWrite extends AppCompatActivity{
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_writing);
        int RiskPercent = 55;
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        EditText Story = findViewById(R.id.CallStory);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String StoryString = Story.getText().toString();

                if (!StoryString.isEmpty()) {
                    if (RiskPercent > 39) {
                        Intent goSeriousPage = new Intent(getApplicationContext(), DiagnoseSerious.class);
                        startActivity(goSeriousPage);
                    } else {
                        Intent goNotSeriousPage = new Intent(getApplicationContext(), DiagnoseNotSerious.class);
                        startActivity(goNotSeriousPage);
                    }
                }
            }
        });
    }
}
