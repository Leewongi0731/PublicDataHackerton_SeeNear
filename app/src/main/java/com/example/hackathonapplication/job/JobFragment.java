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
        jobDatasets.add(new JobDataset(false,"『지역 라이프 코디네이터』 참여자 추가 모집","보람일자리사업단","서울시","02-6295-5066","2020-11-02","2020-11-08","12","모집중","https://50plus.or.kr/appView.do?ANN_NO=619&setPageIndex=1"));
        jobDatasets.add(new JobDataset(false,"[아이돌봄지원단] 참여자 모집 공고","보람일자리사업단","서울시","02-460-5084","2020-11-03","2020-11-09","70","모집중","https://50plus.or.kr/appView.do?ANN_NO=618&setPageIndex=1"));
        jobDatasets.add(new JobDataset(false,"50+데이터큐레이터 참여자 추가모집","서울문화네트워크","서울시","010-5447-2176","2020-10-15","2020-10-19","1","마감","https://50plus.or.kr/appView.do?ANN_NO=617&setPageIndex=1"));
        jobDatasets.add(new JobDataset(false,"50+마을활동가 참여자 모집","공생사회적협동조합","서울시","02-326-0301","2020-10-05","2020-10-06","3","마감","https://50plus.or.kr/appView.do?ANN_NO=615&setPageIndex=1"));
        jobDatasets.add(new JobDataset(false,"[서울시니어클럽] 2021년 노인일자리","서울노원시니어클럽","서울시","02-549-7070","2020.12.01","2020.12.04","20","모집중","http://www.goldenjob.or.kr/job/find-person_view.asp?idx=168&p=1&keyword=&keyfield="));
        jobDatasets.add(new JobDataset(false,"[서울시니어클럽] 2021년 사회활동지원사업","서울강서시니어클럽","서울시","02-549-7070","2020.11.23","2020.12.18","30","모집중","http://www.goldenjob.or.kr/job/find-person_view.asp?idx=167&p=1&keyword=&keyfield="));
        jobDatasets.add(new JobDataset(true,"[50+센터] 2021 노인사회활동_시장형","서울강서시니어클럽","서울시","02-549-7070","2020.12.07","2020.12.18","10","모집중","http://www.goldenjob.or.kr/job/find-person_view.asp?idx=164&p=1&keyword=&keyfield="));
        jobDatasets.add(new JobDataset(true,"[50+센터] 2021 노인사회활동_공익형","서울강서시니어클럽","서울시","02-549-7070","2020.11.23","2020.12.18","5","모집중","http://www.goldenjob.or.kr/job/find-person_view.asp?idx=163&p=1&keyword=&keyfield="));
        jobDatasets.add(new JobDataset(false,"백상빌딩관리위원회","백상빌딩관리위원회","서대문구","02-6220-8640","2020.12.01","2020.12.13","15","모집중","http://www.goldenjob.or.kr/job/find-person_view.asp?idx=153&p=1&keyword=&keyfield="));
        jobDatasets.add(new JobDataset(false,"[공공근로] 서울시민 안심일자리 사","서울강서시니어클럽","중구","02-549-7070","2020.11.18","2020.12.04","130","모집중","http://www.goldenjob.or.kr/job/find-person_view.asp?idx=152&p=1&keyword=&keyfield="));

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