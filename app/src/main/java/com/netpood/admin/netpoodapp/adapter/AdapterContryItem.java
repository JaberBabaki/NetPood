package com.netpood.admin.netpoodapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.R;
import com.netpood.admin.netpoodapp.database.CountryItem;

import java.util.List;

/**
 * Created by Saeed shahini on 7/22/2016.
 */
public class AdapterContryItem extends RecyclerView.Adapter<AdapterContryItem.NewsViewHolder> {


  private Context context;
  private List<CountryItem> posts;
  private OnItemClickListener.OnItemClickCallback onItemClickCallback;
  public AdapterContryItem(Context context, List<CountryItem> posts, OnItemClickListener.OnItemClickCallback onItemClickCallback) {
    this.context = context;
    this.posts = posts;
    this.onItemClickCallback = onItemClickCallback;
  }

  @Override
  public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false);
    return new NewsViewHolder(view);
  }

  public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView nameCountry;
    private TextView txtcodeCountry;
    private LinearLayout layMain;

    public NewsViewHolder(View itemView) {
      super(itemView);
      nameCountry = (TextView) itemView.findViewById(R.id.txt_name);
      txtcodeCountry = (TextView) itemView.findViewById(R.id.txt_code);
      layMain = (LinearLayout) itemView.findViewById(R.id.lay_item_country);
    }
  }



  @Override
  public void onBindViewHolder(final NewsViewHolder holder, final int position) {
    CountryItem post = posts.get(position);
    holder.nameCountry.setText(post.getNameCountry());
    holder.txtcodeCountry.setText("+"+post.getCodeCountry());
   /* holder.layMain.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        Base.position=position;
      }
    });*/
    holder.layMain.setOnClickListener(new OnItemClickListener(position, onItemClickCallback));

  }

  @Override
  public int getItemCount() {
    return posts.size();
  }



}
