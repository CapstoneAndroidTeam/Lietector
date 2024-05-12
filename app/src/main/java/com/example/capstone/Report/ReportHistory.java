package com.example.capstone.Report;

import static com.example.capstone.Login.LogIn.userToken;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.QnA.AskTokenInterceptor;
import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReportHistory extends AppCompatActivity {

    ListView listView;
    List<String> reportNumber = new ArrayList<>();
    List<String> reportType = new ArrayList<>();
    List<String> reportContent = new ArrayList<>();
    List<Integer> reportId = new ArrayList<>();

    MyReportApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reporthistory);


        listView = findViewById(R.id.listView);
        listView.getEmptyView();

        ImageButton BackBtn = findViewById(R.id.BackButton);


        AskTokenInterceptor interceptor = new AskTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(MyReportApiService.class);




        Call<List<getMyReportItems>> call = apiService.reportlist(userToken,"", "", "");
        call.enqueue(new Callback<List<getMyReportItems>>() {

            @Override
            public void onResponse(Call<List<getMyReportItems>> call, Response<List<getMyReportItems>> response) {
                if (response.isSuccessful()) {
                    List<getMyReportItems> items = response.body();

                    for (getMyReportItems item : items) {
                        reportNumber.add(item.report_number);
                        reportType.add(item.report_type);
                        reportContent.add(item.report_content);
                        reportId.add(item.id);

                    }
                    ReportListAdapter reportListAdapter = new ReportListAdapter(ReportHistory.this, reportId, reportNumber, reportType, reportContent);
                    listView.setAdapter(reportListAdapter);

                } else {
                    listView.getEmptyView();
                    Toast.makeText(ReportHistory.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<getMyReportItems>> call, Throwable t) {
                Toast.makeText(ReportHistory.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });



    }
}
