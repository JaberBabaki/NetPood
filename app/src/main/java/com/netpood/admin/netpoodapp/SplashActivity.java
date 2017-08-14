package com.netpood.admin.netpoodapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.netpood.admin.framework.activity.UAppCompatActivity;

public class SplashActivity extends UAppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        finish();
        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
        startActivity(intent);
      }
    }, 2000);
  }
}
