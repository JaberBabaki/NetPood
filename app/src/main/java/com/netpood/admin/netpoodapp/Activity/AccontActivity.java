package com.netpood.admin.netpoodapp.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.CustomSnakeBar;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterContryItem;
import com.netpood.admin.netpoodapp.adapter.OnItemClickListener;
import com.netpood.admin.netpoodapp.database.CountryItem;
import com.netpood.admin.netpoodapp.database.DataFakeCountry;
import com.netpood.admin.netpoodapp.database.LoginItem;
import com.netpood.admin.netpoodapp.webService.ApiClient;
import com.netpood.admin.netpoodapp.webService.ApiInterface;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccontActivity extends UAppCompatActivity {

  LinearLayout layPhoneNumber;
  LinearLayout layVertification;
  LinearLayout layCode;
  LinearLayout layWill;

  private static final String FORMAT = "%02d:%02d";
  TextView txtM3;
  TextView txtCreate;
  LinearLayout layChoise;
  CountDownTimer cd;
  EditText edtPhoneNumber;
  Button btnSignUp;
  TextView btnSignIn;
  LinearLayout layEnableEdit;
  LinearLayout layForgot;
  TextView txtM1;
  TextView txtRecieved;
  TextView txtNetPood;
  EditText edtPass;
  EditText edtPreNumber;
  TextView edtChooseCountry;
  String CountryID = "";
  RecyclerView recyclerView;
  AdapterContryItem adapterMainItem;
  ArrayList<CountryItem> itemCountry;
  ArrayList<CountryItem> itemCountry2;
  String name = " ";
  AdapterContryItem newsAdapter;
  Dialog dialog2;
  int x = 0;
  Timer timer;
  private ApiInterface apiInterface;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_accont);


    layPhoneNumber = (LinearLayout) findViewById(R.id.lay_phone_number);
    layVertification = (LinearLayout) findViewById(R.id.lay_vertification);
    layCode = (LinearLayout) findViewById(R.id.lay_code);
    layWill = (LinearLayout) findViewById(R.id.lay_will);

    edtPhoneNumber = (EditText) findViewById(R.id.edt_phone_number);
    layChoise = (LinearLayout) findViewById(R.id.lay_choice_cuntry);
    btnSignUp = (Button) findViewById(R.id.btn_sign_up_account);
    btnSignIn = (TextView) findViewById(R.id.btn_sign_in);
    layEnableEdit = (LinearLayout) findViewById(R.id.lay_edit);
    layForgot = (LinearLayout) findViewById(R.id.lay_forgot);
    txtCreate = (TextView) findViewById(R.id.txt_create);
    txtM1 = (TextView) findViewById(R.id.txt_m1);
    txtRecieved = (TextView) findViewById(R.id.txt_number_recived);
    txtM3 = (TextView) findViewById(R.id.txt_m3);
    txtNetPood = (TextView) findViewById(R.id.txt_netpood);
    edtPass = (EditText) findViewById(R.id.edt_pass);
    edtPreNumber = (EditText) findViewById(R.id.edt_pre_number);
    edtChooseCountry = (TextView) findViewById(R.id.edt_choice_country);
    EditText edtPreNumberPlus = (EditText) findViewById(R.id.edt_pre_number_plus);
    edtPreNumberPlus.setEnabled(false);

    edtPass.setTypeface(Base.font1);
    edtPhoneNumber.setTypeface(Base.font1);
    btnSignUp.setTypeface(Base.font1);
    GetCountryZipCode();
    edtChooseCountry.setEnabled(true);
    btnSignUp.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                     if (layPhoneNumber.getVisibility() == View.VISIBLE) {
                                       if (edtPreNumber.getText().toString().trim().equals("98")) {
                                         if (edtPhoneNumber.getText().toString().length() == 11) {

                                           showLoadingBar();
                                           loadJSON(edtPhoneNumber.getText().toString(), edtPreNumber.getText().toString(), edtChooseCountry.getText().toString());
                                         } else {
                                           CustomSnakeBar customSnakeBar = new CustomSnakeBar();
                                           customSnakeBar.showMessage(v, "شماره موبایل یازده رقمی وارد کنید");
                                         }
                                       } else if (edtPhoneNumber.getText().toString().length() > 5) {
                                         showLoadingBar();
                                         loadJSON(edtPhoneNumber.getText().toString(), edtPreNumber.getText().toString(), edtChooseCountry.getText().toString());

                                       } else {
                                         CustomSnakeBar customSnakeBar = new CustomSnakeBar();
                                         customSnakeBar.showMessage(v, "لطفا شماره موبایلی درست وارد کنید");


                                       }
                                       Log.i("OSO", "1 name" + "|" + "searchString");
                                     } else if (layPhoneNumber.getVisibility() == View.GONE) {
                                       Log.i("OSO", "2 name " + "|" + "searchString");
                                       if (edtPass.getText().toString().length() >= 6) {
                                         Intent intent = new Intent(Base.getCurrentActivity(), ActivityInitialize.class);
                                         Base.getCurrentActivity().startActivity(intent);
                                         finish();

                                       }
                                     }
                                   }
                                 }

    );

    layEnableEdit.setOnClickListener(new View.OnClickListener()

                                     {
                                       @Override
                                       public void onClick(View v) {
                                         layPhoneNumber.setVisibility(View.VISIBLE);

                                         layVertification.setVisibility(View.GONE);
                                         layCode.setVisibility(View.GONE);
                                         layWill.setVisibility(View.GONE);

                                         btnSignUp.setText("NEXT");
                                         txtCreate.setText("Phone Number");
                                         cd.cancel();
                                       }
                                     }

    );
    btnSignIn.setOnClickListener(new View.OnClickListener()

                                 {
                                   @Override
                                   public void onClick(View v) {

                                     Intent intent = new Intent(Base.getCurrentActivity(), ActivitySignIn.class);
                                     finish();
                                     Base.getCurrentActivity().startActivity(intent);


                                   }
                                 }

    );
    txtNetPood.setOnClickListener(new View.OnClickListener()

                                  {
                                    @Override
                                    public void onClick(View v) {

                                      Intent intent = new Intent(Base.getCurrentActivity(), ActivityInitialize.class);
                                      finish();
                                      Base.getCurrentActivity().startActivity(intent);


                                    }
                                  }

    );
    edtChooseCountry.setOnClickListener(new View.OnClickListener()

                                        {
                                          @Override
                                          public void onClick(View v) {

                                            showDialogCountry();


                                          }
                                        }

    );

    edtPreNumber.addTextChangedListener(new

                                          TextWatcher() {
                                            @Override
                                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                                            }

                                            @Override
                                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                                            }

                                            @Override
                                            public void afterTextChanged(Editable s) {
                                              x = 0;
                                              String searchString = edtPreNumber.getText().toString().trim();
                                              int textLength = searchString.length();
                                              DataFakeCountry dc = new DataFakeCountry();
                                              for (int i = 0; i <= (dc.codeCountry.length - 1); i++) {
                                                name = dc.codeCountry[i].trim();
                                                // if (textLength <= name.length()) {
                                                if (searchString.equals(name)) {
                                                  edtChooseCountry.setText(dc.name[i]);
                                                  Log.i("OSO", name + "|" + searchString);
                                                  x = 1;
                                                }
                                              }
                                              if (x == 0) {
                                                edtChooseCountry.setText("Wrong country code");
                                              }
                                            }
                                          }

    );


    layForgot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showDialogForget();
      }
    });
  }

  public String GetCountryZipCode() {

    String CountryZipCode = "";
    TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
    CountryID = manager.getSimCountryIso().toUpperCase();
    DataFakeCountry dc = new DataFakeCountry();
    for (int i = 0; i < dc.shortName.length; i++) {
      String g = dc.shortName[i].trim();
      if (g.trim().equals(CountryID.trim())) {
        //Toast.makeText(getApplicationContext(), dc.codeCountry[i]+ dc.name[i], Toast.LENGTH_SHORT).show();
        edtPreNumber.setText(dc.codeCountry[i]);
        edtChooseCountry.setText(dc.name[i]);
        break;
      }
    }
    return CountryZipCode;
  }


  public void startTime2Min() {
    cd = new CountDownTimer(120000, 1000) {
      public void onTick(long millisUntilFinished) {
        txtM3.setText("NetPood will call you in  " + String.format(FORMAT,
          TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
          TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
      }

      public void onFinish() {
        cd.cancel();
        CustomSnakeBar customSnakeBar = new CustomSnakeBar();
        customSnakeBar.showMessage(btnSignUp, "Sorry, the code entry time has ended, please try again");
        layVertification.setVisibility(View.GONE);
        layCode.setVisibility(View.GONE);
        layWill.setVisibility(View.GONE);
        layPhoneNumber.setVisibility(View.VISIBLE);
        btnSignUp.setText("NEXT");
        txtCreate.setText("Phone Number");
      }
    };
    cd.start();
  }

  public void showDialogCountry() {
    dialog2 = new Dialog(Base.getCurrentActivity());
    dialog2.setContentView(R.layout.dialog_country);
    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    recyclerView = (RecyclerView) dialog2.findViewById(R.id.recycler_view);
    final EditText edtSearch = (EditText) dialog2.findViewById(R.id.edt_search_country);
    DataFakeCountry dc = new DataFakeCountry();
    itemCountry = dc.getData(this);
    itemCountry2 = new ArrayList<CountryItem>(itemCountry);
    newsAdapter = new AdapterContryItem(this, itemCountry2, onItemClickCallback);
    newsAdapter.notifyDataSetChanged();
    recyclerView.setLayoutManager(new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(newsAdapter);

    edtSearch.addTextChangedListener(new TextWatcher() {

      @Override
      public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
        String searchString = edtSearch.getText().toString().trim();
        int textLength = searchString.length();
        itemCountry2.clear();
        for (int i = 0; i <= (itemCountry.size() - 1); i++) {
          name = itemCountry.get(i).getNameCountry();
          if (textLength <= name.length()) {
            if (searchString.equalsIgnoreCase(name.substring(0, textLength)))
              itemCountry2.add(itemCountry.get(i));
          }
        }
        newsAdapter.notifyDataSetChanged();
      }

      @Override
      public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });

    dialog2.setCancelable(true);
    dialog2.show();
  }

  public void showLoadingBar() {
    dialog2 = new Dialog(Base.getCurrentActivity());
    dialog2.setContentView(R.layout.dialog_loadbar);
    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog2.setCancelable(true);
    dialog2.show();
  }

  public void showDialogForget() {
    final Dialog dialog2 = new Dialog(Base.getCurrentActivity());
    dialog2.setContentView(R.layout.dialog_forgot);
    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    final Button btnSendForgot = (Button) dialog2.findViewById(R.id.btn_forget_ok);
    btnSendForgot.setTypeface(Base.font1);
    btnSendForgot.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog2.cancel();
      }
    });
    dialog2.setCancelable(true);
    dialog2.show();
  }

  private OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
    @Override
    public void onItemClicked(View view, int position) {

      edtChooseCountry.setText("" + itemCountry2.get(position).getNameCountry());
      edtPreNumber.setText("" + itemCountry2.get(position).getCodeCountry());
      dialog2.dismiss();

    }
  };

  private void loadJSON(final String number, final String code, String country) {
    try {
      apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
      Log.i("PAG", "page   " + number);
      //,"app",code,country
      Call<ArrayList<LoginItem>> call = apiInterface.login(number);
      Log.i("RES", "" + call);
      call.enqueue(new Callback<ArrayList<LoginItem>>() {
        @Override
        public void onResponse(Call<ArrayList<LoginItem>> call, Response<ArrayList<LoginItem>> response) {
          // Log.i("RES", response.body().get(0).getNo());
          Log.i("RES", "" + call);
          if (response.isSuccessful()) {
            if ((response.body().get(0).getRand().equals("no"))) {
              dialog2.dismiss();
              layVertification.setVisibility(View.GONE);
              layCode.setVisibility(View.VISIBLE);
              layWill.setVisibility(View.GONE);
              edtPass.setHint("رمز عبور");
              layPhoneNumber.setVisibility(View.GONE);
              btnSignUp.setText("تایید");
              txtCreate.setText("ورود رمز");
              txtRecieved.setText(edtPreNumber.getText().toString()+ edtPhoneNumber.getText().toString());
              layForgot.setVisibility(View.GONE);
              //startTime2Min();
            } else if (!(response.body().get(0).getRand().equals("no"))) {
              dialog2.dismiss();
              edtPass.setHint("کد 6 رقمی");
              layVertification.setVisibility(View.VISIBLE);
              layCode.setVisibility(View.VISIBLE);
              layWill.setVisibility(View.VISIBLE);
              layPhoneNumber.setVisibility(View.GONE);
              btnSignUp.setText("تایید");
              txtCreate.setText("  کد " + response.body().get(0).getRand() + "  وارد کنید  ");
              layForgot.setVisibility(View.VISIBLE);

              startTime2Min();

            }
          }
        }

        @Override
        public void onFailure(Call<ArrayList<LoginItem>> call, Throwable throwable) {
          Log.i("RESE", "jaber" + throwable.toString());
        }
      });
    } catch (Exception e) {
      Log.i("ERR", e.getMessage());
      //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }

}

