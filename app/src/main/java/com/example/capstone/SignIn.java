package com.example.capstone;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignIn extends AppCompatActivity {

    private EditText IDEditText, PasswordEditText, PasswordOnceMoreEditText, NickNameEditText, PhoneNumEditText;

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
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = IDEditText.getText().toString();
                String PW = PasswordEditText.getText().toString();
                String PWOnceMore = PasswordOnceMoreEditText.getText().toString();
                String NickName = NickNameEditText.getText().toString();
                String PhoneNum = PhoneNumEditText.getText().toString();
                if (!ID.isEmpty() && !PW.isEmpty() && !PWOnceMore.isEmpty() && !NickName.isEmpty() && !PhoneNum.isEmpty()) {
                    // Execute AsyncTask to perform HTTP request
                    new SignUpTask().execute(ID, PW, NickName, PhoneNum);
                }
            }
        });
    }

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
