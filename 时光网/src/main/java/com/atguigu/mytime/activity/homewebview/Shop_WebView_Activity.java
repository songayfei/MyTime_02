package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;

public class Shop_WebView_Activity extends Activity {
    private WebView wvShop;
    private ImageView imShareRollback;
    private ImageView imShareCollect;
    private ImageView imShare;
    private TextView tvCommentCount;
    private void findViews() {
        wvShop = (WebView)findViewById( R.id.wv_shop );
        imShareRollback = (ImageView)findViewById( R.id.im_share_rollback );
        imShareCollect = (ImageView)findViewById( R.id.im_share_collect );
        imShare = (ImageView)findViewById( R.id.im_share );
        tvCommentCount = (TextView)findViewById( R.id.tv_comment_count );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__web_view_);
        initData();
    }

    private void initData() {
        String url = getIntent().getStringExtra("url");
        wvShop = (WebView) findViewById(R.id.wv_shop);
        wvShop.getSettings().setJavaScriptEnabled(true);
        wvShop.loadUrl(url);
    }
}
