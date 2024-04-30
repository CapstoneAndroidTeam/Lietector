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

import java.util.ArrayList;

public class QnAListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> title;
    private ArrayList<String> story;
    private ArrayList<String> answer;
    private LayoutInflater inflater;
    public QnAListAdapter(QnA qnA, ArrayList<String> Title, ArrayList<String> Story, ArrayList<String> Answer) {
        this.context = qnA;
        this.title = Title;
        this.story = Story;
        this.answer = Answer;
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
        TextView Title;
        TextView Story;
        TextView Answer;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        QnAListAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.qna_listview, parent, false);

            holder = new QnAListAdapter.ViewHolder();
            holder.Title = convertView.findViewById(R.id.QTitle);
            holder.Story = convertView.findViewById(R.id.story);
            holder.Answer = convertView.findViewById(R.id.Answer);

            convertView.setTag(holder);
        } else {
            holder = (QnAListAdapter.ViewHolder) convertView.getTag();
        }

        holder.Title.setText(title.get(position));
        holder.Story.setText(story.get(position));
        holder.Answer.setText(answer.get(position));

        return convertView;
    }
}
