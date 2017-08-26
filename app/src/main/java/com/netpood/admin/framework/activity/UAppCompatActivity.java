package com.netpood.admin.framework.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.netpood.admin.framework.core.UBase;

public class UAppCompatActivity extends AppCompatActivity {
  public DrawerLayout navigationView;
  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    //
  }

  @Override
  protected void onResume() {
    super.onResume();
    //setUpStatusBar();

    UBase.setCurrentActivity(this);
  }

 public void setUpStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      View view = getWindow().getDecorView();
      view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
  }



}
