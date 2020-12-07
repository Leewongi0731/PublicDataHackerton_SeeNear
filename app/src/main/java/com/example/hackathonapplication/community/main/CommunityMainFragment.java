package com.example.hackathonapplication.community.main;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;
import com.example.hackathonapplication.community.category.CommunityCategoryFragment;
import com.example.hackathonapplication.community.board.Post;
import com.example.hackathonapplication.community.board.PostAdapter;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CommunityMainFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ArrayList<Post> dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PostAdapter adapter;
    private ImageButton categoryButton;
    private FloatingActionButton writePostButton;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

//    private Integer index;

//    private String location;
//    private String writeremail;
//    private String category;
    private String id;
    private String like;
    private String comment;
    private String contents;
    private String date;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_main_fragment, container, false);
        context = container.getContext();
        fragmentManager = getFragmentManager();

        initView();

        return viewGroup;
    }

    private void initView() {
        dataSet = new ArrayList<>();
        //
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        dbOpenHelper.create();

        //한번만 추가돼야하는데 안그럼 계속 늘어남
//        dbOpenHelper.insertColumn("강남구","sample@gmail.com","등산","오늘 산을 갔다","2시간 전",5,3);
//        dbOpenHelper.insertColumn("송파구","sample2@gmail.com","꽃","꽃 이쁘죠?","2시간 전",12,2);

        Cursor cursor = dbOpenHelper.sortColumnDesc("postdate");
        while(cursor.moveToNext()) {

            id = cursor.getString(cursor.getColumnIndex("_id"));
            contents = cursor.getString(cursor.getColumnIndex("contents"));
            date = cursor.getString(cursor.getColumnIndex("postdate"));
            like = cursor.getString(cursor.getColumnIndex("like"));
            comment = cursor.getString(cursor.getColumnIndex("comment"));

            dataSet.add(new Post(id,"로그인구현후?","로그인구현후","뱃지구현후",contents,date,like,comment));
        }

        dbOpenHelper.close();


        recyclerView = viewGroup.findViewById(R.id.rv_post);
        adapter = new PostAdapter(context, dataSet,fragmentManager);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        categoryButton = viewGroup.findViewById(R.id.ib_categoryButton);
        writePostButton = viewGroup.findViewById(R.id.fab_writeButton);
        categoryButton.setOnClickListener(v->onClick(v));
        writePostButton.setOnClickListener(v->onClick(v));

    }



    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_categoryButton:
                replaceFragment(new CommunityCategoryFragment());
                break;
            case R.id.fab_writeButton:
                replaceFragment(new CommunityWriteFragment());
        }

    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commitAllowingStateLoss();               // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.

    }
}
