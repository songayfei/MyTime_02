package com.atguigu.mytime.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.adapter.NewsAdapter;
import com.atguigu.mytime.adapter.PopupwindowAdapter;
import com.atguigu.mytime.view.HorizontalListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.DensityUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by Administrator on 16-4-19.
 * 新闻详情
 */
public class NewsActivity extends Activity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final int DOWN_LOAD_FAILUES = 2;
    private static final int DOWN_LOAD_SUCCESS = 1;
    private RelativeLayout rl_news_bottom;//底部
    private RelativeLayout rl_news_top;//头部
    private LinearLayout ll_viewpager;//三张图片类型的
    private ViewPager viewpager_news;
    private ScrollView scrollView_news;//html5网页
    private WebView news_webview;//webview
    private ImageView news_left;//点击返回原来的页面
    private ImageView news_login;//点击进入登陆界面
    private ImageView news_share;//点击分享
    private ImageButton comment;//点击进入评论界面
    private TextView comment_count;//评论的数量
    private TextView comment_search;//点击显示新闻详情
    private PopupWindow popupWindow;
    private View popView;
    private LayoutInflater mlayoutInflate;//布局加载器
    private String itenUrl;//每个item的详细地址
    private int type;//每个item的类型
    private String content;
    private int commentCount;//评论的数量
    private String time;//电影的时间
    private String title;//电影名称
    private JSONArray relations;//图片的集合
    private WebSettings setting;
    private TextView icon_title;
    private TextView icon_name;
    private TextView icon_desc;//点击查看详细信息
    private RelativeLayout rl_webview;
    private TextView icon_th;
    private LinearLayout ll_title;
    private ArrayList<String> imagesUrls = new ArrayList<>();
    private ArrayList<String> imagesTitles = new ArrayList<>();
    private ArrayList<String> popListimageUrls = new ArrayList<>();
    private ArrayList<String> popListimageName = new ArrayList<>();
    private int year;
    private int rating;
    private WebView webView;
    private ImageButton pic_imglist;
    private ImageButton pic_download;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
           File file= (File) msg.obj;
            switch (msg.what) {
                case DOWN_LOAD_SUCCESS :
                    MessageUtils.showMessage(NewsActivity.this,"下载图片成功！");
                    //发送一个广播，告诉系统更新相册
                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri = Uri.fromFile(file);
                    intent.setData(uri);
                    NewsActivity.this.sendBroadcast(intent);
                    break;
                case DOWN_LOAD_FAILUES:
                    MessageUtils.showMessage(NewsActivity.this,"下载图片失败！");
                    break;

            }
        }
    };

    @Override
    protected void
    onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        webView = new WebView(NewsActivity.this);
        setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setUseWideViewPort(true);
        initView();
        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {
        itenUrl = getIntent().getStringExtra("itemUrl");
        //联网请求数据
        getDataFromNet();

    }

    /**
     * 联网请求数据
     */
    private void getDataFromNet() {
        OkHttpUtils.get().url(itenUrl).build().execute(new NewStringCallback());
    }

    /**
     * viewpager状态改变的回调
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        icon_th.setText((position + 1) + "/" + imagesUrls.size());
        //改变名称

        icon_name.setText(imagesTitles.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class NewStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            MessageUtils.showMessage(NewsActivity.this, "联网请求数据失败！");
        }

        @Override
        public void onResponse(String response) {
            //请求数据成功解析数据
            processData(response);
        }
    }

    /*
    解析数据
     */
    private void processData(String response) {
        parseJsonData(response);//解析数据成功
        //装配数据
        comment_count.setText("" + commentCount);
        setting = news_webview.getSettings();
        //设置webview支持javaScrip
        setting.setDefaultFontSize(30);
        setting.setJavaScriptEnabled(true);
        setting.setUseWideViewPort(true);
        icon_th.setText(1 + "/" + imagesUrls.size());
        switch (type) {
            case 0://有相关电影人
                scrollView_news.setVisibility(View.VISIBLE);
                ll_viewpager.setVisibility(View.GONE);
                icon_th.setVisibility(View.GONE);
                ll_title.setVisibility(View.GONE);
                pic_imglist.setVisibility(View.GONE);
                pic_download.setVisibility(View.GONE);
                rl_webview.setBackgroundColor(Color.parseColor("#ffffff"));
                String s = "<h4>" + title + "</h4><p>" + time + "</p>";
                content = s + content;
                news_webview.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);

                break;
            case 1://没有相关电影人
                scrollView_news.setVisibility(View.GONE);
                ll_viewpager.setVisibility(View.VISIBLE);
                icon_th.setVisibility(View.VISIBLE);
                ll_title.setVisibility(View.VISIBLE);
                pic_imglist.setVisibility(View.VISIBLE);
                pic_download.setVisibility(View.VISIBLE);
                //设置点击事件的监听
                pic_download.setOnClickListener(this);
                pic_imglist.setOnClickListener(this);
                rl_webview.setBackgroundColor(Color.parseColor("#000000"));
                //给Viewpager设置adapter
                icon_title.setText(title);
                icon_name.setText(imagesTitles.get(0));
                viewpager_news.setAdapter(new NewsAdapter(NewsActivity.this, imagesTitles, imagesUrls, icon_name, rl_news_bottom, rl_news_top));
                break;
            case 2://有相关电影人
                scrollView_news.setVisibility(View.VISIBLE);
                ll_viewpager.setVisibility(View.GONE);
                icon_th.setVisibility(View.GONE);
                ll_title.setVisibility(View.GONE);
                pic_imglist.setVisibility(View.GONE);
                pic_download.setVisibility(View.GONE);
                rl_webview.setBackgroundColor(Color.parseColor("#ffffff"));
                content = "<h4>" + title + "</h4><p>" + time + "</p>" + content;
                news_webview.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
                break;
        }

    }

    //解析json数据
    private void parseJsonData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            type = jsonObject.optInt("type");
            //网页
            content = jsonObject.optString("content");
            commentCount = jsonObject.optInt("commentCount");
            time = jsonObject.optString("time");

            title = jsonObject.optString("title");
            relations = jsonObject.optJSONArray("relations");

            if (relations.length() != 0) {
                //获取图片数据
                //遍历集合
                for (int i = 0; i < relations.length(); i++) {
                    JSONObject relationItem = (JSONObject) relations.get(i);
                    //将数据装配到集合中
                    String name = relationItem.optString("name");
                    String image = relationItem.optString("image");
                    if (i == 0) {
                        year = relationItem.optInt("year");
                        rating = relationItem.optInt("rating");
                    }
                    popListimageUrls.add(image);
                    popListimageName.add(name);

                }

            }
            if (type == 1) {

                JSONArray images = jsonObject.optJSONArray("images");
                if (images.length() != 0) {

                    for (int i = 0; i < images.length(); i++) {
                        JSONObject imageItem = (JSONObject) images.get(i);

                        String url1 = imageItem.optString("url1");
                        String desc1 = imageItem.optString("desc");
                        imagesUrls.add(url1);
                        imagesTitles.add(desc1);

                    }

                }
            }
            comment_search.setOnClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        pic_download = (ImageButton) findViewById(R.id.pic_download);
        pic_imglist = (ImageButton) findViewById(R.id.pic_imglist);
        ll_title = (LinearLayout) findViewById(R.id.ll_title);
        icon_th = (TextView) findViewById(R.id.icon_th);
        icon_desc = (TextView) findViewById(R.id.icon_desc);
        icon_title = (TextView) findViewById(R.id.icon_title);
        icon_name = (TextView) findViewById(R.id.icon_name);
        rl_news_top = (RelativeLayout) findViewById(R.id.rl_news_top);
        rl_news_bottom = (RelativeLayout) findViewById(R.id.rl_news_bottom);
        ll_viewpager = (LinearLayout) findViewById(R.id.ll_viewpager);
        viewpager_news = (ViewPager) findViewById(R.id.viewpager_news);
        scrollView_news = (ScrollView) findViewById(R.id.scrollView_news);
        news_webview = (WebView) findViewById(R.id.news_webview);
        news_left = (ImageView) findViewById(R.id.news_left);
        news_login = (ImageView) findViewById(R.id.news_login);
        news_share = (ImageView) findViewById(R.id.news_share);
        comment = (ImageButton) findViewById(R.id.comment);
        comment_count = (TextView) findViewById(R.id.comment_count);
        comment_search = (TextView) findViewById(R.id.comment_search);
        rl_webview = (RelativeLayout) findViewById(R.id.rl_webview);
        //设置点击事件的监听
//        comment_search.setOnClickListener(this);
        news_left.setOnClickListener(this);
        icon_desc.setOnClickListener(this);
        news_login.setOnClickListener(this);
//给viewpager设置滑动事件的监听
        viewpager_news.addOnPageChangeListener(this);
        viewpager_news.setCurrentItem(0);
    }

    /**
     * 点击事件的回调方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_login:
                //点击进入登陆界面

                break;
            case R.id.error:
                popupWindow.dismiss();
                break;
            case R.id.news_left:
                finish();
                break;
            case R.id.pic_download:
                //联网下载图片
                int positionItem = viewpager_news.getCurrentItem();
                String imagesUrl = imagesUrls.get(positionItem);
                downLoadImage(imagesUrl);

                break;
            case R.id.pic_imglist:
                //转换图片列表
                Intent intent = new Intent(NewsActivity.this, ImgGridListActivity.class);
                intent.putStringArrayListExtra("imagesUrls", imagesUrls);
                intent.putExtra("title", title);
                startActivityForResult(intent, 1);
                break;
            case R.id.icon_desc:
                //查看类型1的详细页面
//                news_webview.loadUrl();

                break;
            case R.id.comment_search://
                if (relations.length() == 0) {
                    MessageUtils.showMessage(NewsActivity.this, "没有相关影视人！");
                    break;
                }
                //显示popUpwindown
                //从activity的底部显示一个popupwidown
                if (popupWindow == null) {
                    //初始化popupWindown的布局
                    initPopView();
                    popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(240), false);

                    // 设置点击窗口外边窗口消失
                    popupWindow.setOutsideTouchable(true);

                    popupWindow.setBackgroundDrawable(new ColorDrawable());

                    popupWindow.setAnimationStyle(R.style.PopupAnimation);

                    // 设置此参数获得焦点,否则无法点击
                    popView.setFocusable(true);

                }

                //设置窗口显示的位置CommentWebviewActivity.this.findViewById(R.id.rl_webview)
                popupWindow.showAtLocation(findViewById(R.id.rl_webview), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                break;
        }
    }

    /**
     * 联网下载图片
     *
     * @param imagesUrl
     */
    private void downLoadImage(final String imagesUrl) {
        //启动一个分线程
        new Thread() {
            public void run() {
                //创建URL对象
                HttpURLConnection conn = null;
                FileOutputStream fos = null;
                FileInputStream fis = null;
                try {
                    URL url = new URL(imagesUrl);
                    conn = (HttpURLConnection) url.openConnection();
                    //设置参数
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    //连接
                    conn.connect();
                    int responsecode = conn.getResponseCode();
                    if (responsecode == 200) {
                        //请求数据成功
                        fis = (FileInputStream) conn.getInputStream();
                        //将获取的输入流转换成bitmap
                        Bitmap bitmap = BitmapFactory.decodeStream(fis);
                        //将bitmap保存到本地文件中
                        //判断SD卡是否存在
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            //如果存在，创建文件
                            String filePath = Environment.getExternalStorageDirectory() + "/MyTime";
                            String fileName = imagesUrl.substring(imagesUrl.lastIndexOf("/") + 1);
                            File file = new File(filePath, fileName);
                            //判断目录是否存在
                            if (!file.getParentFile().exists()) {
                                file.getParentFile().mkdirs();
                            }
                            //判断文件是否存在
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            //下载成功
                            Message message = Message.obtain();
                            message.obj = file;
                            message.what = DOWN_LOAD_SUCCESS;
                            handler.sendMessage(message);


                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(DOWN_LOAD_FAILUES);
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                    if(fis!=null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(fos!=null) {
                        try{
                            fos.close();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    /**
     * 初始化popupView的视图
     */
    private void initPopView() {
        mlayoutInflate = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //加载布局
        popView = mlayoutInflate.from(this).inflate(R.layout.new_pop, null);
        HorizontalListView news_horizontal_listView = (HorizontalListView) popView.findViewById(R.id.news_horizontal_listView);
        ImageButton error = (ImageButton) popView.findViewById(R.id.error);
        error.setOnClickListener(this);
        news_horizontal_listView.setAdapter(new PopupwindowAdapter(NewsActivity.this, year, rating, popListimageName, popListimageUrls));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            int position = data.getIntExtra("position", -1);
            viewpager_news.setCurrentItem(position);
        }
    }
}