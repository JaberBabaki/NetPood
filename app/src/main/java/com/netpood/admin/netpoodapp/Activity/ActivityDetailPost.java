package com.netpood.admin.netpoodapp.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.ImagePagerAdapter;
import com.netpood.admin.framework.widget.PageIndicator;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.DataFakeSlideItem;
import com.netpood.admin.netpoodapp.database.SliderItem;

import java.util.ArrayList;

public class ActivityDetailPost extends UAppCompatActivity {
  ArrayList<SliderItem> imageIds;
  PageIndicator indicator ;
  DrawerLayout navigationView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_detail_post);
    setUpItemNavigation();
    ImageView imgDrawer = (ImageView) findViewById(R.id.img_drawer);
    ImageView imgBack = (ImageView) findViewById(R.id.img_back);
    imgDrawer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        navigationView.openDrawer(GravityCompat.START);
      }
    });

    imgBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        finish();
      }
    });



    indicator = (PageIndicator) findViewById(R.id.page_indicator);
    ViewPager pager = (ViewPager) findViewById(R.id.view_page);
    imageIds=new ArrayList<>();
    imageIds  = DataFakeSlideItem.getData(Base.getContext());
    ImagePagerAdapter adapter = new ImagePagerAdapter(imageIds);
    pager.setAdapter(adapter);
    indicator.setIndicatorsCount(imageIds.size());
    pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

      @Override
      public void onPageSelected(int index) {}


      @Override
      public void onPageScrolled(int startIndex, float percent, int pixel) {
        indicator.setPercent(percent);
        Log.i("LOG", "Percent: " + startIndex + ", " + percent);
        indicator.setCurrentPage(startIndex);
        //imageIds.get(startIndex).getSizeW();
        //imageIds.get(startIndex).getSizeH();
      }


      @Override
      public void onPageScrollStateChanged(int arg0) {

      }
    });

  }

  public void setupStatusBar() {
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      View view = getWindow().getDecorView();
      view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      //getWindow().setStatusBarColor(Color.TRANSPARENT);

    }
  }
  public void setUpItemNavigation() {
     navigationView = (DrawerLayout) findViewById(R.id.drawer);
    /*navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
      }
    });*/

  }
}
