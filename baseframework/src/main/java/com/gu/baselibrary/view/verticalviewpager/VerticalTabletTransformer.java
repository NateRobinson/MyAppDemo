package com.gu.baselibrary.view.verticalviewpager;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

/**
 * Created by T530 on 18/09/2014.
 */
public class VerticalTabletTransformer extends VerticalBaseTransformer {
    private static final Matrix OFFSET_MATRIX = new Matrix();
    private static final Camera OFFSET_CAMERA = new Camera();
    private static final float[] OFFSET_TEMP_FLOAT = new float[2];

    @Override
    protected void onTransform(View view, float position) {
        final float rotation = (position < 0 ? 30f : -30f) * Math.abs(position);

        view.setTranslationY(getOffsetXForRotation(rotation, view.getWidth(), view.getHeight()));
        view.setPivotY(view.getHeight() * 0.5f);
        view.setPivotX(0);
        view.setRotationX(rotation);
    }

    protected static final float getOffsetXForRotation(float degrees, int width, int height) {
        OFFSET_MATRIX.reset();
        OFFSET_CAMERA.save();
        OFFSET_CAMERA.rotateX(Math.abs(degrees));
        OFFSET_CAMERA.getMatrix(OFFSET_MATRIX);
        OFFSET_CAMERA.restore();

        OFFSET_MATRIX.preTranslate(-width * 0.5f, -height * 0.5f);
        OFFSET_MATRIX.postTranslate(width * 0.5f, height * 0.5f);
        OFFSET_TEMP_FLOAT[0] = width;
        OFFSET_TEMP_FLOAT[1] = height;
        OFFSET_MATRIX.mapPoints(OFFSET_TEMP_FLOAT);
        return (width - OFFSET_TEMP_FLOAT[0]) * (degrees > 0.0f ? 1.0f : -1.0f);
    }
}
