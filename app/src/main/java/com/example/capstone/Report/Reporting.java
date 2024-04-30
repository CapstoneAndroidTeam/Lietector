package com.example.capstone.Report;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reporting extends AppCompatActivity {
    EditText reportphonenum , reportreason;
    String type;
    private ReportApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportpage);
        reportphonenum = findViewById(R.id.OptionalPhoneNumber);
        reportreason = findViewById(R.id.StoryBox);
        Button popupbtn = findViewById(R.id.PopUpButton);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button reportButton = findViewById(R.id.EndWriteButton);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.213.170/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        apiService = retrofit.create(ReportApiService.class);
        popupbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(final View view) {
              final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
              getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
              popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                  @Override
                  public boolean onMenuItemClick(MenuItem menuItem) {
                      if (menuItem.getItemId() == R.id.action_menu1){
                          popupbtn.setText("수사기관 사칭형");
                          type = "수사기관 사칭형";
                      }
                      if (menuItem.getItemId() == R.id.action_menu2){
                          popupbtn.setText("대출사기형");
                          type = "대출사기형";
                      }

                      return false;
                  }
              });
              popupMenu.show();
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
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reporting();
                Intent intent = new Intent(getApplicationContext(), ReportingEnd.class);
                startActivity(intent);
            }
        });
    }

    private void reporting() {
        String report_number = reportphonenum.getText().toString();
        String report_content = reportreason.getText().toString();
        String report_type = type;
        int reporter = 1;
        int voice_phishing_record = 0; //수정 필요.. 이거때문에 retrofit error 발생

        ReportPost reportPost = new ReportPost(report_number,report_type,report_content, reporter,voice_phishing_record);
        // Call the signup API
        Call<ReportPost> call = apiService.report(reportPost);
        call.enqueue(new Callback<ReportPost>() {
            @Override
            public void onResponse( Call<ReportPost> call, Response<ReportPost> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Reporting.this, "Report successful!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Reporting.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ReportPost> call,  Throwable t) {
                // Network error
                Toast.makeText(Reporting.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
