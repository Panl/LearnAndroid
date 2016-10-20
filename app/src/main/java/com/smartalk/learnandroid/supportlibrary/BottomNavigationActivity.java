package com.smartalk.learnandroid.supportlibrary;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.smartalk.learnandroid.R;

public class BottomNavigationActivity extends AppCompatActivity {

  private BottomNavigationView bottomNavigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bottom_navigation);
    bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
    //navigationView.setItemIconTintList(ContextCompat.getColorStateList(this,R.color.bottom_navigation_item_color));
  }
}
