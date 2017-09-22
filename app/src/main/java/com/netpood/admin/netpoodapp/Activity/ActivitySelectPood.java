package com.netpood.admin.netpoodapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.CustomSnakeBar;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterPoodItem;
import com.netpood.admin.netpoodapp.adapter.OnItemClickListener;
import com.netpood.admin.netpoodapp.database.PoodItem;
import com.netpood.admin.netpoodapp.webService.ApiClient;
import com.netpood.admin.netpoodapp.webService.ApiInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitySelectPood extends UAppCompatActivity {

  public static RelativeLayout layGoMain;
  public static int countPood = 0;
  public static TextView txtCount;
  private ApiInterface apiInterface;
  private List<PoodItem> posts;
  private List<PoodItem> posts2;
  private RecyclerView recyclerView;
  private AdapterPoodItem newsAdapter;
  private CardView cardLoading;
  private LinearLayout layFilter;
  private LinearLayout layTopPost;
  private LinearLayout layTopMember;
  private LinearLayout laySearch;
  private LinearLayout layTitle;
  private LinearLayout layTextSearch;
  private ImageView imgTopMember;
  private ImageView imgTopPst;
  private ImageView imgCancelSearch;
  private EditText edtSearch;
  private String name = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_pood);


    ImageView imgGride = (ImageView) findViewById(R.id.list_gride);
    ImageView imgVertical = (ImageView) findViewById(R.id.list_vertical);
    TextView txtFilter = (TextView) findViewById(R.id.txt_filter);
    ImageView imgFilter = (ImageView) findViewById(R.id.img_filter);
    imgTopMember = (ImageView) findViewById(R.id.img_top_member);
    imgTopPst = (ImageView) findViewById(R.id.img_top_post);
    imgCancelSearch = (ImageView) findViewById(R.id.img_cancel_search);
    layFilter = (LinearLayout) findViewById(R.id.lay_filter);
    layTopPost = (LinearLayout) findViewById(R.id.lay_top_post);
    layTopMember = (LinearLayout) findViewById(R.id.lay_top_member);

    laySearch = (LinearLayout) findViewById(R.id.lay_search);
    layTitle = (LinearLayout) findViewById(R.id.lay_title);
    layTextSearch = (LinearLayout) findViewById(R.id.lay_text_search);

    layGoMain = (RelativeLayout) findViewById(R.id.lay_go_main);
    txtCount = (TextView) findViewById(R.id.txt_pood_count);
    edtSearch = (EditText) findViewById(R.id.edt_search_pood);
    cardLoading = (CardView) findViewById(R.id.card_item_frg_main);
    edtSearch.setTypeface(Base.font1);
    //cardLoading.setVisibility(
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    LinearLayoutManager lay = new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false);
    loadJSON();
    recyclerView.setLayoutManager(lay);

    imgGride.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        StaggeredGridLayoutManager lay = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lay);

      }
    });

    imgVertical.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        LinearLayoutManager lay = new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lay);
      }
    });
    layTopPost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        imgTopPst.setVisibility(View.VISIBLE);
        imgTopMember.setVisibility(View.INVISIBLE);
        Collections.sort(posts2, new MyPostComp());
        newsAdapter.notifyDataSetChanged();
        layFilter.setVisibility(View.GONE);

      }
    });
    layTopMember.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        imgTopMember.setVisibility(View.VISIBLE);
        imgTopPst.setVisibility(View.INVISIBLE);

        Collections.sort(posts2, new MyMemberComp());
        newsAdapter.notifyDataSetChanged();

        layFilter.setVisibility(View.GONE);
      }
    });

    txtFilter.setOnClickListener(onclic);
    imgFilter.setOnClickListener(onclic);

    laySearch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        laySearch.setVisibility(View.GONE);
        layTitle.setVisibility(View.GONE);
        layTextSearch.setVisibility(View.VISIBLE);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(edtSearch.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        edtSearch.requestFocus();
      }
    });
    imgCancelSearch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        laySearch.setVisibility(View.VISIBLE);
        layTitle.setVisibility(View.VISIBLE);
        layTextSearch.setVisibility(View.GONE);

      }
    });

    edtSearch.addTextChangedListener(new TextWatcher() {

      @Override
      public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
        String searchString = edtSearch.getText().toString().trim();
        int textLength = searchString.length();
        posts2.clear();
        for (int i = 0; i <= (posts.size() - 1); i++) {
          name = posts.get(i).getName();
          if (textLength <= name.length()) {
            if (searchString.equalsIgnoreCase(name.substring(0, textLength)))
              posts2.add(posts.get(i));
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
    layGoMain.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (Integer.parseInt(txtCount.getText().toString()) >= 5) {
          Intent intent = new Intent(Base.getCurrentActivity(), MainActivity.class);
          finish();
          Base.getCurrentActivity().startActivity(intent);
        } else {
          CustomSnakeBar customSnakeBar = new CustomSnakeBar();
          customSnakeBar.showMessage(layGoMain, "برای ورود باید حداقل 5 پود انتخاب شود");
        }
      }
    });
  }


  class MyPostComp implements Comparator<PoodItem> {

    @Override
    public int compare(PoodItem e1, PoodItem e2) {
      if (e1.getCountPost() < e2.getCountPost()) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  class MyMemberComp implements Comparator<PoodItem> {

    @Override
    public int compare(PoodItem e1, PoodItem e2) {
      if (e1.getCountUsers() < e2.getCountUsers()) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  View.OnClickListener onclic = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if (layFilter.getVisibility() == View.GONE) {
        layFilter.setVisibility(View.VISIBLE);
      } else {
        layFilter.setVisibility(View.GONE);
      }

    }
  };


  private OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
    @Override
    public void onItemClicked(View view, int position) {
      countPood = countPood + 1;
      layGoMain.setVisibility(View.VISIBLE);
    }
  };

  private void loadJSON() {
    try {
      apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
      Call<ArrayList<PoodItem>> call = apiInterface.selectItem();
      call.enqueue(new Callback<ArrayList<PoodItem>>() {
        @Override
        public void onResponse(Call<ArrayList<PoodItem>> call, Response<ArrayList<PoodItem>> response) {
          //Log.i("RES", response.toString());
          if (response.isSuccessful()) {
            posts = response.body();
            posts2 = new ArrayList<PoodItem>(posts);
            //posts2 = response.body();
            Log.i("PAG", "page  " + posts.get(0).getCountPost() + "  posts  " + posts.get(5).getCountPost());
            if(posts.get(0).getCountPost()==4024){

            }
            newsAdapter = new AdapterPoodItem(Base.getCurrentActivity(), posts2);
            recyclerView.setAdapter(newsAdapter);
            cardLoading.setVisibility(View.GONE);
            showLoadingBar();
          }
        }

        @Override
        public void onFailure(Call<ArrayList<PoodItem>> call, Throwable throwable) {
          Log.i("RESE", "jaber" + throwable.toString());
        }
      });
    } catch (Exception e) {
      Log.i("ERR", e.getMessage());
      //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }

  public void showLoadingBar() {
    final Dialog dialog2 = new Dialog(Base.getCurrentActivity());
    dialog2.setContentView(R.layout.dialog_welcome_pood);
    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    final Button btnOk = (Button) dialog2.findViewById(R.id.btn_ok);
    btnOk.setTypeface(Base.font1);
    btnOk.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dialog2.dismiss();
      }
    });
    dialog2.setCancelable(false);
    dialog2.show();
  }
}
