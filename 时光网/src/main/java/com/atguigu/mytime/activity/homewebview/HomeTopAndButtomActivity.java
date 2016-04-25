package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;

public class HomeTopAndButtomActivity extends Activity implements View.OnClickListener {
    private WebView webView;
    private ImageView imPre;
    private ImageView imNext;
    private ImageView imOpen;
    private ImageView imRefresh;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_top_and_buttom);
        findViews();
        init();
    }

    /**
     * 初始化
     */
    private void findViews() {
        imPre = (ImageView)findViewById( R.id.im_pre );
        imNext = (ImageView)findViewById( R.id.im_next );
        imOpen = (ImageView)findViewById( R.id.im_open );
        imRefresh = (ImageView)findViewById( R.id.im_refresh );
        webView = (WebView) findViewById(R.id.webView);

        imPre.setOnClickListener( this );
        imNext.setOnClickListener(this);
        imOpen.setOnClickListener(this);
        imRefresh.setOnClickListener(this);
    }

    private void init() {
        url = getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        if ( v == imPre ) {
            MessageUtils.showMessage(this,"已经是第一页了");
        } else if ( v == imNext ) {
            MessageUtils.showMessage(this,"已经是最后一页了");
        } else if ( v == imOpen ) {
            //通过隐式意图启动浏览器
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } else if ( v == imRefresh ) {
            webView.loadUrl(url);
        }
    }
    public void onBack(View v) {
        finish();
    }
}
