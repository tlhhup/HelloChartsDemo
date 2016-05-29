package com.tlh.hellochartsdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.BubbleChartData;
import lecho.lib.hellocharts.model.BubbleValue;
import lecho.lib.hellocharts.view.BubbleChartView;

public class BubbleChartViewActivity extends AppCompatActivity {

    @InjectView(R.id.bcv)
    BubbleChartView mBcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_chart_view);
        ButterKnife.inject(this);
        initData();
    }

    private void initData() {

        //准备数据
        List<BubbleValue> values=new ArrayList<>();
        BubbleValue value;
        Random random=new Random();
        int red,blue,green;
        List<Float> axisValue=new ArrayList<>();
        List<String> axisLables=new ArrayList<>();
        for(int i=0;i<5;i++){
            value=new BubbleValue();
            value.set(random.nextInt(100),random.nextInt(100),random.nextInt(100));//设置值
            value.setLabel("这里是"+i+"个");
            red=random.nextInt(190)+65;
            blue=random.nextInt(190)+65;
            green=random.nextInt(190)+65;
            value.setColor(Color.argb(255,red,green,blue));//设置颜色

            axisValue.add(random.nextFloat()*100);
            axisLables.add(i+"个");
            values.add(value);
            value=null;
        }

        //图片数据及属性
        BubbleChartData data=new BubbleChartData();
        data.setValues(values);
        data.setValueLabelsTextColor(Color.BLACK);
        data.setHasLabels(true);
        data.setAxisXBottom(Axis.generateAxisFromCollection(axisValue,axisLables));//设置坐标轴
        data.setAxisYLeft(Axis.generateAxisFromRange(0,100,1));

        mBcv.setBubbleChartData(data);
    }

}
