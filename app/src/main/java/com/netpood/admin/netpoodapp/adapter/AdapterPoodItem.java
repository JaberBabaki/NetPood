package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.netpood.admin.netpoodapp.Activity.ActivityDetailPost;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.PostItem;
import com.netpood.admin.netpoodapp.fragment.FragmentUserAccount;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class AdapterPoodItem extends RecyclerView.Adapter<AdapterPoodItem.NewsViewHolder> {


  private Context context;
  private List<PostItem> posts;

  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView urlImageUserItem;
    private TextView nameUserItem;
    private ImageView urlImageMain;
    private TextView txtMainItem;
    private TextView txtLikeItem;
    private TextView txtComment;
    private TextView txtShare;
    private ImageView imageLike;
    private CardView item;
    private LinearLayout userSendPost;
    private int likeItem;

    public NewsViewHolder(View itemView) {
      super(itemView);
      urlImageUserItem = (ImageView) itemView.findViewById(R.id.img_user_item);
      nameUserItem = (TextView) itemView.findViewById(R.id.txt_user);
      urlImageMain = (ImageView) itemView.findViewById(R.id.img_main_item);
      txtMainItem = (TextView) itemView.findViewById(R.id.txt_main_item);
      txtLikeItem = (TextView) itemView.findViewById(R.id.txt_like_namayeh);
      txtComment = (TextView) itemView.findViewById(R.id.txt_comment_namayeh);
      txtShare = (TextView) itemView.findViewById(R.id.txt_share_namayeh);
      imageLike = (ImageView) itemView.findViewById(R.id.img_like_item);
      item = (CardView) itemView.findViewById(R.id.card_item);
      userSendPost = (LinearLayout) itemView.findViewById(R.id.lay_user_accont);
    }
  }

  public AdapterPoodItem(Context context, List<PostItem> posts) {
    this.context = context;
    this.posts = posts;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_namyeh, parent, false);
    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final NewsViewHolder holder, final int position) {
    final PostItem post = posts.get(position);

    Glide.with(Base.getContext()).load(post.getUrlImageUserItem())
      .crossFade()
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .into(holder.urlImageUserItem);
    //holder.urlImageUserItem.setImageDrawable(post.getUrlImageUserItem());
    holder.nameUserItem.setText(post.getNameUserItem());
    //holder.txtDateItem.setText(post.getTxtDateItem());
   // holder.urlImageMain.setImageDrawable(post.getUrlImageMain());
    Glide.with(Base.getContext()).load(post.getUrlImageMain())
      .crossFade()
      .placeholder(new ColorDrawable(Base.getContext().getResources().getColor(R.color.colorToolbar)))
      .diskCacheStrategy(DiskCacheStrategy.ALL)
      .into(holder.urlImageMain);
    holder.txtMainItem.setText(post.getTxtMainItem());
    holder.txtLikeItem.setText(post.getTxtUserIdea());
   // holder.urlImageUserIdea.setImageDrawable(post.getUrlImageUserIdea());
    holder.txtShare.setText(post.getTxtViewItem());
    holder.txtComment.setText(post.getTxtAllIdea());

    if (post.getLikeItem() == 1) {
      holder.imageLike.setImageResource(R.drawable.likeok);
      Log.i("LIK", "1");
    } else if (post.getLikeItem() == 1) {
      Log.i("LIK", "2");
      holder.imageLike.setImageResource(R.drawable.ufi_heart_bold);
    }

    holder.imageLike.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (post.getLikeItem() == 1) {
          Log.i("LIK", "3");
          holder.imageLike.setImageResource(R.drawable.ufi_heart_bold);
          post.setLikeItem(0);
        } else if (post.getLikeItem() == 0) {
          holder.imageLike.setImageResource(R.drawable.likeok);
          Log.i("LIK", "4");
          post.setLikeItem(1);
        }
      }
    });


    holder.item.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent =new Intent(Base.getCurrentActivity(), ActivityDetailPost.class);
        Base.getCurrentActivity().startActivity(intent);
      }
    });

    holder.userSendPost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Base.fragment = new FragmentUserAccount();
        Bundle bundle = new Bundle();
        bundle.putInt("POS", position);
        Base.fragment.setArguments(bundle);
        final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
        transaction.add(R.id.main_container, Base.fragment).commit();
      }
    });


  }

  @Override
  public int getItemCount() {
    return posts.size();
  }



}
