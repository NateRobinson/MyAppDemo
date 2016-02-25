package com.gu.myapp.ui.frgment.v.home;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gu.baselibrary.baseui.view.AppDelegate;
import com.gu.baselibrary.view.MyPagerGalleryView;
import com.gu.baselibrary.view.dragtoplayout.DragTopLayout;
import com.gu.baselibrary.view.verticalviewpager.VerticalViewPager;
import com.gu.baselibrary.view.verticalviewpager.VerticalStackTransformer;
import com.gu.myapp.R;
import com.gu.myapp.ui.activity.p.home.HomeActivity;
import com.gu.myapp.ui.adapter.ContentFragmentAdapter;
import com.gu.myapp.ui.frgment.p.home.OneContentFragment;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guxuewu on 2016/2/19.
 * 首页 第一个 碎片 view 控制类
 */
public class OneFragmentView extends AppDelegate {
    private DragTopLayout dragLayout;
    private VerticalViewPager viewPager;
    private View titleBgView;
    private RelativeLayout top_view;
    private ImageView refresh_iv;
    private ImageView mail_iv;
    private TextView title_tv;
    private MyPagerGalleryView pagerGalleryView;
    private LinearLayout homeAdOvalLl;
    /**
     * 图片id的数组,本地测试用
     */
    private int[] imageId = new int[]{R.mipmap.test01, R.mipmap.test02, R.mipmap.test03};

    /**
     * 图片网络路径数组
     */
    private List<String> urlImageList = new ArrayList<>();

    /**
     * @return 返回root视图的id
     */
    @Override
    public int getRootLayoutId() {
        return R.layout.frag_home_one;
    }

    /**
     * 此方法里做一些初始化的操作
     */
    @Override
    public void initWidget() {
        dragLayout = get(R.id.drag_layout);
        viewPager = get(R.id.view_pager);
        titleBgView = get(R.id.title_bg_view);
        top_view = get(R.id.top_view);
        refresh_iv = get(R.id.refresh_iv);
        mail_iv = get(R.id.mail_iv);
        title_tv = get(R.id.title_tv);
        pagerGalleryView = get(R.id.home_ad_gv);
        homeAdOvalLl = get(R.id.home_ad_oval_ll);

        //初始化无限广告图
        // 第二和第三参数 2选1 ,参数2为 图片网络路径数组 ,参数3为图片id的数组,本地测试用
        // ,2个参数都有优先采用 参数2
        pagerGalleryView.start(getActivity()
                        .getApplicationContext(), null,
                imageId, 3000, homeAdOvalLl,
                R.drawable.dot_focused,
                R.drawable.dot_normal);
        pagerGalleryView.startTimer();

        initTitleBg();

        viewPager.setPageTransformer(true, new VerticalStackTransformer());
        viewPager.setAdapter(new ContentFragmentAdapter.Holder(((HomeActivity) getActivity()).getSupportFragmentManager())
                .add(OneContentFragment.getOneContentFragment("1"))
                .add(OneContentFragment.getOneContentFragment("2"))
                .add(OneContentFragment.getOneContentFragment("3"))
                .add(OneContentFragment.getOneContentFragment("4"))
                .add(OneContentFragment.getOneContentFragment("5"))
                .set());
        //顶部的或底部的渐变色去除
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        dragLayout.setDrag(true);
                        break;
                    default:
                        //如果不在position 0  那么一定为关闭状态---防止用户快速滑动，出现界面展示bug
                        dragClose();
                        dragLayout.setDrag(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dragLayout.setPanelListener(new DragTopLayout.PanelListener() {
            @Override
            public void onPanelStateChanged(DragTopLayout.PanelState panelState) {
                switch (panelState) {
                    case COLLAPSED:
                        //关闭
                        dragClose();
                        break;
                    case EXPANDED:
                        // 打开
                        dragOpen();
                        break;
                }
            }

            @Override
            public void onSliding(float ratio) {
                ViewHelper.setAlpha(titleBgView, 1 - ratio);
                ViewHelper.setAlpha(top_view, ratio);
            }

            @Override
            public void onRefresh() {

            }
        });
    }

    private void initTitleBg() {
        // 打开---初始化状态
        dragOpen();
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
     * 关闭
     */
    private void dragClose() {
        ViewHelper.setAlpha(titleBgView, 1);
        ViewHelper.setAlpha(top_view, 0);
        refresh_iv.setColorFilter(getActivity().getResources().getColor(R.color.black_txt));
        mail_iv.setColorFilter(getActivity().getResources().getColor(R.color.black_txt));
        title_tv.setTextColor(getActivity().getResources().getColor(R.color.red));
    }

    /**
     * 打开
     */
    private void dragOpen() {
        ViewHelper.setAlpha(titleBgView, 0);
        ViewHelper.setAlpha(top_view, 1);
        refresh_iv.setColorFilter(getActivity().getResources().getColor(R.color.white));
        mail_iv.setColorFilter(getActivity().getResources().getColor(R.color.white));
        title_tv.setTextColor(getActivity().getResources().getColor(R.color.white));
    }

    /**
     * 广告点击监听绑定
     */
    public void bindClick(MyPagerGalleryView.MyOnItemClickListener listener) {
        pagerGalleryView.setMyOnItemClickListener(listener);
    }

    public void onRefresh() {
        viewPager.setCurrentItem(0);
    }
}
