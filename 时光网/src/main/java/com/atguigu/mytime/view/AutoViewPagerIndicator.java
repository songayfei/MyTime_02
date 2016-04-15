package com.atguigu.mytime.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;

/**
 *
 * 配合 viewpager 使用  显示 小圆点
 * @Author YfSong
 * @Time 18:59
 * Created by YfSong on 2016/4/14.
 */
public class AutoViewPagerIndicator extends LinearLayout{

    private int count;
    private int DrawableRes=R.drawable.viewpager_point;


    public AutoViewPagerIndicator(Context context) {
        super(context);
    }

    public AutoViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 小圆点的样式 默认空心选择
     * select  <item android:state_enabled="。。。">
     *
     * @param resId
     */
    public void setImageResource(@DrawableRes int resId) {
        DrawableRes=resId;
    }
        public void setViewPager(ViewPager view, int size){
        int li= MessageUtils.dip2px(getContext(), 5);
        count = size;
        Log.e("count=====",count+"");
        removeAllViews();
        for(int i=0;i< count;i++){
            ImageView imageView=new ImageView(getContext());
            imageView.setImageResource(DrawableRes);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(li,li);
            if (i != 0) {
                params.leftMargin = li;
                imageView.setEnabled(true);
            } else {
                imageView.setEnabled(false);
            }
            this.addView(imageView,params);
        }

        view.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int setposition;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //得到真实下标

            position = position%count;
            Log.e("count=====",position+"");
            getChildAt(setposition).setEnabled(true);
            getChildAt(position).setEnabled(false);
            setposition =position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}
