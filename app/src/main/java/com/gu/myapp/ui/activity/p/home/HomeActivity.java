package com.gu.myapp.ui.activity.p.home;

import android.os.Bundle;

import com.gu.baselibrary.utils.NetUtils;
import com.gu.myapp.ui.activity.v.home.HomeActivityView;
import com.gu.myapp.ui.base.MyAppBaseActivity;

/**
 * Created by guxuewu on 2016/2/19.
 * app 首页
 */
public class HomeActivity extends MyAppBaseActivity {
    /**
     * @return 获取自定义的视图层代理
     */
    @Override
    protected Class getDelegateClass() {
        return HomeActivityView.class;
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

    }
}
