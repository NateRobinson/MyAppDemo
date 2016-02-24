package com.gu.myapp.ui.frgment.v.home;

import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.myapp.R;

/**
 * Created by Nate on 2016/2/22.
 */
public class OneContentFragmentView extends AppDelegate {
    private TextView content_num_tv;
    private LinearLayout content_ll;

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
        content_num_tv = get(R.id.content_num_tv);
        content_ll = get(R.id.content_ll);
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
        content_num_tv.setText(num);
        switch (num) {
            case "1":
                content_ll.setBackgroundColor(Color.BLACK);
                break;
            case "2":
                content_ll.setBackgroundColor(Color.BLUE);
                break;
            case "3":
                content_ll.setBackgroundColor(Color.RED);
                break;
            case "4":
                content_ll.setBackgroundColor(Color.GREEN);
                break;
            case "5":
                content_ll.setBackgroundColor(Color.DKGRAY);
                break;
        }
    }
}
