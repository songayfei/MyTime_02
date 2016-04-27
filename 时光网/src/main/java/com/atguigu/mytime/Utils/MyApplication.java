package com.atguigu.mytime.Utils;

import android.app.Application;
import android.app.Service;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.atguigu.mytime.service.LocationService;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.Timer;

/**
 * Created by Administrator on 2016/4/8.
 */
public class MyApplication extends Application {
    //百度定位服务
    public LocationService locationService;
    public LocationClient mLocationClient;
    public Vibrator mVibrator;
    //操作数据库
    private DbManager.DaoConfig daoConfig;

    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    private static MyApplication myinstance;
    public static double latitude;
    public static double longitude;

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

        refWatcher = LeakCanary.install(this);
        Timer timer = new Timer();

        mLocationClient = new LocationClient(this.getApplicationContext());

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        /**
         * 初始化数据库
         */
       daoConfig=new DbManager.DaoConfig()
       .setDbName("city_data")//数据库名
       .setDbVersion(1)//版本号
               //数据库升级监听
       .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
           @Override
           public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

           }
       });
    }
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context
                .getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(MyApplication.class.getSimpleName(), "onLowMemory and clear cache.");

    }

    @Override
    public void onTrimMemory(int level) {
        Log.d(MyApplication.class.getSimpleName(), "onTrimMemory and level is " + level);
        if (level >= ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {

        }
        super.onTrimMemory(level);
    }
}
