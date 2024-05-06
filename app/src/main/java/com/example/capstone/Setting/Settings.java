package com.example.capstone.Setting;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Login.LogIn;
import com.example.capstone.R;
import com.kakao.sdk.user.UserApiClient;
//import androidx.preference.PreferenceFragmentCompat;


public class Settings extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        ImageButton logout = findViewById(R.id.logout);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserApiClient.getInstance().logout(error -> {
                    if (error != null) {
                        Log.e(TAG, "로그아웃 실패, SDK에서 토큰 삭제됨", error);
                    }else{
                        Log.e(TAG, "로그아웃 성공, SDK에서 토큰 삭제됨");
                        Intent intent = new Intent (getApplicationContext(), LogIn.class);
                        startActivity(intent);
                    }
                    return null;
                });
            }
        });

        SettingFragment SettingFragment = new SettingFragment();
        Intent intent = getIntent();

        if(intent!=null){
            String rootKey = intent.getStringExtra("target");
            if(rootKey!=null){
                Bundle bundle = new Bundle();
                /*
                bundle.putString(PreferenceFragmentCompat.ARG_PREFERENCE_ROOT,rootKey);
                SettingFragment.setArguments(bundle);

                 */
            }
        }
//
//        getSupportFragmentManager().beginTransaction().replace(android.R.id.content,SettingFragment,null).commit();

    }

}
