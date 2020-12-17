package com.example.hackathonapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.edu.EduFragment;
import com.example.hackathonapplication.health.HealthFragment;
import com.example.hackathonapplication.job.JobFragment;
import com.example.hackathonapplication.myhome.MyHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private HealthFragment healthFragment;
    private EduFragment eduFragment;
    private JobFragment jobFragment;
    private CommunityMainFragment communityFragment;
    private MyHomeFragment myHomeFragment;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private FragmentTransaction transaction;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    private ConstraintLayout constraintLayoutMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initLayout();
        bottomNavigationView.setSelected(false);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setItemIconTintList(null);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        transaction = fragmentManager.beginTransaction();

        if(fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
        }

        switch (menuItem.getItemId()) {
            case R.id.health_button: {
                constraintLayoutMainActivity.setBackgroundResource(R.color.healthBackgroundColor);
                transaction.addToBackStack("Health");
                transaction.replace(R.id.frameLayout, healthFragment);
                transaction.commit();
                return true;
            }
            case R.id.edu_button: {
                constraintLayoutMainActivity.setBackgroundResource(R.color.eduBackgroundColor);
                transaction.addToBackStack("Edu");
                transaction.replace(R.id.frameLayout, eduFragment);
                transaction.commit();
                return true;
            }
            case R.id.myhome_button: {
                constraintLayoutMainActivity.setBackgroundResource(R.color.white);
                transaction.addToBackStack("MyPage");
                transaction.replace(R.id.frameLayout, myHomeFragment);
                transaction.commit();
                return true;
            }
            case R.id.job_button: {
                constraintLayoutMainActivity.setBackgroundResource(R.color.jobBackgroundColor);
                transaction.addToBackStack("Job");
                transaction.replace(R.id.frameLayout, jobFragment);
                transaction.commit();
                return true;
            }
            case R.id.community_button: {
                constraintLayoutMainActivity.setBackgroundResource(R.color.communityBackgroundColor);
                transaction.addToBackStack("Community");
                transaction.replace(R.id.frameLayout, communityFragment);
                transaction.commit();
                return true;
            }

        }
        return false;
    }

    private void initLayout() {
        fragmentManager = getSupportFragmentManager();
        healthFragment = new HealthFragment();
        eduFragment = new EduFragment();
        jobFragment = new JobFragment();
        communityFragment = new CommunityMainFragment();
        myHomeFragment = new MyHomeFragment();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, myHomeFragment).commit();

        constraintLayoutMainActivity = findViewById(R.id.constraintLayoutMainActivity);
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            if (intervalTime >= 0 && intervalTime <= FINISH_INTERVAL_TIME) {        // 2초 이내에 뒤로가기 버튼 클릭 시 종료
                super.onBackPressed();
            } else {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "한번 더 누르면 종료됩니다.", Toast.LENGTH_LONG).show();
            }
        }
    }
}