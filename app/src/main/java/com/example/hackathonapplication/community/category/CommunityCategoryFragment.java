package com.example.hackathonapplication.community.category;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.community.board.Post;
import com.example.hackathonapplication.community.board.PostAdapter;
import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.community.main.CommunityWriteFragment;
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
    private FloatingActionButton writePostButton;
    private String categoryName;
    private ImageView categoryImage;
    private TextView categoryNameText;

    private String id;
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
        initView();
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
        writePostButton = viewGroup.findViewById(R.id.fab_writeButton);
        backButton.setOnClickListener(v -> onClick(v));
        writePostButton.setOnClickListener(v->onClick(v));

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                replaceFragment(new CommunityMainFragment());
                break;
            case R.id.fab_writeButton:
                replaceFragment(new CommunityWriteFragment());
        }

    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commitAllowingStateLoss();

    }

    public void setDataSet() {
        dataSet = new ArrayList<>();
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        dbOpenHelper.create();

        Cursor cursor = dbOpenHelper.searchColumnsDesc("category","'"+categoryName+"'","postdate");            //categoryName이 한글이라 '' 를 넣어줌
        while(cursor.moveToNext()) {

            id = cursor.getString(cursor.getColumnIndex("_id"));
            contents = cursor.getString(cursor.getColumnIndex("contents"));
            date = cursor.getString(cursor.getColumnIndex("postdate"));
            like = cursor.getString(cursor.getColumnIndex("like"));
            comment = cursor.getString(cursor.getColumnIndex("comment"));
            dataSet.add(new Post(id,"로그인구현후","로그인구현후","뱃지구현후",contents,date,like,comment));
        }

        dbOpenHelper.close();
    }
}
