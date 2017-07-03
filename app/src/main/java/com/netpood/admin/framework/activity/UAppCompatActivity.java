package com.netpood.admin.framework.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.netpood.admin.framework.core.UBase;
import com.netpood.admin.netpoodapp.R;

import java.lang.reflect.Field;

public class UAppCompatActivity extends AppCompatActivity {

  @Override
  public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

  @Override
  protected void onResume() {
    super.onResume();

    UBase.setCurrentActivity(this);
  }


  public static class Founder {
    private final Activity activity;
    private int[] features;
    private boolean noTitlebar;
    private boolean noActionbar;
    private boolean fullscreen;
    private boolean statusbar;
    private int layoutId;
    private Object ui;

    public Founder(Activity activity) {
      this.activity = activity;
    }

    public Founder requestFeatures(int... features) {
      this.features = features;
      return this;
    }

    public Founder noTitlebar() {
      this.noTitlebar = true;
      return this;
    }

    public Founder noActionbar() {
      this.noActionbar = true;
      return this;
    }

    public Founder fullscreen() {
      this.fullscreen = true;
      return this;
    }
    public Founder statusBar() {
      this.statusbar = true;
      return this;
    }

    public Founder contentView(@LayoutRes int layoutResID) {
      this.layoutId = layoutResID;
      return this;
    }

    public Founder extractUi(Object ui) {
      this.ui = ui;
      return this;
    }

    public Founder build() {
      for (int feature: this.features) {
        activity.getWindow().requestFeature(feature);
      }

      if (noTitlebar) {
        activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
      }

      if (fullscreen) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      }
      if (statusbar) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
          Window window = activity.getWindow();
          window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
          window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          window.setStatusBarColor(activity.getResources().getColor(R.color.colorToolbar));
        }
      }

      if (noActionbar) {
        activity.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        {
          ActionBar actionBar = activity.getActionBar();
          if (actionBar != null) {
            actionBar.hide();
          }
        }

        if (activity instanceof AppCompatActivity) {
          AppCompatActivity castedActivity = (AppCompatActivity) activity;
          android.support.v7.app.ActionBar actionBar = castedActivity.getSupportActionBar();
          if (actionBar != null) {
            actionBar.hide();
          }
        }
      }

      activity.setContentView(layoutId);

      // reflect ui elements
      {
        Class clazz = ui.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
          String name = field.getName();
          Class type = field.getType();
          if (name.contains("$")) {
            continue;
          }


          //Log.i("LOG", "id : "+name );
          int id =  UBase.get().getResources().getIdentifier(name, "id",  UBase.get().getPackageName());
          Log.i("LOG", "id control v  : "+id);
          try {
            field.set(ui, activity.findViewById(id));
          } catch (IllegalAccessException e) {
            Log.i("LOG", e.toString());
            e.printStackTrace();
          }
        }
      }

      return this;
    }
  }
}
