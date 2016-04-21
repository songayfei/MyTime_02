package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.activity.NewsActivity;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.bean.NewsInfo;
import com.atguigu.refreshlistview.RefreshListView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.util.List;

import okhttp3.Call;

/**
 * 新闻
 * Created by Administrator on 16-4-8.
 */
public class Newspager extends BasePager implements AdapterView.OnItemClickListener {
    private JSONObject topNews;//头部信息
    private RefreshListView listview_newspager;
    private ImageView loading;
    //帧动画
    private AnimationDrawable animationDrawable;
    private ImageView loading_bg;
    private ImageView loading_failed;
    //头布局
    private ImageView prevue_head_icon;
    private TextView headview_title;
    private ImageView image_top_picket;
    private ImageView image_top_chinese;
    //列表信息
    private int totalCount;
    private List<NewsInfo.NewsListEntity> newsList;
    private NewsAdapter adapter;
    private int newsId;//头部的id
    private int itemId;
    private boolean isLoadmore;
    private int i=1;

    public Newspager(Activity mactivity, JSONObject topNews) {
        super(mactivity);
        this.topNews = topNews;

    }

    /**
     * 初始化视图
     *
     * @return
     */
    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.layout_newspager, null);
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
                getDataFromnet();
            }
        });
        listview_newspager = (RefreshListView) view.findViewById(R.id.listview_newspager);
        listview_newspager.setIsPullLoadmore(true);
        listview_newspager.setIsRefresh(true);

        //加载头布局
        View headView = View.inflate(mactivity, R.layout.news_head, null);
        prevue_head_icon = (ImageView) headView.findViewById(R.id.prevue_head_icon);
        headview_title = (TextView) headView.findViewById(R.id.headview_title);
        image_top_picket = (ImageView) headView.findViewById(R.id.image_top_picket);
        image_top_chinese = (ImageView) headView.findViewById(R.id.image_top_chinese);
        //给listview添加头布局
        listview_newspager.addTopNewsView(headView);
        //设置点击某个item的监听
        listview_newspager.setOnItemClickListener(this);
        //设置监听
        listview_newspager.setOnRefreshListener(new NewsOnrefreshListener());
        return view;
    }
    class NewsOnrefreshListener implements RefreshListView.OnRefreshListener {

    @Override
    public void onPullDownRefresh() {
        //下拉刷新
        getDataFromnet();

    }

    @Override
    public void onLoadMore() {
        if(newsList.size()<totalCount){
            //加载更多的数据
            isLoadmore=true;
            getDataFromnet();
        }else{
            isLoadmore=false;
            //恢复
            listview_newspager.onFinishRefresh(false);
        }
    }
}
    @Override
    public void initData() {
        super.initData();
        //初始化头部数据
        Glide.with(mactivity).load(topNews.optString("imageUrl")).into(prevue_head_icon);
        headview_title.setText(topNews.optString("title"));
        //从本地获取数据
        //从本地获取图片
        String savejson = SpUtils.getInitialize(mactivity).getJsonData("news_list");
        if (!TextUtils.isEmpty(savejson)) {
            processData(savejson);
        }
        //头部的id
         newsId = topNews.optInt("newsID");
        //联网请求数据
        getDataFromnet();

    }

    /**
     * 联网请求数据
     */
    private void getDataFromnet() {
        //开启动画
        loading.setVisibility(View.VISIBLE);
        loading_bg.setVisibility(View.VISIBLE);
        loading_failed.setVisibility(View.GONE);
        animationDrawable.start();
        String uri = "";
        if(isLoadmore) {
            i++;
            uri = NetUri.NEWSPAGER_LIST_BASE+i;
        }else{
            uri = NetUri.NEWSPAGER_LIST_BASE+1;
        }
        OkHttpUtils.get().url(uri).build().execute(new NewCallback());
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
        if(position==0){
            itemId=newsId;
        }else{

            NewsInfo.NewsListEntity itemInfo = newsList.get(position-1);
            itemId = itemInfo.getId();
        }
        //每个item的url：http://api.m.mtime.cn/News/Detail.api?newsId=1550016
        String itemUrl = NetUri.NEWS_ITEM_URI + itemId;


        Intent intent = new Intent(mactivity, NewsActivity.class);
        //携带数据
        intent.putExtra("itemUrl",itemUrl);

        mactivity.startActivity(intent);


    }

    class NewCallback extends StringCallback {


        @Override
        public void onError(Call call, Exception e) {
            MessageUtils.showMessage(mactivity,"请求数据失败！");
            loading.setVisibility(View.GONE);
            loading_bg.setVisibility(View.GONE);
            loading_failed.setVisibility(View.VISIBLE);
            animationDrawable.stop();
        }

        @Override
        public void onResponse(String response) {
            //请求数据成功解析数据
            processData(response);
            //恢复到原来的状态
            listview_newspager.onFinishRefresh(true);
            loading.setVisibility(View.GONE);
            loading_bg.setVisibility(View.GONE);
            loading_failed.setVisibility(View.GONE);
            animationDrawable.stop();
            //保存到本地
            SpUtils.getInitialize(mactivity).saveJson("news_list", response);
        }
    }

    /**
     * 解析数据
     *
     * @param response
     */
    private void processData(String response) {
        parseData(response);//解析数据成功
        //设置adapter
        if(isLoadmore) {
            listview_newspager.setSelection(listview_newspager.getLastVisiblePosition());
            adapter.notifyDataSetChanged();
        }else{

            adapter = new NewsAdapter();
            //装配数据
            listview_newspager.setAdapter(adapter);
            listview_newspager.setSelection(listview_newspager.getFirstVisiblePosition());
        }

    }

    private void parseData(String response) {
        NewsInfo newsInfo = new Gson().fromJson(response, NewsInfo.class);
        //总的页数和条数
        totalCount = newsInfo.getTotalCount();
        if(isLoadmore){
            //item的数据集合
            newsList.addAll(newsInfo.getNewsList());

        }else{
            //item的数据集合
            newsList = newsInfo.getNewsList();
        }


    }

    class NewsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return newsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            NewsInfo.NewsListEntity itemInfo = newsList.get(position);
            List<NewsInfo.NewsListEntity.ImagesEntity> images = itemInfo.getImages();
            int type = itemInfo.getType();

            return type;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            NewsInfo.NewsListEntity itemInfo = newsList.get(position);
            List<NewsInfo.NewsListEntity.ImagesEntity> images = itemInfo.getImages();
            int type = itemInfo.getType();
            ViewHolder holder = null;
            //分类型的listView
            if (convertView == null){
                holder = new ViewHolder();
                switch (type) {
                    case 0:
                        convertView = View.inflate(mactivity,R.layout.layout_item_type0,null);
                        holder.item_icon1=(ImageView)convertView.findViewById(R.id.item_icon1);
                        holder.tv_title1 = (TextView) convertView.findViewById(R.id.tv_title1);
                        holder.tv_desc1 =(TextView)convertView.findViewById(R.id.tv_desc1);
                        holder.tv_comment1 = (TextView)convertView.findViewById(R.id.tv_comment1);
                        break;
                    case 1:
                        convertView = View.inflate(mactivity, R.layout.layout_item_type1,null);
                        holder.text_title0 = (TextView) convertView.findViewById(R.id.text_title0);
//                        holder.text_time0 = (TextView) convertView.findViewById(R.id.text_time0);
                        holder.tv_comment0 = (TextView) convertView.findViewById(R.id.tv_comment0);
                        holder.image3 = (ImageView) convertView.findViewById(R.id.image3);
                        holder.image1 = (ImageView) convertView.findViewById(R.id.image1);
                        holder.image2 = (ImageView) convertView.findViewById(R.id.image2);
                        if(images.size()==3) {
                            holder.image3.setVisibility(View.VISIBLE);
                        }else{
                            holder.image3.setVisibility(View.GONE);
                        }

                        break;
                    case 2:
                        convertView = View.inflate(mactivity, R.layout.layout_item_type2, null);
                        holder.item2_icon = (ImageView) convertView.findViewById(R.id.item2_icon);
                        holder.tv_title2 = (TextView) convertView.findViewById(R.id.tv_title2);
                        holder.tv_desc2 = (TextView) convertView.findViewById(R.id.tv_desc2);
                        holder.tv_comment2 = (TextView) convertView.findViewById(R.id.tv_comment2);
                        break;
                    default:
                        convertView = View.inflate(mactivity, R.layout.layout_item_type_default, null);
                        holder.iv_default = (ImageView) convertView.findViewById(R.id.iv_default);
                        break;
                }
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            switch (type) {
                case 0 :
                    Glide.with(mactivity).load(itemInfo.getImage()).into(holder.item_icon1);
                    holder.tv_title1.setText(itemInfo.getTitle());
                    holder.tv_desc1.setText(itemInfo.getTitle2());
                    holder.tv_comment1.setText("评论" + itemInfo.getCommentCount());
                    break;
                case 1 :
                    if(images.size()==3) {
                        Glide.with(mactivity).load(images.get(2).getUrl1()).into(holder.image3);
                    }
                    Glide.with(mactivity).load(images.get(0).getUrl1()).into(holder.image1);
                    Glide.with(mactivity).load(images.get(1).getUrl1()).into(holder.image2);
                    holder.text_title0.setText(itemInfo.getTitle());
                    holder.tv_comment0.setText("评论" + itemInfo.getCommentCount());
                    break;
                case 2 :
                    holder.tv_title2.setText(itemInfo.getTitle());
                    holder.tv_desc2.setText(itemInfo.getTitle2());
                    holder.tv_comment2.setText("评论" + itemInfo.getCommentCount());
                    Glide.with(mactivity).load(itemInfo.getImage()).into(holder.item2_icon);
                    break;
                default:
                    Glide.with(mactivity).load(itemInfo.getImage()).into(holder.iv_default);
                    break;
            }

            return convertView;
        }

        class ViewHolder {
            ImageView iv_default;
            TextView text_title0;
            TextView text_time0;
            TextView tv_comment0;
            ImageView image1;
            ImageView image2;
            ImageView image3;
            ImageView item_icon1;
            TextView tv_title1;
            TextView tv_desc1;
            TextView tv_comment1;
            ImageView item2_icon;
            TextView tv_title2;
            TextView tv_desc2;
            TextView tv_comment2;

        }
    }
}
