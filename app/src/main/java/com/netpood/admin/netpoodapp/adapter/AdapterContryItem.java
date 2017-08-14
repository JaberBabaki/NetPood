package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.PostItem;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class AdapterContryItem extends RecyclerView.Adapter<AdapterContryItem.NewsViewHolder> {


  private Context context;
  private List<PostItem> posts;

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
      urlImageUserItem = (ImageView) itemView.findViewById(R.id.img_user_item);
      nameUserItem = (TextView) itemView.findViewById(R.id.txt_main_item);
      txtDateItem = (TextView) itemView.findViewById(R.id.txt_date);
      urlImageMain = (ImageView) itemView.findViewById(R.id.img_main_item);
      txtMainItem = (TextView) itemView.findViewById(R.id.txt_main_item);
      txtViewItem = (TextView) itemView.findViewById(R.id.txt_view_item);
      urlImageUserIdea = (ImageView) itemView.findViewById(R.id.img_user_idea);
      txtIdeaUser = (TextView) itemView.findViewById(R.id.txt_user_idea);
      txtAllIdea = (TextView) itemView.findViewById(R.id.txt_all_comment);
      imageLike = (ImageView) itemView.findViewById(R.id.img_like_item);
      //cardView = (CardView) itemView.findViewById(R.id.card_item);
    }
  }

  public AdapterContryItem(Context context, List<PostItem> posts) {
    this.context = context;
    this.posts = posts;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_namyeh, parent, false);
    return new NewsViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final NewsViewHolder holder, int position) {
    final PostItem post = posts.get(position);
    //final Shohda shohda = new Shohda();
    holder.urlImageUserItem.setImageDrawable(post.getUrlImageUserItem());
    holder.nameUserItem.setText(post.getNameUserItem());
    holder.txtDateItem.setText(post.getTxtDateItem());
    holder.urlImageMain.setImageDrawable(post.getUrlImageMain());
    holder.txtMainItem.setText(post.getTxtMainItem());
    holder.txtViewItem.setText(post.getTxtViewItem());
    holder.urlImageUserIdea.setImageDrawable(post.getUrlImageUserIdea());
    holder.txtIdeaUser.setText(post.getTxtUserIdea());
    holder.txtAllIdea.setText(post.getTxtAllIdea());

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

  }

  @Override
  public int getItemCount() {
    return posts.size();
  }



}
