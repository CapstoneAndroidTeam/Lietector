package com.example.capstone.Diagnose;

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
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DiagnoseAudio extends AppCompatActivity {

    TextView file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnose_audio);

        ImageButton BackBtn = (ImageButton) findViewById(R.id.BackButton);
        ImageButton HomeBtn = (ImageButton) findViewById(R.id.HomeButton);
        file_name = (TextView) findViewById(R.id.file_name); // 오디오 파일의 이름을 표시할 TextView 변수에 참조
        Button bring_audio = (Button) findViewById(R.id.bring_file); // 오디오 파일을 탐색하기 위해 클릭할 Button 변수에 참조
        bring_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file_name.setText(""); // 오디오 파일을 탐색하면 기존 파일이름을 지운다

                // ACTION_GET_CONTENT - 문서나 사진 등의 파일을 선택하고 앱에 그 참조를 반환하기 위해 요청하는 액션
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("audio/*"); // 탐색할 파일 MIME 타입 설정

                launcher_audio.launch(intent); // 파일탐색 액션을 가진 인텐트 실행
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
    }

    // 오디오 파일 탐색 후 선택했을 때 콜백메서드를 설정한 intent launcher
    ActivityResultLauncher<Intent> launcher_audio = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void onActivityResult(ActivityResult result) { // 사용자가 파일 탐색 화면에서 돌아왔을 때 호출되는 메소드
                    if (result.getResultCode() == Activity.RESULT_OK) { // 사용자가 파일 선택을 성공적으로 완료했을 때 내부 코드 실행
                        Log.d("launcher_audio Callback", "audio picking has succeeded"); // 로그 출력

                        Intent data = result.getData(); // 콜백 메서드를 통해 전달 받은 ActivityResult 객체에서 Intent 객체 추출
                        Uri audioUri = data.getData(); // Intent 객체에서 선택한 오디오 파일의 위치를 가리키는 Uri 추출

                        File audioFile = getFileFromUri(DiagnoseAudio.this, audioUri); // Uri를 사용해 파일 복사본 생성
                        if(audioFile != null){ // 파일이 정상적으로 생성되었을 때 내부 코드 실행
                            file_name.setText(audioFile.getName()); // 복사본 파일의 이름으로 TextView에 설정
                        }else{ // 파일이 정상적으로 생성되지 않았을 때 내부 코드 실행
                            Toast.makeText(DiagnoseAudio.this, "오디오 파일을 가져오는데 문제가 생겼습니다.", Toast.LENGTH_SHORT).show(); // 메시지 출력
                        }

                    }else if(result.getResultCode() == Activity.RESULT_CANCELED){ // 사용자가 파일 탐색 중 선택을 하지 않았을 때 내부 코드 실행
                        Log.d("launcher_audio Callback", "audio picking is canceled"); // 로그 출력
                    }else{ // 그 외의 경우 예외 처리
                        Log.e("launcher_audio Callback", "audio picking has failed"); // 로그 출력
                    }
                }
            });

    // URI를 사용해 파일을 복사하고 복사한 경로를 제공하는 메소드
    public static File getFileFromUri(Context context, Uri uri) {
        // 앱과 안드로이드 시스템 간의 데이터 통신을 하기위해 ContentResolver 객체 생성
        // ContentResolver를 통해 앱은 ContentProvider를 사용해 다른 앱의 데이터에 접근하거나 데이터를 읽거나 쓸 수 있다
        ContentResolver contentResolver = context.getContentResolver();
        // Uri를 사용해 파일 이름 반환
        String fileName = getFileName(contentResolver, uri);

        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        // 같은 이름으로 앱 내부저장소에 파일 생성
        File file = new File(context.getCacheDir(), fileName);

        try {
            inputStream = contentResolver.openInputStream(uri); // uri에 있는 데이터를 읽기 위해 InputStream을 열고 InputStream을 반환한다
            if (inputStream == null) {
                return null; // InputStream을 여는데 실패할 경우 null 반환
            }

            outputStream = new FileOutputStream(file); // 이전에 만든 File 객체에 데이터를 쓰기 위해 OutputStream 생성
            byte[] buffer = new byte[4 * 1024]; // 4 KB buffer 생성
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) { // inputStream을 사용해 데이터를 읽고 읽은 데이터가 있다면 while문 내부 코드 실행
                outputStream.write(buffer, 0, bytesRead); // 읽은 byte 데이터를 사용해 File 객체에 데이터 쓰기
            }

            return file; // 그 후 복사가 완료된 파일 객체 반환
        } catch (IOException e) { // 입출력 문제 생기면 오류 출력
            e.printStackTrace();
            return null;
        } finally {
            // 마지막으로 사용한 inputStream, outputStream 종료
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
            } catch (IOException e) { // 입출력 문제 생기면 오류 출력
                e.printStackTrace();
            }
        }
    }

    // uri를 사용해 파일 이름을 반환하는 메서드
    private static String getFileName(ContentResolver contentResolver, Uri uri) {
        // ContentProvider 를 통해 기기 데이터베이스에서 데이터를 조회하기 위해 query() 메서드 사용
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        String displayName = null;
        if (cursor != null && cursor.moveToFirst()) { // 조회된 데이터를 저장한 cursor가 null이 아니고 첫번째 레코드로 이동할 수 있으면(첫번째 레코드가 없다면 false 반환) 내부 코드 실행
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME); // 파일이름이 저장된 열의 위치 반환 후 저장
            if (nameIndex != -1) { // 해당 열이 존재할 경우 내부 코드 실행
                displayName = cursor.getString(nameIndex); // 열에 있는 데이터 반환
            }
            cursor.close(); // 커서 종료
        }
        return displayName; // 받은 파일이름 반환
    }
}