package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class AdapterMainItem extends RecyclerView.Adapter<AdapterMainItem.NewsViewHolder> {


  private Context context;
  private List<PostShohda> posts;

  public class NewsViewHolder extends RecyclerView.ViewHolder {
    private ImageView newsImage;
    private TextView title;
    private TextView content;
    private ImageView like;
    private ImageView shaer;
    private TextView viewAll;
    private LinearLayout layContent;
    private CardView cardView;

    public NewsViewHolder(View itemView) {
      super(itemView);
      newsImage = (ImageView) itemView.findViewById(R.id.profile_image);
      title = (TextView) itemView.findViewById(R.id.txt_name);
      content = (TextView) itemView.findViewById(R.id.txt_vaseat);
      viewAll = (TextView) itemView.findViewById(R.id.txt_view);
      like = (ImageView) itemView.findViewById(R.id.img_like);
      shaer = (ImageView) itemView.findViewById(R.id.img_share);
      layContent = (LinearLayout) itemView.findViewById(R.id.lay_content);
      cardView = (CardView) itemView.findViewById(R.id.card_item);
    }
  }

  public AdapterMainItem(Context context, List<PostShohda> posts) {
    this.context = context;
    this.posts = posts;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_shohda, parent, false);
    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final NewsViewHolder holder, int position) {
    final PostShohda post = posts.get(position);
    holder.layContent.setVisibility(View.VISIBLE);
    final Shohda shohda = new Shohda();


    holder.viewAll.setText(post.getViewAll() + "  بار دیده شده");

    if (post.getLike() == 1) {
      holder.like.setImageResource(R.drawable.likeok);
      Log.i("LIK", "1");
    } else if (post.getLike() == 0) {
      Log.i("LIK", "2");
      holder.like.setImageResource(R.drawable.ufi_heart_bold);
    }

    holder.like.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        shohda.setId(post.getId());
        if (post.getLike() == 1) {
          Log.i("LIK", "3");
          holder.like.setImageResource(R.drawable.ufi_heart_bold);
          shohda.updateGetLike();
          post.setLike(0);
        } else if (post.getLike() == 0) {
          holder.like.setImageResource(R.drawable.likeok);
          shohda.updateSetLike();
          Log.i("LIK", "4");
          post.setLike(1);
        }

      }
    });

    holder.title.setText(post.getTitle());

    if (!("---".equals(post.getZendgiNameh()))) {
      Log.i("COM1", "+" + "zendgi");
      holder.content.setText(post.getZendgiNameh());
    } else if (!("---".equals(post.getVaseatNameh()))) {
      Log.i("COM1", "+" + "vaseat");
      holder.content.setText(post.getVaseatNameh());
    } else if ("---".equals(post.getZendgiNameh()) && "---".equals(post.getVaseatNameh())) {
      Log.i("COM1", "+" + 2);
      holder.layContent.setVisibility(View.GONE);
    }
    //holder.date.setText(post.getDate());


    Log.i("PIC", "+" + post.getPostImage());
    if (post.getPostImage().equals("---") || post.getPostImage() == null) {
      holder.newsImage.setImageDrawable(setPic("pic/laleh.jpg"));
    } else {
      holder.newsImage.setImageDrawable(setPic("pic/" + post.getPostImage()));
    }

    holder.shaer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String ti="وَ لا تَحْسَبَنَّ الَّذينَ قُتِلُوا في سَبيلِ اللَّهِ أَمْواتاً بَلْ أَحْياءٌ عِنْدَ رَبِّهِمْ يُرْزَقُونَ";
        String ti2="\n"+"و هرگز گمان مبر کسانی که در راه خدا کشته شدند مرده اند ، بلکه زنده اند و در نزد پروردگارشان روزی داده می شوند. ";
        String sha = " نام شهید : " + post.getTitle() + "\n" + "محل تولد :"+ post.getMahal()+"\n"+"تاریخ تولد :"+ post.getBorn()+"\n"+"زندگی نامه :"+post.getZendgiNameh()+"\n";
        String aey="آل عمران آیه 169";
        String app="اپلیکشن یادواره شهدای دودانگه کاری از شرکت آریو سافت";
        String tiok=ti+ti2+"\n"+aey+"\n"+"\n"+"\n"+sha+"\n"+"\n"+"\n"+app;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, tiok);
        Base.getCurrentActivity().startActivity(Intent.createChooser(sharingIntent, "ارسال برای دیگران"));
      }
    });

    holder.cardView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(Base.getCurrentActivity(), ShowShahed.class);
        intent.putExtra("id",post.getId());
        Base.getCurrentActivity().startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }


  private Drawable setPic(String pic) {
    InputStream ims = null;
    try {
      ims = Base.getContext().getAssets().open(pic);
      Log.i("ADA", "+" + ims);
    } catch (IOException e) {
      Log.i("ADA", "+" + e.toString());
      try {
        ims = Base.getContext().getAssets().open("pic/laleh.jpg");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
    Drawable d = Drawable.createFromStream(ims, null);
    return d;

  }

}
