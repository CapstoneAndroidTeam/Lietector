package com.example.capstone.SignUp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    private EditText IDEditText, PasswordEditText, LastName, FirstName, NickNameEditText, PhoneNumEditText;

    private SignUpApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        IDEditText = findViewById(R.id.IDEdit);
        PasswordEditText = findViewById(R.id.Password);
        LastName = findViewById(R.id.lastname);
        FirstName = findViewById(R.id.firstname);
        NickNameEditText = findViewById(R.id.NickName);
        PhoneNumEditText = findViewById(R.id.PhoneNum);
        ImageButton BackBtn = findViewById(R.id.BackButton);
        Button signInBtn = findViewById(R.id.SignInBtn);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/") // Replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        apiService = retrofit.create(SignUpApiService.class);
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    signup();
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

    private void signup() {
        String username = IDEditText.getText().toString().trim();
        String password = PasswordEditText.getText().toString().trim();
        String lastname = LastName.getText().toString().trim();
        String firstname = FirstName.getText().toString().trim();
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
        Call<Void> call = apiService.signup(username,password, lastname, firstname, nickname, number);
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
