package com.example.capstone.Community;

import static android.service.controls.ControlsProviderService.TAG;
import static com.example.capstone.Community.CommunityMain.communityPostId;
import static com.example.capstone.Login.LogIn.userToken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class CommunityPostView extends AppCompatActivity {
    CommunityPostListApiService apiService;
    CommentApiService replyApiService;
    ListCommentApiService commentApiService;
    Retrofit retrofit;
    List<String> listReplyContents = new ArrayList<>();
    List<String> commentUsers = new ArrayList<>();
    List<Integer> listCommunityPostId = new ArrayList<>();
    List<Integer> duplicates = new ArrayList<>();
    String title, content, reportnum, postWriter;
    ImageButton replyBtn;
    EditText ReplyContent;
    ListView listView;
    List<String> newContents = new ArrayList<>();
    List<String> newUsers = new ArrayList<>();
    List<Integer> newPostId = new ArrayList<>();

    List<String> finalnewContents = new ArrayList<>();
    List<String> finalnewUsers = new ArrayList<>();
    ListAdapter replyadapter;


    public static String commentPostWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_post);


        listView = findViewById(R.id.replylist);

        replyBtn = findViewById(R.id.replyButton);
        TextView titles = findViewById(R.id.title);
        TextView writers = findViewById(R.id.writer);
        TextView contents = findViewById(R.id.content);
        TextView reportnumber = findViewById(R.id.reportNumber);
        ReplyContent = findViewById(R.id.replyContent);

        TokenInterceptor interceptor = new TokenInterceptor();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://13.209.90.71/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(CommunityPostListApiService.class);
        commentApiService = retrofit.create(ListCommentApiService.class);


        ImageButton HomeBtn = findViewById(R.id.HomeButton);
        HomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHomePage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goHomePage);
            }
        });

        ImageButton BackBtn = findViewById(R.id.BackButton);
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go to the parent page
                finish();
            }
        });

        //게시글 불러오기
        Call<CommunityPostListItems> call = apiService.communityPost(communityPostId, title, content, postWriter, reportnum);
        call.enqueue(new Callback<CommunityPostListItems>() {
            @Override
            public void onResponse(Call<CommunityPostListItems> call, Response<CommunityPostListItems> response) {
                if (response.isSuccessful()) {
                    titles.setText(response.body().title);
                    contents.setText(response.body().content);
                    writers.setText(response.body().user_nickname);

                    if (reportnumber != null) {
                        reportnumber.setText(response.body().report_number);
                    }

                } else {
                    Log.d(TAG, "response data " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<CommunityPostListItems> call, Throwable t) {
                Toast.makeText(CommunityPostView.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Network error: " + t.getMessage());
            }

        });
        callComments();
        replyPost();
    }

        //replyPost();

    //댓글 불러오기
    private void callComments() {
        Call<List<getListComment>> commentCall = commentApiService.listComment(listReplyContents, commentUsers);
        commentCall.enqueue(new Callback<List<getListComment>>() {
            @Override
            public void onResponse(Call<List<getListComment>> call, Response<List<getListComment>> response) {
                if (response.isSuccessful()) {

                    Log.d(TAG, "before : " + newContents);
                    for (getListComment i : response.body()) {
                        newContents.add(i.content);
                        newUsers.add(i.user_nickname);
                        listCommunityPostId.add(i.post);
                    }
                    Log.d(TAG, "listCommunityPostId : " + listCommunityPostId);
                    Log.d(TAG, "after : " + newContents);
                    finalnewContents.clear();
                    finalnewUsers.clear();

                    for(int i = 0; i < listCommunityPostId.size(); i ++) {
                        if (communityPostId == listCommunityPostId.get(i)) {
                            finalnewContents.add(newContents.get(i));
                            finalnewUsers.add(newUsers.get(i));
                        }
                    }

                    // 댓글을 표시하기 위해 어댑터를 초기화하고 ListView에 설정
                    replyadapter = new ReplyAdapter(CommunityPostView.this, finalnewContents, finalnewUsers);
                    listView.setAdapter(replyadapter);

                } else {
                    Log.d(TAG, "response error " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<getListComment>> call, Throwable t) {
                Log.d(TAG, "Network error " + t.toString());
            }
        });
    }




    public void replyPost() {
        replyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringReplyContent = ReplyContent.getText().toString();

                // Add the new reply content to the listReplyContents
                listReplyContents.add(stringReplyContent);

                // Update the commentUsers list with the new user
                newUsers.add(commentPostWriter);

                // Update the adapter with the new reply content
                finalnewContents.add(stringReplyContent);
                finalnewUsers.add(commentPostWriter);
                // Notify the adapter that the dataset has changed
                ((ReplyAdapter) listView.getAdapter()).notifyDataSetChanged();

                replyApiService = retrofit.create(CommentApiService.class);

                Call<Void> replyCall = replyApiService.reply(userToken, communityPostId, stringReplyContent );
                replyCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> replyCall, Response<Void> Rresponse) {
                        if (Rresponse.isSuccessful()) {
                            Log.d(TAG, "users : " + commentPostWriter);
                        } else {
                            Log.d(TAG, "response data " + Rresponse.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> replyCall, Throwable t) {
                        Toast.makeText(CommunityPostView.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }




}
