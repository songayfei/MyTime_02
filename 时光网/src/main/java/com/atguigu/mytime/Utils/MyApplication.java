package com.atguigu.mytime.Utils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/4/8.
 */
public class MyApplication extends Application {
    private static MyApplication myinstance;

    public static MyApplication getMyinstance() {
        return myinstance;
    }

    public static void setMyinstance(MyApplication myinstance) {
        MyApplication.myinstance = myinstance;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        setMyinstance(this);//获取app实例
    }
}
