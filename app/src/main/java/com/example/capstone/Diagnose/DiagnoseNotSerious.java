package com.example.capstone.Diagnose;

import static com.example.capstone.Diagnose.DiagnoseAudio.intPercent;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

public class DiagnoseNotSerious extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_report_notserious);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button reinputBtn = findViewById(R.id.ReInputButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        TextView riskyPercent = findViewById(R.id.riskyPercent);
        ProgressBar progressBar = findViewById(R.id.progressBarCircle);

        riskyPercent.setText(String.valueOf(intPercent));
        progressBar.setProgress((int) intPercent);
        progressBar.setProgressTintList(ColorStateList.valueOf(getResources().getColor(R.color.lietectordarklightblue)));
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
        reinputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnoseMainPage = new Intent(getApplicationContext(), DiagnoseMain.class);
                startActivity(goDiagnoseMainPage);
            }
        });
    }
}
