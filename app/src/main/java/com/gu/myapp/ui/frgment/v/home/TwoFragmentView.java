package com.gu.myapp.ui.frgment.v.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.myapp.R;
import com.gu.myapp.ui.frgment.p.home.MediaFragment;
import com.gu.myapp.ui.frgment.p.home.TopicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuewu on 2016/2/19.
 * 首页 第二个 碎片 view 控制类
 */
public class TwoFragmentView extends AppDelegate {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SimpleFragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> labList = new ArrayList<>();

    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_home_two;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {
        viewPager=get(R.id.viewpager);
        tabLayout=get(R.id.sliding_tabs);
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

    public void initViewPager(FragmentManager fragmentManager) {
        labList.add("媒体");
        labList.add("话题");
        fragments.add(new MediaFragment());
        fragments.add(new TopicFragment());
        pagerAdapter = new SimpleFragmentPagerAdapter(fragmentManager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        //MODE_FIXED模式会使及格tab均分屏幕的宽度
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

        public SimpleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return labList.get(position);
        }
    }
}
