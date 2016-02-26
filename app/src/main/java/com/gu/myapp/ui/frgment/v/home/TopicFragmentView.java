package com.gu.myapp.ui.frgment.v.home;

import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.ListView;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.myapp.R;
import com.gu.myapp.ui.adapter.MediaFragAdapter;
import com.gu.myapp.ui.adapter.TopicFragAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuewu on 2016/2/26.
 * 第二个fragment - 话题 fragment  - 视图控制类
 */
public class TopicFragmentView extends AppDelegate {
    private List<String> strs = new ArrayList<>();
    private GridView topic_grid_view;
    private TopicFragAdapter topicFragAdapter = null;

    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_two_topic;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {
        topic_grid_view = get(R.id.topic_grid_view);
        topicFragAdapter = new TopicFragAdapter(getActivity(), R.layout.item_topic_frag, strs);
        topic_grid_view.setAdapter(topicFragAdapter);
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

    public void setStrs(List<String> datas) {
        strs.clear();
        strs.addAll(datas);
        topicFragAdapter.notifyDataSetChanged();
    }
}
