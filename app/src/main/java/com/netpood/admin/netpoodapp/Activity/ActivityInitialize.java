package com.netpood.admin.netpoodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;

public class ActivityInitialize extends UAppCompatActivity {
  public int                   cl2;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initialize);
    EditText edtUserName=(EditText)findViewById(R.id.edt_username);
    EditText edtPassword=(EditText)findViewById(R.id.edt_password);
    EditText edtConfrimPassword=(EditText)findViewById(R.id.edt_confrim_password);
    //final EditText edtDate=(EditText)findViewById(R.id.edt_date);
    LinearLayout layMen=(LinearLayout)findViewById(R.id.lay_men);
    LinearLayout layWomen=(LinearLayout)findViewById(R.id.lay_women);

    final ImageView imgMen=(ImageView)findViewById(R.id.img_men);
    final ImageView imgWomen=(ImageView)findViewById(R.id.img_women);

    Button btnOk=(Button)findViewById(R.id.btn_ok);

    edtUserName.setTypeface(Base.font1);
    edtPassword.setTypeface(Base.font1);
    btnOk.setTypeface(Base.font1);
    //edtDate.setTypeface(Base.font1);
    edtConfrimPassword.setTypeface(Base.font1);
    btnOk.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(Base.getCurrentActivity(), ActivitySelectPood.class);
        Base.getCurrentActivity().startActivity(intent);
        finish();
      }
    });
    layMen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        imgMen.setImageResource(R.drawable.round_done_button);
        imgWomen.setImageResource(R.drawable.rounddonebutton_free);
      }
    });
    layWomen.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        imgMen.setImageResource(R.drawable.rounddonebutton_free);
        imgWomen.setImageResource(R.drawable.round_done_button);
      }
    });


  }
}
