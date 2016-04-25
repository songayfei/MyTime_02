package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.content.Intent;
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
import com.atguigu.mytime.activity.SystemVideoPlayerActivity;
import com.atguigu.mytime.adapter.PrevueListAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.refreshlistview.RefreshListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 预告片
 * Created by Administrator on 16-4-8.
 */
public class PrevuePager extends BasePager implements AdapterView.OnItemClickListener {

    private static final String TAG = PrevuePager.class.getSimpleName();

    private AnimationDrawable animationDrawable;
    private ImageView loading_bg;
    private JSONObject trailer;
    private PrevueListAdapter adapter;
    private RefreshListView listview_discover;
    private ImageView loading;
    private ImageView loading_failed;
    private ImageView prevue_head_icon;//头部的图片
    private List<JSONObject> lists;
    private TextView headview_title;//头部预告片的题目
    private ArrayList<String> urlList=new ArrayList<>();//视频地址的集合
    private ArrayList<String> heighturl=new ArrayList<>();//视频地址的集合
    private ArrayList<String> moveNames=new ArrayList<>();//视频题目的集合
    private String topHeightUri;//头部的高清地址



    public PrevuePager(Activity mactivity, JSONObject trailer) {
        super(mactivity);
        this.trailer = trailer;
    }

    @Override
    public View initView(){
        View view = View.inflate(mactivity,R.layout.listview_discover,null);
        listview_discover = (RefreshListView) view.findViewById(R.id.listview_discover);
        loading= (ImageView) view.findViewById(R.id.loading);
        loading_bg = (ImageView) view.findViewById(R.id.loading_bg);
        loading_failed = (ImageView) view.findViewById(R.id.loading_failed);
        animationDrawable= (AnimationDrawable) loading.getBackground();
        //设置监听
        loading_failed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading_failed.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                loading_bg.setVisibility(View.VISIBLE);
                animationDrawable.start();
                getDatafromNet();
            }
        });
        listview_discover.setIsRefresh(true);
        listview_discover.setIsPullLoadmore(false);

        //listView的头布局
        View headview = View.inflate(mactivity, R.layout.prevue_headview,null);
        prevue_head_icon = (ImageView) headview.findViewById(R.id.prevue_head_icon);
        headview_title = (TextView) headview.findViewById(R.id.headview_title);
        //给listView加载头部
        listview_discover.addTopNewsView(headview);
        //给listview设置点击事件的监听
        listview_discover.setOnItemClickListener(this);
        listview_discover.setOnRefreshListener(new PrevueOnRefreshListener());

        return view;
    }
class PrevueOnRefreshListener implements RefreshListView.OnRefreshListener {

    @Override
    public void onPullDownRefresh() {
        getDatafromNet();
    }

    @Override
    public void onLoadMore() {

    }
}
    /**
     * 显示头部
     */
    private void initHeadview(){
        x.image().bind(prevue_head_icon, trailer.optString("imageUrl"));
        headview_title.setText(trailer.optString("title"));
        urlList.add(trailer.optString("imageUrl"));
        moveNames.add(trailer.optString("title"));
        heighturl.add(trailer.optString("hightUrl"));


    }

    @Override
    public void initData() {
        //初始化头部
        initHeadview();
        //从本地获取数据
        String saveJson = SpUtils.getInitialize(mactivity).getJsonData(NetUri.DISCOVER_LIST);
        if(!TextUtils.isEmpty(saveJson)) {
            processJsonData(saveJson);
        }

        //联网加载数据
        getDatafromNet();

    }

    @Override
    public void showPger(String[] city_name_id) {

    }

    /**
     * 联网加载数据
     */
    private void getDatafromNet() {
        //开启动画
        loading.setVisibility(View.VISIBLE);
        loading_bg.setVisibility(View.VISIBLE);
        loading_failed.setVisibility(View.GONE);
        animationDrawable.start();
        String uri = NetUri.DISCOVER_LIST;
        /**
         * 使用okhttp联网请求数据
         */
        OkHttpUtils.get()
                .url(uri)
                .build()
                .execute(new MyCallback());
    }

    /**
     * 点击某个item的回调方法
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

        //点击某个item携带地址进入万能播放器
        Intent intent = new Intent(mactivity, SystemVideoPlayerActivity.class);

        //传递视频列表
//        bundle.putByteArray("videolist",lists);
        intent.putStringArrayListExtra("videolist",urlList);
        intent.putStringArrayListExtra("heighturl",heighturl);
        intent.putStringArrayListExtra("moveNames",moveNames);

        //视频的列表中的某条位置
        intent.putExtra("position",position);

        mactivity.startActivity(intent);
    }

    class MyCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e) {
            MessageUtils.showMessage(mactivity, "请求数据失败！");
            loading.setVisibility(View.GONE);
            loading_bg.setVisibility(View.GONE);
            loading_failed.setVisibility(View.VISIBLE);
            animationDrawable.stop();
        }

        @Override
        public void onResponse(String response) {
            //请求数据成功
            processJsonData(response);
            //将数据保存到本地SD卡中
            SpUtils.getInitialize(mactivity).saveJson(NetUri.DISCOVER_LIST, response);
            //恢复到原来的状态
            listview_discover.onFinishRefresh(true);
            loading.setVisibility(View.GONE);
            loading_bg.setVisibility(View.GONE);
            loading_failed.setVisibility(View.GONE);
            animationDrawable.stop();
        }
    }

    /**
     * 解析请求的json数据
     *
     * @param result
     */
    private void processJsonData(String result) {

        parseJsonData(result);//解析数据成功
        //初始化列表信息集合:即为listView中每个item的信息

        adapter = new PrevueListAdapter(mactivity, lists);
        listview_discover.setAdapter(adapter);
        listview_discover.setSelection(listview_discover.getFirstVisiblePosition());


    }

    /**
     * 解析
     *
     * @param result
     */
    private void parseJsonData(String result) {
        //解析数据
        try {

            JSONObject jsonObject = new JSONObject(result);
            JSONArray trailers = jsonObject.optJSONArray("trailers");
            if(lists==null) {

                lists = new ArrayList<>();
            }
            lists.clear();
            //遍历数组
            for (int i = 0; i < trailers.length(); i++) {
                JSONObject item = (JSONObject) trailers.get(i);
                //添加到集合中
                lists.add(item);
                String uri = item.optString("url");
                urlList.add(uri);
                String moveName = item.optString("movieName");
                moveNames.add(moveName);
                heighturl.add(item.optString("hightUrl"));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
