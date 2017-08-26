package com.netpood.admin.netpoodapp.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;

/**
 * Created by jaberALU on 19/07/2017.
 */


public class ActivitySendPost extends UAppCompatActivity {


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
     setContentView(R.layout.send_post);

    setUpStatusBar();
    ImageView imgSend = (ImageView) findViewById(R.id.img_send);
    final TextView edtTitle = (TextView) findViewById(R.id.edt_text);
    final TextView edtText = (TextView) findViewById(R.id.edt_title);
    final TextView edtTag = (TextView) findViewById(R.id.edt_tag);
    final Button btnSend = (Button) findViewById(R.id.btn_send);
    ImageView imgBack = (ImageView) findViewById(R.id.img_back_send_post);
    final LinearLayout layMain = (LinearLayout) findViewById(R.id.lay_main_send_post);
    final CardView layPre = (CardView) findViewById(R.id.card_item);
    final ImageView imgMain = (ImageView) findViewById(R.id.img_main_item);

    imgSend.setImageBitmap(Base.bit);
    edtText.setTypeface(Base.font1);
    edtTitle.setTypeface(Base.font1);
    edtTag.setTypeface(Base.font1);
    btnSend.setTypeface(Base.font1);

    imgBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        if(btnSend.getVisibility()==View.GONE){

          layPre.setVisibility(View.GONE);
          layMain.setVisibility(View.VISIBLE);
          btnSend.setVisibility(View.VISIBLE);

        }else if(btnSend.getVisibility()==View.VISIBLE){
          Base.title=edtTitle.getText().toString();
          Base.tag=edtTag.getText().toString();
          Base.matn=edtText.getText().toString();
          finish();

        }

      }
    });

    btnSend.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        layPre.setVisibility(View.VISIBLE);
        layMain.setVisibility(View.GONE);
        imgMain.setImageBitmap(Base.bit);
        btnSend.setVisibility(View.GONE);
      }
    });

    if(!(Base.title==null)){
      edtTitle.setText(Base.title);

    }
    if(!(Base.tag==null)){
      edtTag.setText(Base.tag);

    }
    if(!(Base.matn==null)){
      edtText.setText(Base.matn);

    }

  }

  public void setUpStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      View view = getWindow().getDecorView();
      view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
      getWindow().setStatusBarColor(Color.parseColor("#F0F0F0"));
    }
  }

  public void showDialogZearat() {
    final Dialog dialog2 = new Dialog(Base.getCurrentActivity());
    dialog2.setContentView(R.layout.dialog_pre_show);
    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    ImageView imgMain = (ImageView) dialog2.findViewById(R.id.img_main_item);
    imgMain.setImageBitmap(Base.bit);
    dialog2.setCancelable(true);
    dialog2.show();
    //drawer.closeDrawers();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();

  }
}
