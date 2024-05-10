package com.example.capstone.Report;

import static android.content.ContentValues.TAG;
import static com.example.capstone.Login.LogIn.userToken;
import static com.example.capstone.Report.ReportListAdapter.reportExistContent;
import static com.example.capstone.Report.ReportListAdapter.reportExistNumber;
import static com.example.capstone.Report.ReportListAdapter.reportPostId;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportEdit extends AppCompatActivity {
    private ReportsEditApiService apiService;
    EditText phonenumber, content;
    String type;
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.reportedit);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        phonenumber = findViewById(R.id.phoneNumber);
        Button popupBtn = findViewById(R.id.PopUpButton);
        content = findViewById(R.id.content);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        phonenumber.setText(reportExistNumber);
        content.setText(reportExistContent);

        ReportTokenInterceptor interceptor = new ReportTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ReportsEditApiService.class);


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
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });

        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
                getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_menu1) {
                            popupBtn.setText("수사기관 사칭형");
                            type = "수사기관 사칭형";
                        }
                        if (menuItem.getItemId() == R.id.action_menu2) {
                            popupBtn.setText("대출사기형");
                            type = "대출사기형";
                        }

                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void edit() {
        String Number = phonenumber.getText().toString().trim();
        String Content = content.getText().toString().trim();

        if (TextUtils.isEmpty(Number)) {
            phonenumber.setError("Enter numbeer");
            phonenumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Content)) {
            content.setError("Enter contents");
            content.requestFocus();
            return;
        }



        Call<getMyReportItems> call = apiService.edit(reportPostId, Number, type , Content, userToken);
        call.enqueue(new Callback<getMyReportItems>() {

            @Override
            public void onResponse(Call<getMyReportItems> call, Response<getMyReportItems> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ReportEdit.this, "Edit successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ReportHistory.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ReportEdit.this, response.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "response data " + response.message());
                }
            }

            @Override
            public void onFailure( Call<getMyReportItems> call, Throwable t) {
                // Network error
                Toast.makeText(ReportEdit.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
