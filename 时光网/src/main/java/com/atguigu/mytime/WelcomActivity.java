package com.atguigu.mytime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

/**
 * 主页面
 */
public class WelcomActivity extends Activity {
    private RelativeLayout rl_welcom;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        rl_welcom = (RelativeLayout)findViewById(R.id.rl_welcom);
        initAnim();
        setAnimListener();
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
                startActivity(new Intent(WelcomActivity.this,GuideActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
