package com.atguigu.mytime.pager;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.view.HorizontalListView;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2016/4/7.
 * 主页面
 */
public class HomePager extends BasePager {
    private ImageView im_load;
    private GifImageView gif_load;
    private NetReceiver receiver;
    private ViewPager vpTitle;
    private LinearLayout llSeek;
    private TextView tvSeek;
    private ImageView imDimension;
    private CirclePageIndicator indicator;
    private TextView tvState01;
    private TextView tvSelectorCity01;
    private TextView tvState02;
    private HorizontalListView horizonListview;
    private ViewPager vpAd;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-04-10 16:09:37 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View view) {
        vpTitle = (ViewPager)view.findViewById(R.id.vp_title);
        llSeek = (LinearLayout)view.findViewById(R.id.ll_seek);
        tvSeek = (TextView)view.findViewById(R.id.tv_seek);
        imDimension = (ImageView)view.findViewById(R.id.im_dimension);
        indicator = (CirclePageIndicator)view.findViewById(R.id.indicator);
        tvState01 = (TextView)view.findViewById(R.id.tv_state01);
        tvSelectorCity01 = (TextView)view.findViewById(R.id.tv_selector_city01);
        tvState02 = (TextView)view.findViewById(R.id.tv_state02);
        horizonListview = (HorizontalListView)view.findViewById(R.id.horizon_listview);
        vpAd = (ViewPager)view.findViewById( R.id.vp_ad );
    }
    public HomePager(Activity mactivity) {
        super(mactivity);
    }

    @Override
    public View initView() {
        View view=View.inflate(mactivity, R.layout.home_pager,null);
        im_load= (ImageView) view.findViewById(R.id.im_load);
        gif_load= (GifImageView) view.findViewById(R.id.gif_load);
        //加载头文件
        View headView=View.inflate(mactivity,R.layout.home_pager_title,null);
        findViews(headView);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
    }

}
