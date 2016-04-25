package com.atguigu.mytime.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.atguigu.mytime.R;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.view.ObservableScrollView;
import com.zf.myzxing.CaptureActivity;

/**
 * Created by Administrator on 2016/4/20.
 */
public class UserPager extends BasePager  implements View.OnClickListener, ObservableScrollView.Callbacks {


    private ObservableScrollView scrollView;


    private Button bt_login_user;


    private Button bt_regist_user;


    private RelativeLayout rl_user_scntwo;

    private RelativeLayout rl_top;

    public UserPager(Activity mactivity) {
        super(mactivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.my_fragment_view,null);
       scrollView = (ObservableScrollView) view.findViewById(R.id.scrollView_obser);
        bt_login_user = (Button) view.findViewById(R.id.bt_login_user);
        bt_regist_user = (Button) view.findViewById(R.id.bt_regist_user);
        rl_user_scntwo = (RelativeLayout) view.findViewById(R.id.rl_user_scntwo);
        rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
        rl_user_scntwo.setOnClickListener(this);
        bt_login_user.setOnClickListener(this);
        bt_regist_user.setOnClickListener(this);
        scrollView.setCallbacks(this);
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(scrollView.getScrollY());
                    }
                });
        return view;
    }
    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void showPger(String[] city_name_id) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_user :
                //mactivity.startActivity(new Intent(mactivity,LoginActivity.class));
                break;
            case R.id.bt_regist_user:

                break;
            case R.id.rl_user_scntwo:
                mactivity.startActivity(new Intent(mactivity, CaptureActivity.class));
                break;
        }
    }

    @Override
    public void onScrollChanged(int scrollY) {
        if(scrollY<0) {
            rl_top.getBackground().setAlpha(0);
        }else {
            if(scrollY<=255) {
                rl_top.getBackground().setAlpha(scrollY);
            }else {
                rl_top.getBackground().setAlpha(255);
            }
        }
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent() {

    }
}
