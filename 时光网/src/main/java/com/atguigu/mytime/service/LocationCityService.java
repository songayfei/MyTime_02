package com.atguigu.mytime.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.atguigu.mytime.Utils.MyApplication;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

import de.greenrobot.event.EventBus;

public class LocationCityService extends Service {
    //定位全局变量
    private LocationService locationService;

    public LocationCityService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startPosition();

    }

    @Override
    public void onDestroy() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onDestroy();
    }

    private void startPosition(){
        locationService = ((MyApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
    }

    /*****
     *
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     *
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getCity()+",");//城市
                sb.append(location.getLongitude()+",");//精度
                sb.append(location.getLatitude());//纬度
                EventBus.getDefault().post(sb);

                MyApplication application = MyApplication.getMyinstance();
                application. latitude = location.getLatitude();
                application.longitude = location.getLongitude();




            }
        }
    };
}
