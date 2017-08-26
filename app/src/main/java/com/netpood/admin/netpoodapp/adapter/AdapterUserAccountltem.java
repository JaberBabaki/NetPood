package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.fragment.FragmentFollowers;
import com.netpood.admin.netpoodapp.fragment.FragmentFollowing;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.PostItem;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class  AdapterUserAccountltem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


  private Context context;
  private List<PostItem> posts;
  private static final int VIEW_TYPE_HEADER = 0;
  private static final int VIEW_TYPE_DEFAULT_ITEM = 1;


  public class NewsViewHolder extends RecyclerView.ViewHolder {

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

    public NewsViewHolder(View itemView) {
      super(itemView);
      urlImageMain = (ImageView) itemView.findViewById(R.id.img_main_item);
    }
  }

  public AdapterUserAccountltem(Context context, List<PostItem> posts) {
    this.context = context;
    this.posts = posts;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VIEW_TYPE_HEADER:
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_account_header, parent, false);
        return new NewsViewHolderBanner(view);
      case VIEW_TYPE_DEFAULT_ITEM:
        View view2 = LayoutInflater.from(context).inflate(R.layout.item_namayeh_personal, parent, false);
        return new NewsViewHolder(view2);
      default:
        return null;
    }
  }

  @Override
  public void onBindViewHolder(final RecyclerView.ViewHolder hold, int position) {
    if (hold instanceof NewsViewHolder) {
      final NewsViewHolder holder = (NewsViewHolder) hold;
      final PostItem post = posts.get(position - 1);
     // holder.urlImageMain.setImageDrawable(post.getUrlImageMain());
    }else{
      final NewsViewHolderBanner holder = (NewsViewHolderBanner) hold;
      holder.layFollowers.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Base.fragment = new FragmentFollowers();
          final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, Base.fragment).commit();
        }
      });
      holder.layFollowing.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Base.fragment = new FragmentFollowing();
          Base.folloing=1;
          final FragmentTransaction transaction = Base.fragmentManager.beginTransaction();
          transaction.replace(R.id.main_container, Base.fragment).commit();
        }
      });
    }

  }

  @Override
  public int getItemCount() {
    return posts.size() + 1;
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return VIEW_TYPE_HEADER;
    } else {
      return VIEW_TYPE_DEFAULT_ITEM;

    }

  }


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
}
