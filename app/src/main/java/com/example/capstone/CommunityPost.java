package com.example.capstone;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommunityPost extends AppCompatActivity {

    private ListView replylist;
    String Names[]={"닉네임","민수","윤경","영우","민주"};
    String Replies[]={"공감하는 댓글!", "좋아요!","조심하세요","정말요?","우와"};
    int Images[]={R.drawable.img1, R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5}; // 프로필사진

    private ArrayAdapter ReplyAdapter;

    private static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_post);

        ListView ReplyList = findViewById(R.id.replylist);


        /* 오류 수정 예정

        ReplyAdapter = new ArrayAdapter<String>(this, Names, Replies, Images);
        ReplyList.setAdapter(ReplyAdapter);

         */

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
    }
    class ReplyAdapter extends ArrayAdapter<String> {

        Context context;
        String rNames[];
        String rReplies[];
        int rImgs[];

        ReplyAdapter(Context c, String name[], String description[], int imgs[]) {
            super(c, R.layout.replies, R.id.NameView, name);
            this.context = c;
            this.rNames = name;
            this.rReplies = description;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            //replies xml파일을 view 객체로 만들기 위해서는 layoutInflater를 이용
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View replies = layoutInflater.inflate(R.layout.replies, parent, false);

            CircleImageView images = replies.findViewById(R.id.profileview);
            TextView Names = replies.findViewById(R.id.NameView);
            TextView Replies = replies.findViewById(R.id.ReplyText);

            images.setImageResource(rImgs[position]);
            Names.setText(rNames[position]);
            Replies.setText(rReplies[position]);


            return replies;// xml 파일
        }
    }
}
