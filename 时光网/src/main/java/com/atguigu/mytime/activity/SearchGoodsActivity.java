package com.atguigu.mytime.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.mytime.Listen.MyTextWatcher;
import com.atguigu.mytime.R;

import java.util.Timer;
import java.util.TimerTask;

public class SearchGoodsActivity extends Activity implements View.OnClickListener {
private EditText tv_title_search;
    private TextView ib_mall_home_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        tv_title_search = (EditText)findViewById(R.id.tv_title_search);
        findViewById(R.id.ib_mall_home_start).setOnClickListener(this);
        findViewById(R.id.ib_mall_home_back).setOnClickListener(this);
        tv_title_search.addTextChangedListener(new MyTextWatcher());


        // 获取编辑框焦点
        tv_title_search.setFocusable(true);
        tv_title_search.setFocusableInTouchMode(true);
        tv_title_search.requestFocus();
        //打开软键盘
//       InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//       imm.showSoftInput(tv_title_search, 0/*InputMethodManager.SHOW_FORCED*/);
       // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) tv_title_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(tv_title_search, 0);
                           }
                       },
                500);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_mall_home_start:
                String text = tv_title_search.getText().toString();
                if(TextUtils.isEmpty(text.trim())) {
                    Toast.makeText(this,"输入为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, MallBaseActivity.class);
                String Url = "http://mall.wv.mtime.cn/Service/callback.mi/ECommerce/SearchGoods.api?keyword="+text+"&pageIndex=1&sf=0&sm=2&topicId=0&movieId=0&roleId=0&categoryId1=0&categoryId2=0&brandId=0&fmin=0&fmax=0&salefid=0&cd=0&t=2016412171575270";
                intent.putExtra("URL",Url);
                startActivity(intent);

                break;
            case R.id.ib_mall_home_back:
                finish();
                break;
        }
    }
}
