package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.atguigu.mytime.R;

public class HomeTopAndButtomActivity extends Activity implements View.OnClickListener {
    private WebView webView;
    private ImageButton imPre;
    private ImageButton imNext;
    private ImageButton imOpen;
    private ImageButton imRefresh;
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
        webView = (WebView)findViewById( R.id.webView );
        imPre = (ImageButton)findViewById( R.id.im_pre );
        imNext = (ImageButton)findViewById( R.id.im_next );
        imOpen = (ImageButton)findViewById( R.id.im_open );
        imRefresh = (ImageButton)findViewById( R.id.im_refresh );
        webView = (WebView) findViewById(R.id.webview);

        imPre.setOnClickListener( this );
        imNext.setOnClickListener(this);
        imOpen.setOnClickListener(this);
        imRefresh.setOnClickListener(this);
    }

    private void init() {
        String url=getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        if ( v == imPre ) {
            // Handle clicks for imPre
        } else if ( v == imNext ) {
            // Handle clicks for imNext
        } else if ( v == imOpen ) {
            // Handle clicks for imOpen
        } else if ( v == imRefresh ) {
            // Handle clicks for imRefresh
        }
    }
}
