package com.netpood.admin.netpoodapp.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.netpood.admin.framework.activity.UAppCompatActivity;
import com.netpood.admin.framework.widget.CustomLoading;
import com.netpood.admin.framework.widget.refresh.RecyclerRefreshLayout;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterMessagePood;
import com.netpood.admin.netpoodapp.database.PostItem;
import com.netpood.admin.netpoodapp.webService.ApiClient;
import com.netpood.admin.netpoodapp.webService.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMessagePood extends UAppCompatActivity {

  public TextView txtName;
  public TextView   txtMember;
  private CardView cardLoading;
  private RecyclerView recyclerView;
  private int page = 0;
  private LinearLayoutManager lay;
  List<PostItem> movies;
  AdapterMessagePood adapter;
  ApiInterface api;
  Context context;
  CustomLoading pb;
  RecyclerRefreshLayout ref;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message_pood);
    txtName =(TextView)findViewById(R.id.txt_name);
    txtMember=(TextView)findViewById(R.id.txt_member);
    Bundle data = getIntent().getExtras();
    if (data != null) {
      txtName.setText(data.getString("name"));
      txtMember.setText(""+data.getInt("member")+ "  عضو " );
    }

    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    lay = new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false);
    cardLoading = (CardView) findViewById(R.id.card_item_frg_main);
    pb = (CustomLoading) findViewById(R.id.pb);
    pb.setTag("2");
    movies = new ArrayList<>();
    adapter = new AdapterMessagePood(Base.getContext(), movies);
    api = ApiClient.getApiClient().create(ApiInterface.class);
    //loadMore(0);

    adapter.setLoadMoreListener(new AdapterMessagePood.OnLoadMoreListener() {
      @Override
      public void onLoadMore() {
        if (!pb.getTag().toString().equals("1")) {
          recyclerView.post(new Runnable() {
            @Override
            public void run() {

              Log.i("LOD", "" + page);
              pb.setVisibility(View.VISIBLE);
              loadMore(page);
              page++;
            }
          });
        }
      }
    });

    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(lay);
    recyclerView.setAdapter(adapter);


  }

  private void loadMore(int index) {

    Call<ArrayList<PostItem>> call = api.getMainItem(index);
    call.enqueue(new Callback<ArrayList<PostItem>>() {
      @Override
      public void onResponse(Call<ArrayList<PostItem>> call, Response<ArrayList<PostItem>> response) {
        if (response.isSuccessful()) {
          ArrayList<PostItem> result = response.body();
          if (result.size() > 0) {
            movies.addAll(result);
            cardLoading.setVisibility(View.GONE);
            pb.setVisibility(View.GONE);
          } else {
            pb.setVisibility(View.GONE);
            pb.setTag("1");
          }
          adapter.notifyDataChanged();
        } else {
        }
      }

      @Override
      public void onFailure(Call<ArrayList<PostItem>> call, Throwable t) {
      }
    });
  }



}
