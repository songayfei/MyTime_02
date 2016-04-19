package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.atguigu.mytime.R;

public class HomeWebViewActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        init();
    }

    private void init() {
        String url=getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }
}
