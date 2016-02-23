/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 */

package com.gu.baselibrary.view.dragtoplayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gu.baselibrary.R;
import com.gu.baselibrary.utils.LogUtils;
import com.gu.baselibrary.utils.ScreenUtils;

/**
 * Created by chenupt@gmail.com on 2015/1/18.
 * Description : Drag down to show a menu panel on the top.
 */
public class DragTopLayout extends FrameLayout {

    private ViewDragHelper dragHelper;
    private int dragRange;//拖拽范围
    private View dragContentView;//内容布局
    private View topView;//顶部的视图

    private int contentTop;//顶部布局的top值
    private int topViewHeight;
    private float ratio;
    private boolean isRefreshing;
    private boolean shouldIntercept = true;

    private PanelListener panelListener;
    private float refreshRatio = 1.5f;
    private boolean overDrag = false;//是否可以有拉伸效果
    private int collapseOffset;//自定义偏移量
    private int topViewId = -1;
    private int dragContentViewId = -1;
    private boolean captureTop = false;//设置顶部布局是否监听viewdraghelper

    // Used for scrolling
    private boolean dispatchingChildrenDownFaked = false;
    private boolean dispatchingChildrenContentView = false;
    private float dispatchingChildrenStartedAtY = Float.MAX_VALUE;
    private PanelState panelState = PanelState.EXPANDED;

    public enum PanelState {

        COLLAPSED(0),//关闭
        EXPANDED(1),//展开
        SLIDING(2);//滑行

        private int asInt;

        PanelState(int i) {
            this.asInt = i;
        }

        static PanelState fromInt(int i) {
            switch (i) {
                case 0:
                    return COLLAPSED;
                case 2:
                    return SLIDING;
                default:
                case 1:
                    return EXPANDED;
            }
        }

        public int toInt() {
            return asInt;
        }
    }


    public DragTopLayout(Context context) {
        this(context, null);
    }

    public DragTopLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragTopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        dragHelper = ViewDragHelper.create(this, 1.0f, callback);
        // init from attrs
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DragTopLayout);
        //setCollapseOffset(a.getDimensionPixelSize(R.styleable.DragTopLayout_dtlCollapseOffset, collapseOffset));
        overDrag = a.getBoolean(R.styleable.DragTopLayout_dtlOverDrag, overDrag);
        dragContentViewId = a.getResourceId(R.styleable.DragTopLayout_dtlDragContentView, -1);
        topViewId = a.getResourceId(R.styleable.DragTopLayout_dtlTopView, -1);
        initOpen(a.getBoolean(R.styleable.DragTopLayout_dtlOpen, true));
        captureTop = a.getBoolean(R.styleable.DragTopLayout_dtlCaptureTop, false);
        a.recycle();
    }

    /**
     * 初始化打开与关闭
     *
     * @param initOpen
     */
    private void initOpen(boolean initOpen) {
        if (initOpen) {
            panelState = PanelState.EXPANDED;
        } else {
            panelState = PanelState.COLLAPSED;
        }
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        //初始化contenttop的高度
        contentTop = ScreenUtils.dp2px(getContext(), 250);
        if (getChildCount() < 2) {
            throw new RuntimeException("Content view must contains two child views at least.");
        }

        if (topViewId != -1 && dragContentViewId == -1) {
            throw new IllegalArgumentException("You have set \"dtlTopView\" but not \"dtlDragContentView\". Both are required!");
        }

        if (dragContentViewId != -1 && topViewId == -1) {
            throw new IllegalArgumentException("You have set \"dtlDragContentView\" but not \"dtlTopView\". Both are required!");
        }

        if (dragContentViewId != -1 && topViewId != -1) {
            bindId(this);
        } else {
            topView = getChildAt(0);
            dragContentView = getChildAt(1);
        }
    }

    private void bindId(View view) {
        topView = view.findViewById(topViewId);
        dragContentView = view.findViewById(dragContentViewId);

        if (topView == null) {
            throw new IllegalArgumentException("\"dtlTopView\" with id = \"@id/"
                    + getResources().getResourceEntryName(topViewId)
                    + "\" has NOT been found. Is a child with that id in this " + getClass().getSimpleName() + "?");
        }


        if (dragContentView == null) {
            throw new IllegalArgumentException("\"dtlDragContentView\" with id = \"@id/"
                    + getResources().getResourceEntryName(dragContentViewId)
                    + "\" has NOT been found. Is a child with that id in this "
                    + getClass().getSimpleName()
                    + "?");
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        dragRange = getHeight();

        // In case of resetting the content top to target position before sliding.
        resetTopViewHeight();
        resetContentHeight();

        topView.layout(left, Math.min(topView.getPaddingTop(), contentTop - topViewHeight) >= 0 ? Math.min(topView.getPaddingTop(), contentTop - topViewHeight) : 0, right,
                contentTop + ScreenUtils.dp2px(getContext(), 50));
        dragContentView.layout(left, contentTop, right,
                contentTop + dragContentView.getHeight());

    }

    /**
     * 重置顶部视图
     */
    private void resetTopViewHeight() {
        int newTopHeight = topView.getHeight();
        // Top layout is changed
        if (topViewHeight != newTopHeight) {
            if (panelState == PanelState.EXPANDED) {
                //第一次的contenttop就是在这边赋值的
                //contentTop = newTopHeight;
                handleSlide(contentTop);
            } else if (panelState == PanelState.COLLAPSED) {
                // update the drag content top when it is collapsed.
                contentTop = collapseOffset;
            }
            topViewHeight = newTopHeight;
        }
    }

    /**
     * 重置内容高度
     */
    private void resetContentHeight() {
        if (dragContentView != null && dragContentView.getHeight() != 0) {
            ViewGroup.LayoutParams layoutParams = dragContentView.getLayoutParams();
            layoutParams.height = getHeight() - collapseOffset - ScreenUtils.dp2px(getContext(), 54);
            dragContentView.setLayoutParams(layoutParams);
        }
    }

    private void handleSlide(final int top) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                dragHelper.smoothSlideViewTo(dragContentView, getPaddingLeft(), top);
                postInvalidate();
            }
        });
    }

    private void resetDragContent(boolean anim, int top) {
        contentTop = top;
        if (anim) {
            dragHelper.smoothSlideViewTo(dragContentView, getPaddingLeft(), contentTop);
            postInvalidate();
        } else {
            requestLayout();
        }
    }

    private void calculateRatio(float top) {
        ratio = (top - collapseOffset - ScreenUtils.dp2px(getContext(), 54)) / (ScreenUtils.dp2px(getContext(), 196) - collapseOffset);
        if (dispatchingChildrenContentView) {
            resetDispatchingContentView();
        }

        if (panelListener != null) {
            // Calculate the ratio while dragging.
            panelListener.onSliding(ratio);
            if (ratio > refreshRatio && !isRefreshing) {
                isRefreshing = true;
                panelListener.onRefresh();
            }
        }
    }

    /**
     * 更新dragtoplayout状态，根据contenttop的值来判断
     */
    private void updatePanelState() {
        if (contentTop <= getPaddingTop() + collapseOffset) {
            panelState = PanelState.COLLAPSED;
        } else if (contentTop >= topView.getHeight()) {
            panelState = PanelState.EXPANDED;
        } else {
            panelState = PanelState.SLIDING;
        }

        if (panelListener != null) {
            panelListener.onPanelStateChanged(panelState);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {

        Parcelable superState = super.onSaveInstanceState();
        SavedState state = new SavedState(superState);
        state.panelState = panelState.toInt();

        return state;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {

        if (!(state instanceof SavedState)) {
            // FIX #10
            super.onRestoreInstanceState(BaseSavedState.EMPTY_STATE);
            return;
        }

        SavedState s = (SavedState) state;
        super.onRestoreInstanceState(s.getSuperState());

        this.panelState = PanelState.fromInt(s.panelState);
        if (panelState == PanelState.COLLAPSED) {
            closeTopView(false);
        } else {
            openTopView(false);
        }
    }

    private boolean isDrag = true;

    public void setDrag(boolean isDrag) {
        this.isDrag = isDrag;
        if (isDrag) {
            //这里有个Bug,当isDrag从false变为true是，mDragHelper的mCallBack在
            //首次滑动时不响应，再次滑动才响应，自能在此调用下，让mDragHelper恢复下状态
            dragHelper.abort();
        } else {
            contentTop = ScreenUtils.dp2px(getContext(), 54);
        }
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            if (child == topView && captureTop) {
                dragHelper.captureChildView(dragContentView, pointerId);
                return false;
            }
            return child == dragContentView;
        }

        /**
         * 正在滚动，滚动没有完成
         * @param changedView
         * @param left
         * @param top
         * @param dx
         * @param dy
         */
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            //可以滚动，并且这个top大于54dp，才可以执行
            if (isDrag && top > ScreenUtils.dp2px(getContext(), 54)) {
                contentTop = top;
                requestLayout();
                calculateRatio(contentTop);
                updatePanelState();
            }
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return dragRange;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            //如果已经到了顶部菜单栏，就不可以滑动
            if (isDrag) {
                if (top > ScreenUtils.dp2px(getContext(), 54)) {
                    if (overDrag) {
                        // Drag over the top view height.
                        return Math.max(top, getPaddingTop() + collapseOffset);
                    } else {
                        //我使用到的是这个
                        return Math.min(ScreenUtils.dp2px(getContext(), 250), Math.max(top, getPaddingTop() + collapseOffset));
                    }
                } else {
                    return ScreenUtils.dp2px(getContext(), 54);
                }
            } else {
                contentTop = ScreenUtils.dp2px(getContext(), 54);
                return ScreenUtils.dp2px(getContext(), 54);
            }

        }

        /**
         * 滚动结束
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (isDrag && contentTop > ScreenUtils.dp2px(getContext(), 54)) {
                // yvel > 0 Fling down || yvel < 0 Fling up
                int top;
                if (yvel > 0 || contentTop > topViewHeight) {
                    top = ScreenUtils.dp2px(getContext(), 250) + getPaddingTop();
                } else {
                    //留下顶部的菜单栏
                    top = getPaddingTop() + collapseOffset + ScreenUtils.dp2px(getContext(), 54);
                }
                dragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
                postInvalidate();
            }

        }

        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }
    };

    @Override
    public void computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            boolean intercept = shouldIntercept && dragHelper.shouldInterceptTouchEvent(ev);
            return intercept;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = MotionEventCompat.getActionMasked(event);

        if (!dispatchingChildrenContentView) {
            try {
                // There seems to be a bug on certain devices: "pointerindex out of range" in viewdraghelper
                // https://github.com/umano/AndroidSlidingUpPanel/issues/351
                dragHelper.processTouchEvent(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (action == MotionEvent.ACTION_MOVE && ratio == 0.0f) {
            dispatchingChildrenContentView = true;
            if (!dispatchingChildrenDownFaked) {
                dispatchingChildrenStartedAtY = event.getY();
                event.setAction(MotionEvent.ACTION_DOWN);
                dispatchingChildrenDownFaked = true;
            }
            dragContentView.dispatchTouchEvent(event);
        }

        if (dispatchingChildrenContentView && dispatchingChildrenStartedAtY < event.getY()) {
            resetDispatchingContentView();
        }

        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            resetDispatchingContentView();
            dragContentView.dispatchTouchEvent(event);
        }

        return true;
    }

    private void resetDispatchingContentView() {
        dispatchingChildrenDownFaked = false;
        dispatchingChildrenContentView = false;
        dispatchingChildrenStartedAtY = Float.MAX_VALUE;
    }

    public void openTopView(boolean anim) {
        // Before created
        if (dragContentView.getHeight() == 0) {
            panelState = PanelState.EXPANDED;
            if (panelListener != null) {
                panelListener.onSliding(1.0f);
            }
        } else {
            resetDragContent(anim, topViewHeight);
        }
    }

    public void closeTopView(boolean anim) {
        if (dragContentView.getHeight() == 0) {
            panelState = PanelState.COLLAPSED;
            if (panelListener != null) {
                panelListener.onSliding(0.0f);
            }
        } else {
            resetDragContent(anim, getPaddingTop() + collapseOffset);
        }
    }

    public interface PanelListener {
        /**
         * Called while the panel state is changed.
         */
        void onPanelStateChanged(PanelState panelState);

        /**
         * Called while dragging.
         * ratio >= 0.
         */
        void onSliding(float ratio);

        /**
         * Called while the ratio over refreshRatio.
         */
        void onRefresh();
    }

    public void setPanelListener(PanelListener listener) {
        this.panelListener = listener;
    }

    /**
     * Save the instance state
     */
    private static class SavedState extends BaseSavedState {

        int panelState;

        SavedState(Parcelable superState) {
            super(superState);
        }

    }
}
