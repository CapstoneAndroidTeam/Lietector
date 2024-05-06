package com.example.capstone.QnA;

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

public class QnAListAdapter extends BaseAdapter {
    private Context context;
    private List<String> title;
    private List<String> content;
    private LayoutInflater inflater;

    public QnAListAdapter(QnA qnA, List<String> title, List<String> content) {
        this.context = qnA;
        this.title = title;
        this.content = content;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        TextView title;
        TextView content;
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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.qna_listview, parent, false);

            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.QTitle);
            holder.content = convertView.findViewById(R.id.story);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(title.get(position));
        holder.content.setText(content.get(position));

        return convertView;
    }
}
