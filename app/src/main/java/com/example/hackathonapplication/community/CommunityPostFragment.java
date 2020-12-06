package com.example.hackathonapplication.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.hackathonapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityPostFragment extends Fragment {

    private ViewGroup viewGroup;
    private Context context;
    private ArrayList<Comment> dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CommentAdapter adapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ImageButton backButton;
    private Button writeCommentButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_post_fragment, container, false);
        context = container.getContext();

        initView();

        return viewGroup;
    }

    private void initView() {
        dataSet = new ArrayList<>();
        dataSet.add(new Comment("백노인", "김노인", "아직", "맞아요 ㅇㅈ", "2시간 전","3"));
        dataSet.add(new Comment("박노인", "김노인", "아직", "나도 좋아해", "2시간 전","3"));
        dataSet.add(new Comment("서노인", "김노인", "아직", "감자팝니다", "2시간 전","3"));
        dataSet.add(new Comment("홍노인", "김노인", "아직", "나훈아쩔어", "2시간 전","3"));

        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.rv_post);
        adapter = new CommentAdapter(context, dataSet);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        backButton = viewGroup.findViewById(R.id.ib_back);
        writeCommentButton = viewGroup.findViewById(R.id.btn_pushComment);
        backButton.setOnClickListener(v -> onClick(v));
        writeCommentButton.setOnClickListener(v->onClick(v));

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                replaceFragment(new CommunityMainFragment());
                break;
            case R.id.btn_pushComment:
                //댓글입력버튼을 누르면 어댑터 갱신하고 뷰 초기화

        }

    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commitAllowingStateLoss();               // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

    }

}
