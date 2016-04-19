package com.atguigu.mytime.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 自定义播放器
 */
public class VideoView  extends io.vov.vitamio.widget.VideoView{

    /**
     * 当我们在布局文件使用该类的时候，当构造这个类的时候Android系统用该构造方法实例化该类
     * @param context
     * @param attrs
     */
    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 在代码中实例化该类的时候，我们人为使用这个构造方法实例化该类
     * @param context
     */
    public VideoView(Context context) {
        super(context);
    }

    /**
     * 重写测量方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 想要设置视频的宽和高
     * @param videoWidth
     * @param videoHeight
     */
    public void setVideoSize(int videoWidth,int videoHeight){

        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = videoWidth;
        params.height = videoHeight;
        setLayoutParams(params);
    }

}
