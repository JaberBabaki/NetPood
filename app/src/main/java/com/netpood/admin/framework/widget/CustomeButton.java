package com.netpood.admin.framework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import com.netpood.admin.netpoodapp.Base;

/**
 * Created by jaberALU on 02/08/2017.
 */
public class CustomeButton extends Button {
  public CustomeButton(Context context) {
    super(context);
    initialize();
  }

  public CustomeButton(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize();
  }

  public CustomeButton(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CustomeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    initialize();
  }

  private void initialize() {
    if ( !isInEditMode()) {
      setTypeface(Base.font1);
    }
  }
}
