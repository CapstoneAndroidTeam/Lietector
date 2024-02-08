package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BanHistory extends AppCompatActivity {

    private List<String> BanList;
    private ArrayAdapter<String> BanAdapter;
    private static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banhistory);

        ListView listView = findViewById(R.id.banlist);


        Button BackBtn = findViewById(R.id.BackButton);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(goMainPage);
            }
        });
    }
}
