package com.netpood.admin.netpoodapp.database;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.netpood.admin.netpoodapp.R;

import java.util.ArrayList;

/**
 * Created by jaber Babaki on 7/21/2016.
 */
public class DataFakeSlideItem {
    public static ArrayList<SliderItem> getData(Context context){
        ArrayList<SliderItem> posts=new ArrayList<>();
        for(int i=1;i<=6;i++){
            SliderItem post=new SliderItem();

            switch (i){
                case 1:
                    post.setIdSlide(i);
                    post.setLink("jaber babaki");
                    post.setImgAddress(ResourcesCompat.getDrawable(context.getResources(), R.drawable.w12,null));

                    break;
                case 2:
                    post.setIdSlide(i);
                    post.setLink("jaber babaki");
                    post.setImgAddress(ResourcesCompat.getDrawable(context.getResources(), R.drawable.a1,null));
                    break;
                case 3:
                    post.setIdSlide(i);
                    post.setLink("jaber babaki");
                    post.setImgAddress(ResourcesCompat.getDrawable(context.getResources(), R.drawable.man1,null));
                    break;
                case 4:
                    post.setIdSlide(i);
                    post.setLink("jaber babaki");
                    post.setImgAddress(ResourcesCompat.getDrawable(context.getResources(), R.drawable.yes,null));
                    break;
                case 5:
                    post.setIdSlide(i);
                    post.setLink("jaber babaki");
                    post.setImgAddress(ResourcesCompat.getDrawable(context.getResources(), R.drawable.yes,null));
                    break;
                case 6:
                    post.setIdSlide(i);
                    post.setLink("jaber babaki");
                    post.setImgAddress(ResourcesCompat.getDrawable(context.getResources(), R.drawable.yes,null));
                    break;
            }
            posts.add(post);
        }
        return posts;
    }
}
