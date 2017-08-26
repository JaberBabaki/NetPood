package com.netpood.admin.netpoodapp.fragment;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.Activity.MainActivity;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterFollowersltem;
import com.netpood.admin.netpoodapp.database.DataFakeFollow;


public class FragmentFollowers extends Fragment {

  public View view;
  public NavigationView drawer;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.layout.fargment_followers, container, false);

    //final EditText edtSearch = (EditText) view.findViewById(R.id.edt_search);
    //edtSearch.setTypeface(Base.font1);

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    AdapterFollowersltem newsAdapter = new AdapterFollowersltem(Base.getCurrentActivity(), DataFakeFollow.getData(Base.getCurrentActivity()));
    recyclerView.setLayoutManager(new LinearLayoutManager(Base.getContext(), LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(newsAdapter);

    ImageView imgDrawer = (ImageView) view.findViewById(R.id.img_drawer);
    ImageView imgBack = (ImageView) view.findViewById(R.id.img_back);
    Activity activity = getActivity();
    if (activity instanceof MainActivity) {
      final MainActivity myactivity = (MainActivity) activity;
      imgDrawer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          myactivity.navigationView.openDrawer(GravityCompat.START);
        }
      });
      imgBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Base.fragment = new FragmentPersonalPage();
          final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, Base.fragment).commit();
        }
      });
    }

    return view;

  }



}