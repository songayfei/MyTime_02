package com.atguigu.mytime.Utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.atguigu.mytime.view.LoadingDailog;

/**
 * @Author YfSong
 * @Time 21:13
 * Created by YfSong on 2016/4/13.
 */
public class ShowUtile {

    private static LoadingDailog dailog;
    private static Application application;


    public static void showToast(Context context, String showString) {
        Toast.makeText(context, showString, Toast.LENGTH_SHORT).show();

    }

    private static void initLog(Application app) {
        application=app;
    }

    public static void showLoadingDailog(Context context) {
        dailog = new LoadingDailog(context);
        dailog.show();
    }
    public static void dismissLoadingDailog(Context context) {
        dailog.dismiss();
        dailog=null;
    }
    public static void showLog(String tag,String msg ) {
        if(application!=null) {
            Log.e(tag,msg);
        }
    }



}
