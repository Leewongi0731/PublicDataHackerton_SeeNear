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

public class HealthTestResultFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button healthTestResultBackBtn;
    private Button healthTestReturnBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.health_today_test_result_fragment, container, false);
        context = container.getContext();

        healthTestResultBackBtn = viewGroup.findViewById(R.id.healthTestResultBackBtn);
        healthTestResultBackBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment( new HealthTestFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        healthTestReturnBtn = viewGroup.findViewById(R.id.healthTestReturnBtn);
        healthTestReturnBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment( new HealthFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });

        return viewGroup;
    }
}