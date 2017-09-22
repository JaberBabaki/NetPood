package com.netpood.admin.netpoodapp.database;

/**
 * Created by jaber babaki on 7/21/2016.
 */
public class PostItem {

  private int id;
  private String urlImageUserItem;
  private String nameUserItem;
  private String txtDateItem;
  private String urlImageMain;
  private String txtMainItem;
  private String txtViewItem;
  private String urlImageUserIdea;
  private String txtUserIdea;
  private String txtAllIdea;
  private String picMain;
  private int likeItem;

  public int getId() {
    return id;
  }


  public String getUrlImageUserItem() {
    return urlImageUserItem;
  }

  public void setUrlImageUserItem(String urlImageUserItem) {
    this.urlImageUserItem = urlImageUserItem;
  }

  public String getNameUserItem() {
    return nameUserItem;
  }

  public void setNameUserItem(String nameUserItem) {
    this.nameUserItem = nameUserItem;
  }

  public String getTxtDateItem() {
    return txtDateItem;
  }

  public void setTxtDateItem(String txtDateItem) {
    this.txtDateItem = txtDateItem;
  }

  public String getUrlImageMain() {
    return urlImageMain;
  }

  public void setUrlImageMain(String urlImageMain) {
    this.urlImageMain = urlImageMain;
  }

  public String getTxtMainItem() {
    return txtMainItem;
  }

  public void setTxtMainItem(String txtMainItem) {
    this.txtMainItem = txtMainItem;
  }

  public String getTxtViewItem() {
    return txtViewItem;
  }

  public void setTxtViewItem(String txtViewItem) {
    this.txtViewItem = txtViewItem;
  }

  public String getUrlImageUserIdea() {
    return urlImageUserIdea;
  }

  public void setUrlImageUserIdea(String urlImageUserIdea) {
    this.urlImageUserIdea = urlImageUserIdea;
  }

  public String getTxtUserIdea() {
    return txtUserIdea;
  }

  public void setTxtUserIdea(String txtUserIdea) {
    this.txtUserIdea = txtUserIdea;
  }

  public String getTxtAllIdea() {
    return txtAllIdea;
  }

  public void setTxtAllIdea(String txtAllIdea) {
    this.txtAllIdea = txtAllIdea;
  }

  public int getLikeItem() {
    return likeItem;
  }

  public void setLikeItem(int likeItem) {
    this.likeItem = likeItem;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPicMain() {
    return picMain;
  }

  public void setPicMain(String picMain) {
    this.picMain = picMain;
  }
}
