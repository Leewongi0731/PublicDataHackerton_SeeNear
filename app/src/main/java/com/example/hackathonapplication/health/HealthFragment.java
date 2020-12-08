package com.example.hackathonapplication.health;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.MVDataset;
import com.example.hackathonapplication.edu.EduFragmentRecyclerViewAdapter;

import java.util.ArrayList;

public class HealthFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button todayTestBtn;
    private ArrayList<MVDataset> mvDataset;
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
        mvDataset = new ArrayList<>();
        mvDataset.add( new MVDataset( "어깨 스트레칭", "윗몸을 힘껏 일으킨다", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/266/266.mp4" ,R.drawable.health_example1) );
        mvDataset.add( new MVDataset( "발바닥 치기", "언제쯤 잘 수 있을까", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/350/350.mp4", R.drawable.health_example2 ) );
        mvDataset.add( new MVDataset( "몸통 비틀기", "아마 못자겠지", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/278/278.mp4", R.drawable.health_example1 ) );
        mvDataset.add( new MVDataset( "종아리 스트레칭", "엎어져 자면 건강에 좋다.", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/277/277.mp4", R.drawable.health_example2 ) );

        healthMVRecyclerView = viewGroup.findViewById(R.id.healthMVRecyclerView);

        healthLayoutManager = new LinearLayoutManager(context);
        healthMVRecyclerView.setLayoutManager(healthLayoutManager);

        healthAdapter = new HealthFragmentRecyclerViewAdapter(context, mvDataset, getActivity());
        healthMVRecyclerView.setAdapter(healthAdapter);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
    }
}
