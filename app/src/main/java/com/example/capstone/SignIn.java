package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);


        String AuthNum = findViewById(R.id.AuthNum).toString();
        Button AuthBtn = findViewById(R.id.AuthButton);
        CheckBox checkBox1 = findViewById(R.id.checkbox1);
        CheckBox checkBox2 = findViewById(R.id.checkbox2);
        CheckBox checkBox3 = findViewById(R.id.checkbox3);
        Button viewbtn1 = findViewById(R.id.viewbtn1);
        Button viewbtn2 = findViewById(R.id.viewbtn2);
        Button viewbtn3 = findViewById(R.id.viewbtn3);
        CheckBox AllAgreeBtn = findViewById(R.id.AllAgreeBtn);
        Button SignInBtn = findViewById(R.id.SignInBtn);


        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = findViewById(R.id.IDEdit).toString();
                String PW = findViewById(R.id.Password).toString();
                String PWOnceMore = findViewById(R.id.PasswordOnceMore).toString();
                String NickName = findViewById(R.id.NickName).toString();
                String PhoneNum = findViewById(R.id.PhoneNum).toString();
                if (!ID.isEmpty() && !NickName.isEmpty() && !PhoneNum.isEmpty()) {
                    Intent goLogInPage = new Intent(getApplicationContext(), LogIn.class);
                    startActivity(goLogInPage);
                }
            }
        });
    }
}
