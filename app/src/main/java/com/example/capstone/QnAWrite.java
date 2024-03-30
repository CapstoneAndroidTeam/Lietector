package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class QnAWrite extends AppCompatActivity{
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.qnawriting);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        EditText title = findViewById(R.id.title);
        EditText story = findViewById(R.id.story);
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
                String StoryString = story.getText().toString();

                if (!story.getText().toString().isEmpty() && !title.getText().toString().isEmpty()) {
                    Intent goqnaPage = new Intent (getApplicationContext(), QnA.class);
                    startActivity(goqnaPage);
                }
            }
        });
    }
}
