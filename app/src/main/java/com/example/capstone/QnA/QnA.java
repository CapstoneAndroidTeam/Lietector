package com.example.capstone.QnA;

import static android.service.controls.ControlsProviderService.TAG;
import static com.example.capstone.Login.LogIn.userToken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class QnA extends AppCompatActivity {

    private QnAListApiService apiService;
    private int ID;
    private String Title ;
    private String Content ;
    private String userNickname;
    private int Writer;
    private ListView listView;
    private QnAListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna);

        ImageButton addBtn = findViewById(R.id.AddBtn);
        listView = findViewById(R.id.QnAlistView);
        listView.getEmptyView();
        ImageButton backBtn = findViewById(R.id.BackButton);
        ImageButton homeBtn = findViewById(R.id.HomeButton);
        AskTokenInterceptor interceptor = new AskTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(QnAListApiService.class);


        // Make API call to fetch the list of questions
        Call<List<getItems>> call = apiService.asklist(userToken,Title, Content, userNickname);
        call.enqueue(new Callback<List<getItems>>() {

            @Override
            public void onResponse(Call<List<getItems>> call, Response<List<getItems>> response) {
                if (response.isSuccessful()) {
                    List<getItems> items = response.body();
                    for(getItems item : items) {
                        Log.d(TAG, "edit Id : " + item.id);
                    }
                    // Create lists to hold titles and contents
                    List<String> user_nickname = new ArrayList<>();
                    List<String> titles = new ArrayList<>();
                    List<String> contents = new ArrayList<>();
                    List<Integer> post_id = new ArrayList<>();
                    // Iterate through each QnAItem and add its title and content to the respective lists
                    for (getItems item : items) {
                        user_nickname.add(item.user_nickname);
                        titles.add(item.getTitle());
                        contents.add(item.getContent());
                        post_id.add(item.id);
                    }
                    for (int i = 0; i < post_id.size(); i ++) {
                        Log.d(TAG, "id list : " + post_id.get(i));
                    }
                    adapter = new QnAListAdapter(QnA.this, post_id, titles, contents, user_nickname);
                    listView.setAdapter(adapter);

                    /*
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ArrayAdapter arrayAdapter = new ArrayAdapter(titles, contents);

                        }
                    });

                     */
                } else {
                    listView.getEmptyView();
                    Toast.makeText(QnA.this, "문의 사항이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<getItems>> call, Throwable t) {
                Toast.makeText(QnA.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goAddPage = new Intent(getApplicationContext(), QnAWrite.class);
                startActivity(goAddPage);
            }
        });
    }

}
