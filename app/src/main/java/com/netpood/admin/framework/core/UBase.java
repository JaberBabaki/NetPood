package com.netpood.admin.framework.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.TransitionRes;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UBase extends Application {
  private static Context context;
  private static Activity currentActivity;
  private static LayoutInflater layoutInflater;
  private static TransitionInflater transitionInflater;
  private static Handler handler;
  private static DisplayMetrics displayMetrics;
  private static UBase base;

  @Override
  public void onCreate() {
    super.onCreate();

    Log.i("LOG", "Hello From Base");

    base = this;
    context = getApplicationContext();
    handler = new Handler();
    displayMetrics = getResources().getDisplayMetrics();
    layoutInflater = LayoutInflater.from(context);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      transitionInflater = TransitionInflater.from(context);
    }
  }

  public static UBase get() {
    return base;
  }

  public static Context getContext() {
    if (currentActivity != null) {
      return currentActivity;
    }

    return context;
  }

  public static void setCurrentActivity(Activity activity) {
    currentActivity = activity;
  }

  public static Activity getCurrentActivity() {
    return currentActivity;
  }

  public static LayoutInflater getLayoutInflater() {
    return layoutInflater;
  }

  public static TransitionInflater getTransitionInflater() {
    return transitionInflater;
  }

  public static Transition inflateTransition(@TransitionRes int res) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      return transitionInflater.inflateTransition(res);
    }

    return null;
  }

  public static View inflateLayout(@LayoutRes int res) {
    return layoutInflater.inflate(res, null);
  }

  public static View inflateLayout(@LayoutRes int res, @Nullable ViewGroup root) {
    return layoutInflater.inflate(res, root);
  }

  public static Handler getHandler() {
    return handler;
  }

  public static DisplayMetrics getDisplayMetrics() {
    return displayMetrics;
  }
}
