package com.netpood.admin.framework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.netpood.admin.netpoodapp.Base;


public class CustomeTextView extends TextView {

    public CustomeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }


    public CustomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    public CustomeTextView(Context context) {
        super(context);
        initialize();
    }


    private void initialize() {
        if ( !isInEditMode()) {
            setTypeface(Base.font1);
        }
    }
}