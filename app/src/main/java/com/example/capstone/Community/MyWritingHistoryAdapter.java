package com.example.capstone.Community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

public class MyWritingHistoryAdapter extends BaseAdapter {
    private Context context;
    private List<String> title = new ArrayList<>();
    private List<Integer> writer = new ArrayList<>();
    private List<String> content = new ArrayList<>();
    private List<Integer> communityPostId = new ArrayList<>();
    private LayoutInflater inflater;
    public static String communityExistTitle;
    public static  Integer communityExistWriter;
    public static String communityExistContent;
    public static int editpostId;


    public MyWritingHistoryAdapter(MyWritingHistory myWritingHistory, List<Integer> communityPostId, List<String> title, List<String> content) {
        this.context = myWritingHistory;
        this.communityPostId = communityPostId;
        this.title = title;
        this.content = content;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return title.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView title;
        TextView writer;
        TextView content;
        ImageButton editBtn;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.writinghistory_listview, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.writer = convertView.findViewById(R.id.writer);
            holder.content = convertView.findViewById(R.id.content);
            holder.editBtn = convertView.findViewById(R.id.editbtn);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                communityExistTitle = title.get(position);
                communityExistContent = content.get(position);
                editpostId = communityPostId.get(position);
                Intent intent = new Intent(context.getApplicationContext(), MyWritingHistoryEdit.class);
                context.startActivity(intent);
            }
        });

        holder.title.setText(title.get(position));
        holder.content.setText(content.get(position));

        return convertView;
    }
}