package com.gu.baselibrary.view.verticalviewpager;

import android.view.View;

/**
 * Created by T530 on 18/09/2014.
 */
public class VerticalZoomInTransformer extends VerticalBaseTransformer {
    @Override
    protected void onTransform(View view, float position) {
        final float scale = position < 0 ? position + 1f : Math.abs(1f - position);
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setAlpha(position < -1f || position > 1f ? 0f : 1f - (scale - 1f));
    }
}
