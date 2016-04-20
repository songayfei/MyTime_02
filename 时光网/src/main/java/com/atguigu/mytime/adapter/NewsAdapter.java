package com.atguigu.mytime.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-4-20.
 * 发现——新闻
 */
public class NewsAdapter extends PagerAdapter implements View.OnClickListener{
    private Context context;
    private ArrayList<String> imagesUrls=new ArrayList<>();
    private ArrayList<String> imagesTitles = new ArrayList<>();
    private TextView icon_name;
    private RelativeLayout rl_news_bottom;
    private RelativeLayout rl_news_top;
    private boolean isShown=true;
    public NewsAdapter(Context context, ArrayList<String> imagesTitles,ArrayList<String> imagesUrls, TextView icon_name, RelativeLayout rl_news_bottom, RelativeLayout rl_news_top) {
        this.context=context;
        this.icon_name=icon_name;
        this.rl_news_bottom=rl_news_bottom;
        this.rl_news_top=rl_news_top;
        this.imagesTitles=imagesTitles;
        this.imagesUrls=imagesUrls;



    }

    @Override
    public int getCount() {
        return imagesUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String name = imagesTitles.get(position);
        String image = imagesUrls.get(position);
        icon_name.setText(name);
        //加载每个item的布局文件
        ImageView imageView = new ImageView(context);
        //给图片设置点击时间的监听
        imageView.setOnClickListener(this);
        Glide.with(context).load(image).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        if(isShown) {
            rl_news_bottom.setVisibility(View.GONE);
            rl_news_top.setVisibility(View.GONE);

            isShown=false;
        }else{

            rl_news_bottom.setVisibility(View.VISIBLE);
            rl_news_top.setVisibility(View.VISIBLE);
            isShown=true;
        }
    }
}
