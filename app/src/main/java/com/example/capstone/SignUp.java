package com.example.capstone;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    private EditText IDEditText, PasswordEditText, PasswordOnceMoreEditText, NickNameEditText, PhoneNumEditText;

    private SignUpApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        IDEditText = findViewById(R.id.IDEdit);
        PasswordEditText = findViewById(R.id.Password);
        PasswordOnceMoreEditText = findViewById(R.id.PasswordOnceMore);
        NickNameEditText = findViewById(R.id.NickName);
        PhoneNumEditText = findViewById(R.id.PhoneNum);

        Button signInBtn = findViewById(R.id.SignInBtn);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.180.213.170/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(SignUpApiService.class);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signup();
            }
        });
    }

    private void signup() {
        String username = IDEditText.getText().toString().trim();
        String password = PasswordEditText.getText().toString().trim();
        String nickname = NickNameEditText.getText().toString().trim();
        String number = PhoneNumEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            IDEditText.setError("Enter username");
            IDEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            PasswordEditText.setError("Enter password");
            PasswordEditText.requestFocus();
            return;
        }


        // Call the signup API
        Call<Void> call = apiService.signup(username,password,"choi", "youngwoo", nickname, number);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // Signup successful
                    Toast.makeText(SignUp.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                    // Navigate to another activity or perform any other action
                } else {
                    // Signup failed
                    Toast.makeText(SignUp.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Network error
                Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

    /*
    private class SignUpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String ID = params[0];
            String PW = params[1];
            String NickName = params[2];
            String PhoneNum = params[3];

            try {
                URL url = new URL("http://54.180.213.170/admin/"); // Change this URL to your signup endpoint
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");

                String requestBody = "{\"username\": \"" + ID + "\", \"password\": \"" + PW + "\", \"nickname\": \"" + NickName + "\", \"number\": \"" + PhoneNum + "\"}";
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();
                    return response.toString();
                } else {
                    return "Error: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Fail msg ");
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Fail msg ");
        }
    }


}

     */
