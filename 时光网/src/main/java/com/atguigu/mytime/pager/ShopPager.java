package com.atguigu.mytime.pager;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.base.BasePager;

/**
 * Created by Administrator on 2016/4/8.
 * 商城
 */
public class ShopPager extends BasePager implements View.OnClickListener {

    private TextView textView;
    private ListView lvMallPager;
    private TextView tvTitleSearch;

    public ShopPager(Activity mactivity) {
        super(mactivity);
    }

    @Override
    public View initView() {

        View view = View.inflate(mactivity,R.layout.mall_base_pager,null);
        lvMallPager = (ListView) view.findViewById(R.id.lv_mall_pager);
        view.findViewById(R.id.ib_mall_home_scan).setOnClickListener(this);
        tvTitleSearch = (TextView) view.findViewById(R.id.tv_title_search);
        view.findViewById(R.id.ib_mall_home_cart).setOnClickListener(this);

        TabLayout bLayout = new TabLayout(mactivity);

        new PagerAdapter(){

            @Override
            public CharSequence getPageTitle(int position) {

                new CharSequence() {
                    @Override
                    public int length() {
                        return 0;
                    }

                    @Override
                    public char charAt(int index) {
                        return 0;
                    }

                    @Override
                    public CharSequence subSequence(int start, int end) {
                        Drawable image = mactivity.getResources().getDrawable(R.drawable.mall_comment_edit);

                        ImageView imageView = new ImageView(mactivity);

                        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
                        // Replace blank spaces with image icon
                        String title="";

                        SpannableString sb = new SpannableString(" ");
                        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
                        sb.setSpan(imageSpan, title.length(), title.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        return sb;
                    }

                    @NonNull
                    @Override
                    public String toString() {
                        return null;
                    }
                };
                return super.getPageTitle(position);
            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }
        };


        return view;
    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("商城页面");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_mall_home_scan:
                //TODO implement
                break;
            case R.id.ib_mall_home_cart:
                //TODO implement
                break;
        }
    }
}
