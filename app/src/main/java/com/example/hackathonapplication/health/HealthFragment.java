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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.MVDataset;
import com.example.hackathonapplication.model.entity.Exercise;
import com.example.hackathonapplication.sqlite.refactored.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

public class HealthFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button todayTestBtn;
    private ArrayList<Exercise> exercises;
    private RecyclerView.LayoutManager healthLayoutManager;
    private RecyclerView healthMVRecyclerView;
    private HealthFragmentRecyclerViewAdapter healthAdapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.health_main_fragment, container, false);
        context = container.getContext();

        initLayout();

        todayTestBtn = viewGroup.findViewById(R.id.todayTestBtn);
        todayTestBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                //((MainActivity)getActivity()).replaceFragment( new HealthTestFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                transaction.replace(R.id.frameLayout, new HealthTestFragment());
                transaction.addToBackStack("HealthTest");
                transaction.commit();
            }
        });

        return viewGroup;
    }

    private void initLayout() {
        List<String> prescriptionList = getNewlyPrescription();

        exercises = new ArrayList<>();
        String prescription = "";
        ExerciseRepository exerciseRepository = new ExerciseRepository(context);
        exerciseRepository.connect();
        for(int i = 0; i < prescriptionList.size() ; i++){
            prescription = prescriptionList.get(i);
            Exercise Exercise = exerciseRepository.findByPrescription( prescription );
            exercises.add( Exercise );
        }
        exerciseRepository.close();

        healthMVRecyclerView = viewGroup.findViewById(R.id.healthMVRecyclerView);

        healthLayoutManager = new LinearLayoutManager(context);
        healthMVRecyclerView.setLayoutManager(healthLayoutManager);

        healthAdapter = new HealthFragmentRecyclerViewAdapter(context, exercises, getActivity());
        healthMVRecyclerView.setAdapter(healthAdapter);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
    }

    private List<String> getNewlyPrescription(){
        // 로그인 유저의 최근 추천운동 이름리스트를 가져옴
        List<String> prescriptionList = new ArrayList<String>();
        prescriptionList.add( "어깨 스트레칭" );
        prescriptionList.add( "발바닥 치기" );
        prescriptionList.add( "몸통 비틀기" );
        prescriptionList.add( "종아리 스트레칭" );

        return prescriptionList;
    }


}
