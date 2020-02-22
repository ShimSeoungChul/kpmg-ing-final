package com.kpmg.kpmgclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.kpmg.kpmgclient.async.GetUserEachPoint;
import com.kpmg.kpmgclient.common.CmmnUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GraphActivity extends AppCompatActivity {

    PieChart pieChart;
    ImageView home, graph, main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        home = (ImageView) findViewById(R.id.home);
        graph = (ImageView) findViewById(R.id.graph);
        main = (ImageView) findViewById(R.id.main);

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GraphActivity.class );
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class );
                startActivity(intent);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class );
                startActivity(intent);
            }
        });

        pieChart = (PieChart) findViewById(R.id.pieChart);

//        setPieChart(30,40,30 );
        getPieChartValue();

    }

    public void setPieChart(int image, int ocr, int stt) {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.15f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(image,"image"));
        yValues.add(new PieEntry(stt,"stt"));
        yValues.add(new PieEntry(ocr,"receipt"));

        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues, "graph");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

    }

    public void getPieChartValue() {
        try {
            String jsonString = new GetUserEachPoint().execute(CmmnUtil.USER_EACH_POINT_GET+"/test").get();
            try {
                JSONObject json = new JSONObject(jsonString);
                int imageInt = json.getInt("image");
                int ocrInt = json.getInt("ocr");
                int sttInt = json.getInt("stt");
                Log.e("piechartvalue확인","image:"+imageInt+"  ocr:"+ocrInt+"   stt:"+sttInt);
                setPieChart(imageInt, ocrInt, sttInt);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
