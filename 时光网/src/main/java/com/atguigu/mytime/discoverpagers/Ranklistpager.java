package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
public class Ranklistpager extends BasePager implements AdapterView.OnItemClickListener {
private RefreshListView listview_ranklist;
    private ImageView loading;
    //帧动画
    private AnimationDrawable animationDrawable;
    private ImageView loading_bg;
    private ImageView loading_failed;
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
    private boolean isLoadMore;
    private int i=1;

    public Ranklistpager(Activity mactivity, JSONObject topList) {
        super(mactivity);
        this.topList=topList;
    }

    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.listview_ranklist, null);
        listview_ranklist = (RefreshListView) view.findViewById(R.id.listview_ranklist);
        listview_ranklist.setIsPullLoadmore(true);
        listview_ranklist.setIsRefresh(true);
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
        //给listView设置点击事件的监听
        listview_ranklist.setOnItemClickListener(this);
        return view;
    }

    /**
     * 点击某个item的回调方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //点击的时候进入详细界面
    }

    class MyOnRefreshListener implements RefreshListView.OnRefreshListener{

    @Override
    public void onPullDownRefresh() {
        //下拉刷新:使用的是新的adapter
        getDatafromNet();
    }

    @Override
    public void onLoadMore(){
        //上拉加载更多：使用的是同一个adapter，只需要更新adapter
        if(lists.size()<totalCount){
            isLoadMore=true;
            getDatafromNet();

        }else{
            isLoadMore=false;
            //如果加载更多的时候，没有数据了，也恢复到原来的状态
            listview_ranklist.onFinishRefresh(false);
        }

    }


}


    @Override
    public void initData() {
        super.initData();
        //初始化头部
        initHeadView();
        //从本地获取图片
        String savejson=SpUtils.getInitialize(mactivity).getJsonData("neturi_ranklist");
        if(!TextUtils.isEmpty(savejson)) {
            processJson(savejson);
        }
        //联网请求数据
        getDatafromNet();
    }

    @Override
    public void showPger(String[] city_name_id) {

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
        loading_bg.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        //开启帧动画
        animationDrawable.start();

        String uri = "";
        if (isLoadMore) {
           i++;
            uri = NetUri.RANKLIST_BASE+i;
        }else{

            uri = NetUri.RANKLIST_BASE+1;
        }
        OkHttpUtils.get().url(uri)
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
                processJson(response);
                //请求数据成功，恢复到原来的状态
                listview_ranklist.onFinishRefresh(true);
                //保存数据到本地
                SpUtils.getInitialize(mactivity).saveJson("neturi_ranklist",response);
        }
    }

    /**
     * 解析数据
     * @param response
     */
    public void processJson(String response) {
        parseJson(response);//解析数据成功
        //设置adapter
        if(isLoadMore&&adapter!=null) {
            adapter.notifyDataSetChanged();
            //加载数据的时候，将item定位到最后一个
            listview_ranklist.setSelection(listview_ranklist.getLastVisiblePosition());
        }else{

            adapter=new RanklistAdapter(mactivity,lists);
            //显示列表
            listview_ranklist.setAdapter(adapter);
            listview_ranklist.setSelection(listview_ranklist.getFirstVisiblePosition());
        }


    }

    private void parseJson(String response) {
        if(lists==null) {

            lists = new ArrayList<>();
        }
        if(!isLoadMore) {
            lists.clear();
        }

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
