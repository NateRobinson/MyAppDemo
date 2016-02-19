package com.gu.myapp.ui.frgment.v.home;

import android.support.v7.widget.Toolbar;

import com.gu.baselibrary.baseui.view.AppDelegate;

/**
 * Created by guxuewu on 2016/2/19.
 * 首页 第二个 碎片 view 控制类
 */
public class TwoFragmentView extends AppDelegate{
    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return 0;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {

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
