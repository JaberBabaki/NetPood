package com.netpood.admin.netpoodapp.database;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.netpood.admin.netpoodapp.R;

import java.util.ArrayList;

/**
 * Created by jaber Babaki on 7/21/2016.
 */
public class DataFakeFollow {



  public static   ArrayList<FollowItem> getData(Context context) {
    ArrayList<FollowItem> follws = new ArrayList<>();
    for (int i = 1; i <= 8; i++) {
      FollowItem follow = new FollowItem();

      switch (i){
        case 1:
          follow.setId(i);
          follow.setName("atousa.m1990");
          follow.setShortName("Atousa");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
          break;
        case 2:
          follow.setId(i);
          follow.setName("majid_tehrani1369");
          follow.setShortName("majid tehrani");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
          break;
        case 3:
          follow.setId(i);
          follow.setName("majid_tehrani1369");
          follow.setShortName("majid tehrani");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
          break;
        case 4:
          follow.setId(i);
          follow.setName("atousa.m1990");
          follow.setShortName("Atousa");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
          break;
        case 5:
          follow.setId(i);
          follow.setName("amir.mousaavi_pirnaemi");
          follow.setShortName("amir mousavi");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
          break;
        case 6:
          follow.setId(i);
          follow.setName("saeed.amini1991");
          follow.setShortName("3aeed");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
          break;
        case 7:
          follow.setId(i);
          follow.setName("saeed.amini1991");
          follow.setShortName("3aeed");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
          break;
        case 8:
          follow.setId(i);
          follow.setName("saeed.amini1991");
          follow.setShortName("3aeed");
          follow.setPic(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
          break;
      }
      follws.add(follow);
    }

    return follws;
  }


}
