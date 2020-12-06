package com.example.hackathonapplication.job;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.EduDataset;
import com.example.hackathonapplication.data.JobDataset;

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

        holder.textViewTitle.setText(jobDataset.getTitle());
        holder.buttonRegister.setText(jobDataset.getIsGathering());
        holder.textViewBusinessName.setText("사업체명 : " + jobDataset.getBusinessName());
        holder.textViewLocation.setText("일자리 위치 : " + jobDataset.getLocation());
        holder.textViewNumOfPeople.setText("모집인원 : " + jobDataset.getNumOfPeople());
        holder.textViewGatherDate.setText("접수기간 : " + jobDataset.getStartDate() + " ~ " + jobDataset.getEndDate());

        holder.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(jobDataset.getPageUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobDatasets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private Button buttonRegister;
        private TextView textViewBusinessName;
        private TextView textViewLocation;
        private TextView textViewNumOfPeople;
        private TextView textViewGatherDate;

        public ViewHolder(View view) {
            super(view);

            textViewTitle = view.findViewById(R.id.textViewTitle);
            buttonRegister = view.findViewById(R.id.buttonRegister);
            textViewBusinessName = view.findViewById(R.id.textViewBusinessName);
            textViewLocation = view.findViewById(R.id.textViewLocation);
            textViewNumOfPeople = view.findViewById(R.id.textViewNumOfPeople);
            textViewGatherDate = view.findViewById(R.id.textViewGatherDate);
        }
    }
}

