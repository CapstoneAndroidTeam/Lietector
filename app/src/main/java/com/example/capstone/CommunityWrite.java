package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CommunityWrite extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_writing);

        Button HomeBtn = findViewById(R.id.HomeButton);
        EditText PhoneNum = findViewById(R.id.OptionalPhoneNumber);
        EditText Story = findViewById(R.id.StoryBox);
        Button SaveBtn = findViewById(R.id.EndWriteButton);

        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNumString = PhoneNum.getText().toString();
                String StoryString = Story.getText().toString();

                if (!StoryString.isEmpty()) {
                    Intent goCommunityMain = new Intent(getApplicationContext(), CommunityMain.class);
                    goCommunityMain.putExtra("PhoneNum", PhoneNumString);
                    goCommunityMain.putExtra("Story", StoryString);
                    startActivity(goCommunityMain);
                }
            }
        });
    }
}
