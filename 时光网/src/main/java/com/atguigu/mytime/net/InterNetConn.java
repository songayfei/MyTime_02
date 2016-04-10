package com.atguigu.mytime.net;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * 联网的类
 * Created by Garbled on 3/19/2016.
 */
public class InterNetConn{

    private String url;

    public InterNetConn(String url) {
        this.url = url;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            Log.e("TAG","网络请求失败");
        }
        @Override
        public void onResponse(String response) {
            Log.e("TAG","网络请求成功");
            EventBus.getDefault().post(response);
        }
    }

}

