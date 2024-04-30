package com.example.capstone.Community;

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
import java.util.List;

public class CommunityListAdapter extends BaseAdapter {
    private Context context;
    private List<communitypost_backend> title;
    private List<communitypost_backend> content;
    private List<String> report_number;
    private List<communitypost_backend> writer;
    private LayoutInflater inflater;



    public CommunityListAdapter(CommunityMain communityMain, List<communitypost_backend> title, List<communitypost_backend> content, List<communitypost_backend> reportNumber, List<communitypost_backend> writer) {
        this.context = communityMain;
        this.title = title;
        this.content = content;
        this.report_number = report_number;
        this.writer = writer;
        inflater = LayoutInflater.from(context);
    }


    public CommunityListAdapter(Context context) {
        this.context = context;
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
        TextView report_number;
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
            holder.report_number = convertView.findViewById(R.id.report_number);
            holder.writer = convertView.findViewById(R.id.writer);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(title.get(position).toString());
        holder.content.setText(content.get(position).toString());
        holder.report_number.setText(report_number.get(position));
        holder.writer.setText(writer.get(position).toString());

        return convertView;
    }
}

