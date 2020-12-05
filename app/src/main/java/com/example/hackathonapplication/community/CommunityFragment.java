package com.example.hackathonapplication.community;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathonapplication.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommunityFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ArrayList<Post> dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PostAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_main_fragment, container, false);
        context = container.getContext();

        initView();

        return viewGroup;
    }

    private void initView() {
        dataSet = new ArrayList<>();
        dataSet.add(new Post("김노인","김노인","아직","나훈아쩔어","2020-12-05"));
        dataSet.add(new Post("김노인","김노인","아직","나훈아쩔어","2020-12-05"));
        dataSet.add(new Post("김노인","김노인","아직","나훈아쩔어","2020-12-05"));
        dataSet.add(new Post("김노인","김노인","아직","나훈아쩔어","2020-12-05"));

        recyclerView = (RecyclerView)viewGroup.findViewById(R.id.rv_post);
        adapter = new PostAdapter(context,dataSet);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}
