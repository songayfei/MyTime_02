package com.atguigu.mytime.net;

import android.content.Context;

import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.entity.OneGetData;
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
    private Context context;
    private boolean isCache;
    private OneGetData isconnection;


    /**
     * 联网请求  该构造方法只负责 联网请求数据 不涉及数据的缓存
     * 通过EventBus 传递数据 包括联网请求失败
     * @param url 地址
     * @param context 上下文
     * @param clazz 对象
     */
    public InterNetConn(String url,Context context,Class clazz) {
        this.aClass=clazz;
        this.context=context;
        this.url = url;
        isconnection=new OneGetData(false);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback01());
    }
    /**
     * 该回调不涉及缓存数据
     */
    class MyStringCallback01 extends StringCallback {
        @Override
        public void onError(Call call, Exception e) {
            //联网请求失败
            EventBus.getDefault().post(isconnection);
        }
        /**
         * 请求数据成功
         * @param response
         */
        @Override
        public void onResponse(String response) {
            //解析Json数据
            parseChangeJson(response);//解析json
        }
    }

    /**
     * 该构造方法涉及 请求数据的缓存
     * @param isCache 为true 表示要对数据进行缓存
     * @param url 地址
     * @param context 上下文
     * @param clazz 对象
     */
    public InterNetConn(boolean isCache,String url,Context context,Class clazz) {
        this.aClass=clazz;
        this.context=context;
        this.url = url;
        this.isCache=isCache;
        isconnection=new OneGetData(false);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback02());
    }
    /**
     * 该回调涉及缓存数据
     */
    class MyStringCallback02 extends StringCallback {
        @Override
        public void onError(Call call, Exception e) {
            //联网请求失败
            EventBus.getDefault().post(isconnection);
        }
        /**
         * 请求数据成功
         * @param response
         */
        @Override
        public void onResponse(String response) {
            if(isCache){
                //以url作为文件名进行缓存
                SpUtils.getInitialize(context.getApplicationContext()).saveJson(url, response);
            }
            //解析Json数据
            parseChangeJson(response);//解析json
        }
    }
    private void parseChangeJson(String result) {
        t = (T) new Gson().fromJson(result, aClass);
        //转换成功发送通知
        EventBus.getDefault().post(t);
    }
}

