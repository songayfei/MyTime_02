package com.atguigu.mytime.Receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.atguigu.mytime.entity.OneGetData;

import de.greenrobot.event.EventBus;

public class NetReceiver extends BroadcastReceiver {

   private  OneGetData isconnected;
    public NetReceiver() {
        isconnected=new OneGetData(false);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(!gprs.isConnected()&&!wifi.isConnected()){
            EventBus.getDefault().post(isconnected);
            new AlertDialog.Builder(context)
                        .setTitle("提示")
                        .setMessage("网络错误")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                showLoadDataDialog(context);
                            }
                        })
                        .show();
        }

    }
    private void showLoadDataDialog(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("加载数据失败,请稍候重试....")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
