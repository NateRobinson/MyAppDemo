package com.gu.myapp.ui.activity.p.start;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.gu.baselibrary.utils.NetUtils;
import com.gu.myapp.ui.activity.p.home.HomeActivity;
import com.gu.myapp.ui.activity.v.start.StartActivityView;
import com.gu.myapp.ui.base.MyAppBaseActivity;

/**
 * Created by guxuewu on 2016/2/19.
 * app 启动页面
 */
public class StartActivity extends MyAppBaseActivity {
    private static final int GO_TO_HOME = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_TO_HOME:
                    go(HomeActivity.class);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * @return 获取自定义的视图层代理
     */
    @Override
    protected Class getDelegateClass() {
        return StartActivityView.class;
    }

    /**
     * 处理Bundle传参
     *
     * @param extras
     */
    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    /**
     * @return true--自定义页面的切换动画   false--不自定义
     */
    @Override
    protected boolean isCustomPendingTransition() {
        return false;
    }

    /**
     * @return 返回自定义的动画切换方式
     */
    @Override
    protected TransitionMode getCustomPendingTransitionType() {
        return null;
    }

    /**
     * 是否绑定了EventBus
     *
     * @return
     */
    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    /**
     * 网络连接连起来了
     *
     * @param type
     */
    @Override
    protected void doOnNetworkConnected(NetUtils.NetType type) {

    }

    /**
     * 网络连接断开
     */
    @Override
    protected void doOnNetworkDisConnected() {

    }

    /**
     * 初始化所有布局和event事件
     */
    @Override
    protected void initViewsAndEvents() {
        handler.sendEmptyMessageDelayed(GO_TO_HOME, 500);
    }
}
