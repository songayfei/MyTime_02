package com.atguigu.mytime.base;

import android.app.Activity;
import android.view.View;

/**
 * 购票页面基类
 */
public abstract class BaseDiscoverPager {
    public Activity mActivity;
    public View rootView;

    public BaseDiscoverPager(Activity activity) {
        this.mActivity = activity;
        this.rootView = initView();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String url ;

    /**
     * 强制子类实现的方法
     * @return
     */
    public abstract View initView();

    /**
     * 子类加载数据的方法
     */
    public void initData(){}


}
