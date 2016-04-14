package com.atguigu.mytime.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.pager.DiscoverPager;
import com.atguigu.mytime.pager.HomePager;
import com.atguigu.mytime.pager.PayticketPager;
import com.atguigu.mytime.pager.ShopPager;
import com.atguigu.mytime.pager.UserPager;

import java.util.ArrayList;
import java.util.List;

public  class MainActivity extends FragmentActivity {
    private static final int WHAT_EXIT = 0;
    private RadioGroup rg_main;
    private List<BasePager> pagers;
    public  int position;
    private boolean isExit=true;
    private NetReceiver receiver;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case  WHAT_EXIT:
                    isExit=true;
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        setReceicer();
        initArrView();
    }

    private void initArrView() {
        pagers=new ArrayList<>();
        pagers.add(new HomePager(this));
        pagers.add(new PayticketPager(this));
        pagers.add(new ShopPager(this));
        pagers.add(new DiscoverPager(this));
        pagers.add(new UserPager(this));
        //设置监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_main.check(R.id.rb_home);
    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home:
                    position=0;
                    break;
                case R.id.rb_payticket:
                    position=1;
                    break;
                case R.id.rb_shop:
                    position=2;
                    break;
                case R.id.rb_discover:
                    position=3;
                    break;
                case R.id.rb_user:
                    position=4;
                    break;
            }
            setFragment();
        }
    }

    private void setFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_main,new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                BasePager basePager=getBasePaer();
                if(basePager!=null){
                    return basePager.rootview;
                }
                return null;
            }
        });
        transaction.commit();
    }

    private BasePager getBasePaer(){
        BasePager basePager = pagers.get(position);
        if(basePager!=null&& basePager.isCreate){
            basePager.isCreate=false;
            basePager.initData();
        }
        return basePager;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(isExit){
                MessageUtils.showMessage(this,"再按一次退出");
                isExit=false;
                handler.sendEmptyMessageDelayed(WHAT_EXIT,2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 通过广播监听网络状态
     */
    private void setReceicer() {
        receiver=new NetReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }
    @Override
    protected void onDestroy() {
        //关闭所有子类的内存对象
        for(int i=0;i<pagers.size();i++){
            pagers.get(i).clearEvent();
        }
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        ShopPager shopPager= (ShopPager) pagers.get(2);

        //shopPager.showTitle(event);
        return super.onTouchEvent(event);
    }


}
