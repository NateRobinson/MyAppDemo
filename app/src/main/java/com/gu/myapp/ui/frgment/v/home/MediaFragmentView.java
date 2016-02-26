package com.gu.myapp.ui.frgment.v.home;

import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.myapp.R;
import com.gu.myapp.ui.adapter.MediaFragAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuewu on 2016/2/26.
 *  第二个fragment - 媒体 fragment  - 视图控制类
 */
public class MediaFragmentView extends AppDelegate {
    private List<String> strs=new ArrayList<>();
    private ListView media_list_view;
    private MediaFragAdapter mediaFragAdapter=null;
    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_two_media;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {
        media_list_view=get(R.id.media_list_view);
        mediaFragAdapter=new MediaFragAdapter(getActivity(),R.layout.item_media_frag,strs);
        media_list_view.setAdapter(mediaFragAdapter);
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

    public void setStrs(List<String> datas){
        strs.clear();
        strs.addAll(datas);
        mediaFragAdapter.notifyDataSetChanged();
    }
}
