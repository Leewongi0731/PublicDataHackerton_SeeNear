package com.example.hackathonapplication.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapplication.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CommunityCommentFragment extends Fragment {

    private ViewGroup viewGroup;
    private Context context;
    private ArrayList<Comment> dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CommentAdapter adapter;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private ImageButton backButton;
    private Button pushCommentButton;
    private ImageView imageViewProfile;
    private TextView textViewWriter;
    private TextView textViewContents;
    private TextView textViewDate;
    private EditText editTextComment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_comment_fragment, container, false);
        context = container.getContext();

        initView();

        return viewGroup;
    }

    private void initView() {
        dataSet = new ArrayList<>();
        dataSet.add(new Comment("이미지", "백노인", "아직", "맞아요 ㅇㅈ", "2시간 전","3"));
        dataSet.add(new Comment("이미지", "박노인", "아직", "나도 좋아해", "2시간 전","3"));
        dataSet.add(new Comment("이미지", "서노인", "아직", "감자팝니다", "2시간 전","3"));
        dataSet.add(new Comment("이미지", "홍노인", "아직", "나훈아쩔어", "2시간 전","3"));

        recyclerView = viewGroup.findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(context, dataSet);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        backButton = viewGroup.findViewById(R.id.ib_back);
        pushCommentButton = viewGroup.findViewById(R.id.btn_pushComment);
        editTextComment = viewGroup.findViewById(R.id.et_comment);
        backButton.setOnClickListener(v -> onClick(v));
        pushCommentButton.setOnClickListener(v->onClick(v));

        //임시데이터
        imageViewProfile = viewGroup.findViewById(R.id.iv_profile);
        textViewWriter = viewGroup.findViewById(R.id.tv_writer);
        textViewContents = viewGroup.findViewById(R.id.tv_contents);
        textViewDate = viewGroup.findViewById(R.id.tv_date);

        imageViewProfile.setImageResource(R.drawable.im_sample_profile);
        textViewContents.setText("나훈아쩔어");
        textViewWriter.setText("김노인");
        textViewDate.setText("2시간 전");

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                replaceFragment(new CommunityMainFragment());
                break;
            case R.id.btn_pushComment:
                //댓글입력버튼을 누르면 어댑터 갱신하고 뷰 초기화
                editTextComment.setText("");

        }

    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commitAllowingStateLoss();               // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

    }

}
