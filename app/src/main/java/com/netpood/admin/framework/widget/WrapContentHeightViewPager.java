package com.netpood.admin.framework.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class WrapContentHeightViewPager extends ViewPager {

  /**
   * Constructor
   *
   * @param context the context
   */
  public WrapContentHeightViewPager(Context context) {
    super(context);
  }

  /**
   * Constructor
   *
   * @param context the context
   * @param attrs the attribute set
   */
  public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int mode = MeasureSpec.getMode(heightMeasureSpec);
    // Unspecified means that the ViewPager is in a ScrollView WRAP_CONTENT.
    // At Most means that the ViewPager is not in a ScrollView WRAP_CONTENT.
    if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
      // super has to be called in the beginning so the child views can be initialized.
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      int height = 0;
      for (int i = 0; i < getChildCount(); i++) {
        View child = getChildAt(i);
        child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        int h = child.getMeasuredHeight();
        if (h > height) height = h;
      }
      heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
    }
    // super has to be called again so the new specs are treated as exact measurements
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  /**
   * Determines the height of this view
   *
   * @param measureSpec A measureSpec packed into an int
   * @param view the base view with already measured height
   *
   * @return The height of the view, honoring constraints from measureSpec
   */
  private int measureHeight(int measureSpec, View view) {
    int result = 0;
    int specMode = MeasureSpec.getMode(measureSpec);
    int specSize = MeasureSpec.getSize(measureSpec);

    if (specMode == MeasureSpec.EXACTLY) {
      result = specSize;
    } else {
      // set the height from the base view if available
      if (view != null) {
        result = view.getMeasuredHeight();
      }
      if (specMode == MeasureSpec.AT_MOST) {
        result = Math.min(result, specSize);
      }
    }
    return result;
  }

}