package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;


public class Settings extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button BackBtn = findViewById(R.id.BackButton);

        SettingFragment SettingFragment = new SettingFragment();
        Intent intent = getIntent();

        if(intent!=null){
            String rootKey = intent.getStringExtra("target");
            if(rootKey!=null){
                Bundle bundle = new Bundle();
                bundle.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT,rootKey);
                SettingFragment.setArguments(bundle);
            }
        }
//
//        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,SettingFragment,null).commit();
        BackBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent MyPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(MyPage);
            }
        });

    }



}
