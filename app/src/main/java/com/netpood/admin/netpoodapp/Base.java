package com.netpood.admin.netpoodapp;


import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.netpood.admin.framework.core.UBase;

import java.io.ByteArrayOutputStream;

public class Base extends UBase {
  public static Typeface font1;
  public static String         FONT1_NAME   = "font/IRAN-Sans-Light.otf";
  public static ByteArrayOutputStream getPic;
  public static int rotation;
  public static Bitmap bit;

  public static String title;
  public static String matn;

  public static String tag;

  public static Fragment fragment;
  public static FragmentManager fragmentManager;

  public static int folloing;

  @Override
  public void onCreate() {
    super.onCreate();
    font1 = Typeface.createFromAsset(getContext().getAssets(), FONT1_NAME);


  }
}
