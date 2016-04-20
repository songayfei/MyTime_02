package com.atguigu.mytime.Utils;

import android.app.Application;
import android.app.Service;
import android.os.Vibrator;

import com.atguigu.mytime.service.LocationService;
import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * Created by Administrator on 2016/4/8.
 */
public class MyApplication extends Application {
    //百度定位服务
    public LocationService locationService;
    public Vibrator mVibrator;

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
        /***
         * 初始化定位sdk，建议在Application中创建
         */

        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }
}
