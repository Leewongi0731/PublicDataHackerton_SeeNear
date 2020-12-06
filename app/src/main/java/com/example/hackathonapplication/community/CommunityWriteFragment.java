package com.example.hackathonapplication.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hackathonapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityWriteFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ImageButton backButton;
    private Button finishWriteButton;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_write_fragment, container, false);
        context = container.getContext();

        initView();

        return viewGroup;
    }

    private void initView() {

        backButton = viewGroup.findViewById(R.id.ib_back);
        finishWriteButton = viewGroup.findViewById(R.id.btn_finish);
        backButton.setOnClickListener(v -> onClick(v));
        finishWriteButton.setOnClickListener(v -> onClick(v));

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
            case R.id.btn_finish:                                                               //액티비티가 아니니까 애초에 쌓일게 없음. 프래그먼트 지정해줘야함
                replaceFragment(new CommunityMainFragment());
                break;
        }

    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commitAllowingStateLoss();               // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

    }

}
