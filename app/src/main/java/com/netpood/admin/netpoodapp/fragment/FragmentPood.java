package com.netpood.admin.netpoodapp.fragment;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterPoodItem;
import com.netpood.admin.netpoodapp.adapter.AdapterSelectedPoodItem;
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


public class FragmentPood extends Fragment {
  private View view;
  public static RelativeLayout layGoMain;
  public static int countPood = 0;
  public static TextView txtCount;
  private ApiInterface apiInterface;
  private List<PoodItem> poodSelect;
  private List<PoodItem> posts;
  private List<PoodItem> posts2;
  private RecyclerView recyclerView;
  private RecyclerView recyclerViewHorizental;
  private AdapterSelectedPoodItem selectAdapter;
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
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.fragment_pood, container, false);
    ImageView imgGride = (ImageView) view.findViewById(R.id.list_gride);
    ImageView imgVertical = (ImageView) view.findViewById(R.id.list_vertical);
    TextView txtFilter = (TextView) view.findViewById(R.id.txt_filter);
    ImageView imgFilter = (ImageView) view.findViewById(R.id.img_filter);
    imgTopMember = (ImageView) view.findViewById(R.id.img_top_member);
    imgTopPst = (ImageView) view.findViewById(R.id.img_top_post);
    imgCancelSearch = (ImageView) view.findViewById(R.id.img_cancel_search);
    layFilter = (LinearLayout) view.findViewById(R.id.lay_filter);
    layTopPost = (LinearLayout) view.findViewById(R.id.lay_top_post);
    layTopMember = (LinearLayout) view.findViewById(R.id.lay_top_member);

    laySearch = (LinearLayout) view.findViewById(R.id.lay_search);
    layTitle = (LinearLayout) view.findViewById(R.id.lay_title);
    layTextSearch = (LinearLayout) view.findViewById(R.id.lay_text_search);

    layGoMain = (RelativeLayout) view.findViewById(R.id.lay_go_main);
    txtCount = (TextView) view.findViewById(R.id.txt_pood_count);
    edtSearch = (EditText) view.findViewById(R.id.edt_search_pood);
    cardLoading = (CardView) view.findViewById(R.id.card_item_frg_main);
    edtSearch.setTypeface(Base.font1);
    //cardLoading.setVisibility(
    recyclerViewHorizental = (RecyclerView) view.findViewById(R.id.recycler_view_horizental);
    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    StaggeredGridLayoutManager lay = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    LinearLayoutManager layHorizental =  new LinearLayoutManager(Base.getContext(), LinearLayoutManager.HORIZONTAL, false);
    loadJSON();
    recyclerViewHorizental.setLayoutManager(layHorizental);
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
        InputMethodManager inputMethodManager = (InputMethodManager) Base.getCurrentActivity().getSystemService(Base.getContext().INPUT_METHOD_SERVICE);
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
        Base.fragment = new FragmentMain();
        final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, Base.fragment).commit();
      }
    });
    return view;
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
            poodSelect = response.body();
            posts2 = new ArrayList<PoodItem>(posts);
            //posts2 = response.body();
            Log.i("PAG", "page  " + posts.get(0).getCountPost() + "  posts  " + posts.get(5).getCountPost());
            if(posts.get(0).getCountPost()==4024){

            }
            newsAdapter = new AdapterPoodItem(Base.getCurrentActivity(), posts2);
            selectAdapter= new AdapterSelectedPoodItem(Base.getCurrentActivity(), poodSelect);
            recyclerView.setAdapter(newsAdapter);
            recyclerViewHorizental.setAdapter(selectAdapter);
            cardLoading.setVisibility(View.GONE);
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


}