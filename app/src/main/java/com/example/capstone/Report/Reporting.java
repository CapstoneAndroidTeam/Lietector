package com.example.capstone.Report;

import static android.content.ContentValues.TAG;
import static com.example.capstone.Login.LogIn.userToken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.File;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reporting extends AppCompatActivity {
    EditText reportphonenum , reportreason;
    public static String report_number;
    public static String report_type;
    String type;
    static File file;
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

        ReportTokenInterceptor interceptor=new ReportTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
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
                /*
                Intent intent = new Intent(getApplicationContext(), ReportingEnd.class);
                startActivity(intent);

                 */
            }
        });
    }



    private void reporting() {
        report_number = reportphonenum.getText().toString();
        String report_content = reportreason.getText().toString();
        report_type = type;

        Call<getReportItems> call = apiService.report(userToken, report_number, report_type, report_content);

        call.enqueue(new Callback<getReportItems>() {
            @Override
            public void onResponse(@NonNull  Call<getReportItems> call, @NonNull  Response<getReportItems> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Reporting.this, "Report successful!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), ReportingEnd.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Reporting.this, "모든 칸을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( @NonNull Call<getReportItems> call, @NonNull  Throwable t) {
                // Network error
                Toast.makeText(Reporting.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "error Log : " + t.getMessage());
            }
        });
    }




}
