package com.netpood.admin.netpoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.netpood.admin.framework.activity.UAppCompatActivity;

public class MainActivity extends UAppCompatActivity {

  private Ui ui;

  public class Ui {
    ImageView imgPersonalPage;
    ViewPager viewPager;
    TabLayout tabLayout;
  }

  private int[] tabIcons = {
    R.drawable.ca,
    R.drawable.nav_logo_whiteout,
    R.drawable.mail
  };


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ui = new Ui();

    new UAppCompatActivity.Founder(this)
      .requestFeatures()
      .noTitlebar()
      .noActionbar()
      .statusBar()
      .contentView(R.layout.activity_main)
      .extractUi(ui)
      .build();

    ui.imgPersonalPage.setOnClickListener(
      new View.OnClickListener() {
           @Override
           public void onClick(View v) {

             Intent intent = new Intent(Base.getCurrentActivity(), PersonalPage.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
             ActivityCompat.finishAffinity(Base.getCurrentActivity());
             Base.getCurrentActivity().startActivity(intent);
            }
            });

      stratUpTab();


  }

  public void stratUpTab() {
    setupViewPager(ui.viewPager);
    ui.tabLayout.setupWithViewPager(ui.viewPager);
    setupTabIcons();
    TabLayout.Tab tab = ui.tabLayout.getTabAt(1);
    tab.select();

    ui.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }
      @Override
      public void onPageSelected(int position) {
        if(position == 0||position == 2) {
          ui.tabLayout.setVisibility(View.GONE);
        }
        else {
          ui.tabLayout.setVisibility(View.VISIBLE);
        }
      }
      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });

  }

  private void setupTabIcons() {
    ui.tabLayout.getTabAt(0).setIcon(tabIcons[0]);
    ui.tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    ui.tabLayout.getTabAt(2).setIcon(tabIcons[2]);
  }
  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new OneFragment(), "ONE");
    adapter.addFragment(new TwoFragment(), "نت پود");
    adapter.addFragment(new ThreeFragment(), "THREE");
    viewPager.setAdapter(adapter);
  }


}
