package com.gu.myapp.ui.frgment.p.home;

import android.os.Bundle;

import com.gu.baselibrary.baseui.presenter.BaseFragmentPresenter;
import com.gu.baselibrary.utils.LogUtils;
import com.gu.myapp.ui.frgment.v.home.OneContentFragmentView;

/**
 * Created by Nate on 2016/2/22.
 */
public class OneContentFragment extends BaseFragmentPresenter<OneContentFragmentView> {

    public static OneContentFragment getOneContentFragment(String num) {
        Bundle args = new Bundle();
        args.putString("num", num);
        OneContentFragment fragment = new OneContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Class getDelegateClass() {
        return OneContentFragmentView.class;
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
        viewDelegate.setText(getTitle());
    }

    public String getTitle() {
        LogUtils.e(TAG_LOG, "num==>" + getArguments().getString("num"));
        return getArguments().getString("num");
    }
}
