package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.activity.CommentWebviewActivity;
import com.atguigu.mytime.adapter.CommentAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.refreshlistview.RefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 影评
 * Created by Administrator on 16-4-8.
 */
public class Commentpager extends BasePager implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = Commentpager.class.getSimpleName();
    private ImageView loading;
    //帧动画
    private AnimationDrawable animationDrawable;
    private ImageView loading_bg;
    private ImageView loading_failed;
    private RefreshListView listview_comment;
    private ImageView prevue_head_icon;//头部的图片

    private TextView headview_title;//头部预告片的题目

    private ImageView top_small;//头部的小图片
    private TextView headview_movename;//电影名称
    private JSONObject review;
    private List<JSONObject> commentInfo;//列表信息集合
    private CommentAdapter adapter;
    private int reviewID;
    private String movieimage;
    private String title;

    public Commentpager(Activity mactivity, JSONObject review) {
        super(mactivity);
        this.review=review;
    }

    @Override
    public View initView(){
        View view = View.inflate(mactivity, R.layout.listview_comment, null);
        listview_comment= (RefreshListView) view.findViewById(R.id.listview_comment);
        listview_comment.setIsPullLoadmore(false);
        listview_comment.setIsRefresh(true);
        loading = (ImageView) view.findViewById(R.id.loading);
        loading_bg = (ImageView) view.findViewById(R.id.loading_bg);
        loading_failed = (ImageView) view.findViewById(R.id.loading_failed);
        //设置监听
        loading_failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading_bg.setVisibility(View.VISIBLE);
                loading_failed.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                animationDrawable.start();
                getDatafromNet();
            }
        });
        /**
         *获取帧动画
         */
        animationDrawable = (AnimationDrawable) loading.getBackground();
        //listView的头布局
        View headview = View.inflate(mactivity, R.layout.comment_headview, null);
        prevue_head_icon = (ImageView) headview.findViewById(R.id.prevue_head_icon);

        headview_title = (TextView) headview.findViewById(R.id.headview_title);
        top_small = (ImageView) headview.findViewById(R.id.top_small);
        headview_movename = (TextView) headview.findViewById(R.id.headview_movename);
        //给listView加载头部
        listview_comment.addTopNewsView(headview);
        //设置点击某个item的监听
        listview_comment.setOnItemClickListener(this);
        //给listview设置下拉刷新的监听
        listview_comment.setOnRefreshListener(new CommentOnrefreshListener());
        return view;
    }

    /**
     *点击某个item的回调方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position==0){
            reviewID = review.optInt("reviewID");
            movieimage=review.optString("posterUrl");
        }else{
            JSONObject iteminfo = commentInfo.get(position - 1);
            JSONObject relatedObj = iteminfo.optJSONObject("relatedObj");
            title = relatedObj.optString("title");
            movieimage = relatedObj.optString("image");
            reviewID = iteminfo.optInt("id");

        }
        //http://api.m.mtime.cn/Review/Detail.api?reviewId=7932392
        String itemDetailUri = NetUri.COMMENT_DETAILS + reviewID;
        /**
         * 启动新的界面
         */
        Intent intent = new Intent(mactivity, CommentWebviewActivity.class);
        //携带uri
        intent.putExtra("title",title);
        intent.putExtra("itemDetailUri",itemDetailUri);
        intent.putExtra("movieimage",movieimage);
        mactivity.startActivity(intent);

        //保存position
        SpUtils.getInitialize(mactivity).save("isClick"+(position-1),position-1);
        adapter.notifyDataSetChanged();

    }

    class CommentOnrefreshListener implements RefreshListView.OnRefreshListener {

        @Override
        public void onPullDownRefresh() {
            //下拉刷新
            getDatafromNet();
        }

        @Override
        public void onLoadMore() {

        }
    }
    /**
     * 点击事件的回调方法
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loading_failed:
                //加载失败点击再次联网请求数据
                getDatafromNet();
                break;
//            case R.id.rl:
//                reviewID = review.optInt("reviewID");
//                String itemUri=NetUri.COMMENT_REVIEWID+reviewID+"/";
//                /**
//                 * 启动新的界面
//                 */
//                Intent intent = new Intent(mactivity, CommentWebviewActivity.class);
//                //携带uri
//
//                intent.putExtra("itemUri",itemUri);
//                mactivity.startActivity(intent);
//                break;
        }
    }
    private void initHeadview(){
        x.image().bind(prevue_head_icon, review.optString("imageUrl"));
        headview_movename.setText(review.optString("movieName"));
        headview_title.setText(review.optString("title"));
        x.image().bind(top_small, review.optString("posterUrl"));

    }

    @Override
    public void initData(){
        super.initData();
        //初始化头部
        initHeadview();
        //请求网络数据
        getDatafromNet();


    }

    public void getDatafromNet() {
        //开启动画
        loading.setVisibility(View.VISIBLE);
        loading_bg.setVisibility(View.VISIBLE);
        loading_failed.setVisibility(View.GONE);
        animationDrawable.start();
        OkHttpUtils.get().url(NetUri.COMMENT_LIST)
                .build()
                .execute(new MyCallback());
    }


    class MyCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            MessageUtils.showMessage(mactivity, "联网请求数据失败");
            animationDrawable.stop();
            //显示加载失败的图片
            loading.setVisibility(View.GONE);
            loading_bg.setVisibility(View.GONE);
            loading_failed.setVisibility(View.VISIBLE);
        }

        @Override
        public void onResponse(String response) {
            loading_failed.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            loading_bg.setVisibility(View.GONE);
            //请求数据成功
            processData(response);
            listview_comment.onFinishRefresh(true);
            //将请求的数据保存到本地
            SpUtils.getInitialize(mactivity).saveJson(NetUri.COMMENT_LIST,response);

        }
    }

    /**
     * 解析数据
     * @param result
     */
    private void processData(String result){
        parseJsonData(result);//解析数据成功
        //初始化adapter
        adapter=new CommentAdapter(mactivity,commentInfo);
        //显示列表
        listview_comment.setAdapter(adapter);
        listview_comment.setSelection(listview_comment.getFirstVisiblePosition());

    }

    private void parseJsonData(String result) {
//获取的是json数组，不能生成实体类对象了，所以用手动解析json
        if(commentInfo==null) {

            commentInfo = new ArrayList<>();
        }
        //刷新的时候，再次请求数据的时候，先清空集合

        commentInfo.clear();

        try {
            //获取json数据
            JSONArray jsonArray = new JSONArray(result);

            //遍历json数组
            for(int i = 0; i < jsonArray.length();i++){
                //获取每一个item的信息
                JSONObject object= (JSONObject)jsonArray.get(i);
                //将数据装入集合中
                commentInfo.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
