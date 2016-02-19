package com.gu.myapp.ui.activity.v.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.baselibrary.view.NoScrollViewPager;
import com.gu.myapp.R;
import com.gu.myapp.ui.activity.p.home.HomeActivity;
import com.gu.myapp.ui.frgment.p.home.FourFragment;
import com.gu.myapp.ui.frgment.p.home.OneFragment;
import com.gu.myapp.ui.frgment.p.home.ThreeFragment;
import com.gu.myapp.ui.frgment.p.home.TwoFragment;

import java.util.ArrayList;

/**
 * Created by guxuewu on 2016/2/19.
 * app 首页 view 控制类
 */
public class HomeActivityView extends AppDelegate {
    RadioGroup bottom_tab;
    NoScrollViewPager vp_main;
    private int[] radioidlist = {R.id.tab_one, R.id.tab_two, R.id.tab_three, R.id.tab_four};
    private int[] radioidlist_bg = {R.id.tab_one_bottom, R.id.tab_two_bottom, R.id.tab_three_bottom, R.id.tab_four_bottom};
    private ArrayList<Fragment> fragList = new ArrayList<>();
    private HomeVpAdapter adapter;

    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_home;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {
        bottom_tab = get(R.id.bottom_tab);
        vp_main = get(R.id.vp_main);

        fragList.add(new OneFragment());
        fragList.add(new TwoFragment());
        fragList.add(new ThreeFragment());
        fragList.add(new FourFragment());
        adapter = new HomeVpAdapter(((HomeActivity) getActivity()).getSupportFragmentManager());
        vp_main.setAdapter(adapter);
        vp_main.setOffscreenPageLimit(4);
        vp_main.setCurrentItem(0, false);// false，关闭viewpager的滑动切换效果
        vp_main.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int index) {
                ((RadioButton) get(radioidlist[index]))
                        .setChecked(true);
                ((RadioButton) get(radioidlist_bg[index]))
                        .setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottom_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i_index = 0; i_index < radioidlist.length; i_index++) {
                    if (radioidlist[i_index] == checkedId) {
                        ((RadioButton) get(radioidlist_bg[i_index])).setChecked(true);
                        vp_main.setCurrentItem(i_index, false);
                    }
                }
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

    /**
     * @author Nate
     * @desc 主页ViewPage适配器
     * @date 2015-12-18
     */
    class HomeVpAdapter extends FragmentPagerAdapter {

        public HomeVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragList.get(i);
        }

        @Override
        public int getCount() {
            return fragList.size();
        }

        // @Override
        // public int getItemPosition(Object object) {
        // return PagerAdapter.POSITION_NONE;
        // }
    }

}
