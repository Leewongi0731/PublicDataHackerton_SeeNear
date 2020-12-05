package com.example.hackathonapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hackathonapplication.community.CommunityFragment;
import com.example.hackathonapplication.edu.EduFragment;
import com.example.hackathonapplication.health.HealthFragment;
import com.example.hackathonapplication.job.JobFragment;
import com.example.hackathonapplication.mypage.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class MainActivity extends AppCompatActivity {
    private FragmentMain fragmentMain;
    private HealthFragment healthFragment;
    private EduFragment eduFragment;
    private JobFragment jobFragment;
    private CommunityFragment communityFragment;
    private MyPageFragment myPageFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction transaction;
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
                        transaction.replace(R.id.frameLayout, healthFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.edu_button: {
                        transaction.replace(R.id.frameLayout, eduFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.job_button: {
                        transaction.replace(R.id.frameLayout, jobFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.community_button: {
                        transaction.replace(R.id.frameLayout, communityFragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.mypage_button: {
                        transaction.replace(R.id.frameLayout, myPageFragment).commitAllowingStateLoss();
                        return true;
                    }
                }
                return false;
            }
        });
    }
    private void initLayout() {
        fragmentManager = getSupportFragmentManager();
        fragmentMain = new FragmentMain();
        healthFragment = new HealthFragment();
        eduFragment = new EduFragment();
        jobFragment = new JobFragment();
        communityFragment = new CommunityFragment();
        myPageFragment = new MyPageFragment();
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavigationView);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
    }
}