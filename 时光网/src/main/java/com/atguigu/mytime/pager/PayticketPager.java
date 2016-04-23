package com.atguigu.mytime.pager;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.base.BaseDiscoverPager;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.pager.payticket.Cinema;
import com.atguigu.mytime.pager.payticket.MyMovie;
import com.atguigu.mytime.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 * 购票
 */
public class PayticketPager extends BasePager  implements View.OnClickListener {


    private TextView tv_city_buyticket;

    private Button btn_title_movie_buyticket;

    public Button btn_title_cinema_buyticket;


    private ImageButton ib_rearch_buyticket;


    public NoScrollViewPager vp_content_buyticket;

    private List<BaseDiscoverPager> data;
    private boolean ismovie = true;
    private boolean isCinema = false;

    public PayticketPager(Activity mactivity) {
        super(mactivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.buyticket_fragment_view, null);
        tv_city_buyticket = (TextView) view.findViewById(R.id.tv_city_buyticket);

        btn_title_movie_buyticket = (Button) view.findViewById(R.id.btn_title_movie_buyticket);
        btn_title_cinema_buyticket = (Button) view.findViewById(R.id.btn_title_cinema_buyticket);
        ib_rearch_buyticket = (ImageButton) view.findViewById(R.id.ib_rearch_buyticket);
        vp_content_buyticket = (NoScrollViewPager) view.findViewById(R.id.vp_content_buyticket);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        data = new ArrayList<>();
        data.add(new MyMovie(mactivity));
        data.add(new Cinema(mactivity));
        setlistener();
        vp_content_buyticket.setAdapter(new MyPagerAdapter());
    }

    @Override
    public void showPger(String[] city_name_id) {

    }


    private void setlistener() {
        tv_city_buyticket.setOnClickListener(this);
        btn_title_movie_buyticket.setOnClickListener(this);
        btn_title_cinema_buyticket.setOnClickListener(this);
        ib_rearch_buyticket.setOnClickListener(this);
        vp_content_buyticket.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                data.get(position).initData();
                if (position == 0) {
                    btn_title_movie_buyticket.performClick();
                } else {
                    btn_title_cinema_buyticket.performClick();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city_buyticket :
                //启动定位页面

                break;
            case R.id.btn_title_movie_buyticket :
                //电影页面
                vp_content_buyticket.setCurrentItem(0);
                btn_title_cinema_buyticket.setTextColor(Color.WHITE);
                btn_title_movie_buyticket.setTextColor(Color.BLUE);
                btn_title_cinema_buyticket.setEnabled(true);
                btn_title_cinema_buyticket.setBackgroundResource(android.R.color.transparent);
                btn_title_movie_buyticket.setEnabled(false);
                btn_title_movie_buyticket.setBackgroundResource(R.drawable.title_bar_movie_selected);
                break;
            case R.id.btn_title_cinema_buyticket :
                //影院页面
                vp_content_buyticket.setCurrentItem(1);
                btn_title_cinema_buyticket.setEnabled(false);
                btn_title_movie_buyticket.setEnabled(true);
                btn_title_movie_buyticket.setBackgroundResource(android.R.color.transparent);
                btn_title_cinema_buyticket.setBackgroundResource(R.drawable.title_bar_cinema_selected);
                btn_title_cinema_buyticket.setTextColor(0xFF4052B5);
                btn_title_movie_buyticket.setTextColor(Color.WHITE);
                break;
            case R.id.ib_rearch_buyticket :
                //搜索页面
                int item = vp_content_buyticket.getCurrentItem();

                if(item==1) {

                }else{

                }


                break;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            data.get(position).initData();
            View rootView = data.get(position).rootView;
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
