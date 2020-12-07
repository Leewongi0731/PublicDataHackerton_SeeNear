package com.example.hackathonapplication.health;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathonapplication.MainActivity;
import com.example.hackathonapplication.R;

public class HealthMovieFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private Button healthInfoBackBtn;
    private Button healthTestCompleteBtn;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private VideoView healthInfoVideo;

    String messageReceived="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String videoPath = "";
        Bundle bundle=getArguments();
        if(bundle !=null) {
            videoPath = bundle.getString("videoPath");
        }

        viewGroup = (ViewGroup) inflater.inflate(R.layout.health_how_info_fragment, container, false);
        context = container.getContext();

        healthInfoBackBtn = viewGroup.findViewById(R.id.healthInfoBackBtn);
        healthInfoBackBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment( new HealthFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            }
        });


        // 비디오 링크걸기
        healthInfoVideo = viewGroup.findViewById(R.id.healthInfoVideo);
        Uri videoUri= Uri.parse( videoPath );

        //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
        healthInfoVideo.setMediaController(new MediaController( context ));

        //VideoView가 보여줄 동영상의 경로 주소(Uri) 설정하기
        healthInfoVideo.setVideoURI(videoUri);

        //동영상을 읽어오는데 시간이 걸리므로..
        //비디오 로딩 준비가 끝났을 때 실행하도록..
        //리스너 설정
        healthInfoVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //비디오 시작
                healthInfoVideo.start();
            }
        });
        return viewGroup;
    }

    /*
    //화면에 안보일때...
    @Override
    protected void onPause() {
        super.onPause();

        //비디오 일시 정지
        if(healthInfoVideo!=null && healthInfoVideo.isPlaying()) healthInfoVideo.pause();
    }
    //액티비티가 메모리에서 사라질때..
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(healthInfoVideo!=null) healthInfoVideo.stopPlayback();
    }
    */
}
