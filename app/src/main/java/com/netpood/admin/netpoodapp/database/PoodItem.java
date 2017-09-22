package com.netpood.admin.netpoodapp.database;

/**
 * Created by jaber babaki on 7/21/2016.
 */
public class PoodItem {
    private int id;
    private String name;
    private String pic;
    private int countUsers;
    private int countPost;
    private int like;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getCountUsers() {
        return countUsers;
    }

    public void setCountUsers(int countUsers) {
        this.countUsers = countUsers;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCountPost() {
        return countPost;
    }

    public void setCountPost(int countPost) {
        this.countPost = countPost;
    }
}
