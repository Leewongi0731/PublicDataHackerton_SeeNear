package com.example.hackathonapplication.health;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
        private ImageButton healthTestBackBtn;
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
                    args.putString( "prescription",  "의자 앉았다 일어서기");
                    args.putString( "contents",  "1.등을 곧게 편 상태로 의자의 중앙 부분에 앉는다./2.양 팔은 손목에서 교차하여 가슴 앞에 모은다./3.시작 신호와 함께 완전히 일어섰다가 완전히 앉는다./4.30초 내에 가능한 한 많이 수행한다.");
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
                    args.putString( "prescription",  "2분 제자리걷기");
                    args.putString( "contents",  "1.줄자로 무릎 중앙에서부터 엉덩뼈까지 길이의 중간지점에 표시한다./2.시작 신호와 함께 우측 발부터 시작하여 무릎이 닿도록 들어올린다./3.적정 무릎 높이가 유지 되지 못할 때에는 속도를 늦추며 측정한다.");
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
                    args.putString( "prescription",  "앉아윗몸앞으로굽히기");
                    args.putString( "contents",  "1.수직면에 완전히 무릎을 펴고 바르게 앉는다./2.양손을 쭉 펴서 상체를 숙여 최대한 앞으로 멀리 뻗는다./3.총 2회 측정하며 좋은 기록을 택한다.");
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