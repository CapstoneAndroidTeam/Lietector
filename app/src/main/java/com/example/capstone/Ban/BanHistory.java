package com.example.capstone.Ban;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.R;

import java.util.ArrayList;

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

        ImageButton BackBtn = findViewById(R.id.BackButton);


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
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });
        BanListAdapter banlistadapter = new BanListAdapter(BanHistory.this,  phonenum, type, date);
        listView.setAdapter(banlistadapter);
    }
}
