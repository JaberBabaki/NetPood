package com.netpood.admin.netpoodapp.database;

/**
 * Created by Saeed shahini on 7/21/2016.
 */
public class PostItem {
    private int id;
    private String postImage;
    private String title;
    private String zendgiNameh;
    private String born;
    private String shahadt;
    private String mahal;
    private String vaseatNameh;
    private String name;
    private String family;
    private int like;
    private int ViewAll;
    private int View;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZendgiNameh() {
        return zendgiNameh;
    }

    public void setZendgiNameh(String zendgiNameh) {
        this.zendgiNameh = zendgiNameh;
    }


    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getViewAll() {
        return ViewAll;
    }

    public void setViewAll(int viewAll) {
        ViewAll = viewAll;
    }

    public int getView() {
        return View;
    }

    public void setView(int view) {
        View = view;
    }

    public String getVaseatNameh() {
        return vaseatNameh;
    }

    public void setVaseatNameh(String vaseatNameh) {
        this.vaseatNameh = vaseatNameh;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getShahadt() {
        return shahadt;
    }

    public void setShahadt(String shahadt) {
        this.shahadt = shahadt;
    }

    public String getMahal() {
        return mahal;
    }

    public void setMahal(String mahal) {
        this.mahal = mahal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
