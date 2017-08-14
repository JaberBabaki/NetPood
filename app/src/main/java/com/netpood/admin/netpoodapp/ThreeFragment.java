package com.netpood.admin.netpoodapp;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netpood.admin.netpoodapp.adapter.AdapterPersonaltem;


public class ThreeFragment extends Fragment{
  View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view= inflater.inflate(R.layout.activity_personal, container, false);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    AdapterPersonaltem newsAdapter = new AdapterPersonaltem(Base.getCurrentActivity(), DataFakeGenerator.getData(Base.getCurrentActivity()));
    GridLayoutManager gride = new GridLayoutManager(Base.getCurrentActivity(), 2);
    gride.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        if (position == 0) {
          return 2;
        } else {
          return 1;
        }
      }
    });

    recyclerView.setLayoutManager(gride);
    recyclerView.setAdapter(newsAdapter);

    return view;
  }

}