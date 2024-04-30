package com.example.capstone.Setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceScreen;

import com.example.capstone.R;

public class SettingFragment extends PreferenceFragmentCompat {

    Preference PushPref;
    Preference ReplyPref;
    Preference EmailPref;
    Preference DisclosurePref;
    Preference BanPref;
    Preference MarketingPref;

    SharedPreferences prefs;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        if (rootKey == null) {
            PushPref = findPreference("push_notification");
            ReplyPref = findPreference("reply_notification");
            EmailPref = findPreference("email_ad");

            DisclosurePref = findPreference("disclosure_list");
            BanPref = findPreference("ban_list");
            MarketingPref = findPreference("marketing");

            prefs = PreferenceManager.getDefaultSharedPreferences(getContext());

            //선택 시
            if (!prefs.getString("disclosure_list", "").equals("")) {
                DisclosurePref.setSummary(prefs.getString("disclosure_list", "공개 범위를 설정하세요"));
            }
            if (!prefs.getString("ban_list", "").equals("")) {
                DisclosurePref.setSummary(prefs.getString("ban_list", "차단 방법을 설정하세요"));
            }

            prefs.registerOnSharedPreferenceChangeListener(prefListener);
        }
    }
    //변경 시
    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, @Nullable String key) {
            if (key.equals("disclosure_list")){
                DisclosurePref.setSummary(prefs.getString("disclosure_list", "공개 범위를 설정하세요"));
            }
            if (key.equals("ban_list")) {
                BanPref.setSummary(prefs.getString("ban_list", "차단 방법을 설정하세요"));
            }
        }
    };

    @Override
    public void onNavigateToScreen(PreferenceScreen preferenceScreen) {
        Intent intent = new Intent(getActivity(),Settings.class);
        intent.putExtra("target",preferenceScreen.getKey());
        startActivity(intent);
    }
}