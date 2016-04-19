package com.atguigu.mytime.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.view.RoundImageView;
import com.bumptech.glide.Glide;

public class AdvListActivity extends Activity {
    private ImageView imClose;
    private RoundImageView imAdv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_list);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高

        WindowManager.LayoutParams p = getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.88);   //高度设置为屏幕的1.0
        p.width = (int) (d.getWidth() * 1);    //宽度设置为屏幕的0.8
        p.alpha = 1.0f;      //设置本身透明度
        p.dimAmount = 0.0f;      //设置黑暗度

        getWindow().setAttributes(p);
        getWindow().setGravity(Gravity.TOP);//设置生效
        init();
        showDavListView();
    }

    private void init() {
        imClose = (ImageView)findViewById(R.id.im_close);
        imAdv = (RoundImageView)findViewById(R.id.im_adv);
    }
    private void showDavListView(){
        String advertisement = getIntent().getStringExtra("advertisementURL");
        Glide.with(this).load(advertisement).into(imAdv);
        //点击广告图片
        imAdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击关闭广告
        imClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
