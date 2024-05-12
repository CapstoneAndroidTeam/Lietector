package com.example.capstone.Diagnose;

import static android.service.controls.ControlsProviderService.TAG;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiagnoseAudio extends AppCompatActivity {

    TextView file_name;

    private DiagnoseVoiceApiService apiService;

    private static File file;
    public static int intPercent;
    public static Number percent;
    public static List<String> suspiciousWord = new ArrayList<>();
    public static List<Integer> suspiciousOrder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnose_audio);

        ImageButton BackBtn = findViewById(R.id.BackButton);
        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        file_name = findViewById(R.id.file_name);
        Button bring_audio = findViewById(R.id.bring_file);
        Button ExportBtn = findViewById(R.id.export_file);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(DiagnoseVoiceApiService.class);

        bring_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file_name.setText("");

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*");

                launcher_audio.launch(intent);
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        ExportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagnose();
            }
        });
    }

    ActivityResultLauncher<Intent> launcher_audio = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.d("launcher_audio Callback", "audio picking has succeeded");

                        Intent data = result.getData();
                        Uri audioUri = data.getData();

                        file = getFileFromUri(DiagnoseAudio.this, audioUri);
                        if (file != null) {
                            file_name.setText(file.getName());
                        } else {
                            Toast.makeText(DiagnoseAudio.this, "오디오 파일을 가져오는데 문제가 생겼습니다.", Toast.LENGTH_SHORT).show();
                        }

                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        Log.d("launcher_audio Callback", "audio picking is canceled");
                    } else {
                        Log.e("launcher_audio Callback", "audio picking has failed");
                    }
                }
            });

    public static File getFileFromUri(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        String fileName = getFileName(contentResolver, uri);

        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        file = new File(context.getCacheDir(), fileName);

        try {
            inputStream = contentResolver.openInputStream(uri);
            if (inputStream == null) {
                return null;
            }

            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getFileName(ContentResolver contentResolver, Uri uri) {
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        String displayName = null;
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            if (nameIndex != -1) {
                displayName = cursor.getString(nameIndex);
            }
            cursor.close();
        }
        return displayName;
    }

    private void diagnose() {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("diagnosis_type", RequestBody.create(MediaType.parse("text/plain"), "통화 녹음본으로 입력하기"));

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part audioFile = MultipartBody.Part.createFormData("audio_file", file.getName(), requestFile);

        Call<DgetItems> call = apiService.diagnoseVoice(map, audioFile);
        call.enqueue(new Callback<DgetItems>() {
            @Override
            public void onResponse(@NonNull Call<DgetItems> call, @NonNull Response<DgetItems> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DiagnoseAudio.this, "진단 완료", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Audio data : " + response.body().suspicion_percentage);
                    percent = response.body().suspicion_percentage;
                    Log.d(TAG, "Suspicious word : " + response.body().suspicion_word);
                    for(String i : response.body().suspicion_word) {
                        suspiciousWord.add(i);
                    }
                    for (int i = 1; i <= suspiciousWord.size(); i ++) {
                        suspiciousOrder.add(i);
                    }
                    for(int i : suspiciousOrder) {
                        Log.d(TAG, "suspicious order list : " + i);
                    }

                    String stringPercent = "";
                    for (char x : percent.toString().toCharArray()) {
                        if (Character.isDigit(x)) {

                            stringPercent += x;
                            Log.d(TAG, "x data : " + x);
                        }
                    }
                    intPercent = Integer.parseInt(stringPercent);
                    Log.d(TAG, "percent : " + intPercent);

                    if (intPercent > 55) {
                        Intent intent = new Intent(getApplicationContext(), DiagnoseSerious.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), DiagnoseNotSerious.class);
                        startActivity(intent);
                    }


                } else {
                    Toast.makeText(DiagnoseAudio.this, "Diagnosis failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(@NonNull Call<DgetItems> call, @NonNull Throwable t) {
                Toast.makeText(DiagnoseAudio.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Network Error : " + t.getMessage());
            }
        });
    }

    /*
    private void returnData() {
        Call<List<DiagnosisResponse>> call = apiService.getDiagnose(percent);
        call.enqueue(new Callback<List<DiagnosisResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<DiagnosisResponse>> call, @NonNull Response<List<DiagnosisResponse>> response) {
                if (response.isSuccessful()) {
                    List<DiagnosisResponse> items = response.body();
                    Toast.makeText(DiagnoseAudio.this, "Diagnosis successful", Toast.LENGTH_SHORT).show();
                    for(DiagnosisResponse item : items) {
                        Toast.makeText(DiagnoseAudio.this, item.getNumber().toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "item : " + item.getNumber());
                    }

                } else {
                    Toast.makeText(DiagnoseAudio.this, "Diagnosis failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(@NonNull Call<List<DiagnosisResponse>> call, @NonNull Throwable t) {
                Toast.makeText(DiagnoseAudio.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}

