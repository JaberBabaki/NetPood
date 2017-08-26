package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.Base;
import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.FollowItem;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class AdapterFollowersltem extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


  private Context context;
  private List<FollowItem> posts;
  private static final int VIEW_TYPE_HEADER = 0;
  private static final int VIEW_TYPE_DEFAULT_ITEM = 1;


  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView urlImageFollow;
    private TextView nameUserItem;
    private TextView shortName;
    private Button btnFollow;
    private int likeItem;

    public NewsViewHolder(View itemView) {
      super(itemView);
      urlImageFollow = (ImageView) itemView.findViewById(R.id.img_user_follow);
      nameUserItem = (TextView) itemView.findViewById(R.id.txt_name);
      shortName = (TextView) itemView.findViewById(R.id.txt_short_name);
      btnFollow = (Button) itemView.findViewById(R.id.btn_follow);
    }
  }

  public AdapterFollowersltem(Context context, List<FollowItem> posts) {
    this.context = context;
    this.posts = posts;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VIEW_TYPE_HEADER:
        View view = LayoutInflater.from(context).inflate(R.layout.item_followers_header, parent, false);
        return new NewsViewHolderBanner(view);
      case VIEW_TYPE_DEFAULT_ITEM:
        View view2 = LayoutInflater.from(context).inflate(R.layout.item_followers, parent, false);
        return new NewsViewHolder(view2);
      default:
        return null;
    }
  }

  @Override
  public void onBindViewHolder(final RecyclerView.ViewHolder hold, int position) {
    if (hold instanceof NewsViewHolder) {
      final NewsViewHolder holder = (NewsViewHolder) hold;
      final FollowItem post = posts.get(position - 1);
      holder.urlImageFollow.setImageDrawable(post.getPic());
      holder.nameUserItem.setText(post.getName());
      holder.shortName.setText(post.getShortName());
    } else {
      final NewsViewHolderBanner holder = (NewsViewHolderBanner) hold;
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

    private EditText edtSearch;
    private TextView txtNamePersonal;
    private TextView txtShortTextPersonal;
    private TextView txtPosts;
    private TextView txtFollowers;
    private TextView txtFollowing;
    private TextView txtPoods;
    private LinearLayout layFollowers;

    public NewsViewHolderBanner(View itemView) {
      super(itemView);
      edtSearch = (EditText) itemView.findViewById(R.id.edt_search);
      edtSearch.setTypeface(Base.font1);

    }

  }
}
