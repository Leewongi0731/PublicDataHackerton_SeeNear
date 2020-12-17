package com.example.hackathonapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.HashMap;

public class LoadingActivity extends AppCompatActivity {
    static public final String LOGIN_USER_EMAIL = "test@gmail.com";
    static public String LOGIN_USER_RECOMMEND_KEY = "60대/비만전단계비만/M/금";

    private ImageView loadingImageView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);



        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst",false);
        if(checkFirst == false){
            initializeDataBase();
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst",true);
            editor.commit();
        }
        
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity( intent );
                finish();
            }
        },1500);
    }


    private void initializeDataBase(){
        // DB 초기화
        initializCustomerRepository();
        initializMedalDb();
        initializExerciseRepository();
        initializBoardDb();
//        initializCommentDb();
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

        exerciseRepository.insert("가슴/어깨 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/263/263.mp4",
                "1. 한 사람은 바닥에 엎드려 양손 깍지를 끼고 머리 뒤에 위치시킨다./2. 파트너가 양팔꿈치를 잡고 천천히 위로 당겨준다.", R.drawable.exercise_1);
        exerciseRepository.insert("고관절 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/271/271.mp4",
                "1. 엎드려 누운 파트너의 대퇴 앞쪽과 발목을 잡는다./2. 수직 방향으로 무릎을 들어 올린다.", R.drawable.exercise_2);
        exerciseRepository.insert("내전근 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/285/285.mp4",
                "1. 바른 자세로 의자에 앉아 발은 어깨너비보다 넓게 벌리고 양손은 대퇴부에 올려 놓는다./2. 상체를 비틀어 숙이며 한쪽 어깨를 아래쪽으로 밀어 넣는다.", R.drawable.exercise_3);
        exerciseRepository.insert("달리기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/2/2.mp4",
                "1. 팔과 다리를 더 힘차게 움직인다./2. 팔꿈치가 너무 벌어지거나 모이지 않게 주의한다.", R.drawable.exercise_4);
        exerciseRepository.insert("대퇴사두근 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/287/287.mp4",
                "1. 어깨너비로 벌려 선 자세에서 한 손으로 의자를 잡고 시선은 정면을 응시한다./2. 한쪽 발을 손으로 잡아 엉덩이 쪽으로 들어 당겨준다.", R.drawable.exercise_5);
        exerciseRepository.insert("대퇴이두근 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/272/272.mp4",
                "1. 두 다리를 펴고 앉은 파트너의 허리 하부를 잡는다./2. 허리 하부를 잡고 천천히 앞으로 밀어준다.", R.drawable.exercise_6);
        exerciseRepository.insert("등 대고 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/268/268.mp4",
                "1. 등을 맞대고 앉아 한사람은 무릎을 펴고 상대방은 무릎을 세운다./2. 무릎을 편 파트너의 방향으로 등을 밀어준다.", R.drawable.exercise_7);
        exerciseRepository.insert("몸통 비틀기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/278/278.mp4",
                "1. 어꺠너비로 선 자세를 잡는다./2.한 손은 의자 위, 다른 손은 머리뒤로 위치한다./3.들고 있는 손의 방향으로 온몸을 돌려 뒤를본다./4.번갈아가며 반복한다", R.drawable.exercise_8);
        exerciseRepository.insert("물병 들고 앉았다 일어서기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/212/212.mp4",
                "1. 물병을 양손에 들고 바르게 선다./2. 허리는 곧게 편 상태를 유지하며 앞뒤 무릎의 각도가 90도 정도 되도록 앉는다.", R.drawable.exercise_9);
        exerciseRepository.insert("스텝박스","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/171/171.mp4",
                "1. 스텝박스를 이용해 전후,좌우로 활발하게 움직인다./2. 15분 이상 걷기 운동을 실시한다.", R.drawable.exercise_10);
        exerciseRepository.insert("자전거타기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/452/452.mp4",
                "1. 본인의 체형에 맞는 자전거를 선택한다./2. 헬멧, 장갑등의 안전장비를 구비하도록 한다.", R.drawable.exercise_11);
        exerciseRepository.insert("발등굽힘/발바닥굽힘","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/72/72.mp4",
                "1. 무릎 밑에 수건을 대고 안정된 자세로 앉는다./2. 발목을 발바닥쪽으로 굽힌다./3. 운동 할 발을 반대쪽 발목 위에 올려 밴드를 걸고 엄지발가락을 몸 쪽으로 당긴다.", R.drawable.exercise_12);
        exerciseRepository.insert("옆구리 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/283/283.mp4",
                "1. 어깨너비로 벌려 선 자세에서 한 손은 의자를 잡고 다른 한 팔은 위를 항해 곧게 뻗어준다./2. 위를 향하고 있는 팔과 옆구리를 의자 방향으로 굽혀준다.", R.drawable.exercise_13);
        exerciseRepository.insert("짐볼에서 윗몸 일으키기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/232/232.mp4",
                "1. 짐볼에 등을 대고 누워 양손은 머리를 잡고 눕는다./2. 무릎을 90도로 굽히고 허리와 엉덩이를 일직선으로 유지한다./3. 견갑골 부분까지만 짐볼에서 떨어질 수 있게 윗몸을 일으켜준다.", R.drawable.exercise_14);
        exerciseRepository.insert("척추 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/302/302.mp4",
                "1. 폼롤러 위에 균형을 잡고 눕는다./2. 몸을 양옆으로 굴려준다.", R.drawable.exercise_15);
        exerciseRepository.insert("허리 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/250/250.mp4",
                "1. 네발기기 자세를 취한다./2. 머리를 앞으로 숙이며 등을 활처럼 둥글게 말아 올린다.", R.drawable.exercise_16);
        exerciseRepository.insert("벽에서 팔굽혀 펴기","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/225/225.mp4",
                "1. 양손을 벽에 대고 상체와 하체가 일직선을 이루도록 준비자세를 취한다./2. 양 팜꿈치를 구부려 벽에 상체를 밀착시킨 후 시작자세로 돌아온다.", R.drawable.exercise_17);
        exerciseRepository.insert("엉덩이 스트레칭","http://nfa.kspo.or.kr/common/site/www/front/movie_zip/246/246.mp4",
                "1. 바른 자세로 누워 한 쪽 무릎을 구부려 무릎을 양손으로 감싼다./2. 양손으로 무릎을 자신의 가슴 방향으로 당긴다..", R.drawable.exercise_18);


        exerciseRepository.close();
    }

    private void initializBoardDb() {
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(getApplicationContext());
        dbOpenHelper.open();
        dbOpenHelper.create();

        //이경배인 데이터만 이메일 LOGIN_USER_EMAIL 넣고 나머지는 임의 이메일 넣어야 내 글 조회에서 나옴
        dbOpenHelper.insertColumn("강남구", LOGIN_USER_EMAIL,String.valueOf(R.drawable.im_sample_profile), "이경배","트로트", "길 잃은 나그네의 나침판이냐 항구잃은 연락선의 고등이드냐", "20/12/15 14:00:01", 12, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile1), "길명근","트로트", "요즘 세상이 나훈아와 남진보다 미스타트롯에 열광하는군요...", "20/11/02 11:34:30", 0, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile2), "강지훈","트로트", "아 ~~,, 테스형 세상이 왜이래", "20/11/22 10:34:00", 4, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile3), "임영호","트로트", "돌아와요 부산항에 가사가 너무.절절합니다. 요즘.즐겨듣는답니다 ^^", "20/12/16 19:38:01", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile4), "김꽂자","트로트", "트로트 신곡 추천 ^^~~ **", "20/12/06 09:34:00", 20, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile5), "나순자","트로트", "심수봉이 신바람나게 잘 부르는거같아요. 남편이랑. 매일 틀어놓고 듣는데.", "20/12/08 11:34:00", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile6), "박현우","트로트", "트로트계는.다.죽었다.", "20/12/09 11:34:00", 9, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile7), "방정호","트로트", "트로트 들으면....... 잠이 잘 오고...... 여러분도 해보세요....", "20/12/12 11:38:06", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile8), "이미자","트로트", "고장난 벽시계같은 노래 들으면 요즘노래랑 차이나죠.. 나훈아도 아직 최고입니다.", "20/12/13 05:13:00", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile9), "고현숙","트로트", "훈아오빠 보다 남진오빠가 더 좋은거같은데 ~*~* ", "20/12/12 11:34:29", 30, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile10), "안경자","트로트", "콘서트 후에 등산가실분 있나요", "20/12/15 11:35:40", 2, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile4), "구본식","트로트", "나훈아콘서트가실분구합니다", "20/12/04 01:34:05", 5, 0);

        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile1), "길명근","등산", "무등산 등산하실 분 없나요?", "20/12/13 19:57:11", 4, 0);
        dbOpenHelper.insertColumn("강남구", LOGIN_USER_EMAIL,String.valueOf(R.drawable.im_sample_profile), "이경배","등산", "산은 구봉산이 최고죠. 뒷산에 올라가서 만세외치면 근심.걱정.날아갑니다 ^^~", "20/12/16 11:29:16", 8, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile2), "임영호","등산", "mountain is my life ~ ~ ", "20/12/13 11:15:00", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile3), "김꽂자","등산", "산공기 숨이 탁! 트이는 이기분!", "20/12/15 10:34:00", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile4), "나순자","등산", "주변좋은산정보있으시면댓글로달아주세요", "20/12/12 11:34:50", 9, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile5), "박현우","등산", "등산장비 어디가서 사시나요?", "20/11/01 06:34:02", 2, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile6), "방정호","등산", "산정상사진 보내주실분", "20/12/02 10:34:22", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile7), "이미자","등산", "저번주에 등산갔는데 날씨가 벌써 추워졌어요....", "20/12/12 15:34:08", 9, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile8), "고현숙","등산", "핫팩 나눔합니다 ^^* 아차산 아래 정기모임이에요~", "20/12/07 11:44:280", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile9), "안경자","등산", "산 정상 뷰~가 너무 좋아요", "20/12/10 15:34:30", 0, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile10), "구본식","등산", "더 나은 내일을 위해 오늘도 등산합니다.......", "20/12/09 14:34:09", 5, 0);

        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile1), "강미자","식물", "빨간장미의 꽃말은 사랑,욕망,절정,기쁨,아름다움 이라고해요. . .,", "20/12/16 11:04:24", 15, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile2), "임영호","식물", "저 어제 데이지 많이 사서 기분이 좋아요. 여러분은 어느 꽃 좋아하세요?", "20/12/13 02:04:08", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile3), "이영기","식물", "꽃 좋아하시는분,, 무슨 꽃. 키우세요?", "20/12/09 15:04:30", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile4), "이영기","식물", "선인장이.요즘엔.대세. ", "20/12/13 02:54:00", 4, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile5), "유영희","식물", "아레카야자. 공기정화되어서 너무좋아요", "20/12/16 16:15:12", 9, 0);
        dbOpenHelper.insertColumn("강남구", LOGIN_USER_EMAIL,String.valueOf(R.drawable.im_sample_profile), "이경배","식물", "www.ccotshop.com 꽃가게 오픈했어요 놀러오세요~", "20/12/12 18:04:10", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile6), "마경숙","식물", "식물도감빌려드려요 저희남편식물박사요", "20/12/06 22:04:00", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile7), "안경자","식물", "식물모임 정기모임 날짜 정해봅시다.", "20/12/13 09:04:00", 13, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile8), "박현호","식물", "꽃 키우는거 쉽지 않네요.", "20/12/14 12:04:00", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile9), "김땡초","식물", "저희 딸이 꽃을 정말 좋아해요. 무슨 꽃이냐고 물어봤더니 자기도 모른다고 하더라구요 ㅋㅋ", "20/12/03 02:44:00", 3, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile10), "강한형","식물", "꽃 향 기 너 무 좋 다 ^^~,", "20/12/16 08:04:00", 3, 0);

        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile1), "고두한","낚시", "미사용.낚시찌.저렴하게.분양합니다.", "20/12/14 13:24:03", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile2), "고두심","낚시", "좋은 낚시터 정보좀 알려주세요. 같이갑시다..", "20/12/15 15:24:03", 10, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile3), "김영자","낚시", "오늘도 월척 !^^", "20/12/04 20:24:03", 6, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile4), "이영지","낚시", "바다가 너무 예뻐서 잠시 생각에 잠겼어요...... 여러분도 명상 많이 해보세요.", "20/12/04 13:24:06", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile5), "한상숙","낚시", "낚시하는 게임도 있더라고요. 저희 아들이 하는건대 재밌어보여.", "20/12/04 13:24:03", 2, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile6), "이상순","낚시", "내일 비가오면.낚시도 못가고 . . . .", "20/12/14 18:24:07", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile7), "이화자","낚시", "눈이 이렇게 째진 물고기는 이름이 뭘까요? ", "20/12/13 13:15:55", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile8), "박현식","낚시", "남해 내항가서 볼락 처무냐?", "20/12/16 18:24:03", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile9), "강경보","낚시", "겨울 물낚시 제철이에요. 용대리 낚시터 아주 조아요.", "20/12/16 11:24:05", 1, 0);
        dbOpenHelper.insertColumn("강남구", LOGIN_USER_EMAIL,String.valueOf(R.drawable.im_sample_profile), "이경배","낚시", "낚시터에서 자리를 잡으면 받침대(받침틀)을 설치하고 받침대의 높이로 낚싯대 끝인 초릿대의 높이를 조절합니다.간혹 초보자 중에는 초릿대를 높이 쳐드는 형상으로 낚싯대를 펴는 것을 자주 볼 수 있는데, 바람이 불면 낚싯대와 낚싯줄의 저항이 커져 찌와 줄이 쉽게 흔들릴 뿐 아니라 정확한 입질을 보기 어렵게 되며, 또한 초릿대 끝의 흔들림에 따라 물 속에 있는 미끼까지 영향을 주게 되어 좋지 않은 낚시대의 설치방법이라고 합니다.", "20/12/16 13:24:03", 20, 0);

        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile1), "구본주","운동", "운동귀차니즘 극복하는법좀 알려주세요 날씨가추워서 나가기싫어요 ^^;", "20/12/15 09:11:02", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile2), "두보칠","운동", "무릎이 좀. 쑤셔서. 이매정형외과 괜찮은지요.", "20/12/15 10:11:08", 10, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile3), "김영남","운동", "운동을 매일하면 정신도 맑아집니다 ~ 더 나은 훗날을 위하여,, ! ^^", "20/12/14 13:11:21", 13, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile4), "장정남","운동", "날씨가 추워서 머리가 아파요. 운동 오늘은 스탑! ㅋㅋ", "20/12/15 11:20:02", 2, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile5), "김종철","운동", "운동장비 싸게 처분합니다. 010-7776-5120 연락주세요.", "20/12/05 21:11:35", 1, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile6), "나순자","운동", "아들이 엄마 운동 편하게 하라고~~ 난닝머신 하나 헤줬네요. 착하죠?", "20/12/05 22:11:12", 50, 0);
        dbOpenHelper.insertColumn("강남구", LOGIN_USER_EMAIL,String.valueOf(R.drawable.im_sample_profile), "이경배","운동", "숨쉬기 운동도 운동인가요 ~ ^^; 오늘은 너무 춥내요.", "20/12/05 13:11:02", 0, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile7), "마성호","운동", "근육이 많이 생길라면 고기 먹어야 하잖아요. 그러니까 고기집 추천해주셔요. 땡기네요", "20/12/15 11:43:02", 2, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile8), "두식킴","운동", "오늘 운동장 30분 뛰었어. 심장이 두근두근. 39일째 하니 몸이 조금가벼워진건가?", "20/12/13 16:31:02", 10, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile9), "양미경","운동", "79세.다치지않고.할수.있는운동.뭐가있죠", "20/12/15 08:22:02", 5, 0);
        dbOpenHelper.insertColumn("강남구", "test1@gmail.com",String.valueOf(R.drawable.im_sample_profile10), "한상수","운동", "매일아침일어나서동네한바퀴돌고제친구미란이랑얘기하고하다보면어느새해가뉘엿뉘엿", "20/12/16 18:11:02", 3, 0);


        dbOpenHelper.close();
    }

    private void initializCommentDb() {
        CommentRepository commentRepository = new CommentRepository(getApplicationContext());
        commentRepository.connect();

        commentRepository.insert(LOGIN_USER_EMAIL, "1", "와정말 이뻐요", "20/12/05 11:11:01", 3);
        commentRepository.insert(LOGIN_USER_EMAIL, "1", "감자팝니다~", "20/12/05 11:11:09", 2);

        commentRepository.close();
    }
}
