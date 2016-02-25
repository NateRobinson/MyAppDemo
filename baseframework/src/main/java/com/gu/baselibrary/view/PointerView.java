package com.gu.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.gu.baselibrary.R;

/**
 * Created by guxuewu on 2016/2/25.
 */
public class PointerView extends View {

    private int[] colors = new int[]{R.color.point_one, R.color.point_two, R.color.point_three, R.color.point_four};

    private int mHeight;
    private int mWidth;

    private int index = 0;
    private boolean selected = false;


    public PointerView(Context context) {
        super(context);
    }

    public PointerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeight = getHeight();
        mWidth = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 创建画笔
        Paint p = new Paint();
        p.setStrokeWidth(5);
        p.setColor(colors[index]);
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        p.setStyle(Paint.Style.STROKE);//设置空心
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 10, p);// 小圆

        if (selected) {
            canvas.save();

            Paint p2 = new Paint();
            p2.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
            p2.setColor(colors[index]);
            p2.setStyle(Paint.Style.FILL);//设置空心
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, 5, p2);// 小圆

            canvas.restore();
        }

    }

    public void setIndex(int index) {
        this.index = index;
        requestLayout();
        postInvalidate();
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        requestLayout();
        postInvalidate();
    }
}
