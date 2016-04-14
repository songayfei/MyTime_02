package com.atguigu.mytime.net;

import android.app.Activity;
import android.util.Log;

import com.atguigu.mytime.Utils.SpUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * 联网的类
 * Created by Garbled on 3/19/2016.
 */
public class InterNetConn<T>{

    private Class aClass;
    private String url;
    private T t;
    private Activity activity;
    private boolean isCache;
    public InterNetConn(String url,Activity activity,Class clazz,boolean isCache) {
        this.aClass=clazz;
        this.activity=activity;
        this.url = url;
        this.isCache=isCache;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            EventBus.getDefault().post(false);
            Log.e("TAG","网络请求失败");
        }
        @Override
        public void onResponse(String response) {
            Log.e("TAG","网络请求成功");
            if(isCache) {
                SpUtils.getInitialize(activity.getApplicationContext()).saveJson(url, response);
            }
            parseChangeJson(response);//解析json
        }
    }
    private void parseChangeJson(String result) {
        t = (T) new Gson().fromJson(result, aClass);
        //转换成功发送通知
        EventBus.getDefault().post(t);
    }
}

