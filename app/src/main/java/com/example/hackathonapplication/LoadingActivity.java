package com.example.hackathonapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import com.example.hackathonapplication.sqlite.CommentDbOpenHelper;
import com.example.hackathonapplication.sqlite.CustomerDbOpenHelper;
import com.example.hackathonapplication.sqlite.ExercisePrescriptionDbOpenHelper;
import com.example.hackathonapplication.sqlite.MedalDbOpenHelper;
import com.example.hackathonapplication.sqlite.refactored.CommentRepository;
import com.example.hackathonapplication.sqlite.refactored.CustomerRepository;
import com.example.hackathonapplication.sqlite.refactored.ExerciseRepository;
import com.example.hackathonapplication.sqlite.refactored.SqliteTableScheme;

public class LoadingActivity extends AppCompatActivity {
    static public final String LOGIN_USER_EMAIL = "test@gmail.com";


    private ImageView loadingImageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initializeDataBase();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity( intent );
            }
        },1500);
    }


    private void initializeDataBase(){
        // DB 초기화
        initializCustomerRepository();
        initializMedalDb();
        initializExerciseRepository();
        initializBoardDb();
        initializCommentDb();
    }


    private void initializCustomerRepository(){
        CustomerRepository customerRepository = new CustomerRepository(getApplicationContext());
        customerRepository.connect();

        customerRepository.insert("test@gmail.com", 65, 165, "남", "강남구", "이경배", "출석 은, 건강 동, 교육 은, 커뮤니티 금", "커뮤니티 금");
        customerRepository.insert("sample1@gmail.com", 65, 176, "남", "강남구", "김원주", "출석 은, 건강 동, 교육 은, 커뮤니티 금", "커뮤니티 금");
        customerRepository.insert("sample2@gmail.com", 63, 165, "여", "강남구", "이영주", "출석 은, 건강 동, 교육 은, 커뮤니티 금", "커뮤니티 금");
        customerRepository.insert("sample3@gmail.com", 77, 163, "남", "서대문구", "백원배", "출석 은, 건강 동, 교육 은, 커뮤니티 금", "커뮤니티 금");
        customerRepository.insert("sample4@gmail.com", 75, 158, "여", "서대문구", "이춘자", "출석 은, 건강 동, 교육 은, 커뮤니티 금", "커뮤니티 금");

        customerRepository.close();
    }

    private void initializMedalDb(){
        MedalDbOpenHelper dbOpenHelper = new MedalDbOpenHelper(getApplicationContext());
        dbOpenHelper.open();
        dbOpenHelper.create();

        dbOpenHelper.insertRow("출석 금","./res/drawable/ic_attend_gold.xml");
        dbOpenHelper.insertRow("출석 은","./res/drawable/ic_attend_silver.xml");
        dbOpenHelper.insertRow("출석 동","./res/drawable/ic_attend_bronze.xml");
        dbOpenHelper.insertRow("건강 금","./res/drawable/ic_health_gold.xml");
        dbOpenHelper.insertRow("건강 은","./res/drawable/ic_health_silver.xml");
        dbOpenHelper.insertRow("건강 동","./res/drawable/ic_health_bronze.xml");
        dbOpenHelper.insertRow("교육 금","./res/drawable/ic_edu_gold.xml");
        dbOpenHelper.insertRow("교육 은","./res/drawable/ic_edu_silver.xml");
        dbOpenHelper.insertRow("교육 동","./res/drawable/ic_edu_bronze.xml");
        dbOpenHelper.insertRow("커뮤니티 금","./res/drawable/ic_community_gold.xml");
        dbOpenHelper.insertRow("커뮤니티 은","./res/drawable/ic_community_silver.xml");
        dbOpenHelper.insertRow("커뮤니티 동","./res/drawable/ic_community_bronze.xml");
    }

    private void initializExerciseRepository(){
        ExerciseRepository exerciseRepository = new ExerciseRepository(getApplicationContext());
        exerciseRepository.connect();

        exerciseRepository.insert("어깨 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/266/266.mp4","1.엎드려 누운 파트너의 양손목을 잡는다./2.양팔을 내회전 하면서 바깥쪽으로 밀어준다.");
        exerciseRepository.insert("발바닥 치기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/350/350.mp4","1.바르게 선자세에서 제기차기를 하듯 한 발을 올린다./2.반대쪽 손으로 발바닥을 떄린다./3.번갈아가며 반복한다.");
        exerciseRepository.insert("몸통 비틀기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/278/278.mp4","1.어꺠너비로 선 자세를 잡는다./2.한 손은 의자 위, 다른 손은 머리뒤로 위치한다./3.들고 있는 손의 방향으로 온몸을 돌려 뒤를본다./4.번갈아가며 반복한다");
        exerciseRepository.insert("종아리 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/277/277.mp4","1.엎드려 누운 파트너의 무릎을 굽혀유지한다./2.한 손으로 파트너의 발바닥을 감싼다./3.발 끝을 무릎방향으로 눌러준다.");

        exerciseRepository.close();
    }

    private void initializBoardDb() {
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(getApplicationContext());
        dbOpenHelper.open();
        dbOpenHelper.create();

    //    dbOpenHelper.insertColumn("강남구", "sample1@gmail.com", "트로트", "오늘의 트로트", "오늘트로트를 신나게 부른다.", "2020.12.03.16.30.15", "2020.12.03.16.30.15", 5, 3);
    }

    private void initializCommentDb() {
        CommentRepository commentRepository = new CommentRepository(getApplicationContext());
        commentRepository.connect();

        commentRepository.insert("test@gamil.com", "1", "와정말 이뻐요", "2020.12.03.16.30.15", 3);
        commentRepository.insert("test@gamil.com", "1", "감자팝니다~", "2020.12.04.26.29.41", 2);

        commentRepository.close();
    }
}
