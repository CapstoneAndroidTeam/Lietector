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


            for(int i = 0; i < 5; i ++) {
                Title.add("01012345678");
                Story.add("기관사칭형");
                Answer.add("2024.03.23");
            }

            if(Title.size() == 0) {
                setContentView(R.layout.qna_non);
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
