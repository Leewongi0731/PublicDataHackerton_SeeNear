package com.example.hackathonapplication.mypage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.hackathonapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ChangeLocationFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private List<Button> btnList;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.mypage_change_location, container, false);
        context = container.getContext();

        fragmentManager = getActivity().getSupportFragmentManager();

        setBtnClick();

        return viewGroup;
    }


    View.OnClickListener changeLocation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button selectBtn = (Button) viewGroup.findViewById( v.getId() );
            String selectLocation = selectBtn.getText().toString();

            updateDB( selectLocation );

            //((MainActivity)getActivity()).replaceFragment( new MyPageFragment() );    // 새로 불러올 Fragment의 Instance를 Main으로 전달
            fragmentManager.popBackStackImmediate();
        }
    };

    void updateDB(String newLocation){
        // CustomerDB Update
        Toast.makeText(context, "CustomerDB update -> new locate : " + newLocation, Toast.LENGTH_SHORT).show();
    }


    void setBtnClick(){
        btnList = new ArrayList<Button>();
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn1)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn2)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn3)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn4)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn5)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn6)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn7)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn8)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn9)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn10)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn11)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn12)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn13)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn14)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn15)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn16)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn17)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn18)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn19)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn20)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn21)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn22)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn23)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn24)  );
        btnList.add(  (Button)viewGroup.findViewById(R.id.locationBtn25)  );

        for(int i = 0 ; i < btnList.size() ; i++){
            btnList.get(i).setOnClickListener( this.changeLocation );
        }

    }
}