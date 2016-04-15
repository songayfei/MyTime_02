package com.atguigu.mytime.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.atguigu.mytime.R;

public class WebViewActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webView);

    }
}
