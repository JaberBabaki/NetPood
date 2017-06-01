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

public class PersonalPage extends UAppCompatActivity {

  private Ui ui;

  public class Ui {
    ImageView imgPersonalPage;
    ImageView imgAddPost;
    ImageView imgHome;
    ViewPager viewPager;
    TabLayout tabLayout;
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ui = new Ui();

    new Founder(this)
      .requestFeatures()
      .noTitlebar()
      .noActionbar()
      .statusBar()
      .contentView(R.layout.main)
      .extractUi(ui)
      .build();

  ui.imgHome.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                  Intent intent = new Intent(Base.getCurrentActivity(), MainActivity.class);
                                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                  ActivityCompat.finishAffinity(Base.getCurrentActivity());
                                  Base.getCurrentActivity().startActivity(intent);
                                }
                              });


    ui.imgAddPost.setOnClickListener(
      new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          Intent intent = new Intent(Base.getCurrentActivity(), CameraDemoActivity.class);
          Base.getCurrentActivity().startActivity(intent);
        }
      });


  }



}
