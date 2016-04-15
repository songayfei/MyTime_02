package com.atguigu.mytime.net;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.view.LoadingDailog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * 联网的类
 * Created by Garbled on 3/19/2016.
 */
public class OkhttpUtils2<T>{

    private Class aClass;
    private String url;
    private T t;
    private Activity activity;
    private static LoadingDailog dailog;
    private ImageView errow;
    /**
     * url 网络连接  activity  上下文    clazz  实体类
     * @param url 网络连接
     * @param activity  上下文
     * @param clazz  实体类
     * url 网络连接  activity  上下文    clazz  实体类
     */
    public OkhttpUtils2(String url,Activity activity,Class clazz) {
        this.aClass=clazz;
        this.activity=activity;
        this.url = url;
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
    public OkhttpUtils2(String url,Activity activity,Class clazz,boolean showLoading) {
        this.aClass=clazz;
        this.activity=activity;
        this.url = url;
        if(showLoading) {
            if(dailog==null) {
                dailog = new LoadingDailog(activity);
            }
            if(!dailog.isShowing()) {
                dailog.show();
            }

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
    public OkhttpUtils2(String url,Activity activity,Class clazz,boolean showLoading,ImageView e) {
        this(url, activity, clazz, showLoading);
        errow = e;
    }
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            if(dailog!=null) {
                dailog.dismiss();
                dailog=null;

            } if(errow!=null) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(errow!=null) {
                            errow.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
            //ShowUtile.showToast(activity,"联网请求失败，请检查网络...");

            Log.e("TAG","网络请求失败");
        }
        @Override
        public void onResponse(String response) {
            Log.e("TAG","网络请求成功");
            Log.e("TAG", response);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                   if(errow!=null) {
                       errow.setVisibility(View.GONE);
                   }

                }
            });
            if(dailog!=null) {
                dailog.dismiss();
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

