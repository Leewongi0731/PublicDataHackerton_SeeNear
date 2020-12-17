package com.example.hackathonapplication.myhome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.data.CertificatesDataset;
import com.example.hackathonapplication.health.HealthFragmentRecyclerViewAdapter;

import java.util.ArrayList;

public class MyHomeFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ImageView myProfile;
    private LinearLayout myLocation;
    private Object MypageClickListener;

    private RecyclerView.LayoutManager certificatesLayoutManager;
    private RecyclerView certificatesRecyclerView;
    private CertificatesRecyclerViewAdapter certificatesAdapter;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_main_fragment, container, false);
        context = container.getContext();

        initLayout();

        myProfile = (ImageView) viewGroup.findViewById( R.id.myProfile );
        myLocation = (LinearLayout) viewGroup.findViewById( R.id.myLocation );

        myProfile.setOnClickListener(this.click);
        myLocation.setOnClickListener(this.click);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        return viewGroup;
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myProfile:
                    Toast.makeText(context, "myProfile", Toast.LENGTH_SHORT).show();
                    // btn1 동작
                    break;
                case R.id.myLocation:
                    //((MainActivity)getActivity()).replaceFragment( new ChangeLocationFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                    transaction.replace(R.id.frameLayout, new ChangeLocationFragment());
                    transaction.addToBackStack("ChangeLocation");
                    transaction.commit();
                    break;
            }
        }
    };

    private void initLayout(){
        ArrayList<CertificatesDataset> datasets = new ArrayList<CertificatesDataset>();
        datasets.add( new CertificatesDataset( "이원기", "집에서 즐기는 세계 인기 브런치 교육", "20.12.12", "20.12.13", "2020년 12월 18일" ) );
        datasets.add( new CertificatesDataset( "이원기", "나를 바꾸는 스마트폰 PRO 교육", "20.12.16", "20.12.17", "2020년 12월 15일" ) );
        datasets.add( new CertificatesDataset( "이원기", "IT 교육", "20.12.20", "20.12.22", "2020년 12월 22일" ) );

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        certificatesRecyclerView = viewGroup.findViewById(R.id.certificatesRecyclerView);

        certificatesLayoutManager = new LinearLayoutManager(context);
        certificatesRecyclerView.setLayoutManager(certificatesLayoutManager);
        certificatesRecyclerView.setLayoutManager(layoutManager);  // 세로로 나오게 설정

        certificatesAdapter = new CertificatesRecyclerViewAdapter(context, datasets, getActivity());
        certificatesRecyclerView.setAdapter(certificatesAdapter);

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
    }
}
