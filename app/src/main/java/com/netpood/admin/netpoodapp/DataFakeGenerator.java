package com.netpood.admin.netpoodapp;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;

import com.netpood.admin.netpoodapp.database.PostItem;

import java.util.ArrayList;

/**
 * Created by jaber Babaki on 7/21/2016.
 */
public class DataFakeGenerator {
    public static ArrayList<PostItem> getData(Context context){
        ArrayList<PostItem> posts=new ArrayList<>();
        for(int i=1;i<=6;i++){
            PostItem post=new PostItem();

            switch (i){
                case 1:
                    post.setId(i);
                    post.setNameUserItem("jaber babaki");
                    post.setTxtDateItem("23 خرداد 96");
                    post.setTxtMainItem("متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
                    post.setTxtViewItem("313 بار ديده شده");
                    post.setTxtUserIdea("براي من که خيلي جالب شده");
                    post.setTxtAllIdea("نمايش 340 پيام");
                    post.setLikeItem(1);
                    post.setUrlImageUserItem(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
                    post.setUrlImageMain(ResourcesCompat.getDrawable(context.getResources(),R.drawable.j13,null));
                    post.setUrlImageUserIdea(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
                    break;
                case 2:
                    post.setId(i);
                    post.setNameUserItem("jaber babaki");
                    post.setTxtDateItem("23 خرداد 97");
                    post.setTxtMainItem("متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
                    post.setTxtViewItem("313 بار ديده شده");
                    post.setTxtUserIdea("براي من که خيلي جالب شده");
                    post.setTxtAllIdea("نمايش 340 پيام");
                    post.setLikeItem(0);
                    post.setUrlImageUserItem(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
                    post.setUrlImageMain(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w12,null));
                    post.setUrlImageUserIdea(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
                    break;
                case 3:
                    post.setId(i);
                    post.setNameUserItem("jaber babaki");
                    post.setTxtDateItem("23 خرداد 98");
                    post.setTxtMainItem("متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
                    post.setTxtViewItem("313 بار ديده شده");
                    post.setTxtUserIdea("براي من که خيلي جالب شده");
                    post.setTxtAllIdea("نمايش 340 پيام");
                    post.setLikeItem(0);
                    post.setUrlImageUserItem(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
                    post.setUrlImageMain(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w12,null));
                    post.setUrlImageUserIdea(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
                    break;
                case 4:
                    post.setId(i);
                    post.setNameUserItem("jaber babaki");
                    post.setTxtDateItem("23 خرداد 99");
                    post.setTxtMainItem("متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
                    post.setTxtViewItem("313 بار ديده شده");
                    post.setTxtUserIdea("براي من که خيلي جالب شده");
                    post.setTxtAllIdea("نمايش 340 پيام");
                    post.setLikeItem(0);
                    post.setUrlImageUserItem(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
                    post.setUrlImageMain(ResourcesCompat.getDrawable(context.getResources(),R.drawable.man1,null));
                    post.setUrlImageUserIdea(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
                    break;
                case 5:
                    post.setId(i);
                    post.setNameUserItem("jaber babaki");
                    post.setTxtDateItem("23 خرداد 100");
                    post.setTxtMainItem("متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
                    post.setTxtViewItem("313 بار ديده شده");
                    post.setTxtUserIdea("براي من که خيلي جالب شده");
                    post.setTxtAllIdea("نمايش 340 پيام");
                    post.setLikeItem(1);
                    post.setUrlImageUserItem(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
                    post.setUrlImageMain(ResourcesCompat.getDrawable(context.getResources(),R.drawable.yes,null));
                    post.setUrlImageUserIdea(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
                    break;
                case 6:
                    post.setId(i);
                    post.setNameUserItem("jaber babaki");
                    post.setTxtDateItem("23 خرداد 101");
                    post.setTxtMainItem("متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد. در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها و شرایط سخت تایپ به پایان رسد وزمان مورد نیاز شامل حروفچینی دستاوردهای اصلی و جوابگوی سوالات پیوسته اهل دنیای موجود طراحی اساسا مورد استفاده قرار گیرد.");
                    post.setTxtViewItem("313 بار ديده شده");
                    post.setTxtUserIdea("براي من که خيلي جالب شده");
                    post.setTxtAllIdea("نمايش 340 پيام");
                    post.setLikeItem(1);
                    post.setUrlImageUserItem(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w2,null));
                    post.setUrlImageMain(ResourcesCompat.getDrawable(context.getResources(),R.drawable.yes,null));
                    post.setUrlImageUserIdea(ResourcesCompat.getDrawable(context.getResources(),R.drawable.w5,null));
                    break;
            }
            posts.add(post);
        }
        return posts;
    }
}
