package com.atguigu.mytime.net;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * 联网的类
 * Created by Garbled on 3/19/2016.
 */
public class InterNetConn<T> {

    private Class aClass;
    private String url;
    private T t;

    public InterNetConn(Class aClass,String url) {
        this.aClass = aClass;
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

        }

        @Override
        public void onResponse(String response) {
            parseChangeJson(response);
            ////获取数据成功保存json数据
            /*SpUtils.getInitialize(mactivity).saveJson(NetUri.DISCOVER_TOP,response);*/
        }
    }
    private void parseChangeJson(String result) {
        t = (T) new Gson().fromJson(result, aClass);
        //转换成功发送通知
        EventBus.getDefault().post(t);
    }
}
