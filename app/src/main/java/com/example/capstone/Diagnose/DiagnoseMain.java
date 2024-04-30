package com.example.capstone.Diagnose;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

public class DiagnoseMain extends AppCompatActivity {
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_main);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        ImageButton inputVoiceRecordBtn = findViewById(R.id.InputByVoiceRecord);
        ImageButton inputTextBtn = findViewById(R.id.InputByText);
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
        inputTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnoseWriting = new Intent(getApplicationContext(), DiagnoseWrite.class);
                startActivity(goDiagnoseWriting);
            }
        });
        inputVoiceRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnoseAudio = new Intent(getApplicationContext(), DiagnoseAudio.class);
                startActivity(goDiagnoseAudio);
            }
        });
    }
}
