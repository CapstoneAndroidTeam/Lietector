package com.example.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button signbtn = findViewById(R.id.signinButton);
        String ID = findViewById(R.id.IDEditText).toString();
        String PassWord = findViewById(R.id.PassWordEditText).toString();

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goEditPage = new Intent(getApplicationContext(), SignIn.class);
                startActivity(goEditPage);
            }
        });
    }
}
