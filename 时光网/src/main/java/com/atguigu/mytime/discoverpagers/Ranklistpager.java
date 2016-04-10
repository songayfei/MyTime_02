package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.adapter.RanklistAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.refreshlistview.RefreshListView;
import com.bumptech.glide.Glide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 排行榜
 * Created by Administrator on 16-4-8.
 */
public class Ranklistpager extends BasePager {
private RefreshListView listview_ranklist;
    private ImageView image_top_pick;
    private RanklistAdapter adapter;
    private ImageView prevue_head_icon;
    private TextView headview_title;
    private JSONObject topList;
    private List<JSONObject> lists;
    private ImageView image_top_time;
    private ImageView image_top_chinese;
    private int totalCount;//list中的item的总条数
    private int pageCount;//item的页数

    public Ranklistpager(Activity mactivity, JSONObject topList) {
        super(mactivity);
        this.topList=topList;
    }

    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.listview_ranklist, null);
        listview_ranklist = (RefreshListView) view.findViewById(R.id.listview_ranklist);
        View headView = View.inflate(mactivity, R.layout.ranklist_headview, null);
        prevue_head_icon = (ImageView) headView.findViewById(R.id.prevue_head_icon);
        headview_title = (TextView) headView.findViewById(R.id.headview_title);
        image_top_time = (ImageView) headView.findViewById(R.id.image_top_time);
        image_top_chinese = (ImageView) headView.findViewById(R.id.image_top_chinese);
        image_top_pick = (ImageView) headView.findViewById(R.id.image_top_pick);

        //给listView添加布局
        listview_ranklist.addTopNewsView(headView);
        //设置下拉刷新和上拉加载更多的监听
        listview_ranklist.setOnRefreshListener(new MyOnRefreshListener());
        return view;
    }
class MyOnRefreshListener implements RefreshListView.OnRefreshListener{

    @Override
    public void onPullDownRefresh() {
        //下拉刷新:使用的是新的adapter
        getDatafromNet();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多：使用的是同一个adapter，只需要更新adapter

    }
}
    @Override
    public void initData() {
        super.initData();
        //初始化头部
        initHeadView();
        //从本地获取图片
        String savejson=SpUtils.getInitialize(mactivity).getJsonData(NetUri.RANKLIST);
        if(!TextUtils.isEmpty(savejson)) {
            processJson(savejson);
        }
        //联网请求数据
        getDatafromNet();
    }

    /**
     * 初始化头部
     */
    private void initHeadView() {
        Glide.with(mactivity)
                .load(topList.optString("imageUrl"))
                .into(prevue_head_icon);
        headview_title.setText(topList.optString("title"));

    }

    /**
     * 联网请求数据
     */
    private void getDatafromNet() {
        OkHttpUtils.get().url(NetUri.RANKLIST)
                .build()
                .execute(new MyCallback());

    }
    class MyCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            MessageUtils.showMessage(mactivity,"联网请求数据");
        }

        @Override
        public void onResponse(String response) {
            //解析数据
            processJson(response);
            //保存数据到本地
            SpUtils.getInitialize(mactivity).saveJson(NetUri.RANKLIST,response);
        }
    }

    /**
     * 解析数据
     * @param response
     */
    public void processJson(String response) {
        parseJson(response);//解析数据成功
//设置adapter
adapter=new RanklistAdapter(mactivity,lists);
        //显示列表
        listview_ranklist.setAdapter(adapter);


    }

    private void parseJson(String response) {
        if(lists==null) {

            lists = new ArrayList<>();
        }
        lists.clear();


        //手动解析数据
        try {
            JSONObject jsonObject = new JSONObject(response);
            //获取共有多条数据
            totalCount=jsonObject.optInt("totalCount");
            pageCount=jsonObject.optInt("pageCount");
            JSONArray topLists = jsonObject.optJSONArray("topLists");
            //遍历数组
            for (int i = 0; i < topLists.length(); i++) {
                JSONObject item = (JSONObject) topLists.get(i);
                //添加到集合中
                lists.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
