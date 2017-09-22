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
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.netpood.admin.netpoodapp.Activity.ActivityDetailPost;
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.PostItem;
import com.netpood.admin.netpoodapp.fragment.FragmentUserAccount;

import java.util.List;
import java.util.Random;

/**
 * Created by jaber
 */
public class AdapterProfileItem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_HEADER = 0;
  private static final int VIEW_TYPE_DEFAULT_ITEM = 1;

  static Context context;
  List<PostItem> movies;
  OnLoadMoreListener loadMoreListener;
  boolean isLoading = false, isMoreDataAvailable = true;
  NewsViewHolderBanner holderBanner ;


  public AdapterProfileItem(Context context, List<PostItem> movies) {
    this.context = context;
    this.movies = movies;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //LayoutInflater inflater = LayoutInflater.from(context);
    switch (viewType) {
      case VIEW_TYPE_HEADER:
        View view = LayoutInflater.from(context).inflate(R.layout.item_namyeh_header, parent, false);
        return new NewsViewHolderBanner(view);
      case VIEW_TYPE_DEFAULT_ITEM:
        View view2 = LayoutInflater.from(context).inflate(R.layout.item_namyeh, parent, false);
        return new MovieHolder(view2);
      default:
        return null;
    }

  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holde, final int position) {
    Log.i("TYP", position + "   " + (getItemCount() - 2) + "    " + isMoreDataAvailable + "    " + !isLoading + "   " + loadMoreListener);
    if (position >= getItemCount() - 2 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
      Log.i("TYP", "page   " + "movies.get(position).type");
      isLoading = true;
      loadMoreListener.onLoadMore();
    }
    if (holde instanceof MovieHolder) {
      final MovieHolder holder = (MovieHolder) holde;
      final PostItem post = movies.get(position - 1);

      Random rand = new Random();
      int n = rand.nextInt(9) + 1;
      PostItem postp = movies.get(n);
      Log.i("TYP", "" +postp.getPicMain());
      Glide.with(Base.getContext())
        .load(postp.getPicMain())
        .placeholder(new ColorDrawable(Base.getContext().getResources().getColor(R.color.colorToolbar)))
        .listener(new RequestListener<String, GlideDrawable>() {
          @Override
          public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            holderBanner.LayProp.setVisibility(View.GONE);
            return false;
          }

          @Override
          public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            holderBanner.LayProp.setVisibility(View.GONE);
            return false;
          }
        })
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .fitCenter()
        .into(holderBanner.imgPersonal);

      /*Glide.with(Base.getContext()).load(post.getUrlImageMain())
        .crossFade()
        .placeholder(new ColorDrawable(Base.getContext().getResources().getColor(R.color.colorToolbar)))
        .into(holderBanner.imgPersonal);*/

      Glide.with(Base.getContext()).load(post.getUrlImageUserItem())
        .crossFade()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(holder.urlImageUserItem);
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
          Intent intent = new Intent(Base.getCurrentActivity(), ActivityDetailPost.class);
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
    } else {
      holderBanner = (NewsViewHolderBanner) holde;


    }
  }

  @Override
  public int getItemCount() {
    return movies.size() + 1;
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return VIEW_TYPE_HEADER;
    } else {
      return VIEW_TYPE_DEFAULT_ITEM;
    }

  }

  /* VIEW HOLDERS */
  public class NewsViewHolderBanner extends RecyclerView.ViewHolder {

    private ImageView imgPersonal;
    private LinearLayout LayProp;

    public NewsViewHolderBanner(View itemView) {
      super(itemView);
      imgPersonal = (ImageView) itemView.findViewById(R.id.img_pesonal);
      LayProp = (LinearLayout) itemView.findViewById(R.id.lay_prog);
    }

  }

  static class MovieHolder extends RecyclerView.ViewHolder {
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

    public MovieHolder(View itemView) {
      super(itemView);
      urlImageUserItem = (ImageView) itemView.findViewById(R.id.img_user_item);
      nameUserItem = (TextView) itemView.findViewById(R.id.txt_user);
      urlImageMain = (ImageView) itemView.findViewById(R.id.img_main_item);
      txtMainItem = (TextView) itemView.findViewById(R.id.txt_main_item);
      txtLikeItem = (TextView) itemView.findViewById(R.id.txt_like_namayeh);
      txtComment = (TextView) itemView.findViewById(R.id.txt_comment_namayeh);
      txtShare = (TextView) itemView.findViewById(R.id.txt_share_namayeh);
      imageLike = (ImageView) itemView.findViewById(R.id.img_like_item);
      item = (CardView) itemView.findViewById(R.id.card_item_message);
      userSendPost = (LinearLayout) itemView.findViewById(R.id.lay_user_accont);
    }


  }

  static class LoadHolder extends RecyclerView.ViewHolder {
    public LoadHolder(View itemView) {
      super(itemView);
    }
  }

  public void setMoreDataAvailable(boolean moreDataAvailable) {
    isMoreDataAvailable = moreDataAvailable;
  }

  /* notifyDataSetChanged is final method so we can't override it
       call adapter.notifyDataChanged(); after update the list
       */
  public void notifyDataChanged() {
    notifyDataSetChanged();
    isLoading = false;
  }


  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
    this.loadMoreListener = loadMoreListener;
  }
}
