package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class NumNotSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.num_notsearch);

        Button bannumBtn = findViewById(R.id.BanNumberBtn);
        Button communityBtn = findViewById(R.id.GalleryBtn);
        Button preventBtn = findViewById(R.id.PreventionBtn);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });


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