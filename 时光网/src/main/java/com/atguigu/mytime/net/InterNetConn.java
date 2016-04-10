package com.atguigu.mytime.net;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.EventBus;

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

        //开始联网
        RequestParams entity = new RequestParams(url);
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //解析Json
                parseChangeJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void parseChangeJson(String result) {
        t = (T) new Gson().fromJson(result, aClass);
        //转换成功发送通知
        EventBus.getDefault().post(t);
    }
}
