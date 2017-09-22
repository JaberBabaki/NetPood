package com.netpood.admin.framework.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.widget.ImageView;


public class CustomLoading extends ImageView {

    private Paint            circleOn;
    private Paint            circleOff;
    private Paint            textPaint;

    private static final int STROKE_WIDTH = 8;

    private int              indicate     = 3;


    public CustomLoading(Context context) {
        super(context);
        initialize(context);
    }


    public CustomLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }


    public CustomLoading(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }


    public void setProgress(int value) {
        postInvalidate();
    }


    private void initialize(Context context) {
        circleOn = new Paint();
        circleOn.setColor(Color.parseColor("#1A75BC"));
        circleOn.setAntiAlias(true);
        circleOn.setStrokeWidth(STROKE_WIDTH);
        circleOn.setStyle(Style.FILL);

        circleOff = new Paint();
        circleOff.setColor(Color.parseColor("#959595"));
        circleOff.setAntiAlias(true);
        circleOff.setStrokeWidth(STROKE_WIDTH);
        circleOff.setStyle(Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Align.CENTER);
        textPaint.setTextSize(30);
        textPaint.setStyle(Style.FILL_AND_STROKE);

        ser();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*RectF rect = new RectF();
        rect.left = 0 + STROKE_WIDTH;
        rect.right = getWidth() - STROKE_WIDTH;
        rect.top = 0 + STROKE_WIDTH;
        rect.bottom = getHeight() - STROKE_WIDTH;

        int sweepAngle = percent * 360 / 100;
        int red = (int) ((100 - percent) * 2.5f);
        int green = (int) (percent * 2.5f);
        circlePaint.setColor(Color.rgb(red, green, 0));
        canvas.drawArc(rect, -90, sweepAngle, false, circlePaint);*/
        // canvas.drawText(percent + "%", getWidth() / 2, getHeight() / 2, textPaint);

        if (indicate == 1) {
            canvas.drawCircle((getWidth() / 2) - 35, getHeight() / 2, 8, circleOff);
            canvas.drawCircle((getWidth() / 2), getHeight() / 2, 8, circleOn);
            canvas.drawCircle((getWidth() / 2) + 35, getHeight() / 2, 8, circleOn);
        } else if (indicate == 2) {
            canvas.drawCircle((getWidth() / 2) - 35, getHeight() / 2, 8, circleOn);
            canvas.drawCircle((getWidth() / 2), getHeight() / 2, 8, circleOff);
            canvas.drawCircle((getWidth() / 2) + 35, getHeight() / 2, 8, circleOn);
        } else if (indicate == 3) {
            canvas.drawCircle((getWidth() / 2) - 35, getHeight() / 2, 8, circleOn);
            canvas.drawCircle((getWidth() / 2), getHeight() / 2, 8, circleOn);
            canvas.drawCircle((getWidth() / 2) + 35, getHeight() / 2, 8, circleOff);
        }

    }


    private void ser() {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int percent = 0;
                while (true) {
                    try {
                        Thread.sleep(200);
                        if (indicate == 3) {
                            indicate = 1;
                        } else if (indicate == 1) {
                            indicate = 2;
                        } else if (indicate == 2) {
                            indicate = 3;
                        }
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
