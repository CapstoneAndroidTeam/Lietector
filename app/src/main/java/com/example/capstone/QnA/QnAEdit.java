package com.example.capstone.QnA;

import static android.content.ContentValues.TAG;
import static com.example.capstone.Login.LogIn.userToken;
import static com.example.capstone.QnA.QnAListAdapter.existContent;
import static com.example.capstone.QnA.QnAListAdapter.existTitle;
import static com.example.capstone.QnA.QnAListAdapter.postId;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class QnAEdit extends AppCompatActivity {
    private QnAEditApiService apiService;
    EditText title, content;
    protected void onCreate (Bundle savedInstanceStatus) {
        super.onCreate(savedInstanceStatus);
        setContentView(R.layout.qnawriting);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        Button saveBtn = findViewById(R.id.EndWriteButton);
        title = findViewById(R.id.title);
        content = findViewById(R.id.story);
        ImageButton BackBtn = findViewById(R.id.BackButton);

        title.setText(existTitle);
        content.setText(existContent);
        AskTokenInterceptor interceptor = new AskTokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(QnAEditApiService.class);



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

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });
    }

    private void edit() {
        String Title = title.getText().toString().trim();
        String Content = content.getText().toString().trim();

        if (TextUtils.isEmpty(Title)) {
            title.setError("Enter title");
            title.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Content)) {
            content.setError("Enter contents");
            content.requestFocus();
            return;
        }



        Call<postItems> call = apiService.edit(postId, Title, Content, userToken);
        call.enqueue(new Callback<postItems>() {

            @Override
            public void onResponse(Call<postItems> call, Response<postItems> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(QnAEdit.this, "Edit successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), QnA.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(QnAEdit.this, response.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "response data " + response.message());
                }
            }

            @Override
            public void onFailure( Call<postItems> call, Throwable t) {
                // Network error
                Toast.makeText(QnAEdit.this, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
