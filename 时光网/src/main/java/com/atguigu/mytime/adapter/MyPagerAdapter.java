package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author YfSong
 * @Time 13:59
 * Created by YfSong on 2016/4/11.
 */
public abstract class MyPagerAdapter<T> extends PagerAdapter {
    private List data;

    private Activity mActivity;

    public MyPagerAdapter(List data, Activity mActivity) {
        this.data = data;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE/20;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % data.size();

        return MyInstantiateItem(container,position);
    }

    public abstract Object MyInstantiateItem(ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
