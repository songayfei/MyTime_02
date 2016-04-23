package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.atguigu.mytime.R;

public class Home_movie_Activity extends Activity {
    private WebView movie_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_movie_);
        movie_webview = (WebView) findViewById(R.id.movie_webview);
        initData();
    }

    private void initData() {
        String url=getIntent().getStringExtra("url");
        movie_webview = (WebView) findViewById(R.id.movie_webview);
        movie_webview.getSettings().setJavaScriptEnabled(true);
        movie_webview.loadUrl(url);
    }
}
