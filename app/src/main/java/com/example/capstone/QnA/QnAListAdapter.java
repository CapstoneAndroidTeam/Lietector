package com.example.capstone.QnA;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class QnAListAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> id;
    private List<String> title;
    private List<String> content;
    private LayoutInflater inflater;

    public static int postId;
    public static String existTitle;
    public static String existContent;





    public QnAListAdapter(QnA qnA, List<Integer> id,List<String> title, List<String> content) {
        this.context = qnA;
        this.id = id;
        this.title = title;
        this.content = content;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {

        TextView title;
        TextView content;
        ImageButton editBtn;
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
            holder.editBtn = convertView.findViewById(R.id.editbtn);

            holder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "id list 2 : " + id.size());
                    postId = id.get(position);
                    Log.d(TAG, "postId2 : " + postId);
                    Log.d(TAG, "position2 : " + position);
                    existTitle = title.get(position);
                    existContent = content.get(position);
                    Intent intent = new Intent(context.getApplicationContext(), QnAEdit.class);
                    context.startActivity(intent);
                }
            });


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.title.setText(title.get(position));
        holder.content.setText(content.get(position));

        return convertView;
    }
}
