package com.tlh.hellochartsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    @InjectView(R.id.pcv)
    PieChartView mPcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {
        SliceValue sliceValue;
        List<SliceValue> values=new ArrayList<>();
        Random random = new Random();
        int red,blue,green;
        for(int i=0;i<5;i++){
            sliceValue=new SliceValue();
            sliceValue.setValue(random.nextFloat()*100);
            red=random.nextInt(190)+65;
            blue=random.nextInt(190)+65;
            green=random.nextInt(190)+65;
            sliceValue.setColor(Color.argb(255,red,green,blue));//设置颜色
            sliceValue.setLabel("这是第"+i+"个");

            values.add(sliceValue);
            sliceValue=null;
        }

        PieChartData data=new PieChartData();
        data.setValues(values);

        //设置数据属性
        data.setHasLabels(true);//设置显示标题 默认不显示
//        data.setHasCenterCircle(true);//显示中心圆圈  默认不显示
//        data.setHasLabelsOutside(true);//设置数据文本的位置
        data.setValueLabelsTextColor(Color.BLACK);//设置数据文本颜色
        //设置中心圆的文本
        data.setCenterText1("你好");
        data.setCenterText1Color(Color.BLACK);
        data.setCenterText2("你坏");
        data.setCenterText2Color(Color.BLACK);

        //设置图表属性
        mPcv.setChartRotationEnabled(true);//设置是否可以旋转 默认true
        mPcv.setPieChartData(data);

        //设置点击事件
        mPcv.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                Toast.makeText(PieChartActivity.this,"点击的是："+arcIndex+"值为"+value.getValue(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });
    }
}
