package com.example.hackathonapplication.community;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Post> postDataset;

    public PostAdapter(Context context,ArrayList<Post> postDataset) {
        this.context = context;
        this.postDataset = postDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.community_category_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // 임시데이터
        Post data = postDataset.get(position);

        holder.iv_profile.setImageResource(R.drawable.im_sample_profile);
        holder.tv_writer.setText(data.getWriter());
        holder.tv_contents.setText(data.getContents());
        holder.tv_date.setText(data.getDate());
    }

    @Override
    public int getItemCount() {
        return postDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_profile;
        private TextView tv_writer;
        private TextView tv_contents;
        private TextView tv_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            tv_writer = itemView.findViewById(R.id.tv_writer);
            tv_contents = itemView.findViewById(R.id.tv_contents);
            tv_date = itemView.findViewById(R.id.tv_date);
        }

    }

}
