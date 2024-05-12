package com.example.capstone.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capstone.R;

import java.util.ArrayList;
import java.util.List;

public class ReplyAdapter extends BaseAdapter {
    private Context context;
    private List<String> contents = new ArrayList<>();
    private List<Integer> users = new ArrayList<>();
    private List<Integer> posts = new ArrayList<>();
    private LayoutInflater inflater;



    public ReplyAdapter(CommunityPostView communityPostView, List<String> content, List<Integer> user) {
        this.context = communityPostView;
        this.contents = content;
        this.users = user;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return contents.size();
    }

    @Override
    public Object getItem(int position) {
        return contents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {
        TextView content;
        TextView user;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ReplyAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.replies, parent, false);

            holder = new ReplyAdapter.ViewHolder();
            holder.content = convertView.findViewById(R.id.replyContent);
            holder.user = convertView.findViewById(R.id.replyName);

            convertView.setTag(holder);
        } else {
            holder = (ReplyAdapter.ViewHolder) convertView.getTag();
        }

        holder.content.setText(contents.get(position).toString());
        holder.user.setText(users.get(position).toString());

        return convertView;
    }
}


