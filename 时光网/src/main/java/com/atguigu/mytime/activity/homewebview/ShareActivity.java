package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;

public class ShareActivity extends Activity {
    private WebView shareWebview;
    private ImageView imShareRollback;
    private ImageView imShareCollect;
    private ImageView imShare;
    private TextView tvCommentCount;
    private void findViews() {
        shareWebview = (WebView)findViewById( R.id.share_webview );
        imShareRollback = (ImageView)findViewById( R.id.im_share_rollback );
        imShareCollect = (ImageView)findViewById( R.id.im_share_collect );
        imShare = (ImageView)findViewById( R.id.im_share );
        tvCommentCount = (TextView)findViewById( R.id.tv_comment_count );

        imShareRollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imShareCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageUtils.showMessage(getApplicationContext(), "收藏失败");
            }
        });

        imShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        findViews();
        init();
    }
    private void init() {
        String url=getIntent().getStringExtra("url");
        shareWebview = (WebView) findViewById(R.id.share_webview);
        shareWebview.getSettings().setJavaScriptEnabled(true);
        shareWebview.loadUrl(url);
    }
}
