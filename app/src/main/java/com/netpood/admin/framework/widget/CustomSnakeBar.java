package com.netpood.admin.framework.widget;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;

/**
 * Created by jaberALU on 01/08/2017.
 */
public class CustomSnakeBar {

  public void showMessage(View v, String str) {
    Snackbar snackbar = Snackbar.make(v, "", Snackbar.LENGTH_LONG);
    Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
    //layout.setBackgroundColor(Color.parseColor("#E25452"));
    View snackView = LayoutInflater.from(Base.getContext()).inflate(R.layout.item_snakebar, null);


    ViewGroup.LayoutParams parentParams = (ViewGroup.LayoutParams) layout.getLayoutParams();
    parentParams.height = (int) Base.getContext().getResources().getDimension(R.dimen.snakebar);
    parentParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
    layout.setLayoutParams(parentParams);


    TextView textViewTop = (TextView) snackView.findViewById(R.id.txt_main_item);
    textViewTop.setText(str);
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
      textViewTop.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    } else {
      textViewTop.setGravity(Gravity.CENTER_HORIZONTAL);
    }
    textViewTop.setTextColor(Color.parseColor("#ffffff"));
    layout.addView(snackView, 0);
    snackbar.show();

  }
}
