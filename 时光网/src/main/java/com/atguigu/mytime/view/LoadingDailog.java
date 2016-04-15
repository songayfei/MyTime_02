package com.atguigu.mytime.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.atguigu.mytime.R;

/**
 * @Author YfSong
 * @Time 19:17
 * Created by YfSong on 2016/4/13.
 */
public class LoadingDailog extends Dialog {
    int layoutRes;//布局文件
    Context context;
    private AnimationDrawable anim;

    public LoadingDailog(Context context) {
        super(context, R.style.add_dialog);
        this.context = context;
    }
    /**
     * 自定义布局的构造方法
     * @param context
     * @param resLayout
     */
    public LoadingDailog(Context context,int resLayout){
        super(context);
        this.context = context;
        this.layoutRes=resLayout;
    }
    /**
     * 自定义主题及布局的构造方法
     * @param context
     * @param theme
     * @param resLayout
     */
    public LoadingDailog(Context context, int theme,int resLayout){
        super(context, theme);
        this.context = context;
        this.layoutRes=resLayout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adv_item);
       /* ImageView loading = (ImageView)findViewById(R.id.loading);

        anim = (AnimationDrawable) loading.getBackground();
        anim.start();*/

    }

    @Override
    public void dismiss() {
        //anim.stop();
        super.dismiss();

    }
}
