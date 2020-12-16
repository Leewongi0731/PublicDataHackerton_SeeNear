package com.example.hackathonapplication.myhome;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.CertificatesDataset;
import com.example.hackathonapplication.health.HealthFragmentRecyclerViewAdapter;
import com.example.hackathonapplication.health.HealthMovieFragment;
import com.example.hackathonapplication.model.entity.Exercise;

import java.util.ArrayList;

public class CertificatesRecyclerViewAdapter extends RecyclerView.Adapter<CertificatesRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CertificatesDataset> datasets;
    private Activity activity;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    public CertificatesRecyclerViewAdapter(Context context, ArrayList<CertificatesDataset> datasets, Activity activity) {
        this.context = context;
        this.datasets = datasets;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CertificatesRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.certificates_recycler_view_item, parent, false);
        CertificatesRecyclerViewAdapter.ViewHolder vh = new CertificatesRecyclerViewAdapter.ViewHolder(view);

        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CertificatesRecyclerViewAdapter.ViewHolder holder, int position) {
        CertificatesDataset dataSet = datasets.get(position);

        holder.cerificatesNameTextView.setText( dataSet.getUserName() );
        holder.cerificatesEduTitle.setText( dataSet.getEduTitle() );
        holder.cerificatesEduPeriod.setText( dataSet.getStartDay() + "-" + dataSet.getEdnDay() );
        holder.certificatesDayTextView.setText( dataSet.getGiveDay() );


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 팝업 뛰우기?

            }
        };
        holder.cerificatesLayout.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return datasets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout cerificatesLayout;
        private TextView cerificatesNameTextView;
        private TextView cerificatesEduTitle;
        private TextView cerificatesEduPeriod;
        private TextView certificatesDayTextView;


        public ViewHolder(View view) {
            super(view);

            cerificatesLayout = view.findViewById(R.id.cerificatesLayout);
            cerificatesNameTextView = view.findViewById(R.id.cerificatesNameTextView);
            cerificatesEduTitle = view.findViewById(R.id.cerificatesEduTitle);
            cerificatesEduPeriod = view.findViewById(R.id.cerificatesEduPeriod);
            certificatesDayTextView = view.findViewById(R.id.certificatesDayTextView);
        }
    }
}
