package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.mytime.activity.MainActivity;
import com.atguigu.mytime.activity.WebViewActivity;
import com.atguigu.mytime.entity.HomeShopInfo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016/4/10.
 * 顶部Viewpager
 */
public class TopViewPagerAdapter extends PagerAdapter {
    private List<HomeShopInfo.TopPostersEntity> topPosters;
    private Activity mActivity;

    public TopViewPagerAdapter(List<HomeShopInfo.TopPostersEntity> topPosters, Activity mActivity) {
        this.topPosters = topPosters;
        this.mActivity = mActivity;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % topPosters.size();
        ImageView imageView = new ImageView(mActivity);
        //联网加载图片
        Glide.with(mActivity).load(topPosters.get(position).getImg()).into(imageView);
        imageView.setClickable(true);
        imageView.setTag(position);
        imageView.setOnClickListener(new MyImgOnClickListener());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    class MyImgOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
                if(position==topPosters.size()-1) {
                    MainActivity mainActivity = (MainActivity) mActivity;
                    mainActivity.position = topPosters.size() - 1;
                }else {
                    Intent intent = new Intent(mActivity, WebViewActivity.class);
                    intent.putExtra("url", topPosters.get(position).getUrl());
                    mActivity.startActivity(intent);
                }
            }


    }
}
