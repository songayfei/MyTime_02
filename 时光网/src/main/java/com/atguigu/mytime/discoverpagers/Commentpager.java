package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.adapter.CommentAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.refreshlistview.RefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 影评
 * Created by Administrator on 16-4-8.
 */
public class Commentpager extends BasePager implements View.OnClickListener {
    private static final String TAG = Commentpager.class.getSimpleName();
//    @ViewInject(R.id.loading_failed)
//    private ImageView loading_failed;
//    @ViewInject(R.id.loading)
//    private GifImageView loading;
    @ViewInject(R.id.listview_comment)
    private RefreshListView listview_comment;
    @ViewInject(R.id.prevue_head_icon)
    private ImageView prevue_head_icon;//头部的图片
    @ViewInject(R.id.headview_title)
    private TextView headview_title;//头部预告片的题目
    @ViewInject(R.id.top_small)
    private ImageView top_small;//头部的小图片
    @ViewInject(R.id.headview_movename)
    private TextView headview_movename;//电影名称
    private JSONObject review;
    private List<JSONObject> commentInfo;//列表信息集合
    private CommentAdapter adapter;
    public Commentpager(Activity mactivity, JSONObject review) {
        super(mactivity);
        this.review=review;
    }

    @Override
    public View initView(){
        View view = View.inflate(mactivity, R.layout.listview_comment, null);
//        //设置点击事件的监听
//        loading_failed.setOnClickListener(this);
//        /**
//         * 加载gif图片
//         */
//        Glide.with(mactivity).load(R.id.loading).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(loading);
        x.view().inject(this,view);
        //listView的头布局
        View headview = View.inflate(mactivity, R.layout.comment_headview, null);
        x.view().inject(this, headview);
        //给listView加载头部
        listview_comment.addTopNewsView(headview);
        return view;
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
        }
    }
    private void initHeadview(){
        x.image().bind(prevue_head_icon, review.optString("imageUrl"));
        headview_movename.setText(review.optString("movieName"));
        headview_title.setText(review.optString("title"));
        x.image().bind(top_small, review.optString("posterUrl"));

    }

    @Override
    public void initData() {
        super.initData();
        //初始化头部
        initHeadview();
        //请求网络数据
        getDatafromNet();

    }

    public void getDatafromNet() {
        //显示加载图片
//        loading.setVisibility(View.VISIBLE);
        OkHttpUtils.get().url(NetUri.COMMENT_LIST)
                .build()
                .execute(new MyCallback());
    }


    class MyCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e) {
            //请求数据失败
//          loading_failed.setVisibility(View.VISIBLE);

        }

        @Override
        public void onResponse(String response) {
            //请求数据成功
            processData(response);
            //将请求的数据保存到本地
            SpUtils.getInitialize(mactivity).saveJson(NetUri.COMMENT_LIST,response);
            //更改加载界面
//            loading.setVisibility(View.GONE);
//            loading_failed.setVisibility(View.GONE);

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

    }

    private void parseJsonData(String result) {
//获取的是json数组，不能生成实体类对象了，所以用手动解析json
        try {
            //获取json数据
            JSONArray jsonArray = new JSONArray(result);
            commentInfo = new ArrayList<>();
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
