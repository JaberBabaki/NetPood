package com.netpood.admin.netpoodapp;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netpood.admin.netpoodapp.adapter.AdapterMainItem;


public class TwoFragment extends Fragment{
  View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
     view= inflater.inflate(R.layout.f_two, container, false);

    RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
    AdapterMainItem newsAdapter=new AdapterMainItem(Base.getCurrentActivity(), DataFakeGenerator.getData(Base.getCurrentActivity()));
    recyclerView.setLayoutManager(new LinearLayoutManager(Base.getContext(),LinearLayoutManager.VERTICAL,false));
    recyclerView.setAdapter(newsAdapter);

    return view;
  }

}