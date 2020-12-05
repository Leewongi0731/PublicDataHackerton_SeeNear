package com.example.hackathonapplication.mypage;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hackathonapplication.R;

public class MyPageFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_main_fragment, container, false);
        context = container.getContext();
        return viewGroup;
    }
}
