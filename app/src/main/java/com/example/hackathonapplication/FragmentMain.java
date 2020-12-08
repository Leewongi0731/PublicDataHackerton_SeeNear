package com.example.hackathonapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class FragmentMain extends Fragment {
    private ViewGroup viewGroup;
    private Context context;

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

    private LineChart lineChartHealth;
    private PieChart pieChartCommunity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main_page, container, false);
        context = container.getContext();

        initLayout();

        imageViewTrophyAttend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

                textViewAttend.setTypeface(boldTypeface);
                textViewHealth.setTypeface(normalTypeface);
                textViewEdu.setTypeface(normalTypeface);
                textViewCommunity.setTypeface(normalTypeface);

                linearLayoutAttend.setVisibility(View.VISIBLE);
                linearLayoutHealth.setVisibility(View.GONE);
                linearLayoutEdu.setVisibility(View.GONE);
                linearLayoutCommunity.setVisibility(View.GONE);
            }
        });

        imageViewTrophyHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

                textViewAttend.setTypeface(normalTypeface);
                textViewHealth.setTypeface(boldTypeface);
                textViewEdu.setTypeface(normalTypeface);
                textViewCommunity.setTypeface(normalTypeface);

                linearLayoutAttend.setVisibility(View.GONE);
                linearLayoutHealth.setVisibility(View.VISIBLE);
                linearLayoutEdu.setVisibility(View.GONE);
                linearLayoutCommunity.setVisibility(View.GONE);
            }
        });

        imageViewTrophyEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

                textViewAttend.setTypeface(normalTypeface);
                textViewHealth.setTypeface(normalTypeface);
                textViewEdu.setTypeface(boldTypeface);
                textViewCommunity.setTypeface(normalTypeface);

                linearLayoutAttend.setVisibility(View.GONE);
                linearLayoutHealth.setVisibility(View.GONE);
                linearLayoutEdu.setVisibility(View.VISIBLE);
                linearLayoutCommunity.setVisibility(View.GONE);
            }
        });

        imageViewTrophyCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface normalTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

                textViewAttend.setTypeface(normalTypeface);
                textViewHealth.setTypeface(normalTypeface);
                textViewEdu.setTypeface(normalTypeface);
                textViewCommunity.setTypeface(boldTypeface);

                linearLayoutAttend.setVisibility(View.GONE);
                linearLayoutHealth.setVisibility(View.GONE);
                linearLayoutEdu.setVisibility(View.GONE);
                linearLayoutCommunity.setVisibility(View.VISIBLE);
            }
        });

        return viewGroup;
    }

    public void makeHealthLineChart() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 61));
        entries.add(new Entry(2, 70));
        entries.add(new Entry(3, 68));
        entries.add(new Entry(4, 71));
        entries.add(new Entry(5, 65));
        entries.add(new Entry(6, 70));
        entries.add(new Entry(7, 73));

        LineDataSet dataset = new LineDataSet(entries, "");

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.PASTEL_COLORS);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        lineChartHealth.setData(data);
        lineChartHealth.animateY(1000);
    }

    public void makeHealthPieChart() {
        pieChartCommunity.setUsePercentValues(true);
        pieChartCommunity.getDescription().setEnabled(false);
        pieChartCommunity.setExtraOffsets(5,10,5,5);

        pieChartCommunity.setDragDecelerationFrictionCoef(0.95f);

        pieChartCommunity.setDrawHoleEnabled(false);
        pieChartCommunity.setHoleColor(Color.WHITE);
        pieChartCommunity.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(5,"댓글"));
        yValues.add(new PieEntry(10,"추천"));
        yValues.add(new PieEntry(3,"게시물"));

        pieChartCommunity.animateY(1000); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(12f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChartCommunity.setData(data);
    }

    public void initLayout() {
        textViewAttend = viewGroup.findViewById(R.id.textViewAttend);
        textViewHealth = viewGroup.findViewById(R.id.textViewHealth);
        textViewEdu = viewGroup.findViewById(R.id.textViewEdu);
        textViewCommunity = viewGroup.findViewById(R.id.textViewCommunity);

        imageViewTrophyAttend = viewGroup.findViewById(R.id.imageViewTrophyAttend);
        imageViewTrophyHealth = viewGroup.findViewById(R.id.imageViewTrophyHealth);
        imageViewTrophyEdu = viewGroup.findViewById(R.id.imageViewTrophyEdu);
        imageViewTrophyCommunity = viewGroup.findViewById(R.id.imageViewTrophyCommunity);

        linearLayoutAttend = viewGroup.findViewById(R.id.linearLayoutAttend);
        linearLayoutHealth = viewGroup.findViewById(R.id.linearLayoutHealth);
        linearLayoutEdu = viewGroup.findViewById(R.id.linearLayoutEdu);
        linearLayoutCommunity = viewGroup.findViewById(R.id.linearLayoutCommunity);

        lineChartHealth = viewGroup.findViewById(R.id.lineChartHealth);
        pieChartCommunity = viewGroup.findViewById(R.id.pieChartCommunity);

        makeHealthLineChart();
        makeHealthPieChart();
    }
}