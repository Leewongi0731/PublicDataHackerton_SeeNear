package com.example.hackathonapplication.health;

import android.annotation.SuppressLint;
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

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.MVDataset;
import com.example.hackathonapplication.model.entity.Exercise;
import com.example.hackathonapplication.sqlite.ExercisePrescriptionDbOpenHelper;

import java.util.ArrayList;

public class HealthFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HealthFragmentRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Exercise> exercises;
    private HealthFragment healthFragment;
    private Activity activity;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    public HealthFragmentRecyclerViewAdapter(Context context, ArrayList<Exercise> exercises, Activity activity) {
        this.context = context;
        this.exercises = exercises;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_recycler_view_item, parent, false);
        HealthFragmentRecyclerViewAdapter.ViewHolder vh = new HealthFragmentRecyclerViewAdapter.ViewHolder(view);

        fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise dataSet = exercises.get(position);

    //    holder.healthMVThumbnailImageView.setImageResource( (int)dataSet.getThumbnailPath() );
        holder.healthMVThumbnailImageView.setImageResource( R.drawable.health_example1 );
        holder.healthMVTitle.setText(dataSet.getPrescription());

        String mvContent = dataSet.getContents();
        if( mvContent.length() > 15 ){
            holder.healthMVContent.setText(mvContent.substring(0,13) + "..." ) ;
        }else{
            holder.healthMVContent.setText(mvContent);
        }


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // frame간 parameter전달
                Bundle args = new Bundle();
                args.putString("prescription", dataSet.getPrescription()); // key value를 Bundle에 담아서 파라미터로 전송
                args.putString("contents", dataSet.getContents());
                args.putString("videoPath", dataSet.getVideopath());

                HealthMovieFragment healthMovieFragment = new HealthMovieFragment();
                healthMovieFragment.setArguments(args);
                transaction.replace(R.id.frameLayout, healthMovieFragment);
                transaction.addToBackStack("HealthMovie");
                transaction.commit();
            }
        };
        holder.layout.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView healthMVThumbnailImageView;
        private TextView healthMVTitle;
        private TextView healthMVContent;

        public ViewHolder(View view) {
            super(view);

            layout = view.findViewById(R.id.healthMVView);
            healthMVThumbnailImageView = view.findViewById(R.id.healthMVThumbnailImageView);
            healthMVTitle = view.findViewById(R.id.healthMVTitle);
            healthMVContent = view.findViewById(R.id.healthMVContent);
        }
    }
}
