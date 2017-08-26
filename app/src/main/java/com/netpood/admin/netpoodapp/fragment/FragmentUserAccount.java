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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netpood.admin.netpoodapp.Activity.MainActivity;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.adapter.AdapterUserAccountltem;
import com.netpood.admin.netpoodapp.database.DataFakeMainItem;


public class FragmentUserAccount extends Fragment {

  public View view;
  public NavigationView drawer;
  public int position;

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    Bundle bundle = getArguments();
    if (bundle != null) {
      position = bundle.getInt("POS");
      Toast.makeText(Base.getCurrentActivity(),"position  "+position,Toast.LENGTH_SHORT).show();
    }
    view = inflater.inflate(R.layout.fragment_user_account, container, false);

    //final EditText edtSearch = (EditText) view.findViewById(R.id.edt_search);
    //edtSearch.setTypeface(Base.font1);

    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
    AdapterUserAccountltem newsAdapter = new AdapterUserAccountltem(Base.getCurrentActivity(), DataFakeMainItem.getData(Base.getCurrentActivity()));
    GridLayoutManager gride = new GridLayoutManager(Base.getContext(), 2);
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

          Base.fragment = new FragmentMain();
          Bundle bundle = new Bundle();
          bundle.putInt("POS", position);
          Base.fragment.setArguments(bundle);
          final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, Base.fragment).commit();

          /*Fragment fragment = Base.fragmentManager.findFragmentById(R.id.main_container);
          FragmentTransaction ft = Base.fragmentManager.beginTransaction();
          ft.remove(fragment);
          ft.commit();*/

         /*/ Base.fragment = new FragmentPersonalPage();
          Base.fragmentManager.beginTransaction()
            .replace(Base.fragment, R.id.main_container)
            .commit();
          //final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
         // transaction.remove(Base.fragment);
         // Base.fragmentManager.popBackStack();
          //FragmentManager.BackStackEntry first = Base.fragmentManager.getBackStackEntryAt(1);
          //Base.fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
          //Base.fragmentManager.popBackStack();
          Toast.makeText(Base.getContext(),"ok",Toast.LENGTH_SHORT).show();
          /*Base.fragment = new FragmentPersonalPage();
          final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, Base.fragment).commit();*/
        }
      });
    }

    return view;

  }


}