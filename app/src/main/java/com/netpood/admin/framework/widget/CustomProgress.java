package com.netpood.admin.framework.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;


public class CustomProgress extends ImageView {

    private Paint            circlePaint;
    private Paint            textPaint;

    private static final int STROKE_WIDTH = 5;

    private int              percent      = -180;
    public String            color        = "#818d9d";


    public void setColor(String col) {
        color = col;
        postInvalidate();
    }


    public CustomProgress(Context context) {
        super(context);
        initialize(context);
    }


    public CustomProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }


    public CustomProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }


    private void initialize(Context context) {
        circlePaint = new Paint();

        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(STROKE_WIDTH);
        circlePaint.setStyle(Style.STROKE);

        ser();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circlePaint.setColor(Color.parseColor(color));
        RectF rect = new RectF();
        rect.left = 0 + STROKE_WIDTH;
        rect.right = getWidth() - STROKE_WIDTH;
        rect.top = 0 + STROKE_WIDTH;
        rect.bottom = getHeight() - STROKE_WIDTH;

        canvas.drawArc(rect, percent, 280, false, circlePaint);
    }


    private void ser() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1);
                        percent++;
                        postInvalidate();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
}
