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

import com.example.hackathonapplication.LoadingActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.community.board.Comment;
import com.example.hackathonapplication.community.board.CommentAdapter;
import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import com.example.hackathonapplication.sqlite.CommentDB;
import com.example.hackathonapplication.sqlite.CommentDbOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    //[게시글데이터]
    private String id;
    private String like;
    private String writer;
    private String profile;
    private String comments;
    private String contents;
    private String date;
    private String badge;
    //[댓글데이터]
    private String c_writer;
    private String c_badge;
    private String c_contents;
    private String c_date;
    private String c_like;
    private String c_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_comment_fragment, container, false);
        context = container.getContext();
        if (getArguments() != null) {
            id = getArguments().getString("id");                                               // 전달한 key 값
        }
        fragmentManager = getFragmentManager();

        initView();

        return viewGroup;
    }

    private void initView() {

        //[댓글화면]
        setCommentDataSet();
        recyclerView = viewGroup.findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(context, commentdataSet);                                      //여기서 adapter에 데이터셋 넘겨주니까 여기서 아예 DB에서 게시글 id별 댓글넣은거 찾아서 넣어야
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


        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        Cursor cursor = dbOpenHelper.searchColumns("_id", id);
        while (cursor.moveToNext()) {

            id = cursor.getString(cursor.getColumnIndex("_id"));
            profile = cursor.getString(cursor.getColumnIndex("profile"));
            writer = cursor.getString(cursor.getColumnIndex("writer"));
            contents = cursor.getString(cursor.getColumnIndex("contents"));
            date = cursor.getString(cursor.getColumnIndex("postdate"));
            like = cursor.getString(cursor.getColumnIndex("like"));
            comments = cursor.getString(cursor.getColumnIndex("comment"));

            post = new Post(id,profile,writer,"배지",contents,date,like,comments);
        }
        dbOpenHelper.close();
        imageViewProfile.setImageResource(Integer.valueOf(post.getProfile()));
        textViewContents.setText(post.getContents());
        textViewWriter.setText(post.getWriter());
        textViewDate.setText(post.getDate());
        textViewPostLike.setText(post.getLike());
        textViewCommentCount.setText(post.getComment());

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                fragmentManager.popBackStackImmediate();
                break;
            case R.id.btn_pushComment:
                writeComment();
                adapter.notifyDataSetChanged();
                editTextComment.setText("");




        }

    }

    private void setCommentDataSet() {
        commentdataSet = new ArrayList<>();
        CommentDbOpenHelper dbOpenHelper = new CommentDbOpenHelper(context);
        dbOpenHelper.open();

        Cursor cursor = dbOpenHelper.searchColumnsDesc("boardkey", id,"commentdate");
        while (cursor.moveToNext()) {

            c_id = cursor.getString(cursor.getColumnIndex("_id"));
            c_contents = cursor.getString(cursor.getColumnIndex("contents"));
            c_like = cursor.getString(cursor.getColumnIndex("like"));
            c_date = cursor.getString(cursor.getColumnIndex("commentdate"));

            commentdataSet.add(new Comment(c_id,id,"임시사진", "이경배", "배지", c_contents, c_date, c_like));
        }
        dbOpenHelper.close();
    }


    public void writeComment() {
        CommentDbOpenHelper dbOpenHelper = new CommentDbOpenHelper(context);
        dbOpenHelper.open();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        String commentdate = sdfNow.format(date);

        dbOpenHelper.insertColumn(LoadingActivity.LOGIN_USER_EMAIL, id, editTextComment.getText().toString(), commentdate, 0);

        //추가하고 나서 commentdataSet 갱신
        commentdataSet.clear();
        Cursor cursor = dbOpenHelper.searchColumnsDesc("boardkey", id,"commentdate");
        while (cursor.moveToNext()) {

            c_id = cursor.getString(cursor.getColumnIndex("_id"));
            c_contents = cursor.getString(cursor.getColumnIndex("contents"));
            c_like = cursor.getString(cursor.getColumnIndex("like"));
            c_date = cursor.getString(cursor.getColumnIndex("commentdate"));

            commentdataSet.add(new Comment(c_id,id,"임시사진", "이경배", "배지", c_contents, c_date, c_like));
        }

        //게시글에 연동된 댓글개수 갱신
        BoardDbOpenHelper bdbOpenHelper = new BoardDbOpenHelper(context);
        bdbOpenHelper.open();
        int commentCount = 0;
        Cursor bcursor = dbOpenHelper.searchColumns("_id", id); //가져오고
        while (bcursor.moveToNext()) {
            comments = bcursor.getString(bcursor.getColumnIndex("comment"));
        }
        commentCount = Integer.valueOf(comments) + 1;
        bdbOpenHelper.updateColumnInt("_id",id,"comment",commentCount);

        dbOpenHelper.close();
        bdbOpenHelper.close();
        textViewCommentCount.setText(String.valueOf(commentCount));


    }

    public void initCount() { //댓글 추가,삭제할 때
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        Cursor cursor = dbOpenHelper.searchColumns("_id", id);
        while (cursor.moveToNext()) {

            like = cursor.getString(cursor.getColumnIndex("like"));
            comments = cursor.getString(cursor.getColumnIndex("comment"));
            post.setLike(like);
            post.setComment(comments);

        }
        dbOpenHelper.close();
        textViewPostLike.setText(post.getLike());
        textViewCommentCount.setText(post.getComment());
    }
}
