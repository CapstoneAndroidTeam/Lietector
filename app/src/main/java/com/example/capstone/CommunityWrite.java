package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CommunityWrite extends AppCompatActivity {
    EditText PhoneNum = findViewById(R.id.OptionalPhoneNumber);
    EditText Story = findViewById(R.id.StoryBox);
    Button SaveBtn = findViewById(R.id.EndWriteButton);
    String PhoneNumString = PhoneNum.getText().toString();
    String StoryString = Story.getText().toString();
    @Override
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.community_writing);
        if (PhoneNumString != "" && StoryString != "") {
            SaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goCommunityMain = new Intent(getApplicationContext(), CommunityMain.class);
                    startActivity(goCommunityMain);
                }
            });
        }
    }
}
