package com.netpood.admin.netpoodapp;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.bottombar.BottomBar;
import com.netpood.admin.netpoodapp.adapter.AdapterPersonaltem;

public class ActivityPersonalPage extends UAppCompatActivity {


  private Ui ui;
  BottomBar bottomBar;

  public class Ui {

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_personal);
    //setUpStatusBar();
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    AdapterPersonaltem newsAdapter = new AdapterPersonaltem(Base.getCurrentActivity(), DataFakeGenerator.getData(Base.getCurrentActivity()));
    GridLayoutManager gride = new GridLayoutManager(this, 2);
    gride.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        if (position == 0) {
          return 2;
        } else {
          return 1;
        }
      }
    });

    recyclerView.setLayoutManager(gride);
    recyclerView.setAdapter(newsAdapter);


  }

  /*public void setUpStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      //window.setStatusBarColor(Color.TRANSPARENT);
      View view = window.getDecorView();
      view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      window.setStatusBarColor(Color.parseColor("#FFFfff"));
    }
  }*/

}
