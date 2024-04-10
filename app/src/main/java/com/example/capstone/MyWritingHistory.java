package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyWritingHistory extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> profileimg = new ArrayList<>();
    ArrayList<String> nickname = new ArrayList<>();
    ArrayList<String> storytext = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.writinghistory);

        ListView listView = findViewById(R.id.listView);

        ImageButton BackBtn = findViewById(R.id.BackButton);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });

        for (int i = 0; i < 5; i ++) {
            profileimg.add(R.drawable.profileimg);
            nickname.add("닉네임");
            storytext.add("게시물 내용");
        }

        if(storytext.size() == 0) {
            listView.getEmptyView();
        }



        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(goMainPage);
            }
        });
        MyWritingHistoryAdapter listadapter = new MyWritingHistoryAdapter(MyWritingHistory.this,  profileimg, nickname, storytext);
        listView.setAdapter(listadapter);
    }
}
