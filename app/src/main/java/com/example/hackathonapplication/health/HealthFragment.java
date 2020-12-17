package com.example.hackathonapplication.health;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.example.hackathonapplication.LoadingActivity;
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
        String[] prescriptionList = getNewlyPrescription();

        exercises = new ArrayList<>();
        String prescription = "";
        ExerciseRepository exerciseRepository = new ExerciseRepository(context);
        exerciseRepository.connect();
        for(int i = 0; i < prescriptionList.length ; i++){
            prescription = prescriptionList[i];
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

    private String[] getNewlyPrescription(){
        // 로그인 유저의 최근 추천운동 이름리스트를 가져옴0.
        if ( LoadingActivity.LOGIN_USER_RECOMMEND_KEY.equals(  "60대/비만전단계비만/M/금" ) ){
            String[] gold_recommed = { "짐볼에서 윗몸 일으키기","등 대고 대퇴이두근 스트레칭","척추 스트레칭","스텝박스","몸통 비틀기","대퇴이두근 스트레칭","물병 들고 한발 앞으로 내밀고 앉았다 일어서기","가슴/어깨 스트레칭" };
            return gold_recommed;
        }else if( LoadingActivity.LOGIN_USER_RECOMMEND_KEY.equals(  "60대/비만전단계비만/M/은" ) ){
            String[] silver_recommed = { "척추 스트레칭","대퇴사두근 스트레칭","내전근 스트레칭","발등굽힘/발바닥굽힘","몸통 비틀기","대퇴이두근 스트레칭","고관절 스트레칭","물병 들고 한발 앞으로 내밀고 앉았다 일어서기","가슴/어깨 스트레칭" };
            return silver_recommed;
        }else{
            String[] bronze_recommed = { "달리기","척추 스트레칭","대퇴사두근 스트레칭","내전근 스트레칭","몸통 비틀기","대퇴이두근 스트레칭","고관절 스트레칭","물병 들고 한발 앞으로 내밀고 앉았다 일어서기","가슴/어깨 스트레칭" };
            return bronze_recommed;
        }
    }
}
