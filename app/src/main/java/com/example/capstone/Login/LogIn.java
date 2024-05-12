package com.example.capstone.Login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone.Home.MainActivity;
import com.example.capstone.R;
import com.example.capstone.SignUp.SignUp;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {

    public static String kakaoNickName;
    public static String kakaoProfileImg;
    public static SharedPreferences preferences;
    public static String userToken;

    public static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        EditText idEditText = findViewById(R.id.IDEditText);
        EditText passwordEditText = findViewById(R.id.PassWordEditText);
        Button signInButton = findViewById(R.id.signButton);
        Button logInButton = findViewById(R.id.LogInButton);
        //ImageButton kakao = findViewById(R.id.kakaobtn);


        CookieManager manager = new CookieManager();
        manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(manager);

        CookieStore store = manager.getCookieStore();


        /*
        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            // 콜백 메서드 ,
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                Log.e(TAG, "CallBack Method");
                //oAuthToken != null 이라면 로그인 성공
                if (oAuthToken != null) {
                    // 토큰이 전달된다면 로그인이 성공한 것이고 토큰이 전달되지 않으면 로그인 실패한다.
                    updateKakaoLoginUi();
                    Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goHome);

                } else {
                    //로그인 실패
                    Log.e(TAG, "invoke: login fail");
                }

                return null;
            }
        };

         */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://13.209.90.71/") // Adjust the base URL as per your backend endpoint
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        LogInService loginService = retrofit.create(LogInService.class);

        preferences = getSharedPreferences("Token", MODE_PRIVATE);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("getHashKey", "getHashKey" + getKey(LogIn.this));
                String ID = idEditText.getText().toString();
                String PassWord = passwordEditText.getText().toString();

                Call<LogInResponse> call = loginService.login(ID, PassWord);

                call.enqueue(new Callback<LogInResponse>() {
                    @Override
                    public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "Token : " + response.body().token);
                            SharedPreferences.Editor editor = preferences.edit();
                            //putString(KEY,VALUE)
                            editor.putString("Token", response.body().token);
                            userToken = response.body().token;
                            userName = response.body().getUsername();
                            Log.d(TAG, "username : " + userName);
                            //항상 commit & apply 를 해주어야 저장이 된다.
                            editor.commit();
                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
                            goHome.putExtra("firstKeyName", ID); // Verify된 경우 userId 다음 액티비티로 전달하기
                            startActivity(goHome);
                        } else {
                            Log.d(TAG, "잘못된 아이디 혹은 비밀번호 입니다.");
                            Toast.makeText(getApplicationContext(), "잘못된 아이디 혹은 비밀번호 입니다.", Toast.LENGTH_LONG).show();
                            Log.d(TAG, response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<LogInResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                        Log.d(TAG, "Fail msg : " + t.getMessage());
                    }
                });
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goSignInPage = new Intent(getApplicationContext(), SignUp.class);
                startActivity(goSignInPage);
            }
        });

        /*
        kakao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 해당 기기에 카카오톡이 설치되어 있는 확인
                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LogIn.this)){
                    UserApiClient.getInstance().loginWithKakaoTalk(LogIn.this, callback);
                }else{
                    // 카카오톡이 설치되어 있지 않다면
                    UserApiClient.getInstance().loginWithKakaoAccount(LogIn.this, callback);
                }
            }
        });

        updateKakaoLoginUi();
    }
    private void updateKakaoLoginUi () {
        UserApiClient.getInstance().me(new Function2 <User, Throwable, Unit>() {
            @Override
            public Unit invoke (User user, Throwable throwable) {
                if(user != null) {
                    kakaoNickName = user.getKakaoAccount().getProfile().getNickname();
                    kakaoProfileImg = user.getKakaoAccount().getProfile().getProfileImageUrl();

                    Log.d(ContentValues.TAG, "invoke: id" + user.getId());
                    Log.d(ContentValues.TAG, "invoke: email" + user.getKakaoAccount().getEmail());
                    Log.d(ContentValues.TAG, "invoke: nickname" + user.getKakaoAccount().getProfile().getNickname());
                    Log.d(ContentValues.TAG, "invoke: gender" + user.getKakaoAccount().getGender());
                    Log.d(ContentValues.TAG, "invoke: AgeRange" + user.getKakaoAccount().getAgeRange());
                    Log.d(ContentValues.TAG, "invoke: Profile URL" + user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                } else {
                    Log.d(ContentValues.TAG, "로그인 실패");
                }
                return null;
            }
        });

    }

    public static String getKey(final Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (packageInfo == null) {
                return null;
            }
            for (Signature signature : packageInfo.signatures) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    return android.util.Base64.encodeToString(md.digest(), Base64.NO_WRAP);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

         */
    }
}
