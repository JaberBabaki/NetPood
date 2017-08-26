package com.netpood.admin.netpoodapp.database;

import android.graphics.drawable.Drawable;

/**
 * Created by jaber babaki on 7/21/2016.
 */
public class FollowItem {
    private int id;
    private String name;
    private String shortName;
    private Drawable pic;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public Drawable getPic() {
        return pic;
    }

    public void setPic(Drawable pic) {
        this.pic = pic;
    }
}
