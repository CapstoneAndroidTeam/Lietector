package com.example.capstone.Diagnose;

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

public class MoreInfoWriteListView extends BaseAdapter {
    private Context context;
    private List<Integer> order;
    private List<String> suspicious_word;
    private LayoutInflater inflater;






    public MoreInfoWriteListView(MoreInfoWritePopup qnA, List<Integer> order, List<String> suspicious_word) {
        this.context = qnA;
        this.order = order;
        this.suspicious_word = suspicious_word;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {

        TextView order;
        TextView suspicious_word;
    }

    @Override
    public int getCount() {
        return suspicious_word.size();
    }

    @Override
    public Object getItem(int position) {
        return suspicious_word.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        MoreInfoListView.ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.diangose_morepage_listview, parent, false);

            holder = new MoreInfoListView.ViewHolder();

            holder.order = convertView.findViewById(R.id.suspiciousOrder);
            holder.suspicious_word = convertView.findViewById(R.id.suspiciousWord);



            convertView.setTag(holder);
        } else {
            holder = (MoreInfoListView.ViewHolder) convertView.getTag();
        }


        holder.order.setText(String.valueOf(order.get(position)));
        holder.suspicious_word.setText(suspicious_word.get(position));

        return convertView;
    }

}
