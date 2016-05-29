package com.tlh.hellochartsdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class LineChartsActivity extends AppCompatActivity {

    @InjectView(R.id.chart)
    LineChartView mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_charts);
        ButterKnife.inject(this);
        initView();
        iniData();
    }

    private void initView() {
        //控制用户是否能缩放、滑动、选择：默认为true
        this.mChart.setInteractive(false);
        //将charts包含在可滑动的容器中
        this.mChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
    }

    private void iniData() {
        //准备点
        List<PointValue> values = new ArrayList<PointValue>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        //In most cased you can call data model methods in builder-pattern-like manner.
        //构成线
        Line line = new Line(values).setColor(Color.BLUE).setCubic(true);
        line.setStrokeWidth(2);
        //支持多线
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        //设置数据
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //设置坐标轴
        data.setAxisXBottom(Axis.generateAxisFromRange(0,5,1).setName("X轴"));
        data.setAxisYLeft(Axis.generateAxisFromRange(0,5,1).setName("Y轴"));

        mChart.setLineChartData(data);
    }
}
