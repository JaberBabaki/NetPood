
package com.netpood.admin.framework.widget.bottombar;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

class BadgeCircle {
    /**
     * Creates a new circle for the Badge background.
     *
     * @param size  the width and height for the circle
     * @param color the activeIconColor for the circle
     * @return a nice and adorable circle.
     */
    @NonNull
    static ShapeDrawable make(@IntRange(from = 0) int size, @ColorInt int color) {
        ShapeDrawable indicator = new ShapeDrawable(new OvalShape());
        indicator.setIntrinsicWidth(size);
        indicator.setIntrinsicHeight(size);
        indicator.getPaint().setColor(color);
        return indicator;
    }
}
