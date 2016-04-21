package com.atguigu.mytime.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.SpUtils;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

public class CommentWebviewActivity extends Activity implements View.OnClickListener {

    private WebView comment_webview;
    private WebSettings setting;
    private String itemUri;
    private ImageView comment_left;
    private ImageView comment_login;
    private ImageView comment_share;
    private ImageButton comment;
    private TextView comment_count;
    private TextView comment_search;
    private String movieimage;//电影图片
    private String itemDetailUri;
    private String rating;
    private int commentCount;
    private PopupWindow popupWindow;
    private View popView;
    private LayoutInflater mlayoutInflate;//布局加载器
    private String time;
    private String title;
    private String content;
    private String userTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_webview);
        //电影相关图片
        movieimage = getIntent().getStringExtra("movieimage");
        //详细uri
        itemDetailUri = getIntent().getStringExtra("itemDetailUri");
        //电影名称
        title=getIntent().getStringExtra("title");

        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        getDatafromnet();
    }

    /**
     * 联网请求数据
     */
    private void getDatafromnet() {
        OkHttpUtils.get().url(itemDetailUri).build()
                .execute(new CommentCallback());
    }
    class CommentCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e) {
            //请求数据失败
        }

        @Override
        public void onResponse(String response) {
            //得到数据
            processData(response);
            //将数据缓存
            SpUtils.getInitialize(CommentWebviewActivity.this).saveJson(itemDetailUri,response);
        }
    }

    /**
     * 解析数据
     * @param response
     */
    private void processData(String response){
        parseData(response);
        //装配数据
        comment_count.setText("" + commentCount);
        setting = comment_webview.getSettings();
        //设置webview支持javaScrip

        setting.setJavaScriptEnabled(true);
        setting.setUseWideViewPort(true);
        setting.setDefaultFontSize(30);
        setting.setAppCacheEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式
        //设置webview的客户端
        comment_webview.setWebViewClient(new WebViewClient() {
            //当页面加载完成的时候
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //当页面加载完成的时候，让加载页面消失
            }
        });

        comment_webview.loadDataWithBaseURL(null,content,"text/html","UTF-8",null);

    }
    //解析
    private void parseData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject relatedObj = jsonObject.optJSONObject("relatedObj");
            //电影评分
            rating = relatedObj.optString("rating");
            commentCount = jsonObject.optInt("commentCount");
            userTitle = jsonObject.optString("title");
            Log.e("TAG", "userTitle==" + userTitle);
            time = jsonObject.optString("time");
            String s="<h4>"+userTitle+"</h4><br><p><img width=\"70\" height=\"100\" src="+movieimage+"><hr><p>"+time+"</p></p>";
            content = jsonObject.optString("content");
            content=s+content;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {
        comment_webview = (WebView) findViewById(R.id.comment_webview);
        comment_left = (ImageView)findViewById(R.id.comment_left);//返回
        comment_login = (ImageView)findViewById(R.id.comment_login);//进入登陆界面
        comment_share = (ImageView)findViewById(R.id.comment_share);//分享
        comment = (ImageButton)findViewById(R.id.comment);
        comment_count = (TextView)findViewById(R.id.comment_count);//评论的数量
        comment_search = (TextView)findViewById(R.id.comment_search);//搜索影片信息
//设置点击事件的监听
        comment_left.setOnClickListener(this);
        comment_login.setOnClickListener(this);
        comment_share.setOnClickListener(this);
        comment.setOnClickListener(this);
        comment_search.setOnClickListener(this);


    }

    /**
     * 点击事件的回调方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.comment_left :
                finish();
                break;
            case R.id.comment_login://进入登陆界面

                break;
            case R.id.comment_share://分享


                break;
            case R.id.comment://进入评论界面

                break;
            case R.id.comment_search://搜索
                //从activity的底部显示一个popupwidown
                if(popupWindow==null) {
                    //初始化popupWindown的布局
                    initPopView();
                    popupWindow=new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);

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
            case R.id.error:
                popupWindow.dismiss();
                break;
            case R.id.iv_movie:

                break;
        }
    }

    private void initPopView(){
        mlayoutInflate = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        //加载布局
        popView = mlayoutInflate.from(this).inflate(R.layout.popview,null);

        ImageButton error = (ImageButton) popView.findViewById(R.id.error);
        ImageView iv_movie = (ImageView) popView.findViewById(R.id.iv_movie);
        TextView tv_moive_rate = (TextView) popView.findViewById(R.id.tv_moive_rate);
        TextView movie_title = (TextView) popView.findViewById(R.id.movie_title);
        TextView movie_time = (TextView) popView.findViewById(R.id.movie_time);
        //装配数据
        Glide.with(this).load(movieimage).into(iv_movie);
        tv_moive_rate.setText(rating);
        movie_time.setText(time);
        movie_title.setText(title);

        error.setOnClickListener(this);
        iv_movie.setOnClickListener(this);

    }
}
