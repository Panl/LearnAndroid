package com.smartalk.learnandroid.canvas;

import android.os.Bundle;

import com.smartalk.learnandroid.Base.BaseActivity;
import com.smartalk.learnandroid.R;
import com.smartalk.learnandroid.canvas.model.PieData;
import com.smartalk.learnandroid.canvas.widget.PieView;

import java.util.ArrayList;

public class CanvasActivity extends BaseActivity {

    ArrayList<PieData> pieDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        initPieView();
    }

    void initPieView(){
        PieData pieData0 = new PieData("小米",2000f);
        PieData pieData1 = new PieData("华为",500f);
        PieData pieData2 = new PieData("苹果",3000f);
        PieData pieData3 = new PieData("三星",300f);
        PieData pieData4 = new PieData("魅族",100f);
        pieDatas.add(pieData0);
        pieDatas.add(pieData1);
        pieDatas.add(pieData2);
        pieDatas.add(pieData3);
        pieDatas.add(pieData4);
        ((PieView)findViewById(R.id.pie_view)).setData(pieDatas);
    }
}
