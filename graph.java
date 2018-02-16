package com.example.romanvenger.myapplication1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import mnk.Mnk;

public class graph extends AppCompatActivity {
private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
           mChart =new LineChart(this);
         setContentView(mChart);
        setData();
        System.out.println(44);

    }

    private ArrayList setYAxisValues(){
        ArrayList yVals = new ArrayList<>();
        yVals=Mnk.yVals;

        return yVals;
    }
    private ArrayList setXAxisValues(){
        ArrayList xVals = new ArrayList<>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("30.5");
        xVals.add("40");

        return xVals;
    }
    private void setData() {
        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1;

// create a dataset and give it a type
        set1 = new LineDataSet(yVals, "DataSet 1");
        set1.setFillAlpha(110);

        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
      //  set1.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

LineData data = new LineData(dataSets);
        mChart.setData(data);
    }
    }

