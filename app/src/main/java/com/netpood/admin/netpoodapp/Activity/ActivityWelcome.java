package com.netpood.admin.netpoodapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.netpoodapp.R;

import java.util.Locale;


public class ActivityWelcome extends UAppCompatActivity {

  private ViewPager viewPager;
  private MyViewPagerAdapter myViewPagerAdapter;
  private LinearLayout dotsLayout;
  private TextView[] dots;
  private int[] layouts;
  private Button btnSkip, btnNext;
  int[] colorsActive;
  int[] colorsInactive;
  //private PrefManager prefManager;
  Locale myLocale;
  String CountryID = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupStatusBar();

    setContentView(R.layout.activity_welcome);
   // GetCountryZipCode();
    viewPager = (ViewPager) findViewById(R.id.view_pager);
    dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
    btnSkip = (Button) findViewById(R.id.btn_skip);

    layouts = new int[]{
      R.layout.welcome_slide1,
      R.layout.welcome_slide2,
      R.layout.welcome_slide3,
      R.layout.welcome_slide4};

    addBottomDots(0);
    changeStatusBarColor();

    myViewPagerAdapter = new MyViewPagerAdapter();
    viewPager.setAdapter(myViewPagerAdapter);
    viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

    btnSkip.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(ActivityWelcome.this, AccontActivity.class));
        finish();
      }
    });

    dotsLayout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(ActivityWelcome.this, MainActivity.class));
        finish();
      }
    });

  }

  public void setupStatusBar() {
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      View view = getWindow().getDecorView();
      view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      getWindow().setStatusBarColor(Color.parseColor("#908B9DAF"));
    }
  }

  private void addBottomDots(int currentPage) {
    dots = new TextView[layouts.length];

    colorsActive = getResources().getIntArray(R.array.array_dot_active);
    colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

    dotsLayout.removeAllViews();
    for (int i = 0; i < dots.length; i++) {
      dots[i] = new TextView(this);
      dots[i].setText(Html.fromHtml("&#8226;"));
      dots[i].setTextSize(35);
      dots[i].setTextColor(colorsInactive[currentPage]);
      dotsLayout.addView(dots[i]);
    }

    if (dots.length > 0)
      dots[currentPage].setTextColor(colorsActive[currentPage]);
  }

  private int getItem(int i) {
    return viewPager.getCurrentItem() + i;
  }

  //  viewpager change listener
  ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

    @Override
    public void onPageSelected(int position) {
      addBottomDots(position);
      btnSkip.setBackgroundColor(colorsInactive[position]);
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
  };

  /**
   * Making notification bar transparent
   */
  private void changeStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.TRANSPARENT);
    }
  }

  public void changeLang(String lang) {
    if (lang.equalsIgnoreCase(""))
      return;
    myLocale = new Locale(lang);
    saveLocale(lang);
    Locale.setDefault(myLocale);
    android.content.res.Configuration config = new android.content.res.Configuration();
    config.locale = myLocale;
    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    // updateTexts();
  }

  public void saveLocale(String lang) {
    String langPref = "Language";
    SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(langPref, lang);
  }

  public void GetCountryZipCode() {

    String CountryZipCode = "";
    TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    String CountryID = manager.getSimCountryIso().toUpperCase();
    if (CountryID.equals("IR") || CountryID.equals("ir")) {
      changeLang("fa");
    } else {
      changeLang("en");
    }
  }

  /**
   * View pager adapter
   */
  public class MyViewPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;

    public MyViewPagerAdapter() {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view = layoutInflater.inflate(layouts[position], container, false);
      container.addView(view);
      return view;
    }

    @Override
    public int getCount() {
      return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
      return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      View view = (View) object;
      container.removeView(view);
    }

  }
}