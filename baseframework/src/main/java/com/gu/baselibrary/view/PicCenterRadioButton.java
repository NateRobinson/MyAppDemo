package com.gu.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

/**
 * Created by guxuewu on 2016/2/19.
 */
public class PicCenterRadioButton extends RadioButton {

    public PicCenterRadioButton(Context context) {
        super(context);
    }

    public PicCenterRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    // 核心代码部分
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableTop = drawables[1];
            if (drawableTop != null) {
                float textHeight = 0;
                int drawablePadding = getCompoundDrawablePadding();
                int drawableHeight = drawableTop.getIntrinsicHeight();
                float bodyHeight = textHeight + drawableHeight + drawablePadding;
                setPadding(0, (int) (getHeight() - bodyHeight), 0, 0);
                canvas.translate(0, 0 - (getHeight() - bodyHeight) / 2);
            }
        }
        super.onDraw(canvas);
    }
}
