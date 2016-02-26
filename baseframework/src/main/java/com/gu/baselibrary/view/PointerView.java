package com.gu.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by guxuewu on 2016/2/25.
 */
public class PointerView extends View {

    private int index = 0;
    private boolean selected = false;
    private Paint outPaint;
    private Paint inPaint;


    public PointerView(Context context, int index) {
        super(context);
        this.index = index;
        init();
    }

    public PointerView(Context context) {
        super(context);
        init();
    }

    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        outPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outPaint.setStrokeWidth(2);
        outPaint.setAntiAlias(true);
        outPaint.setStyle(Paint.Style.STROKE);//设置空心

        inPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inPaint.setAntiAlias(true);
        inPaint.setStyle(Paint.Style.FILL);//设置实心

        switch (index % 4) {
            case 0:
                outPaint.setColor(Color.rgb(255, 99, 76));
                inPaint.setColor(Color.rgb(255, 99, 76));
                break;
            case 1:
                outPaint.setColor(Color.rgb(252, 202, 81));
                inPaint.setColor(Color.rgb(252, 202, 81));
                break;
            case 2:
                outPaint.setColor(Color.rgb(63, 222, 122));
                inPaint.setColor(Color.rgb(63, 222, 122));
                break;
            case 3:
                outPaint.setColor(Color.rgb(1, 190, 255));
                inPaint.setColor(Color.rgb(1, 190, 255));
                break;
            default:
                outPaint.setColor(Color.rgb(255, 99, 76));
                inPaint.setColor(Color.rgb(255, 99, 76));
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 5, inPaint);// 实心圆
        if (selected) {
            canvas.save();
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 3, outPaint);// 圆圈
            canvas.restore();
        }
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        invalidate();
    }
}
