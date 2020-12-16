package com.example.hackathonapplication.health;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;

public class HealthTestResultFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button healthTestResultBackBtn;
    private Button healthTestReturnBtn;
    private ImageView healthTestResultImageView1;
    private ImageView healthTestResultImageView2;
    private ImageView healthTestResultImageView3;
    private TextView healthTestResultScoreTextView;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private String score1= "";
    private String score2= "";
    private String score3= "";
    int score;
   @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        if(bundle !=null) {
            score1 = bundle.getString("score1");
            score2 = bundle.getString("score2");
            score3 = bundle.getString("score3");
        }

        viewGroup = (ViewGroup) inflater.inflate(R.layout.health_today_test_result_fragment, container, false);
        context = container.getContext();

       healthTestResultImageView1 = viewGroup.findViewById(R.id.healthTestResultImageView1);
       healthTestResultImageView2 = viewGroup.findViewById(R.id.healthTestResultImageView2);
       healthTestResultImageView3 = viewGroup.findViewById(R.id.healthTestResultImageView3);
       int total = 0;
       total += setImageView1();
       total += setImageView2();
       total += setImageView3();

       healthTestResultScoreTextView = viewGroup.findViewById(R.id.healthTestResultScoreTextView);
       healthTestResultScoreTextView.setText( Integer.toString(total)  + "점 획득!");

        fragmentManager = getActivity().getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        healthTestResultBackBtn = viewGroup.findViewById(R.id.healthTestResultBackBtn);
        healthTestResultBackBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                //((MainActivity)getActivity()).replaceFragment( new HealthTestFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                fragmentManager.popBackStackImmediate();
            }
        });

        healthTestReturnBtn = viewGroup.findViewById(R.id.healthTestReturnBtn);
        healthTestReturnBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                //((MainActivity)getActivity()).replaceFragment( new HealthFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
                fragmentManager.popBackStackImmediate();
                fragmentManager.popBackStackImmediate();
            }
        });

        return viewGroup;
    }


    private int setImageView1(){
       try{
           score = Integer.parseInt( score1 );
       }catch (Exception e){
           score = 0;
       }

        if( score > 25 ){
            healthTestResultImageView1.setImageResource( R.drawable.ic_test_score_4 );
            return 3;
        }else if( score >  21){
            healthTestResultImageView1.setImageResource( R.drawable.ic_test_score_3 );
            return 2;
        }else{
            healthTestResultImageView1.setImageResource( R.drawable.ic_test_score_1 );
            return 1;
        }
    }
    private int setImageView2(){
        try{
            score = Integer.parseInt( score2 );
        }catch (Exception e){
            score = 0;
        }

        if( score > 122 ){
            healthTestResultImageView2.setImageResource( R.drawable.ic_test_score_4 );
            return 3;
        }else if( score >  109){
            healthTestResultImageView2.setImageResource( R.drawable.ic_test_score_3 );
            return 2;
        }else{
            healthTestResultImageView2.setImageResource( R.drawable.ic_test_score_1 );
            return 1;
        }
    }
    private int setImageView3(){
        try{
            score = Integer.parseInt( score3 );
        }catch (Exception e){
            score = 0;
        }

        if( score > 11.4 ){
            healthTestResultImageView3.setImageResource( R.drawable.ic_test_score_4 );
            return 3;
        }else if( score >  6.6){
            healthTestResultImageView3.setImageResource( R.drawable.ic_test_score_3 );
            return 2;
        }else{
            healthTestResultImageView3.setImageResource( R.drawable.ic_test_score_1 );
            return 1;
        }
    }

}