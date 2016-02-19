package com.gu.myapp.ui.activity.v.home;

import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.baselibrary.view.NoScrollViewPager;
import com.gu.myapp.R;

/**
 * Created by guxuewu on 2016/2/19.
 * app 首页 view 控制类
 */
public class HomeActivityView extends AppDelegate {
    RadioGroup bottom_tab;
    NoScrollViewPager vp_main;
    private int[] radioidlist = {R.id.tab_one, R.id.tab_two, R.id.tab_three, R.id.tab_four};
    private int[] radioidlist_bg = {R.id.tab_one_bottom, R.id.tab_two_bottom, R.id.tab_three_bottom, R.id.tab_four_bottom};

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

        bottom_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i_index = 0; i_index < radioidlist.length; i_index++) {
                    if (radioidlist[i_index] == checkedId) {
                        ((RadioButton)get(radioidlist_bg[i_index])).setChecked(true);
                        //vp_main.setCurrentItem(i_index, false);
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

}
