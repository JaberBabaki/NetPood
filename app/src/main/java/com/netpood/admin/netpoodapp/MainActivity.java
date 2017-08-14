package com.netpood.admin.netpoodapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;

public class MainActivity extends UAppCompatActivity {
  DrawerLayout drawer;

  ImageView imgPersonalPage;
  ImageView imgAddPost;
  ViewPager viewPager;
  TabLayout tabLayout;

  LinearLayout layAddPost;

  LinearLayout layHomeBottom;
  ImageView imgHomeBottom;
  TextView txtHomeBottom;

  LinearLayout layPoods;
  ImageView imgPoodBottom;
  TextView txtPoodBottom;

  LinearLayout layProfile;
  ImageView imgProfileBottom;
  TextView txtProfileBottom;

  private Fragment fragment;
  private FragmentManager fragmentManager;


  int home = 1;
  int post = 0;
  int pood = 0;
  int profile = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_a);
    //setUpStatusBar();
    setUpItemNavigation();
    fragmentManager = getSupportFragmentManager();

    layHomeBottom = (LinearLayout) findViewById(R.id.lay_home_bottom);
    imgHomeBottom = (ImageView) findViewById(R.id.img_home_bottom);
    txtHomeBottom = (TextView) findViewById(R.id.txt_home_bottom);

    layPoods = (LinearLayout) findViewById(R.id.lay_poods_bottom);
    imgPoodBottom = (ImageView) findViewById(R.id.img_poods_bottom);
    txtPoodBottom = (TextView) findViewById(R.id.txt_home_bottom);

    layProfile = (LinearLayout) findViewById(R.id.lay_profile_bottom);
    imgProfileBottom = (ImageView) findViewById(R.id.img_profile_bottom);
    txtProfileBottom = (TextView) findViewById(R.id.txt_profile_bottom);


    imgHomeBottom.setImageResource(R.drawable.home_black);
    txtHomeBottom.setVisibility(View.VISIBLE);
    fragment = new TwoFragment();
    final FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.main_container, fragment).commit();


    layProfile.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (profile == 0) {
          fragment = new ThreeFragment();
          final FragmentTransaction transaction = fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, fragment).commit();
          imgProfileBottom.setImageResource(R.drawable.user);
          txtProfileBottom.setVisibility(View.VISIBLE);
          imgHomeBottom.setImageResource(R.drawable.home_bottom_g);
          txtHomeBottom.setVisibility(View.GONE);
          profile = 1;
          home = 0;
          post = 0;
          pood = 0;

        }
      }
    });

    layHomeBottom.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (home == 0) {
          fragment = new TwoFragment();
          final FragmentTransaction transaction = fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, fragment).commit();
          imgHomeBottom.setImageResource(R.drawable.home_black);
          txtHomeBottom.setVisibility(View.VISIBLE);
          imgProfileBottom.setImageResource(R.drawable.user_w);
          txtProfileBottom.setVisibility(View.GONE);
          profile = 0;
          home = 1;
          post = 0;
          pood = 0;

        }
      }
    });

  }

  public void setUpItemNavigation() {
    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
      }
    });

  }

}
