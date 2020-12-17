package com.example.hackathonapplication.job;

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
import com.example.hackathonapplication.data.JobDataset;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class JobFragmentRecyclerViewAdapter extends RecyclerView.Adapter<JobFragmentRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<JobDataset> jobDatasets;

    public JobFragmentRecyclerViewAdapter(Context context, ArrayList<JobDataset> jobDatasets) {
        this.context = context;
        this.jobDatasets = jobDatasets;
    }

    @NonNull
    @Override
    public JobFragmentRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_recycler_view_item, parent, false);
        JobFragmentRecyclerViewAdapter.ViewHolder vh = new JobFragmentRecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull JobFragmentRecyclerViewAdapter.ViewHolder holder, int position) {
        JobDataset jobDataset = jobDatasets.get(position);

        if(jobDataset.getRecommended()) {
            holder.cardViewJobInfo.setCardBackgroundColor(Color.parseColor("#9BFAFF"));
            holder.textViewRecommended.setVisibility(View.VISIBLE);
        } else {
            holder.cardViewJobInfo.setCardBackgroundColor(Color.WHITE);
            holder.textViewRecommended.setVisibility(View.GONE);
        }

        holder.textViewTitle.setText(jobDataset.getTitle());
        holder.textViewGatherDate.setText("접수기간 : " + jobDataset.getStartDate() + " ~ " + jobDataset.getEndDate());
    }

    @Override
    public int getItemCount() {
        return jobDatasets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardViewJobInfo;
        private TextView textViewRecommended;
        private TextView textViewTitle;
        private TextView textViewGatherDate;

        public ViewHolder(View view) {
            super(view);

            cardViewJobInfo = view.findViewById(R.id.cardViewJobInfo);
            textViewRecommended = view.findViewById(R.id.textViewRecommended);
            textViewTitle = view.findViewById(R.id.textViewTitle);
            textViewGatherDate = view.findViewById(R.id.textViewGatherDate);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jobDatasets.get(position).getPageUrl()));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}

