package com.example.capstone.Chatbot;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chat extends AppCompatActivity {

    private chatApiService apiService;

    RecyclerView recycler_view;
    EditText Question;
    ImageButton btn_send;

    List<Message> messageList;
    //MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        recycler_view = findViewById(R.id.recycler_view);
        btn_send = findViewById(R.id.imageButton);

        recycler_view.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setStackFromEnd(true);
        recycler_view.setLayoutManager(manager);
        Question = findViewById(R.id.add_question);


        messageList = new ArrayList<>();
        MessageAdapter messageAdapter = new MessageAdapter(messageList);
        recycler_view.setAdapter(messageAdapter);

        ImageButton BackBtn = findViewById(R.id.BackButton);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
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


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/") // Adjust the base URL as per your backend endpoint
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        chatApiService chatApiService = retrofit.create(chatApiService.class);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = Question.getText().toString();

                // Add user's message to the chat interface
                messageList.add(new Message(question, true));
                messageAdapter.notifyDataSetChanged();

                // Scroll to the bottom of the chat interface
                recycler_view.smoothScrollToPosition(messageAdapter.getItemCount() - 1);

                // Send user's message to the backend for processing
                Call<Message> call = chatApiService.chat(question);
                call.enqueue(new Callback<Message>() {
                    @Override
                    public void onResponse(Call<Message> call, Response<Message> response) {
                        if (response.isSuccessful()) {
                            // Add response from the backend to the chat interface
                            String botMessage = response.body().getAnswer();
                            Log.d(TAG, "answer : " + botMessage);
                            messageList.add(new Message(botMessage, false)); // Assuming false as bot's response
                            messageAdapter.notifyDataSetChanged();

                            // Scroll to the bottom of the chat interface
                            recycler_view.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to get response from server", Toast.LENGTH_LONG).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<Message> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failed to connect to server", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Error: " + t.getMessage(), t);
                    }
                });

                // Clear the EditText after sending the message
                Question.getText().clear();
            }
        });
    }
}
