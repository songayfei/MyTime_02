package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.LogUtils;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.entity.NewsEntity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class Share_news_Activity extends Activity {
    private static final String  TAG =Share_news_Activity.class.getSimpleName() ;
    private WebView htv_news_detail_content;
    private ImageView imShareRollback;
    private ImageView imShareCollect;
    private ImageView imShare;
    private TextView tvCommentCount;
    private TextView tv_content_title;
    private TextView tv_issue_time;
    private NewsEntity newsEntity;
    private RelativeLayout gif_loading;

    private void findViews() {
        imShareRollback = (ImageView)findViewById( R.id.im_share_rollback );
        imShareCollect = (ImageView)findViewById( R.id.im_share_collect );
        imShare = (ImageView)findViewById( R.id.im_share );
        tvCommentCount = (TextView)findViewById( R.id.tv_comment_count );

        tv_issue_time = (TextView)findViewById(R.id.tv_issue_time);
        tv_content_title = (TextView)findViewById(R.id.tv_content_title);
        htv_news_detail_content = (WebView) findViewById(R.id.htv_news_detail_content);
        gif_loading = (RelativeLayout)findViewById(R.id.gif_loading);
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

         setContentView(R.layout.activity_share_01);

        getNetWorkData();
        findViews();
    }

    private void getNetWorkData() {
        int id=getIntent().getIntExtra("id",0);
        LogUtils.d(TAG,id+"");
        String url= NetUri.NEWS_ITEM_URI+id;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        newsEntity = new Gson().fromJson(response, NewsEntity.class);
                        init();
                    }
                });
    }

    private void init() {
        tv_content_title.setText(newsEntity.getTitle());
        tv_issue_time.setText(newsEntity.getTime() + newsEntity.getSource());
        WebSettings settings = htv_news_detail_content.getSettings();
        settings.setJavaScriptEnabled(true);
        htv_news_detail_content.loadDataWithBaseURL(null, newsEntity.getContent(), "text/html", "utf-8", null);
        settings.setSupportZoom(true);
        /*Spanned sp = Html.fromHtml(newsEntity.getContent(), new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                InputStream is = null;
                ImageView imageView = new ImageView(getApplicationContext());
                try {
                    x.image().bind(imageView, "http://img31.mtime.cn/CMS/News/2016/04/22/165146.30733625_620X620.jpg");
                  *//*  is = (InputStream) new URL("http://img31.mtime.cn/CMS/News/2016/04/22/165146.30733625_620X620.jpg").getContent();
                    Drawable d = Drawable.createFromStream(is, "src");
                    d.setBounds(0, 0, d.getIntrinsicWidth(),
                            d.getIntrinsicHeight());
                    is.close();*//*
                    return imageView.getDrawable();
                } catch (Exception e) {
                    return null;
                }
            }
        },null);
        htv_news_detail_content.setText(sp);*/
        /*htv_news_detail_content.setText(Html.fromHtml(newsEntity.getContent(), new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                InputStream is = null;
                try {
                    is = (InputStream) new URL(source).getContent();
                    Drawable d = Drawable.createFromStream(is, "src");
                    d.setBounds(0, 0, d.getIntrinsicWidth(),
                            d.getIntrinsicHeight());
                    is.close();
                    return d;
                } catch (Exception e) {
                    return null;
                }
            }
        },null));*/
       // htv_news_detail_content.setHtmlFromString(newsEntity.getContent(), new HtmlTextView.RemoteImageGetter());

       gif_loading.setVisibility(View.GONE);
    }
}
