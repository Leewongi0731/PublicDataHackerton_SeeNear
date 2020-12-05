package com.example.hackathonapplication.edu;

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

        holder.textViewCampus.setText(dataSet.getCampus());
        holder.buttonRegister.setText(dataSet.getIsRegister());
        holder.textViewContents.setText(dataSet.getContent());
        holder.textViewGatherDate.setText("모집기간 : " + dataSet.getGatherDate());
        holder.textViewEduDate.setText("교육기간 : " + dataSet.getEduDate());

        holder.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(dataSet.getPlusUrl()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eduDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCampus;
        private Button buttonRegister;
        private TextView textViewContents;
        private TextView textViewGatherDate;
        private TextView textViewEduDate;

        public ViewHolder(View view) {
            super(view);

            textViewCampus = view.findViewById(R.id.textViewCampus);
            buttonRegister = view.findViewById(R.id.buttonRegister);
            textViewContents = view.findViewById(R.id.textViewContents);
            textViewGatherDate = view.findViewById(R.id.textViewGatherDate);
            textViewEduDate = view.findViewById(R.id.textViewEduDate);
        }
    }
}
