package com.example.hackathonapplication.health;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.MVDataset;

import java.util.ArrayList;

public class HealthFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HealthFragmentRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MVDataset> MVDataset;
    private HealthFragment healthFragment;
    private Activity activity;
    public HealthFragmentRecyclerViewAdapter(Context context, ArrayList<MVDataset> MVDataset, Activity activity) {
        this.context = context;
        this.MVDataset = MVDataset;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_recycler_view_item, parent, false);
        HealthFragmentRecyclerViewAdapter.ViewHolder vh = new HealthFragmentRecyclerViewAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MVDataset dataSet = MVDataset.get(position);

        holder.healthMVThumbnailImageView.setImageResource( (int)dataSet.getThumbnailPath() );
        holder.healthMVTitle.setText(dataSet.getExercise());
        holder.healthMVContent.setText(dataSet.getContent());


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).replaceFragment( new HealthMovieFragment() );
            }
        };
        holder.layout.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return MVDataset.size();
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
