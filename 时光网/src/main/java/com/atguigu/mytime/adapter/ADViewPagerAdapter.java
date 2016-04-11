package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.mytime.entity.HomeShopInfo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public class ADViewPagerAdapter extends PagerAdapter {
    private List<HomeShopInfo.AdvListEntity> advList;
    private Activity mActivity;

    public ADViewPagerAdapter(List<HomeShopInfo.AdvListEntity> advList, Activity mActivity) {
        this.advList=advList;
        this.mActivity=mActivity;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE%2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % advList.size();
        ImageView imageView=new ImageView(mActivity);
        Glide.with(mActivity).load(advList.get(position).getImg()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
