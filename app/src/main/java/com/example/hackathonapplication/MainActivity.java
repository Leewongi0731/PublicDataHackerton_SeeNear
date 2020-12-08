package com.example.hackathonapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathonapplication.community.main.CommunityMainFragment;
import com.example.hackathonapplication.edu.EduFragment;
import com.example.hackathonapplication.health.HealthFragment;
import com.example.hackathonapplication.job.JobFragment;
import com.example.hackathonapplication.mypage.MyPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initLayout();
        bottomNavigationView.setSelected(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        transaction = fragmentManager.beginTransaction();

        String fragmentTag = "";
        if(fragmentManager.getBackStackEntryCount() > 0) {
            fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        }
        boolean isSame;

        switch (menuItem.getItemId()) {
            case R.id.health_button: {
                isSame = fragmentTag.equals("Health");
                if(!isSame) {
                    transaction.addToBackStack("Health");
                    transaction.replace(R.id.frameLayout, healthFragment);
                    transaction.commit();
                }
                return true;
            }
            case R.id.edu_button: {
                isSame = fragmentTag.equals("Edu");
                if(!isSame) {
                    transaction.addToBackStack("Edu");
                    transaction.replace(R.id.frameLayout, eduFragment);
                    transaction.commit();
                }
                return true;
            }
            case R.id.job_button: {
                isSame = fragmentTag.equals("Job");
                if(!isSame) {
                    transaction.addToBackStack("Job");
                    transaction.replace(R.id.frameLayout, jobFragment);
                    transaction.commit();
                }
                return true;
            }
            case R.id.community_button: {
                isSame = fragmentTag.equals("Community");
                if(!isSame) {
                    transaction.addToBackStack("Community");
                    transaction.replace(R.id.frameLayout, communityFragment);
                    transaction.commit();
                }
                return true;
            }
            case R.id.mypage_button: {
                isSame = fragmentTag.equals("MyPage");
                if(!isSame) {
                    transaction.addToBackStack("MyPage");
                    transaction.replace(R.id.frameLayout, myPageFragment);
                    transaction.commit();
                }
                return true;
            }
        }
        return false;
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
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commit();
    }

    public void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment).commit();               // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(fragmentManager.getBackStackEntryCount() > 0) {
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