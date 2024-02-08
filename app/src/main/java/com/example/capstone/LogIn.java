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

        String ExID = "capstone1234";
        String ExPW = "12345678";

        Button signbtn = findViewById(R.id.signinButton);
        Button loginbtn = findViewById(R.id.LogInButton);
        String ID = findViewById(R.id.IDEditText).toString();
        String PassWord = findViewById(R.id.PassWordEditText).toString();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignInPage = new Intent(getApplicationContext(), SignIn.class);
                startActivity(goSignInPage);
            }
        });
    }
}
