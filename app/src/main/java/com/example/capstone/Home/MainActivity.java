package com.example.capstone.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.capstone.Chatbot.Chat;
import com.example.capstone.Community.CommunityMain;
import com.example.capstone.Diagnose.DiagnoseMain;
import com.example.capstone.MyPage.MypageMain;
import com.example.capstone.NumberSearch.NumNotSearch;
import com.example.capstone.NumberSearch.NumSearch;
import com.example.capstone.R;
import com.example.capstone.Report.Reporting;
import com.kakao.sdk.common.KakaoSdk;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    NSApiService apiService;
    public  static  List<String> phonenumList = new ArrayList<>();
    public static List<String> reportTypeList = new ArrayList<>();
    public  static String searchNumber;
    public  static String searchReportType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KakaoSdk.init(this, "d1ccf91d3f67f62c53f4a4025df7b4b8"); // KakaoSdk.init(this, ${NATIVE_APP_KEY});
        String kakaoHashKey = KakaoSdk.INSTANCE.getKeyHash();
        Log.d("kakaoHashKey", kakaoHashKey);

        ImageButton diagnoseBtn = findViewById(R.id.DiagnoseButton);
        ImageButton communityBtn = findViewById(R.id.CommunityBtn);
        ImageButton reportBtn = findViewById(R.id.ReportBtn);
        ImageButton treatBtn = findViewById(R.id.TreatBtn);
        ImageButton myPageBtn = findViewById(R.id.MyPageBtn);


        SearchView searchview = findViewById(R.id.search);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(NSApiService.class);


        // Make API call to fetch the list of questions
        Call<List<NSgetItems>> call = apiService.reportList("", "");

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<List<NSgetItems>> call = apiService.reportList( "", "");
                call.enqueue(new Callback<List<NSgetItems>>() {

                    @Override
                    public void onResponse(Call<List<NSgetItems>> call, Response<List<NSgetItems>> response) {
                        if (response.isSuccessful()) {
                            List<NSgetItems> items = response.body();
                            for (NSgetItems item : items) {
                                phonenumList.add(item.report_number);
                                reportTypeList.add(item.report_type);
                            }
                            if (phonenumList.contains(query)) {
                                int index = phonenumList.indexOf(query);
                                searchNumber = query;
                                searchReportType = reportTypeList.get(index);
                                Intent intent = new Intent(getApplicationContext(), NumSearch.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), NumNotSearch.class);
                                startActivity(intent);
                            }
                            Toast.makeText(MainActivity.this, "Data Received Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<NSgetItems>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text change if needed
                return false;
            }
        });

        //Go Diagnose page
        diagnoseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDiagnose = new Intent(getApplicationContext(), DiagnoseMain.class);
                startActivity(goDiagnose);
            } //진단하기 버튼 누르면 진단하기 창으로
        });
        //Go Community Page
        communityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCommunity = new Intent(getApplicationContext(), CommunityMain.class);
                startActivity(goCommunity);
            }
        });
        //Go Report Page
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReport = new Intent(getApplicationContext(), Reporting.class);
                startActivity(goReport);
            }
        });

        //Go Treat page
        treatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goChat = new Intent(getApplicationContext(), Chat.class);
                startActivity(goChat);
            } //진단하기 버튼 누르면 진단하기 창으로
        });

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gomyPage = new Intent(getApplicationContext(), MypageMain.class);
                startActivity(gomyPage);
            }
        });

        //PreventPage have not been finished.
    }
}