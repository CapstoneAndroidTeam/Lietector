package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;


public class Settings extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

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

    }

}
