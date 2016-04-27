package com.atguigu.mytime.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.adapter.NewsImagesAdapter;
import com.atguigu.mytime.entity.HomeListViewInfo;
import com.atguigu.mytime.entity.NewsEntity;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;
import uk.co.senab.photoview.PhotoView;

public class NewsImageActivity extends Activity {
    private static final int WHAT_ISSHOW = 1;
    private static final int WHAT_LAST = 2;
    private static final int WHAT_NEXT = 3;
    private ImageView imShareRollback;
    private TextView tvImageCount;
    private ImageView imShareCollect;
    private ImageView imShare;
    private ScrollView sllNewsContent;
    private ViewPager vpNewsTop;
    private ImageView imLast;
    private ImageView imNext;
    private TextView tvImgName;
    private TextView tvNewsContent;
    private ViewPager vpNews;
    private RelativeLayout rlNewsContent;
    private TextView tvNewsTitle;
    private TextView tvImgContent;
    private TextView tvCheckAll;
    private TextView tvCommentCount;
    private TextView tvKnown;
    private FrameLayout fl_news_images;
    private FrameLayout fl_ALL_images;

    private TextView tv_news_title01;
    private TextView tv_img_content01;
    private RelativeLayout rl_img_pager;
    private ImageView im_all_images;
    private GridView gv_img;

    private boolean isshow=false;

    private ArrayList<HomeListViewInfo.DataEntity.ImagesEntity> images;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case WHAT_LAST:
                    int last = vpNewsTop.getCurrentItem() - 1;
                    if(last<0) {
                        last=images.size();
                    }
                    vpNewsTop.setCurrentItem(last);
                    break;
                case  WHAT_NEXT:
                    int next = vpNewsTop.getCurrentItem() + 1;
                    if(next>images.size()) {
                        next=0;
                    }
                    vpNewsTop.setCurrentItem(next);
                    break;
            }
        }
    };

    private ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tvImageCount.setText(position+"/"+images.size());
            tvImgContent.setText(images.get(position).getDesc());

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_image);
        findViews();
        initData();
        getNetWork();
    }

    private void getNetWork() {
        int id = getIntent().getIntExtra("id", 0);
        String url= NetUri.NEWS_ITEM_URI+id;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        NewsEntity newsEntity = new Gson().fromJson(response, NewsEntity.class);
                        init(newsEntity);
                    }
                });
    }

    private void init(NewsEntity newsEntity) {
        tv_news_title01.setText(newsEntity.getTitle());
        tv_img_content01.setText(newsEntity.getTime()+newsEntity.getSource());
        tvNewsContent.setText(Html.fromHtml(newsEntity.getContent()));
        tvNewsContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_img_pager.setVisibility(View.GONE);
            }
        });
    }

    private void initData() {
        images = (ArrayList<HomeListViewInfo.DataEntity.ImagesEntity>) getIntent().getSerializableExtra("images");
        String title = getIntent().getStringExtra("title");
        vpNews.setAdapter(new NewsImageAdapter());
        vpNewsTop.setAdapter(new NewsImageAdapter());
        tvNewsTitle.setText(title);

        gv_img.setAdapter(new NewsImagesAdapter<>(getApplicationContext(), images));
        //viewpager状态改变监听
        vpNews.addOnPageChangeListener(listener);
        vpNewsTop.addOnPageChangeListener(listener);
        gv_img.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fl_ALL_images.setVisibility(View.GONE);
                fl_news_images.setVisibility(View.VISIBLE);
                sllNewsContent.setVisibility(View.GONE);
                vpNews.setCurrentItem(position);
            }
        });

    }

    private void findViews() {
        imShareRollback = (ImageView)findViewById( R.id.im_share_rollback );
        tvImageCount = (TextView)findViewById( R.id.tv_image_count );
        imShareCollect = (ImageView)findViewById( R.id.im_share_collect );
        imShare = (ImageView)findViewById( R.id.im_share );
        sllNewsContent = (ScrollView)findViewById( R.id.sll_news_content );
        vpNewsTop = (ViewPager)findViewById( R.id.vp_news_top );
        imLast = (ImageView)findViewById( R.id.im_last );
        imNext = (ImageView)findViewById( R.id.im_next );
        tvImgName = (TextView)findViewById( R.id.tv_img_name );
        tvNewsContent = (TextView)findViewById( R.id.tv_news_content );
        vpNews = (ViewPager)findViewById( R.id.vp_news );
        rlNewsContent = (RelativeLayout)findViewById( R.id.rl_news_content );
        tvNewsTitle = (TextView)findViewById( R.id.tv_news_title );
        tvImgContent = (TextView)findViewById( R.id.tv_img_content );
        tvCheckAll = (TextView)findViewById( R.id.tv_check_all );
        tvCommentCount = (TextView)findViewById( R.id.tv_comment_count );
        tvKnown = (TextView)findViewById( R.id.tv_known );
        fl_news_images = (FrameLayout) findViewById(R.id.fl_news_images);
        tv_news_title01 = (TextView) findViewById(R.id.tv_news_title01);
        tv_img_content01 = (TextView) findViewById(R.id.tv_img_content01);
        rl_img_pager = (RelativeLayout) findViewById(R.id.rl_img_pager);
        im_all_images = (ImageView)findViewById(R.id.im_all_images);
        gv_img = (GridView)findViewById(R.id.gv_img);
        fl_ALL_images = (FrameLayout)findViewById(R.id.fl_ALL_images);
        //所有的图片
        im_all_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_ALL_images.setVisibility(View.VISIBLE);
                fl_news_images.setVisibility(View.GONE);
                sllNewsContent.setVisibility(View.GONE);
            }
        });
        imLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(WHAT_LAST);
            }
        });
         imNext.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 handler.sendEmptyMessage(WHAT_NEXT);
             }
         });
        //切换到网页版
        tvCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fl_news_images.setVisibility(View.GONE);
                sllNewsContent.setVisibility(View.VISIBLE);
                fl_ALL_images.setVisibility(View.GONE);
            }
        });
        //回退
        imShareRollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //收藏
        imShareCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageUtils.showMessage(getApplicationContext(), "收藏失败");
            }
        });
        //分享
        imShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    class NewsImageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(getApplicationContext()).load(images.get(position).getUrl1()).into(photoView);
            photoView.setOnClickListener(new PagerPhotoViewOnClickListener());
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        class PagerPhotoViewOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                if(rlNewsContent.isShown()){
                    rlNewsContent.setVisibility(View.GONE);
                }else {
                    rlNewsContent.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
