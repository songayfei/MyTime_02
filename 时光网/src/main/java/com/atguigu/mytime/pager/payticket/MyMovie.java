package com.atguigu.mytime.pager.payticket;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.CacheUtils;
import com.atguigu.mytime.base.BaseDiscoverPager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/29.
 */
public class MyMovie extends BaseDiscoverPager {

    @ViewInject(R.id.tablayout_movie_pager)
    private TabLayout tablayout_movie_pager;

    @ViewInject(R.id.movie_viewpager)
    public ViewPager movie_viewpager;

    /**
     * 正在热映和即将上映的页面的集合
     */
    private ArrayList<BaseDiscoverPager> list;
    private String[] title;

    public MyMovie(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.movie_pager_view,null);
        x.view().inject(this,view);
        addView();
        return view;
    }

    private void addView() {
        list = new ArrayList();
        list.add(new PlayingMovie(mActivity));
        list.add(new WillShowMovie(mActivity));
        title = new String[]{"正在热映","即将上映"};
    }
    @Override
    public void initData() {
        //viewpager添加页面
        movie_viewpager.setAdapter(new MyPagerAdapter());
        boolean willshow = CacheUtils.getBoolean(mActivity, "willshow");
        if(willshow) {
            CacheUtils.putBoolean(mActivity, "willshow", false);
            movie_viewpager.setCurrentItem(1);
        }
        tablayout_movie_pager.setupWithViewPager(movie_viewpager);
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseDiscoverPager baseDiscoverPager = list.get(position);
            baseDiscoverPager.initData();
            View rootView = baseDiscoverPager.rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
