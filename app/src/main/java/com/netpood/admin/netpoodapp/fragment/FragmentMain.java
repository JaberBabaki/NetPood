package com.netpood.admin.netpoodapp.fragment;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.netpood.admin.framework.widget.CustomLoading;
import com.netpood.admin.framework.widget.refresh.RecyclerRefreshLayout;
import com.netpood.admin.netpoodapp.Activity.MainActivity;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterMainItem;
import com.netpood.admin.netpoodapp.database.PostItem;
import com.netpood.admin.netpoodapp.webService.ApiClient;
import com.netpood.admin.netpoodapp.webService.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMain extends Fragment {
  private View view;
  private int position;
  private Bundle bundle;
  private CardView cardLoading;
  private RecyclerView recyclerView;
  private int page = 0;
  private LinearLayoutManager lay;
  List<PostItem> movies;
  AdapterMainItem adapter;
  ApiInterface api;
  Context context;
  CustomLoading pb;
  RecyclerRefreshLayout ref;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.fragment_main, container, false);
    initView();
    initToolbar();
    return view;
  }
  public void initView() {
    bundle = getArguments();
    if (bundle != null) {
      position = bundle.getInt("POS");
      lay.scrollToPositionWithOffset(position, 0);
      Toast.makeText(Base.getCurrentActivity(), "position  " + position, Toast.LENGTH_SHORT).show();
    }

    ref = (RecyclerRefreshLayout) view.findViewById(R.id.refresh_layout);
    ref.setRefreshStyle(RecyclerRefreshLayout.RefreshStyle.PINNED);
    ref.setRefreshInitialOffset(35);
    ref.setRefreshTargetOffset(163);
    ref.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {

        //deleteTen();
        loadMore(page++);

      }
    });
    //ref.setBackgroundColor(Color.parseColor("#818d9d"));

    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    lay = new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false);
    cardLoading = (CardView) view.findViewById(R.id.card_item_frg_main);
    pb = (CustomLoading) view.findViewById(R.id.pb);
    pb.setTag("2");
    movies = new ArrayList<>();
    adapter = new AdapterMainItem(Base.getContext(), movies);
    api = ApiClient.getApiClient().create(ApiInterface.class);
    //loadMore(0);

    adapter.setLoadMoreListener(new AdapterMainItem.OnLoadMoreListener() {
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


  public void initToolbar() {
    final LinearLayout laySearch = (LinearLayout) view.findViewById(R.id.lay_search_p);
    ImageView imgDrawer = (ImageView) view.findViewById(R.id.img_drawer);
    final ImageView imgSearch = (ImageView) view.findViewById(R.id.img_search);
    final ImageView imgCancel = (ImageView) view.findViewById(R.id.img_cancel_search);
    final EditText edtSearch = (EditText) view.findViewById(R.id.edt_search);
    edtSearch.setTypeface(Base.font1);
    Activity activity = getActivity();
    if (activity instanceof MainActivity) {
      final MainActivity myactivity = (MainActivity) activity;
      imgDrawer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          myactivity.navigationView.openDrawer(GravityCompat.START);
        }
      });
    }
    imgSearch.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        laySearch.setVisibility(View.VISIBLE);
        imgSearch.setVisibility(View.GONE);
      }
    });
    imgCancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        laySearch.setVisibility(View.GONE);
        imgSearch.setVisibility(View.VISIBLE);
      }
    });
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
            ref.setRefreshing(false);
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

  public void deleteTen(){
    movies=new ArrayList<>();
    //adapter.notifyDataChanged();
    page=0;

  }
}