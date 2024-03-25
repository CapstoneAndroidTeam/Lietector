package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BanHistory extends AppCompatActivity {

    ListView listView;
    ArrayList<String> phonenum = new ArrayList<>();
    ArrayList<String> type = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banhistory);


        ListView listView = findViewById(R.id.banlist);

        Button BackBtn = findViewById(R.id.BackButton);


        for(int i = 0; i < 5; i ++) {
            phonenum.add("01012345678");
            type.add("기관사칭형");
            date.add("2024.03.23");
        }

        if(type.size() == 0) {
            setContentView(R.layout.banhistory_non);
        }

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(goMainPage);
            }
        });
        BanListAdapter banlistadapter = new BanListAdapter(BanHistory.this,  phonenum, type, date);
        listView.setAdapter(banlistadapter);
    }
}
