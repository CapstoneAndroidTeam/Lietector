package com.example.capstone.Report;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.R;

import java.util.ArrayList;

public class ReportHistory extends AppCompatActivity {

    ListView listView;
    ArrayList<Integer> percent = new ArrayList<>();
    ArrayList<String> phonenum = new ArrayList<>();
    ArrayList<String> type = new ArrayList<>();
    ArrayList<String> reportedtimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporthistory);


        ListView listView = findViewById(R.id.listView);

        ImageButton BackBtn = findViewById(R.id.BackButton);


        for(int i = 0; i < 5; i ++) {
            phonenum.add("01012345678");
            type.add("기관사칭형");
            reportedtimes.add("50건");
            percent.add(R.drawable.percent);
        }

        if(type.size() == 0) {
            listView.getEmptyView();
        }

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });
        ReportListAdapter reportListAdapter = new ReportListAdapter(ReportHistory.this, percent, phonenum, type, reportedtimes);
        listView.setAdapter(reportListAdapter);
    }
}
