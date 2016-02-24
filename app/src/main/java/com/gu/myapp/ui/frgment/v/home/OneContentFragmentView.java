package com.gu.myapp.ui.frgment.v.home;

import android.support.v7.widget.Toolbar;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.myapp.R;

/**
 * Created by Nate on 2016/2/22.
 */
public class OneContentFragmentView extends AppDelegate {

    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_one_content;
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

    public void setText(String num) {
    }
}
