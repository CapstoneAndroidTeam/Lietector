package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class QnA extends AppCompatActivity {

    ArrayList<String> Title = new ArrayList<>();
    ArrayList<String> Story = new ArrayList<>();
    ArrayList<String> Answer = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.qna);
            ImageButton AddBtn = findViewById(R.id.AddBtn);

            ListView listView = findViewById(R.id.QnAlistView);
            ImageButton BackBtn = findViewById(R.id.BackButton);
            BackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Finish the current activity to go to the parent page
                    finish();
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
