package com.gu.myapp.ui.frgment.p.home;

import com.gu.baselibrary.baseui.presenter.BaseFragmentPresenter;
import com.gu.myapp.ui.frgment.v.home.ThreeFragmentView;

/**
 * Created by guxuewu on 2016/2/19.
 * 首页 第三个 碎片
 */
public class ThreeFragment extends BaseFragmentPresenter<ThreeFragmentView> {
    @Override
    protected Class getDelegateClass() {
        return ThreeFragmentView.class;
    }

    /**
     * 是否绑定EventBus
     */
    @Override
    protected boolean isBindEventBus() {
        return false;
    }

    /**
     * 当用户第一次可以看到这个Fragment的时候，我们可以在里面进行一些数据的请求初始化操作
     */
    @Override
    protected void ontUserFirsVisible() {

    }

    /**
     * Fragment用户不可见的时候可以 做的事情 就是onPause中应该做的事情就放这个方法
     */
    @Override
    protected void onUserInvisible() {

    }

    /**
     * Fragment用户可见的时候，可以做的事情
     */
    @Override
    protected void onUserVisible() {

    }

    /**
     * 初始化一些布局和数据
     */
    @Override
    protected void initViewsAndEvents() {

    }
}
