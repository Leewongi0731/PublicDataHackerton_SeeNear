package com.example.hackathonapplication.health;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;

public class HealthTestFragment extends Fragment {
        private ViewGroup viewGroup;
        private Context context;
        private Button healthTestBackBtn;
        private Button healthTestCompleteBtn;
        private TextView healthTestTitle1;
        private TextView healthTestTitle2;
        private TextView healthTestTitle3;
        private EditText healthTestEditText1;
        private EditText healthTestEditText2;
        private EditText healthTestEditText3;

        private FragmentManager fragmentManager;
        private FragmentTransaction transaction;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            viewGroup = (ViewGroup) inflater.inflate(R.layout.health_today_test_fragment, container, false);
            context = container.getContext();

            fragmentManager = getActivity().getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();

            Bundle args = new Bundle();
            HealthMovieFragment healthMovieFragment = new HealthMovieFragment();

            healthTestTitle1 = viewGroup.findViewById(R.id.healthTestTitle1);
            healthTestTitle1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    args.putString("videoPath", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/kind4_5/kind4_5.mp4"); // key value를 Bundle에 담아서 파라미터로 전송
                    healthMovieFragment.setArguments(args);

                    transaction.replace(R.id.frameLayout, healthMovieFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            healthTestTitle2 = viewGroup.findViewById(R.id.healthTestTitle2);
            healthTestTitle2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    args.putString("videoPath", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/kind4_9/kind4_9.mp4"); // key value를 Bundle에 담아서 파라미터로 전송
                    healthMovieFragment.setArguments(args);

                    transaction.replace(R.id.frameLayout, healthMovieFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });

            healthTestTitle3 = viewGroup.findViewById(R.id.healthTestTitle3);
            healthTestTitle3.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    args.putString("videoPath", "http://nfa.kspo.or.kr/common/site/www/front/movie_zip/kind4_7/kind4_7.mp4"); // key value를 Bundle에 담아서 파라미터로 전송
                    healthMovieFragment.setArguments(args);

                    transaction.replace(R.id.frameLayout, healthMovieFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            });





            healthTestBackBtn = viewGroup.findViewById(R.id.healthTestBackBtn);
            healthTestBackBtn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view){
                    //((MainActivity)getActivity()).replaceFragment( new HealthFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                    fragmentManager.popBackStackImmediate();
                }
            });

            healthTestCompleteBtn = viewGroup.findViewById(R.id.healthTestCompleteBtn);
            healthTestCompleteBtn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view){
                    //((MainActivity)getActivity()).replaceFragment( new HealthTestResultFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                    healthTestEditText1 = viewGroup.findViewById(R.id.healthTestEditText1);
                    healthTestEditText2 = viewGroup.findViewById(R.id.healthTestEditText2);
                    healthTestEditText3 = viewGroup.findViewById(R.id.healthTestEditText3);

                    args.putString("score1", healthTestEditText1.getText().toString()); // key value를 Bundle에 담아서 파라미터로 전송
                    args.putString("score2", healthTestEditText2.getText().toString());
                    args.putString("score3", healthTestEditText3.getText().toString());

                    HealthTestResultFragment healthTestResultFragment = new HealthTestResultFragment();
                    healthTestResultFragment.setArguments(args);

                    transaction.replace(R.id.frameLayout, healthTestResultFragment);
                    transaction.addToBackStack("HealthResult");
                    transaction.commit();
                }
            });

            return viewGroup;
        }
}