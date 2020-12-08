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
    private boolean isPop = false;
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
        switch (menuItem.getItemId()) {
            case R.id.health_button: {
                transaction.replace(R.id.frameLayout, healthFragment);
                if(!isPop) transaction.addToBackStack("Health");
                transaction.commit();
                return true;
            }
            case R.id.edu_button: {
                transaction.replace(R.id.frameLayout, eduFragment);
                if(!isPop) transaction.addToBackStack("Edu");
                transaction.commit();
                return true;
            }
            case R.id.job_button: {
                transaction.replace(R.id.frameLayout, jobFragment);
                if(!isPop) transaction.addToBackStack("Job");
                transaction.commit();
                return true;
            }
            case R.id.community_button: {
                transaction.replace(R.id.frameLayout, communityFragment);
                if(!isPop) transaction.addToBackStack("Community");
                transaction.commit();
                return true;
            }
            case R.id.mypage_button: {
                transaction.replace(R.id.frameLayout, myPageFragment);
                if(!isPop) transaction.addToBackStack("MyPage");
                transaction.commit();
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
            fragmentManager.popBackStackImmediate();                // 프래그먼트 스택에 프래그먼트가 있다면 pop
            if(fragmentManager.getBackStackEntryCount() > 0) {      // 최상단 프래그먼트 pop, isPop : pop 하자마자 push 를 막기 위함
                isPop = true;
                String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                switch(fragmentTag) {
                    case "Health":
                        bottomNavigationView.setSelectedItemId(R.id.health_button);
                        break;
                    case "Edu":
                        bottomNavigationView.setSelectedItemId(R.id.edu_button);
                        break;
                    case "Job":
                        bottomNavigationView.setSelectedItemId(R.id.job_button);
                        break;
                    case "Community":
                        bottomNavigationView.setSelectedItemId(R.id.community_button);
                        break;
                    case "MyPage":
                        bottomNavigationView.setSelectedItemId(R.id.mypage_button);
                        break;
                }
                isPop = false;
            }
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