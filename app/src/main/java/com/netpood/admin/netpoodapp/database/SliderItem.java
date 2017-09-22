package com.netpood.admin.netpoodapp.database;

import android.graphics.drawable.Drawable;

/**
 * Created by jaberALU on 20/08/2017.
 */
public class SliderItem {
  private int    idSlide;
  private Drawable imgAddress;
  private String link;


  public int getIdSlide() {
    return idSlide;
  }

  public void setIdSlide(int idSlide) {
    this.idSlide = idSlide;
  }


  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public Drawable getImgAddress() {
    return imgAddress;
  }

  public void setImgAddress(Drawable imgAddress) {
    this.imgAddress = imgAddress;
  }
}
