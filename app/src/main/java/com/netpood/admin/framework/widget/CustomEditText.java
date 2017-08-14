package com.netpood.admin.framework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import com.netpood.admin.netpoodapp.Base;

/**
 * Created by jaberALU on 02/08/2017.
 */
public class CustomEditText extends EditText {
  public CustomEditText(Context context) {
    super(context);
    initialize();
  }

  public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initialize();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    initialize();
  }

  public CustomEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    initialize();
  }

  private void initialize() {
    if ( !isInEditMode()) {
      setTypeface(Base.font1);
    }
  }
}
