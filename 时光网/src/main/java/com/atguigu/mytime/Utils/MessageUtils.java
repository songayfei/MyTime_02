package com.atguigu.mytime.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/4/8.
 */
public class MessageUtils {

     public static void showMessage(Context context,String mss){
         Toast.makeText(context,mss,Toast.LENGTH_LONG).show();
    }

    /**
     * 判断是否联网
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            connected = info.isConnected();
        }
        return connected;
    }


}
