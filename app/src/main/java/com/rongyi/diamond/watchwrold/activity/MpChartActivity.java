package com.rongyi.diamond.watchwrold.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.rongyi.diamond.watchwrold.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.github.mikephil.charting.data.LineDataSet.Mode.CUBIC_BEZIER;

/**
 * Author:    Diamond_Lin
 * Version    V1.0
 * Date:      2017/3/15 下午5:40
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/3/15      Diamond_Lin            1.0                    1.0
 * Why & What is modified:
 */

public class MpChartActivity extends AppCompatActivity {

    @BindView(R.id.chart)
    LineChart mChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        initChart();
        setData();
    }


    private void initChart() {
        mChart.setExtraBottomOffset(5f);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false);
        mChart.getXAxis().setLabelCount(5);
        mChart.getXAxis().setGranularity(1f);

        mChart.getDescription().setEnabled(false);
        mChart.getDescription().setText("");


        // enable touch gestures
        mChart.setTouchEnabled(false);

        mChart.setDrawGridBackground(false);//很丑的网格背景

        mChart.setNoDataText("我们需要测量您7个数据");

        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.setTextSize(8f);
        leftAxis.setTextColor(Color.DKGRAY);
//        leftAxis.setValueFormatter(new ChartFormatter(BODY_WEIGHT));
        mChart.getAxisRight().setEnabled(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(8f);
        xAxis.setTextColor(Color.DKGRAY);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                switch ((int) value){
                    case 1:return "3.1";
                    case 2:return "3.2";
                    case 3:return "3.3";
                    case 4:return "3.4";
                    case 5:return "3.5";
                    case 6:return "3.6";
                    case 7:return "3.7";
                    case 8:return "3.8";
                }

                return "";
            }
        });
    }


    private void setData() {
        ArrayList<Entry> values = new ArrayList<>();

        values.add(new Entry(1, 65));
        values.add(new Entry(2, 68));
        values.add(new Entry(3, 71));
        values.add(new Entry(5, 73));
        values.add(new Entry(6, 68));
        values.add(new Entry(7, 65));

        LineDataSet set1;


        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }else {
            set1 = new LineDataSet(values, " ");
            set1.setColor(getResources().getColor(R.color.theme_textColorPrimary));
            set1.setCircleColor(getResources().getColor(R.color.theme_textColorPrimary));//描点颜色
            set1.setLineWidth(1f);//线粗
            set1.setCircleRadius(2f);//描点大小
            set1.setDrawCircleHole(false);//描点是否实心
            set1.setValueTextSize(6f);//描点值字体大小
            set1.setDrawFilled(true);//是否覆盖区域
            set1.setFormSize(8f);//设置小块大小
            set1.setMode(CUBIC_BEZIER);



            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_white);
                set1.setFillDrawable(drawable);
            }
            else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1); // add the datasets

            // create a data object with the datasets
            LineData data = new LineData(dataSets);

            // set data
            mChart.setData(data);
        }


    }


}
