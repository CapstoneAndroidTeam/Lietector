package com.example.capstone.Diagnose;

import static com.example.capstone.Diagnose.DiagnoseAudio.intPercent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Community.CommunityWrite;
import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;
import com.example.capstone.Report.Reporting;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiagnoseSerious extends AppCompatActivity {
    private ProgressBar progressBarCircle;
    DiagnoseResultApiService apiService;
    protected void onCreate(Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.diagnose_report);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button banBtn = findViewById(R.id.BanNumberButton);
        Button CommunityBtn = findViewById(R.id.WriteinGalleryButton);
        Button preventBtn = findViewById(R.id.PreventionButton);
        Button moreInfo = findViewById(R.id.MoreButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        TextView riskpercent = findViewById(R.id.riskyPercent);
        progressBarCircle = findViewById(R.id.progressBarCircle);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        apiService = retrofit.create(DiagnoseResultApiService.class);


        // Make API call to fetch the list of questions
        Call<List<DgetItems>> call = apiService.diagnoseList((Integer) intPercent);
        call.enqueue(new Callback<List<DgetItems>>() {
            @Override
            public void onResponse(Call<List<DgetItems>> call, Response<List<DgetItems>> response) {
                if (response.isSuccessful()) {
                    List<DgetItems> items = response.body();
                    Toast.makeText(DiagnoseSerious.this, "Data Received Successfully", Toast.LENGTH_SHORT).show();

                    if (items != null && !items.isEmpty()) {
                        DgetItems item = items.get(0); // Assuming you only expect one item


                        // Update UI with percentage value
                        riskpercent.setText(String.valueOf(intPercent));
                        progressBarCircle.setProgress((int) intPercent);
                    } else {
                        Toast.makeText(DiagnoseSerious.this, "No data found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DiagnoseSerious.this, "Failed to fetch data: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call  call, Throwable t) {
                Toast.makeText(DiagnoseSerious.this, "error to fetch data: " + t.toString(), Toast.LENGTH_SHORT).show();
            }

        });



        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent (getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });
        //Go Report Page
        banBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goReportPage = new Intent(getApplicationContext(), Reporting.class);
                startActivity(goReportPage);
            }
        });
        //Go Community Page
        CommunityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goCommunityPage = new Intent(getApplicationContext(), CommunityWrite.class);
                startActivity(goCommunityPage);
            }
        });
        //go Prevention Page - Not yet
        /*
        preventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

         */

        //More Info Popup Page Calling
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popup = new Intent(getApplicationContext(), MoreInfoPopup.class);
                startActivity(popup);
            }
        });
    }
}
