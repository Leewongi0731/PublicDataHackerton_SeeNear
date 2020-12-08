package com.example.hackathonapplication.mypage;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapplication.LoadingActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.community.board.Post;
import com.example.hackathonapplication.community.board.PostAdapter;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyPagePostFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ArrayList<Post> dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PostAdapter adapter;
    private ImageButton backButton;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FloatingActionButton writePostButton;


    private String id;
    private String like;
    private String comment;
    private String contents;
    private String date;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_post_fragment, container, false);
        context = container.getContext();

        fragmentManager = getFragmentManager();
        initView();
        return viewGroup;
    }

    private void initView() {

        setDataSet();
        recyclerView = viewGroup.findViewById(R.id.rv_post);
        adapter = new PostAdapter(context, dataSet,fragmentManager);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        backButton = viewGroup.findViewById(R.id.ib_back);
        writePostButton = viewGroup.findViewById(R.id.fab_writeButton);
        backButton.setOnClickListener(v -> onClick(v));
        writePostButton.setOnClickListener(v->onClick(v));

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                fragmentManager.popBackStackImmediate();
                break;
        }
    }

    public void setDataSet() {
        dataSet = new ArrayList<>();
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        dbOpenHelper.create();

        Cursor cursor = dbOpenHelper.searchColumnsDesc("writeremail","'"+ LoadingActivity.LOGIN_USER_EMAIL +"'","postdate");            //categoryName이 한글이라 '' 를 넣어줌

        while(cursor.moveToNext()) {
            id = cursor.getString(cursor.getColumnIndex("_id"));
            contents = cursor.getString(cursor.getColumnIndex("contents"));
            date = cursor.getString(cursor.getColumnIndex("postdate"));
            like = cursor.getString(cursor.getColumnIndex("like"));
            comment = cursor.getString(cursor.getColumnIndex("comment"));
            dataSet.add(new Post(id,"로그인구현후","이경배","뱃지구현후",contents,date,like,comment));
        }
        dbOpenHelper.close();
    }
}

