package com.example.capstone.QnA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QnA extends AppCompatActivity {

    ArrayList<String> Title = new ArrayList<>();
    ArrayList<String> Story = new ArrayList<>();
    ArrayList<String> Answer = new ArrayList<>();

    private QnAApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna);
        ImageButton AddBtn = findViewById(R.id.AddBtn);

        ListView listView = findViewById(R.id.QnAlistView);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.213.170/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(QnAApiService.class);
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



        if(Title.size() == 0) {
            listView.getEmptyView();
        }

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddPage = new Intent(getApplicationContext(), QnAWrite.class);
                startActivity(goAddPage);
            }
        });
        QnAListAdapter listadapter = new QnAListAdapter(QnA.this,  Title, Story, Answer);
        listView.setAdapter(listadapter);
    }
}
