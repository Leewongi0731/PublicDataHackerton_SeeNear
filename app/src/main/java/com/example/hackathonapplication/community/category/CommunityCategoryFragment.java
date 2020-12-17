package com.example.hackathonapplication.community.category;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.community.board.Post;
import com.example.hackathonapplication.community.board.PostAdapter;
import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.community.main.CommunityWriteFragment;
import com.example.hackathonapplication.myhome.MyPostFragment;
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

public class CommunityCategoryFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ArrayList<Post> dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PostAdapter adapter;
    private ImageButton backButton;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private FloatingActionButton plusButton;
    private FloatingActionButton writeButton;
    private FloatingActionButton mypostButton;
    private String categoryName;
    private ImageView categoryImage;
    private TextView categoryNameText;
    private Animation fab_open, fab_close;
    private Boolean openFlag = false;

    private String id;
    private String profile;
    private String writer;
    private String like;
    private String comment;
    private String contents;
    private String date;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_category_fragment, container, false);
        context = container.getContext();
        if (getArguments() != null) {
            categoryName = getArguments().getString("categoryname"); // 전달한 key 값
        }
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();

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

        categoryImage = viewGroup.findViewById(R.id.iv_categoryImage);
        categoryNameText = viewGroup.findViewById(R.id.tv_catecoryTitle);
        categoryNameText.setText(categoryName);
        switch (categoryName) {
            case "트로트":
                categoryImage.setImageResource(R.drawable.im_sample_category1);
                break;
            case "등산":
                categoryImage.setImageResource(R.drawable.im_sample_category2);
                break;
            case "식물":
                categoryImage.setImageResource(R.drawable.im_sample_category3);
                break;
            case "낚시":
                categoryImage.setImageResource(R.drawable.im_sample_category4);
                break;
            case "운동":
                categoryImage.setImageResource(R.drawable.im_sample_category5);
                break;
        }

        backButton = viewGroup.findViewById(R.id.ib_back);
        plusButton = viewGroup.findViewById(R.id.fab_plus);
        writeButton = viewGroup.findViewById(R.id.fab_write);
        mypostButton = viewGroup.findViewById(R.id.fab_mypost);
        backButton.setOnClickListener(v -> onClick(v));
        plusButton.setOnClickListener(v->onClick(v));
        writeButton.setOnClickListener(v->onClick(v));
        mypostButton.setOnClickListener(v->onClick(v));

        fab_open = AnimationUtils.loadAnimation(context, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(context,R.anim.fab_close);
        writeButton.setVisibility(View.INVISIBLE);
        mypostButton.setVisibility(View.INVISIBLE);
        writeButton.setClickable(false);
        mypostButton.setClickable(false);

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                fragmentManager.popBackStackImmediate();
                break;
            case R.id.fab_plus:
                anim();
                break;
            case R.id.fab_write:
                anim();
                replaceFragment(new CommunityWriteFragment(),"2");
                break;
            case R.id.fab_mypost:
                anim();
                replaceFragment(new MyPostFragment(),"MyPost");
                break;
        }

    }

    public void replaceFragment(Fragment fragment,String backstack) {
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(backstack);
        transaction.commit();
    }

    public void anim() {

        if (openFlag) {
            writeButton.startAnimation(fab_close);
            mypostButton.startAnimation(fab_close);
            writeButton.setClickable(false);
            mypostButton.setClickable(false);
            openFlag = false;
        } else {
            writeButton.startAnimation(fab_open);
            mypostButton.startAnimation(fab_open);
            writeButton.setClickable(true);
            mypostButton.setClickable(true);
            openFlag = true;
        }
    }

    public void setDataSet() {
        dataSet = new ArrayList<>();
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        dbOpenHelper.create();

        Cursor cursor = dbOpenHelper.searchColumnsDesc("category","'"+categoryName+"'","postdate");            //categoryName이 한글이라 '' 를 넣어줌
        while(cursor.moveToNext()) {

            id = cursor.getString(cursor.getColumnIndex("_id"));
            profile = cursor.getString(cursor.getColumnIndex("profile"));
            writer = cursor.getString(cursor.getColumnIndex("writer"));
            contents = cursor.getString(cursor.getColumnIndex("contents"));
            date = cursor.getString(cursor.getColumnIndex("postdate"));
            like = cursor.getString(cursor.getColumnIndex("like"));
            comment = cursor.getString(cursor.getColumnIndex("comment"));

            dataSet.add(new Post(id,profile,writer,"배지",contents,date,like,comment));
        }

        dbOpenHelper.close();
    }
}
