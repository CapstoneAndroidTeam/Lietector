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

import java.util.List;

public class CommunityListAdapter extends BaseAdapter {
    private Context context;
    private List<String> title;
    private List<String> content;
    private List<Integer> writer;
    private LayoutInflater inflater;



    public CommunityListAdapter(CommunityMain communityMain, List<String> title, List<String> content, List<Integer> writer) {
        this.context = communityMain;
        this.title = title;
        this.content = content;
        this.writer = writer;
        inflater = LayoutInflater.from(context);
    }





    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public Object getItem(int position) {
        return content.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    static class ViewHolder {
        TextView title;
        TextView content;
        TextView writer;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.community_listview, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.content = convertView.findViewById(R.id.content);
            holder.writer = convertView.findViewById(R.id.writer);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(title.get(position).toString());
        holder.content.setText(content.get(position).toString());
        holder.writer.setText(writer.get(position).toString());

        return convertView;
    }
}

