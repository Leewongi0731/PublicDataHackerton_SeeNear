package com.example.hackathonapplication.health;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathonapplication.R;

public class HealthFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button todayTestBtn;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HealthTestFragment healthTestFragment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.health_main_fragment, container, false);
        context = container.getContext();

        todayTestBtn = viewGroup.findViewById(R.id.todayTestBtn);
        todayTestBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                healthTestFragment = new HealthTestFragment();
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout, healthTestFragment).commitAllowingStateLoss();
            }
        });


        return viewGroup;
    }
}