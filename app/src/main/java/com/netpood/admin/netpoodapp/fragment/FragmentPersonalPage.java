package com.netpood.admin.netpoodapp.fragment;

/**
 * Created by jaberALU on 25/05/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.Activity.MainActivity;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterPersonaltem;
import com.netpood.admin.netpoodapp.database.DataFakeMainItem;


public class FragmentPersonalPage extends Fragment{
  View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view= inflater.inflate(R.layout.activity_personal, container, false);
    ImageView imgDrawer = (ImageView) view.findViewById(R.id.img_drawer);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    AdapterPersonaltem newsAdapter = new AdapterPersonaltem(Base.getCurrentActivity(), DataFakeMainItem.getData(Base.getCurrentActivity()));
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
    return view;
  }

}