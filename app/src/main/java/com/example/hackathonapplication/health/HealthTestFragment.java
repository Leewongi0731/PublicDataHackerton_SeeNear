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

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;

public class HealthTestFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button healthTestBackBtn;
    private Button healthTestCompleteBtn;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.health_today_test_fragment, container, false);
        context = container.getContext();

        healthTestBackBtn = viewGroup.findViewById(R.id.healthTestBackBtn);
        healthTestBackBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment( new HealthFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        healthTestCompleteBtn = viewGroup.findViewById(R.id.healthTestCompleteBtn);
        healthTestCompleteBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment( new HealthTestResultFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        return viewGroup;
    }
}