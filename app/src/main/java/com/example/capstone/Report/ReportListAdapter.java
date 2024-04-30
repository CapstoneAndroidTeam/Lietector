package com.example.capstone.Report;

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

public class ReportListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Integer> percent;
    private ArrayList<String> phonenum;
    private ArrayList<String> type;
    private ArrayList<String> times;
    private LayoutInflater inflater;


    public ReportListAdapter(ReportHistory reportHistory, ArrayList<Integer> percent, ArrayList<String> phonenum, ArrayList<String> type, ArrayList<String> reportedtimes) {
        this.context = reportHistory;
        this.percent = percent;
        this.phonenum = phonenum;
        this.type = type;
        this.times = reportedtimes;
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
        ImageView percent;
        TextView phonenum;
        TextView type;
        TextView times;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ReportListAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.report_listview, parent, false);

            holder = new ReportListAdapter.ViewHolder();
            holder.percent = convertView.findViewById(R.id.percent);
            holder.phonenum = convertView.findViewById(R.id.phonenumber);
            holder.type = convertView.findViewById(R.id.type);
            holder.times = convertView.findViewById(R.id.reportedtimes);

            convertView.setTag(holder);
        } else {
            holder = (ReportListAdapter.ViewHolder) convertView.getTag();
        }

        holder.phonenum.setText(phonenum.get(position));
        holder.times.setText(times.get(position));
        holder.type.setText(type.get(position));
        holder.percent.setImageResource(percent.get(position));

        return convertView;
    }
}
