package com.example.hackathonapplication.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.health.HealthFragment;

public class MyPageFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ImageView myProfile;
    private LinearLayout myLocation;
    private TextView myWriteList;
    private Object MypageClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_main_fragment, container, false);
        context = container.getContext();

        myProfile = (ImageView) viewGroup.findViewById( R.id.myProfile );
        myLocation = (LinearLayout) viewGroup.findViewById( R.id.myLocation );
        myWriteList = (TextView) viewGroup.findViewById( R.id.myWriteList );

        myProfile.setOnClickListener(this.click);
        myLocation.setOnClickListener(this.click);
        myWriteList.setOnClickListener(this.click);


        return viewGroup;
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.myProfile:
                    Toast.makeText(context, "myProfile", Toast.LENGTH_SHORT).show();
                    // btn1 동작
                    break;
                case R.id.myLocation:
                    ((MainActivity)getActivity()).replaceFragment( new ChangeLocationFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                    break;
                case R.id.myWriteList:
                    Toast.makeText(context, "myWriteList", Toast.LENGTH_SHORT).show();
                    // btn2 동작
                    break;
            }
        }
    };

}
