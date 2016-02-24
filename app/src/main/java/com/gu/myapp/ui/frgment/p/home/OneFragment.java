package com.gu.myapp.ui.frgment.p.home;

import android.view.View;

import com.gu.baselibrary.baseui.presenter.BaseFragmentPresenter;
import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.baselibrary.view.MyPagerGalleryView;
import com.gu.myapp.R;
import com.gu.myapp.ui.frgment.v.home.OneFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuewu on 2016/2/19.
 * 首页 第一个 碎片
 */
public class OneFragment extends BaseFragmentPresenter<OneFragmentView> {

    @Override
    protected Class getDelegateClass() {
        return OneFragmentView.class;
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
        viewDelegate.bindClick(new MyPagerGalleryView.MyOnItemClickListener() {
            @Override
            public void onItemClick(int curIndex) {
                viewDelegate.showToast("点击了" + curIndex);
            }
        });
        viewDelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.refresh_iv:
                        viewDelegate.showToast("点击了刷新");
                        break;
                    case R.id.mail_iv:
                        viewDelegate.showToast("点击了消息");
                        break;
                    case R.id.title_rl:
                        //使title处理点击事件，防止被广告插件抢去焦点
                        break;
                    default:
                        break;
                }
            }
        }, R.id.refresh_iv, R.id.mail_iv, R.id.title_rl);
    }
}
