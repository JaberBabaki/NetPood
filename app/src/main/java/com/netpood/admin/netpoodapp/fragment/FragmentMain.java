package com.netpood.admin.netpoodapp.fragment;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.netpood.admin.framework.widget.EndlessRecyclerViewScrollListener;
import com.netpood.admin.framework.widget.InfiniteScrollListener;
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
  private NavigationView drawer;
  private int position;
  private Bundle bundle;
  private ApiInterface apiInterface;
  private List<PostItem> posts;
  private RecyclerView recyclerView;
  private SwipeRefreshLayout swipRefresh;
  private CardView cardLoading;
  private AdapterMainItem newsAdapter;
  private LinearLayoutManager lay;
  private EndlessRecyclerViewScrollListener scrollListener;
  private int page=0;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    view = inflater.inflate(R.layout.fragment_main, container, false);
    initView();
    initToolbar();

    swipRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swip_refresh);
    swipRefresh.setColorSchemeResources(R.color.colorToolbar);
    swipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        loadJSON(page++);

      }
    });
    initToolbar();
    return view;
  }

  public void initView() {
    cardLoading = (CardView) view.findViewById(R.id.card_item);
    cardLoading.setVisibility(View.VISIBLE);
    loadJSON(page++);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    lay = new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(lay);
    bundle = getArguments();
    if (bundle != null) {
      position = bundle.getInt("POS");
      //recyclerView.scrollToPosition(position);
      lay.scrollToPositionWithOffset(position, 0);
      Toast.makeText(Base.getCurrentActivity(), "position  " + position, Toast.LENGTH_SHORT).show();
    }

    recyclerView.addOnScrollListener(createInfiniteScrollListener());
  }
  @NonNull
  private InfiniteScrollListener createInfiniteScrollListener() {
    return new InfiniteScrollListener(10, lay) {
      @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
        Log.i("PAG","page   "+page);
        int start = page++ ;
        loadJSON(page++);
        final boolean allItemsLoaded = start >= posts.size();

      }
    };
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

  private void loadJSON(final int page) {
    try {
      apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
      Log.i("PAG","page   "+page);
      Call<ArrayList<PostItem>> call = apiInterface.getMainItem(page);
      call.enqueue(new Callback<ArrayList<PostItem>>() {
        @Override
        public void onResponse(Call<ArrayList<PostItem>> call, Response<ArrayList<PostItem>> response) {
          //Log.i("RES", response.toString());
          if (response.isSuccessful()) {
            posts = response.body();
            Log.i("PAG","page  "+page+"  ID  "+ posts.get(0).getId()+"  posts  "+posts.size());
            newsAdapter = new AdapterMainItem(Base.getCurrentActivity(), posts);
            recyclerView.setAdapter(newsAdapter);
            cardLoading.setVisibility(View.GONE);
            swipRefresh.setRefreshing(false);
          }
        }

        @Override
        public void onFailure(Call<ArrayList<PostItem>> call, Throwable throwable) {
          Log.i("RESE", "jaber" + throwable.toString());
        }
      });
    } catch (Exception e) {
      Log.i("ERR", e.getMessage());
      //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }
  }
}