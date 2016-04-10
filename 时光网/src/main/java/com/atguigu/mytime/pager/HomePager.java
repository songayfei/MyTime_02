package com.atguigu.mytime.pager;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.base.BasePager;

import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2016/4/7.
 * 主页面
 */
public class HomePager extends BasePager {
    private ImageView im_load;
    private GifImageView gif_load;
    private NetReceiver receiver;
    public HomePager(Activity mactivity) {
        super(mactivity);
    }

    @Override
    public View initView() {
        View view=View.inflate(mactivity, R.layout.home_pager,null);
        im_load= (ImageView) view.findViewById(R.id.im_load);
        gif_load= (GifImageView) view.findViewById(R.id.gif_load);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
    }

}
