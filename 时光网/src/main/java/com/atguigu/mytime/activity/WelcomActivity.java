package com.atguigu.mytime.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.entity.AdvListInfo;
import com.atguigu.mytime.net.InterNetConn;
import com.bumptech.glide.Glide;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 主页面
 */
public class WelcomActivity extends Activity {
    private ImageView rl_welcom;
    private Animation animation;
    private boolean value;
    private List<AdvListInfo.AdvListEntity> advList;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        rl_welcom = (ImageView) findViewById(R.id.rl_welcom);
        //判断网络状态
        boolean connected = MessageUtils.isConnected(this);
        EventBus.getDefault().register(this);
        //联网请求广告数据
        new InterNetConn(NetUri.AD_LIST, this, AdvListInfo.class);
        if (!connected) {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("网络错误")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            showLoadDataDialog();
                        }
                    })
                    .show();
        }
        //判断是否进入过引导页面
        value = SpUtils.getInitialize(this).getValue(SpUtils.GUIDE, false);
        initAnim();
        setAnimListener();
    }

    public void onEventMainThread(AdvListInfo advListInfo) {
        advList = advListInfo.getAdvList();
    }

    private void showLoadDataDialog() {
        new AlertDialog.Builder(this)
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


    private void initAnim() {
        animation = AnimationUtils.loadAnimation(this, R.anim.welcom_anim);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        rl_welcom.startAnimation(animation);
    }


    private void setAnimListener() {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (advList != null && advList.size() > 0) {
                    Glide.with(WelcomActivity.this).load(advList.get(0).getUrl()).into(rl_welcom);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (value) {
                                //如果进入过引导页面
                                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                            } else {
                                startActivity(new Intent(WelcomActivity.this, GuideActivity.class));
                            }
                            finish();
                        }
                    }, 5000);
                } else {
                    if (value) {
                        //如果进入过引导页面
                        startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(WelcomActivity.this, GuideActivity.class));
                    }
                    finish();
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


}
