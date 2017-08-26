package com.netpood.admin.netpoodapp.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.CustomSnakeBar;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;

public class ActivitySignIn extends UAppCompatActivity {
  EditText edtPre;
  EditText txtPhoneNumber;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    TextView txtForGot=(TextView) findViewById(R.id.txt_forgot);
    TextView txtSignUp=(TextView) findViewById(R.id.txt_sign_up);
    edtPre=(EditText) findViewById(R.id.edt_pre_number);
    txtPhoneNumber=(EditText) findViewById(R.id.edt_phone_number);
    edtPre.setText( GetCountryZipCode());
    txtForGot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (txtPhoneNumber.getText().toString().length() > 5 && edtPre.getText().toString().length()>=1) {
          showDialogZearat();
        }else{
          CustomSnakeBar customSnakeBar = new CustomSnakeBar();
          customSnakeBar.showMessage(v, "Please enter the phone number correctly");
        }
      }
    });
    txtSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(Base.getCurrentActivity(), AccontActivity.class);
        finish();
        Base.getCurrentActivity().startActivity(intent);
      }
    });

  }

  public void showDialogZearat() {
    final Dialog dialog2 = new Dialog(Base.getCurrentActivity());
    dialog2.setContentView(R.layout.dialog_forgot);
    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    //ImageView imgMain = (ImageView) dialog2.findViewById(R.id.img_main_item);
    //imgMain.setImageBitmap(Base.bit);
    dialog2.setCancelable(true);
    dialog2.show();
  }
  public String GetCountryZipCode() {
    String CountryID = "";
    String CountryZipCode = "";
    TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    CountryID = manager.getSimCountryIso().toUpperCase();
    String[] rl = this.getResources().getStringArray(R.array.CountryCodes);
    for (int i = 0; i < rl.length; i++) {
      String[] g = rl[i].split(",");
      if (g[1].trim().equals(CountryID.trim())) {
        CountryZipCode = g[0];
        break;
      }
    }
    return CountryZipCode;
  }
}
