package com.gu.myapp.ui.adapter;

import android.content.Context;

import com.gu.baselibrary.baseadapter.BaseViewHolder;
import com.gu.baselibrary.baseadapter.CustomBaseAdapter;

import java.util.List;

/**
 * Created by guxuewu on 2016/2/26.
 * 首页 第二个碎片 第一个媒体布局的适配器
 */
public class MediaFragAdapter extends CustomBaseAdapter<String> {
    public MediaFragAdapter(Context context, int resource, List<String> list) {
        super(context, resource, list);
    }

    /**
     * @param viewHolder
     * @param s
     * @return void 返回类型
     * @Title: setConvert
     * @Description: 抽象方法，由子类去实现每个itme如何设置
     */
    @Override
    public void setConvert(BaseViewHolder viewHolder, String s) {

    }
}
