package com.gu.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gu.baselibrary.utils.LogUtils;

/**
 * Created by guxuewu on 2016/2/24.
 */
public class AutoAdaptTextView extends TextView {
    private boolean calculatedLines = false;

    public AutoAdaptTextView(Context context) {
        super(context);
    }

    public AutoAdaptTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoAdaptTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!calculatedLines) {
            calculateLines();
            calculatedLines = true;
        }
        super.onDraw(canvas);
    }

    private void calculateLines() {
        int mHeight = getMeasuredHeight();
        int lHeight = getLineHeight();
        int lines = mHeight / lHeight;
        setEllipsize(TextUtils.TruncateAt.END);
        setLines(lines - 1);
    }
}
