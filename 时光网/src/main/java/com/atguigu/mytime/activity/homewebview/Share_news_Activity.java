package com.atguigu.mytime.activity.homewebview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.entity.NewsEntity;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import okhttp3.Call;
import pl.droidsonroids.gif.GifImageView;

public class Share_news_Activity extends Activity {
    private HtmlTextView htv_news_detail_content;
    private ImageView imShareRollback;
    private ImageView imShareCollect;
    private ImageView imShare;
    private TextView tvCommentCount;
    private TextView tv_content_title;
    private TextView tv_issue_time;
    private NewsEntity newsEntity;
    private GifImageView gif_loading;

    private void findViews() {
        imShareRollback = (ImageView)findViewById( R.id.im_share_rollback );
        imShareCollect = (ImageView)findViewById( R.id.im_share_collect );
        imShare = (ImageView)findViewById( R.id.im_share );
        tvCommentCount = (TextView)findViewById( R.id.tv_comment_count );

        tv_issue_time = (TextView)findViewById(R.id.tv_issue_time);
        tv_content_title = (TextView)findViewById(R.id.tv_content_title);
        htv_news_detail_content = (HtmlTextView) findViewById(R.id.htv_news_detail_content);
        gif_loading = (GifImageView)findViewById(R.id.gif_loading);
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
        int id=getIntent().getIntExtra("id",0);
        getNetWorkData(id);
        findViews();
        init();
    }

    private void getNetWorkData(int id) {
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
                    }
                });
    }

    private void init() {
        tv_content_title.setText(newsEntity.getTitle());
        tv_issue_time.setText(newsEntity.getTime() + newsEntity.getSource());
        htv_news_detail_content.setHtmlFromString(newsEntity.getContent(),new HtmlTextView.RemoteImageGetter());
        gif_loading.setVisibility(View.GONE);
    }
}
