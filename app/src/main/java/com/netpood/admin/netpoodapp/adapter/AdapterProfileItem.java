package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
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
import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.PostItem;
import com.netpood.admin.netpoodapp.fragment.FragmentFollowing;

import java.util.List;

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
  NewsViewHolderBanner holderBanner;


  public AdapterProfileItem(Context context, List<PostItem> movies) {
    this.context = context;
    this.movies = movies;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //LayoutInflater inflater = LayoutInflater.from(context);
    switch (viewType) {
      case VIEW_TYPE_HEADER:
        View view = LayoutInflater.from(context).inflate(R.layout.item_peraonal_header, parent, false);
        return new NewsViewHolderBanner(view);
      case VIEW_TYPE_DEFAULT_ITEM:
        View view2 = LayoutInflater.from(context).inflate(R.layout.item_namayeh_personal, parent, false);
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

      Glide.with(Base.getContext()).load(post.getUrlImageMain())
        .crossFade()
        .placeholder(new ColorDrawable(Base.getContext().getResources().getColor(R.color.colorToolbar)))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(holder.urlImageMain);
    } else {
      final NewsViewHolderBanner holder = (NewsViewHolderBanner) holde;
      holder.layFollowing.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Base.fragment = new FragmentFollowing();
          Base.folloing = 1;
          final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, Base.fragment).commit();
        }
      });
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
    private TextView txtNamePersonal;
    private TextView txtShortTextPersonal;
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFollowing;
    private TextView txtPoods;
    private LinearLayout layFollowers;
    private LinearLayout layFollowing;

    public NewsViewHolderBanner(View itemView) {
      super(itemView);
      imgPersonal = (ImageView) itemView.findViewById(R.id.img_pesonal);
      txtNamePersonal = (TextView) itemView.findViewById(R.id.txt_name_persoanl);
      txtShortTextPersonal = (TextView) itemView.findViewById(R.id.txt_short_text);
      txtPosts = (TextView) itemView.findViewById(R.id.txt_post);
      txtFollowers = (TextView) itemView.findViewById(R.id.txt_followers);
      txtFollowing = (TextView) itemView.findViewById(R.id.txt_following);
      txtPoods = (TextView) itemView.findViewById(R.id.txt_poods);
      layFollowers = (LinearLayout) itemView.findViewById(R.id.lay_followers);
      layFollowing = (LinearLayout) itemView.findViewById(R.id.lay_following);


    }

  }

  static class MovieHolder extends RecyclerView.ViewHolder {
    private ImageView urlImageUserItem;
    private TextView nameUserItem;
    private TextView txtDateItem;
    private ImageView urlImageMain;
    private TextView txtMainItem;
    private TextView txtViewItem;
    private ImageView urlImageUserIdea;
    private TextView txtIdeaUser;
    private TextView txtAllIdea;
    private ImageView imageLike;
    private int likeItem;

    public MovieHolder(View itemView) {
      super(itemView);
      urlImageMain = (ImageView) itemView.findViewById(R.id.img_main_item);
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
