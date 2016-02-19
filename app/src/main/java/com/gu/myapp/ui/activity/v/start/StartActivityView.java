package com.gu.myapp.ui.activity.v.start;

import android.support.v7.widget.Toolbar;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.myapp.R;

/**
 * Created by guxuewu on 2016/2/19.
 * app 启动界面 view 控制类
 */
public class StartActivityView extends AppDelegate {
    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_start;
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
