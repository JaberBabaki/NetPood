package com.netpood.admin.netpoodapp;


import android.graphics.Typeface;

import com.netpood.admin.framework.core.UBase;

import java.io.ByteArrayOutputStream;

public class Base extends UBase {
  public static Typeface font1;
  public static String         FONT1_NAME   = "font/IRAN-Sans-Light.otf";
  public static ByteArrayOutputStream getPic;
  public static int rotation;

  @Override
  public void onCreate() {
    super.onCreate();
    font1 = Typeface.createFromAsset(getContext().getAssets(), FONT1_NAME);

  }
}
