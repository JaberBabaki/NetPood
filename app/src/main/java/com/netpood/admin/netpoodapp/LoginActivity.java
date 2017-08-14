package com.netpood.admin.netpoodapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.CustomSnakeBar;

import java.util.Locale;

public class LoginActivity extends UAppCompatActivity {
  Locale myLocale;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    TextView txt = (TextView) findViewById(R.id.txt_netpood);
    TextView txtEng = (TextView) findViewById(R.id.txt_en);
    TextView txtPer = (TextView) findViewById(R.id.txt_per);
    final Button btnSignUp = (Button) findViewById(R.id.btn_signup);
    final Button btnSignIN = (Button) findViewById(R.id.btn_sign_in);

    txtEng.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        btnSignUp.setText("SIGN UP");
        btnSignIN.setText("SIGN IN");
        CustomSnakeBar customSnakeBar = new CustomSnakeBar();
        customSnakeBar.showMessage(v, "    Language changed to english");
      }
    });
    txtPer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        changeLang("fa");
        btnSignUp.setText("ثبت نام");
        btnSignIN.setText("ورود");
        CustomSnakeBar customSnakeBar = new CustomSnakeBar();
        customSnakeBar.showMessage(v, "   زبان به فارسي تغيير يافته  ");
      }
    });

    txt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Base.getCurrentActivity(), MainActivity.class);
        Base.getCurrentActivity().startActivity(intent);
      }
    });
    btnSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Base.getCurrentActivity(), AccontActivity.class);
        Base.getCurrentActivity().startActivity(intent);
      }
    });
    btnSignIN.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Base.getCurrentActivity(), SignInActivity.class);
        Base.getCurrentActivity().startActivity(intent);
      }
    });
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

  public void loadLocale() {
    String langPref = "Language";
    SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
    String language = prefs.getString(langPref, "");
    changeLang(language);
  }
}
