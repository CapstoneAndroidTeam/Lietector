package com.example.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.capstone.R;

import java.util.ArrayList;

public class CommunityListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> profileimg;
    private ArrayList<String> nickname;
    private ArrayList<String> storytext;
    private LayoutInflater inflater;


    public CommunityListAdapter(CommunityMain communityMain, ArrayList<Integer> profileimg, ArrayList<String> nickname, ArrayList<String> storytext) {
        this.context = communityMain;
        this.profileimg = profileimg;
        this.nickname = nickname;
        this.storytext = storytext;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return storytext.size();
    }

    @Override
    public Object getItem(int position) {
        return storytext.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView nickname;
        TextView storytext;
        ImageView profileimg;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.community_listview, parent, false);

            holder = new ViewHolder();
            holder.profileimg = convertView.findViewById(R.id.profileimg);
            holder.storytext = convertView.findViewById(R.id.story);
            holder.nickname = convertView.findViewById(R.id.NickName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nickname.setText(nickname.get(position));
        holder.storytext.setText(storytext.get(position));
        holder.profileimg.setImageResource(profileimg.get(position));

        return convertView;
    }
}

