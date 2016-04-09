package com.atguigu.mytime.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.atguigu.mytime.Utils.MessageUtils;

public class CheckNetwork extends Service {

    private boolean connected;
    public CheckNetwork() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder(){
            @Override
            public boolean pingBinder() {
                return connected;
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //判断是否联网
        connected = MessageUtils.isConnected(this);
    }

}
