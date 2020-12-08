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
import com.example.hackathonapplication.sqlite.ExercisePrescriptionDbOpenHelper;

import java.util.ArrayList;

public class HealthFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HealthFragmentRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MVDataset> MVDataset;
    private HealthFragment healthFragment;
    private Activity activity;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    public HealthFragmentRecyclerViewAdapter(Context context, ArrayList<MVDataset> MVDataset, Activity activity) {
        this.context = context;
        this.MVDataset = MVDataset;
        this.activity = activity;
        insertDB( this.MVDataset );
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
        MVDataset dataSet = MVDataset.get(position);

        holder.healthMVThumbnailImageView.setImageResource( (int)dataSet.getThumbnailPath() );
        holder.healthMVTitle.setText(dataSet.getExercise());
        holder.healthMVContent.setText(dataSet.getContent());

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // frame간 parameter전달
                Bundle args = new Bundle();
                args.putString("videoPath", dataSet.getVideoPath()); // key value를 Bundle에 담아서 파라미터로 전송

                HealthMovieFragment healthMovieFragment = new HealthMovieFragment();
                healthMovieFragment.setArguments(args);
                //((MainActivity) activity).replaceFragment( healthMovieFragment );
                transaction.replace(R.id.frameLayout, new HealthMovieFragment());
                transaction.addToBackStack("HealthMovie");
                transaction.commit();
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

    public void insertDB( ArrayList<MVDataset> MVDataset ){
        ExercisePrescriptionDbOpenHelper dbOpenHelper = new ExercisePrescriptionDbOpenHelper(context);
        dbOpenHelper.open();
        dbOpenHelper.create();

        for( int i = 0 ; i<MVDataset.size() ; i++ ){
            MVDataset mvDataset = MVDataset.get(i);
            dbOpenHelper.insertRow( mvDataset.getExercise(), mvDataset.getVideoPath(), mvDataset.getContent());
        }
    }
}
