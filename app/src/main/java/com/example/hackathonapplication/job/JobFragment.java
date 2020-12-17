package com.example.hackathonapplication.job;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.JobDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class JobFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private TextView textViewLocation;
    private Spinner spinnerSortList;
    private RecyclerView jobRecyclerView;
    private RecyclerView.LayoutManager jobLayoutManager;
    private JobFragmentRecyclerViewAdapter jobAdapter;
    private ArrayList<JobDataset> jobDatasets;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.job_main_fragment, container, false);
        context = container.getContext();

        initLayout();

        textViewLocation.setText("서울특별시 강남구");

        spinnerSortList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerSortList.getSelectedItemPosition() >= 0) {
                    String selectedItem = spinnerSortList.getSelectedItem().toString();

                    switch(selectedItem) {
                        case "접수기간순":
                            Collections.sort(jobDatasets, new StartDateDescending());
                            break;
                        case "이름순":
                            Collections.sort(jobDatasets, new NameAscending());
                            break;
                        case "모집인원순":
                            Collections.sort(jobDatasets, new NumOfPeopleDescending());
                            break;
                    }
                    jobAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return viewGroup;
    }

    private void initLayout() {
        jobDatasets = new ArrayList<>();
        jobDatasets.add(new JobDataset(true,"경로당 중식도우미(경로당도우미파견활동단)","사회복지법인 영산","서초구","02-622-3012","2020.12.01","2020.12.17","30","모집중","https://seniorro.or.kr:4431/seniorro/main/main.do"));
        jobDatasets.add(new JobDataset(false,"공원 및 놀이터 안전지킴이","대한노인회 강남구지회","강남구","02-699-1234","2020.12.01","2020.12.18","30","모집중","https://seniorro.or.kr:4431/seniorro/main/main.do"));
        jobDatasets.add(new JobDataset(true,"경로당청소도우미","사회복지법인 영산","성동구","02-624-5285","2020.11.31","2020.12.11","20","마감","https://seniorro.or.kr:4431/seniorro/main/main.do"));
        jobDatasets.add(new JobDataset(false,"지하철안전지킴이","강남노인종합복지관","강남구","02-549-7070","2020.11.30","2020.12.14","40","마감","https://seniorro.or.kr:4431/seniorro/main/main.do"));
        jobDatasets.add(new JobDataset(false,"복지시설 이용자 공익서비스 제공활동(누리선생님)","강남구립논현노인종합복지관","강남구","02-549-7070","2020.12.05","2020.12.15","30","모집중","https://seniorro.or.kr:4431/seniorro/main/main.do"));

        textViewLocation = viewGroup.findViewById(R.id.textViewLocation);
        spinnerSortList = viewGroup.findViewById(R.id.spinnerSortList);
        jobRecyclerView = viewGroup.findViewById(R.id.jobRecyclerView);

        jobLayoutManager = new LinearLayoutManager(context);
        jobRecyclerView.setLayoutManager(jobLayoutManager);

        jobAdapter = new JobFragmentRecyclerViewAdapter(context, jobDatasets);
        jobRecyclerView.setAdapter(jobAdapter);
    }
}

class StartDateDescending implements Comparator<JobDataset> {
    @Override
    public int compare(JobDataset o1, JobDataset o2) {
        if (o1.getRecommended() || o2.getRecommended()) {
            return o2.getRecommended().compareTo(o1.getRecommended());
        } else {
            return o1.getStartDate().compareTo(o2.getStartDate());
        }
    }
}

class NameAscending implements Comparator<JobDataset> {
    @Override
    public int compare(JobDataset o1, JobDataset o2) {
        if (o1.getRecommended() || o2.getRecommended()) {
            return o2.getRecommended().compareTo(o1.getRecommended());
        } else {
            return o1.getBusinessName().compareTo(o2.getBusinessName());
        }
    }
}

class NumOfPeopleDescending implements Comparator<JobDataset> {
    @Override
    public int compare(JobDataset o1, JobDataset o2) {
        if (o1.getRecommended() || o2.getRecommended()) {
            return o2.getRecommended().compareTo(o1.getRecommended());
        } else {
            return o2.getNumOfPeople().compareTo(o1.getNumOfPeople());
        }
    }
}