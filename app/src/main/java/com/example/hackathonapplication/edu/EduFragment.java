package com.example.hackathonapplication.edu;

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
import com.example.hackathonapplication.data.EduDataset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EduFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private TextView textViewLocation;
    private Spinner spinnerSortList;
    private RecyclerView eduRecyclerView;
    private RecyclerView.LayoutManager eduLayoutManager;
    private EduFragmentRecyclerViewAdapter eduAdapter;
    private ArrayList<EduDataset> eduDataset;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.edu_main_fragment, container, false);
        context = container.getContext();

        initLayout();

        textViewLocation.setText("서울특별시 강남구");

        spinnerSortList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerSortList.getSelectedItemPosition() >= 0) {
                    String selectedItem = spinnerSortList.getSelectedItem().toString();

                    switch(selectedItem) {
                        case "모집기간순":
                            Collections.sort(eduDataset, new GatherDateDescending());
                            break;
                        case "교육기간순":
                            Collections.sort(eduDataset, new EduDateDescending());
                            break;
                        case "이름순":
                            Collections.sort(eduDataset, new ContentsDescending());
                            break;
                    }
                    eduAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return viewGroup;
    }

    private void initLayout() {
        eduDataset = new ArrayList<>();
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[인생설계-온라인]나를 바꾸는 하루공부'스마트폰 PRO 유튜버 도전하기 심화과정' (수익 채널로 만드는)", "2020.11.18~2020.12.17", "2020.12.17~2020.12.17", "https://50plus.or.kr/education-detail.do?id=10162464"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[인생설계-온라인]나를 바꾸는 하루공부'스마트폰 PRO 유튜버 도전하기 심화과정' (홍보영상제작의 모든것)", "2020.11.06~2020.12.17", "2020.12.11~2020.12.17", "https://50plus.or.kr/education-detail.do?id=10162219"));
        eduDataset.add(new EduDataset(true,"성북센터", "수강신청", "[문화조성] 집에서 즐기는 세계 인기 브런치 [12월 29일 매콤 미나리 쌀국수 볶음]", "2020.10.29~2020.12.29", "2020.12.29~2020.12.29", "https://50plus.or.kr/education-detail.do?id=9882145"));
        eduDataset.add(new EduDataset(true,"성북센터", "수강신청", "[문화조성] 집에서 즐기는 세계 인기 브런치 [12월 22일 누룽지 견과 범벅]", "2020.10.29~2020.12.22", "2020.12.22~2020.12.22", "https://50plus.or.kr/education-detail.do?id=9882106"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[문화조성] 집에서 즐기는 세계 인기 브런치 [12월 15일 투움바 파스타]", "2020.10.29~2020.12.15", "2020.12.15~2020.12.15", "https://50plus.or.kr/education-detail.do?id=9882080"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[인생설계-온라인] 나의 버킷리스트 ‘100일간의 세계일주’ (실시간 유튜브 온라인 특강-유럽편)", "2020.10.23~2020.12.10", "2020.12.10~2020.12.10", "https://50plus.or.kr/education-detail.do?id=9811493"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[인생설계-온라인] 나를 바꾸는 하루공부 (시간을 정리하는 라이프저널)", "2020.12.04~2020.12.23", "2020.12.23~2020.12.23", "https://50plus.or.kr/education-detail.do?id=10367715"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[전통아카데미-온라인] 한자놀이지도사 양성과정", "2020.12.14~2020.12.16", "2020.12.16~2020.12.31", "https://50plus.or.kr/education-detail.do?id=10434789"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[전통아카데미-온라인] 역사논술지도사 양성과정", "2020.12.14~2020.12.16", "2020.12.16~2020.12.31", "https://50plus.or.kr/education-detail.do?id=10433466"));
        eduDataset.add(new EduDataset(false,"성북센터", "수강신청", "[경력-온라인] ]50+ 1인 크리에이터 전문가 양성", "2020.09.21~2020.10.04", "2020.10.06~2020.11.24", "https://50plus.or.kr/education-detail.do?id=9440244"));

        textViewLocation = viewGroup.findViewById(R.id.textViewLocation);
        spinnerSortList = viewGroup.findViewById(R.id.spinnerSortList);
        eduRecyclerView = viewGroup.findViewById(R.id.eduRecyclerView);

        eduLayoutManager = new LinearLayoutManager(context);
        eduRecyclerView.setLayoutManager(eduLayoutManager);

        eduAdapter = new EduFragmentRecyclerViewAdapter(context, eduDataset);
        eduRecyclerView.setAdapter(eduAdapter);
    }
}

class GatherDateDescending implements Comparator<EduDataset> {
    @Override
    public int compare(EduDataset o1, EduDataset o2) {
        if (o1.getRecommended() || o2.getRecommended()) {
            return o2.getRecommended().compareTo(o1.getRecommended());
        } else {
            return o2.getGatherDate().compareTo(o1.getGatherDate());
        }
    }
}

class EduDateDescending implements Comparator<EduDataset> {
    @Override
    public int compare(EduDataset o1, EduDataset o2) {
        if (o1.getRecommended() || o2.getRecommended()) {
            return o2.getRecommended().compareTo(o1.getRecommended());
        } else {
            return o2.getEduDate().compareTo(o1.getEduDate());
        }
    }
}

class ContentsDescending implements Comparator<EduDataset> {
    @Override
    public int compare(EduDataset o1, EduDataset o2) {
        if (o1.getRecommended() || o2.getRecommended()) {
            return o2.getRecommended().compareTo(o1.getRecommended());
        } else {
            return o2.getContent().compareTo(o1.getContent());
        }
    }
}