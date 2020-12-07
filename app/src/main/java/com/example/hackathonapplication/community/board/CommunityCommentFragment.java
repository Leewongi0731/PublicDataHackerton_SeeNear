package com.example.hackathonapplication.community.board;

import android.content.Context;
import android.database.Cursor;
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
import com.example.hackathonapplication.community.board.Comment;
import com.example.hackathonapplication.community.board.CommentAdapter;
import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;

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
    private ArrayList<Comment> commentdataSet;
    private Post post;
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
    private TextView textViewPostLike;
    private TextView textViewCommentLike;
    private TextView textViewCommentCount;

    private String id;
    private String like;
    private String comment;
    private String contents;
    private String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_comment_fragment, container, false);
        context = container.getContext();
        if (getArguments() != null) {
            id = getArguments().getString("id"); // 전달한 key 값
        }
        initView();

        return viewGroup;
    }

    private void initView() {
        //[댓글화면]
        commentdataSet = new ArrayList<>();                                                         //임시댓글 리사이클러뷰
        commentdataSet.add(new Comment("이미지", "백노인", "아직", "맞아요 ㅇㅈ", "2시간 전", "3"));
        commentdataSet.add(new Comment("이미지", "백노인", "아직", "맞아요 ㅇㅈ", "2시간 전", "3"));
        commentdataSet.add(new Comment("이미지", "백노인", "아직", "맞아요 ㅇㅈ", "2시간 전", "3"));
        commentdataSet.add(new Comment("이미지", "백노인", "아직", "맞아요 ㅇㅈ", "2시간 전", "3"));

        recyclerView = viewGroup.findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(context, commentdataSet);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //[댓글입력,백버튼]
        backButton = viewGroup.findViewById(R.id.ib_back);
        pushCommentButton = viewGroup.findViewById(R.id.btn_pushComment);
        editTextComment = viewGroup.findViewById(R.id.et_comment);
        backButton.setOnClickListener(v -> onClick(v));
        pushCommentButton.setOnClickListener(v -> onClick(v));

        //[게시글화면]
        imageViewProfile = viewGroup.findViewById(R.id.iv_profile);
        textViewWriter = viewGroup.findViewById(R.id.tv_writer);
        textViewContents = viewGroup.findViewById(R.id.tv_contents);
        textViewDate = viewGroup.findViewById(R.id.tv_date);
        textViewPostLike = viewGroup.findViewById(R.id.tv_postLikeCount);
        textViewCommentCount = viewGroup.findViewById(R.id.tv_commentCount);


        imageViewProfile.setImageResource(R.drawable.im_sample_profile); //임시사진

        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        Cursor cursor = dbOpenHelper.searchColumns("_id", id);
        while (cursor.moveToNext()) {

            contents = cursor.getString(cursor.getColumnIndex("contents"));
            date = cursor.getString(cursor.getColumnIndex("postdate"));
            like = cursor.getString(cursor.getColumnIndex("like"));
            comment = cursor.getString(cursor.getColumnIndex("comment"));

            post = new Post(id, "로그인구현후?", "로그인구현후", "뱃지구현후", contents, date, like, comment);
        }
        dbOpenHelper.close();
        textViewContents.setText(post.getContents());
        textViewWriter.setText("로그인구현후");
        textViewDate.setText(post.getDate());
        //textViewPostLike.setText(post.getLike()); //좋아요개수, 댓글개수 연동 오류. nullPointer
        //textViewCommentCount.setText(post.getComment());

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
        transaction.replace(R.id.frameLayout, fragment).commitAllowingStateLoss();
    }

}
