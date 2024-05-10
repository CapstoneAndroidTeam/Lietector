package com.example.capstone.Report;

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

import java.util.List;

public class ReportListAdapter extends BaseAdapter {
    private Context context;
    private List<String> reportNumber;
    private List<String> reportType;
    private List<String> reportContent;
    private List<Integer> reportId;
    private LayoutInflater inflater;
    public static int reportTimes;
    public static int reportPostId;
    public static String  reportExistNumber;
    public static String reportExistContent;





    public ReportListAdapter(ReportHistory reportHistory, List<Integer> reportId, List<String> reportNumber, List<String> reportType, List<String> reportContent) {
        this.context = reportHistory;
        this.reportId = reportId;
        this.reportNumber = reportNumber;
        this.reportType = reportType;
        this.reportContent = reportContent;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return reportNumber.size();
    }

    @Override
    public Object getItem(int position) {
        return reportNumber.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageButton editBtn;
        TextView number;
        TextView type;
        TextView contet;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ReportListAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.report_listview, parent, false);


            holder = new ReportListAdapter.ViewHolder();
            holder.number = convertView.findViewById(R.id.phonenumber);
            holder.type = convertView.findViewById(R.id.type);
            holder.contet = convertView.findViewById(R.id.reportContent);
            holder.editBtn = convertView.findViewById(R.id.editbtn);
            reportTimes = reportNumber.size();

            convertView.setTag(holder);
        } else {
            holder = (ReportListAdapter.ViewHolder) convertView.getTag();
        }

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportPostId = reportId.get(position);
                reportExistNumber = reportNumber.get(position);
                reportExistContent = reportContent.get(position);
                Intent intent = new Intent(context.getApplicationContext(), ReportEdit.class);
                context.startActivity(intent);
            }
        });

        holder.number.setText(reportNumber.get(position));
        holder.type.setText(reportType.get(position));
        holder.contet.setText(reportContent.get(position));

        return convertView;
    }
}
