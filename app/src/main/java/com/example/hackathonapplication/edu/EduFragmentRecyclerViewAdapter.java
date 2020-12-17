package com.example.hackathonapplication.edu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.EduDataset;

import java.util.ArrayList;

public class EduFragmentRecyclerViewAdapter extends RecyclerView.Adapter<EduFragmentRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<EduDataset> eduDataset;

    public EduFragmentRecyclerViewAdapter(Context context, ArrayList<EduDataset> eduDataset) {
        this.context = context;
        this.eduDataset = eduDataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edu_recycler_view_item, parent, false);
        EduFragmentRecyclerViewAdapter.ViewHolder vh = new EduFragmentRecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EduDataset dataSet = eduDataset.get(position);

        if(dataSet.getRecommended()) {
            holder.cardViewEduInfo.setCardBackgroundColor(Color.parseColor("#ACA4FF"));
            holder.textViewRecommended.setVisibility(View.VISIBLE);
        } else {
            holder.cardViewEduInfo.setCardBackgroundColor(Color.WHITE);
            holder.textViewRecommended.setVisibility(View.GONE);
        }
        holder.textViewContents.setText(dataSet.getContent());
        holder.textViewGatherDate.setText("모집기간 : " + dataSet.getGatherDate());
    }

    @Override
    public int getItemCount() {
        return eduDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardViewEduInfo;
        private TextView textViewRecommended;
        private TextView textViewContents;
        private TextView textViewGatherDate;

        public ViewHolder(View view) {
            super(view);

            cardViewEduInfo = view.findViewById(R.id.cardViewEduInfo);
            textViewRecommended = view.findViewById(R.id.textViewRecommended);
            textViewContents = view.findViewById(R.id.textViewContents);
            textViewGatherDate = view.findViewById(R.id.textViewGatherDate);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(eduDataset.get(position).getPlusUrl()));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
