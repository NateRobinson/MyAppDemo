package com.gu.myapp.ui.frgment.v.home;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.baselibrary.view.dragtoplayout.DragTopLayout;
import com.gu.baselibrary.view.verticalviewpager.StackTransformer;
import com.gu.myapp.R;
import com.gu.myapp.ui.activity.p.home.HomeActivity;
import com.gu.myapp.ui.adapter.ContentFragmentAdapter;
import com.gu.myapp.ui.frgment.p.home.OneContentFragment;

/**
 * Created by guxuewu on 2016/2/19.
 * 首页 第一个 碎片 view 控制类
 */
public class OneFragmentView extends AppDelegate {
    private DragTopLayout dragLayout;
    private ViewPager viewPager;

    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_home_one;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {
        dragLayout = get(R.id.drag_layout);
        viewPager = get(R.id.view_pager);
        viewPager.setPageTransformer(true, new StackTransformer());
        viewPager.setAdapter(new ContentFragmentAdapter.Holder(((HomeActivity) getActivity()).getSupportFragmentManager())
                .add(OneContentFragment.getOneContentFragment("1"))
                .add(OneContentFragment.getOneContentFragment("2"))
                .add(OneContentFragment.getOneContentFragment("3"))
                .add(OneContentFragment.getOneContentFragment("4"))
                .add(OneContentFragment.getOneContentFragment("5"))
                .set());
        //顶部的或底部的渐变色去除
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        dragLayout.setDrag(true);
                        break;
                    default:
                        dragLayout.setDrag(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * @return menu菜单id
     */
    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    /**
     * @return 返回toolbar对象
     */
    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
