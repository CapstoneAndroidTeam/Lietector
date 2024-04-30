package com.example.capstone.Ban;

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

public class BanListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> phonenum;
    private ArrayList<String> type;
    private ArrayList<String> date;
    private LayoutInflater inflater;
    public BanListAdapter(BanHistory banHistory, ArrayList<String> phonenum, ArrayList<String> type, ArrayList<String> date) {
        this.context = banHistory;
        this.phonenum = phonenum;
        this.type = type;
        this.date = date;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return phonenum.size();
    }

    @Override
    public Object getItem(int position) {
        return phonenum.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView phonenum;
        TextView type;
        TextView date;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BanListAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.banhistory_listview, parent, false);

            holder = new BanListAdapter.ViewHolder();
            holder.phonenum = convertView.findViewById(R.id.phonenumber);
            holder.type = convertView.findViewById(R.id.type);
            holder.date = convertView.findViewById(R.id.date);

            convertView.setTag(holder);
        } else {
            holder = (BanListAdapter.ViewHolder) convertView.getTag();
        }

        holder.phonenum.setText(phonenum.get(position));
        holder.type.setText(type.get(position));
        holder.date.setText(date.get(position));

        return convertView;
    }
}
