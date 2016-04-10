package com.atguigu.mytime.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.Utils.SpUtils;

/**
 * 主页面
 */
public class WelcomActivity extends Activity {
    private RelativeLayout rl_welcom;
    private Animation animation;
    private boolean value;
    private NetReceiver receiver;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isNetwork = service.pingBinder();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    private boolean isNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        rl_welcom = (RelativeLayout) findViewById(R.id.rl_welcom);
        /*Intent intent = new Intent(this, CheckNetwork.class);

        bindService(intent, conn, Context.BIND_AUTO_CREATE);//绑定服务
        startService(intent);//启动服务
        setReceicer();//网络状态监听*/
        setReceicer();
        //判断是否进入过引导页面
        value = SpUtils.getInitialize(this).getValue(SpUtils.GUIDE, false);
        initAnim();
        setAnimListener();
    }

    /**
     * 通过广播监听网络状态
     */
    private void setReceicer() {
        receiver=new NetReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,filter);
    }


    private void initAnim() {
        animation = AnimationUtils.loadAnimation(this, R.anim.welcom_anim);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        rl_welcom.startAnimation(animation);
        //当前手机没有网络
        if (!isNetwork) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("网络错误")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showLoadDataDialog();
                        }
                    })
                    .show();
        }
    }

    private void showLoadDataDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("加载数据失败,请稍候重试....")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private void setAnimListener() {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (value) {
                    //如果进入过引导页面
                    startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(WelcomActivity.this, GuideActivity.class));
                }
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);//广播解注册
        super.onDestroy();
    }
}
