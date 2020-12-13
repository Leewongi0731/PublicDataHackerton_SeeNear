package com.example.hackathonapplication.community.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.hackathonapplication.LoadingActivity;
import com.example.hackathonapplication.R;
import com.example.hackathonapplication.sqlite.BoardDbOpenHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CommunityWriteFragment extends Fragment {
    private ViewGroup viewGroup;
    private Context context;
    private ImageButton backButton;
    private Button finishWriteButton;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private EditText contentsEditText;
    private Spinner categoryListSpin;
    private String categoryName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.community_write_fragment, container, false);
        context = container.getContext();
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        initView();
        return viewGroup;
    }

    private void initView() {

        backButton = viewGroup.findViewById(R.id.ib_back);
        finishWriteButton = viewGroup.findViewById(R.id.btn_finish);
        categoryListSpin = viewGroup.findViewById(R.id.spi_categoryList);
        contentsEditText = viewGroup.findViewById(R.id.et_postcontents);
        backButton.setOnClickListener(v -> onClick(v));
        finishWriteButton.setOnClickListener(v -> onClick(v));
        categoryListSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(categoryListSpin.getSelectedItemPosition() >= 0) {
                    String selectedItem = categoryListSpin.getSelectedItem().toString();
                    categoryName = selectedItem;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    categoryName = "기타";
            }
        });

    }

    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                fragmentManager.popBackStackImmediate();
                break;
            case R.id.btn_finish:                                                                   //저장하고 전송
                writePost();
                replaceBundleFragment(new CommunityMainFragment(), categoryName);
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void writePost() {
        BoardDbOpenHelper dbOpenHelper = new BoardDbOpenHelper(context);
        dbOpenHelper.open();
        dbOpenHelper.create();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
        String postdate = sdfNow.format(date);

        dbOpenHelper.insertColumn("강남구", LoadingActivity.LOGIN_USER_EMAIL, categoryName, contentsEditText.getText().toString(), postdate, 0, 0);
        dbOpenHelper.close();
    }

    public void replaceBundleFragment(CommunityMainFragment cmf, String categoryname) {
        Bundle bundle = new Bundle();
        bundle.putString("categoryname", categoryname);
        cmf.setArguments(bundle);
        replaceFragment(cmf);
    }
}
