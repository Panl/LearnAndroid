package com.smartalk.learnandroid.Base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.smartalk.learnandroid.R;
import com.smartalk.learnandroid.canvas.CanvasActivity;
import com.smartalk.learnandroid.customview.CustomActivity;
import com.smartalk.learnandroid.imageloader.ui.GalleryActivity;
import com.smartalk.learnandroid.surfaceview.SurfaceActivity;
import com.smartalk.learnandroid.svg.SvgActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView lvList;
    private List<String> catalog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewById();
        initListView();
    }

    private void initViewById() {
        lvList = (ListView) findViewById(R.id.lv_list);
    }

    private void initListView() {
        catalog.add("自定义view之Canvas");
        catalog.add("自己写的ImageLoader");
        catalog.add("PageLayout");
        catalog.add("SurfaceView");
        catalog.add("svg");
        lvList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, catalog));
        lvList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, CanvasActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, GalleryActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, CustomActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, SurfaceActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, SvgActivity.class));
                break;
        }
    }
}
