package com.example.hackathonapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.edu.EduFragment;
import com.example.hackathonapplication.health.HealthFragment;
import com.example.hackathonapplication.job.JobFragment;
import com.example.hackathonapplication.mypage.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private FragmentMain fragmentMain;
    private HealthFragment healthFragment;
    private EduFragment eduFragment;
    private JobFragment jobFragment;
    private CommunityMainFragment communityFragment;
    private MyPageFragment myPageFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction transaction;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    private Integer currentState = 0;

    private TextView textViewAttend;
    private TextView textViewHealth;
    private TextView textViewEdu;
    private TextView textViewCommunity;

    private ImageView imageViewTrophyAttend;
    private ImageView imageViewTrophyHealth;
    private ImageView imageViewTrophyEdu;
    private ImageView imageViewTrophyCommunity;

    private LinearLayout linearLayoutAttend;
    private LinearLayout linearLayoutHealth;
    private LinearLayout linearLayoutEdu;
    private LinearLayout linearLayoutCommunity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initLayout();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                transaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.health_button: {
                        transaction.replace(R.id.frameLayout, healthFragment).commit();
                        return true;
                    }
                    case R.id.edu_button: {
                        transaction.replace(R.id.frameLayout, eduFragment).commit();
                        return true;
                    }
                    case R.id.job_button: {
                        transaction.replace(R.id.frameLayout, jobFragment).commit();
                        return true;
                    }
                    case R.id.community_button: {
                        transaction.replace(R.id.frameLayout, communityFragment).commit();
                        return true;
                    }
                    case R.id.mypage_button: {
                        transaction.replace(R.id.frameLayout, myPageFragment).commit();
                        return true;
                    }
                }
                return false;
            }
        });

        imageViewTrophyAttend.setOnClickListener(this.click);
    }
    private void initLayout() {
        fragmentManager = getSupportFragmentManager();
        fragmentMain = new FragmentMain();
        healthFragment = new HealthFragment();
        eduFragment = new EduFragment();
        jobFragment = new JobFragment();
        communityFragment = new CommunityMainFragment();
        myPageFragment = new MyPageFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        textViewAttend = findViewById(R.id.textViewAttend);
        textViewHealth = findViewById(R.id.textViewHealth);
        textViewEdu = findViewById(R.id.textViewEdu);
        textViewCommunity = findViewById(R.id.textViewCommunity);

        imageViewTrophyAttend = findViewById(R.id.imageViewTrophyAttend);
        imageViewTrophyHealth = findViewById(R.id.imageViewTrophyHealth);
        imageViewTrophyEdu = findViewById(R.id.imageViewTrophyEdu);
        imageViewTrophyCommunity = findViewById(R.id.imageViewTrophyCommunity);

        linearLayoutAttend = findViewById(R.id.linearLayoutAttend);
        linearLayoutHealth = findViewById(R.id.linearLayoutHealth);
        linearLayoutEdu = findViewById(R.id.linearLayoutEdu);
        linearLayoutCommunity = findViewById(R.id.linearLayoutCommunity);

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commit();
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageViewTrophyAttend:
                    Toast.makeText(getApplicationContext(), "myProfile", Toast.LENGTH_SHORT).show();
                    // 프로필 변경
                    break;
            }
        }
    };


    public void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();               // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(intervalTime >= 0 && intervalTime <= FINISH_INTERVAL_TIME) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
        }
    }
}