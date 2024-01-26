package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MypageEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_edit);

        ImageButton ImageBtn = findViewById(R.id.imgchangeBtn); //프로필사진변경
        Button VerifyBtn = findViewById(R.id.VerifyBtn); //인증번호
        Button EditBtn = findViewById(R.id.EditBtn);

        Button BackBtn = findViewById(R.id.BackButton);
        Button SettingBtn = findViewById(R.id.SetButton);

        //backbtn -> 마이페이지 메인으로
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goMainPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(goMainPage);
            }
        });

    }
}
