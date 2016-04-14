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
    private LoadingDailog dailog;
    private ImageView errow;
    /**
     * url 网络连接  activity  上下文    clazz  实体类
     * @param url 网络连接
     * @param activity  上下文
     * @param clazz  实体类
     * url 网络连接  activity  上下文    clazz  实体类
     */
    public InterNetConn(String url,Activity activity,Class clazz) {
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
    /**
     *url 网络连接  activity  上下文    clazz  实体类
     * @param url 网络连接
     * @param activity  上下文
     * @param clazz  实体类
     * @param showLoading  是否显示正在加载
     *
     */
    public InterNetConn(String url,Activity activity,Class clazz,boolean showLoading) {
        this.aClass=clazz;
        this.activity=activity;
        this.url = url;
        if(showLoading) {
            dailog = new LoadingDailog(activity);
            dailog.show();
        }
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    /**
     * url 网络连接  activity  上下文    clazz  实体类
     * @param url 网络连接
     * @param activity  上下文
     * @param clazz  实体类
     * @param showLoading  是否显示正在加载
     * @param e 加载错误图片 布局中隐藏的
     */
    public InterNetConn(String url,Activity activity,Class clazz,boolean showLoading,ImageView e) {
        this(url, activity, clazz, showLoading);
        errow = e;
    }
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            EventBus.getDefault().post(false);
            if(dailog!=null) {
                dailog.dismiss();

            } if(errow!=null) {
                errow.setVisibility(View.VISIBLE);
            }
            ShowUtile.showToast(activity,"联网请求失败，请检查网络...");

            Log.e("TAG","网络请求失败");
        }
        @Override
        public void onResponse(String response) {
            Log.e("TAG","网络请求成功");
            if(isCache) {
                SpUtils.getInitialize(activity.getApplicationContext()).saveJson(url, response);
            }
            Log.e("TAG", response);
            if(dailog!=null) {
                dailog.dismiss();

            }
            if(errow!=null) {
                errow.setVisibility(View.GONE);
            }

            SpUtils.getInitialize(activity.getApplicationContext()).saveJson(url,response);
            parseChangeJson(response);//解析json
        }
    }
    private void parseChangeJson(String result) {
        t = (T) new Gson().fromJson(result, aClass);
        //转换成功发送通知
        Log.e("TAG","EventBus.getDefault().post(t);");
        EventBus.getDefault().post(t);
    }
}

