package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.netpood.admin.netpoodapp.Activity.ActivityMessagePood;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.PoodItem;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class AdapterSelectedPoodItem extends RecyclerView.Adapter<AdapterSelectedPoodItem.NewsViewHolder> {


  private Context context;
  private List<PoodItem> posts;
  private OnItemClickListener.OnItemClickCallback onItemClickCallback;

  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView namePood;
    private ImageView poodImage;
    private ImageView imageLike;
    private TextView countUser;
    private CardView item;

    public NewsViewHolder(View itemView) {
      super(itemView);
      poodImage = (ImageView) itemView.findViewById(R.id.img_main_item);
      countUser = (TextView) itemView.findViewById(R.id.txt_count_user);
      namePood = (TextView) itemView.findViewById(R.id.txt_main_item);
      imageLike = (ImageView) itemView.findViewById(R.id.img_like_pood);
      item = (CardView) itemView.findViewById(R.id.card_item_message);
    }
  }

  public AdapterSelectedPoodItem(Context context, List<PoodItem> posts) {
    this.context = context;
    this.posts = posts;
    this.onItemClickCallback = onItemClickCallback;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_pood_selected, parent, false);
    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final NewsViewHolder holder, final int position) {
    final PoodItem post = posts.get(position);

    Glide.with(Base.getCurrentActivity()).load(post.getPic())
      .crossFade()
      .placeholder(new ColorDrawable(Base.getContext().getResources().getColor(R.color.colorToolbar)))
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .into(holder.poodImage);

    holder.countUser.setText(" تعداد اعضاء  " + post.getCountUsers());
    holder.namePood.setText(post.getName());

    if (post.getLike() == 1) {
      holder.imageLike.setImageResource(R.drawable.like_w);
      Log.i("LIK", "1");
    } else if (post.getLike() == 1) {
      Log.i("LIK", "2");
      holder.imageLike.setImageResource(R.drawable.likepood);
    }

    holder.imageLike.setImageResource(R.drawable.like_w);
    holder.item.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(Base.getCurrentActivity(), ActivityMessagePood.class);
        intent.putExtra("name",post.getName());
        intent.putExtra("member",post.getCountUsers());
        Base.getCurrentActivity().startActivity(intent);
      }
    });
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }


}
