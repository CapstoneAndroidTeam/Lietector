package com.example.capstone.NumberSearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.capstone.Community.CommunityMain;
import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;
import com.example.capstone.Report.Reporting;

public class NumSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_search);

        Button banBtn = findViewById(R.id.Banbtn);
        Button bannumBtn = findViewById(R.id.BanNumberButton);
        Button communityBtn = findViewById(R.id.GalleryButton);
        Button preventBtn = findViewById(R.id.PreventionButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });

        //신고된 번호 리스트랑 NumText 비교 후 없으면 notsearch 페이지로


        bannumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goReporting = new Intent(getApplicationContext(), Reporting.class);
                startActivity(goReporting);
            }
        });
        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goCommunity = new Intent(getApplicationContext(), CommunityMain.class);
                startActivity(goCommunity);
            }
        });


        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        SearchView searchview = findViewById(R.id.searchView);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("01012345678")) {
                    Intent goNumSearch = new Intent(getApplicationContext(), NumSearch.class);
                    startActivity(goNumSearch);
                } else {
                    Intent goNumNotSearch = new Intent(getApplicationContext(), NumNotSearch.class);
                    startActivity(goNumNotSearch);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text change if needed
                return false;
            }
        });



}
}

