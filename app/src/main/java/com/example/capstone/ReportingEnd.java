package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReportingEnd extends AppCompatActivity {
    Button BanNumberButton = findViewById(R.id.BanNumberButton);
    Button WriteInGalleryButton = findViewById(R.id.WriteinGalleryButton);
    Button PreventButton = findViewById(R.id.PreventionButton);
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endreport);
        /*
        BanNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go To Ban Number List Page;
            }
        });
        */
        WriteInGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCommunity = new Intent(getApplicationContext(), CommunityWrite.class);
                startActivity(goCommunity);
            }
        });
    }
}
