package com.netpood.admin.framework.widget;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.SliderItem;

import java.util.ArrayList;


public class ImagePagerAdapter extends PagerAdapter {

    public ArrayList<SliderItem> imageIds;

    public ImagePagerAdapter(ArrayList<SliderItem> imageIds) {
        this.imageIds = imageIds;
    }


    @Override
    public int getCount() {
        return imageIds.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = Base.getLayoutInflater().inflate(R.layout.item_slider, null);
        ImageView imageSliderDefault = (ImageView) view.findViewById(R.id.img_default);
        imageSliderDefault.setImageDrawable(imageIds.get(position).getImgAddress());
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
