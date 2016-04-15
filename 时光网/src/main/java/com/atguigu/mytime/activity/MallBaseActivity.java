package com.atguigu.mytime.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.adapter.MyBaseAdapter;
import com.atguigu.mytime.entity.SerachGoodsBean;
import com.atguigu.mytime.net.OkhttpUtils2;
import com.atguigu.mytime.view.LoadingDailog;
import com.bumptech.glide.Glide;

import java.util.List;

import de.greenrobot.event.EventBus;

public class MallBaseActivity extends Activity implements View.OnClickListener {
    private TextView tvTitleSearch;
    private TextView load_tv;
    private GridView gdMallBase;
    private ImageView gif;
    private ImageView loading_failed;
    private AlertDialog alertDialog;
    private SerachGoodsBean goodsBean;
    private String url;
    private int index = 1;
    private boolean isFrist = true;
    private MyBaseAdapter<SerachGoodsBean.ContentEntity.GoodsEntity.GoodsListEntity> adapter;
    private ImageView loadingbg;
    private LoadingDailog loadingDailog;
    private String[] split;
    private List<SerachGoodsBean.ContentEntity.GoodsEntity.GoodsListEntity> goodsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall_base);
        EventBus.getDefault().register(this);
        url = getIntent().getStringExtra("URL");
        split = url.split("pageIndex=1");
        if (!TextUtils.isEmpty(url.trim())) {
            new OkhttpUtils2<SerachGoodsBean>(url, this, SerachGoodsBean.class,true);
        }

        initView();
        showLoading();

    }

    private void showLoading() {

        loadingDailog = new LoadingDailog(this);
        loadingDailog.show();

    }

    /**
     * EventBus 接收联网请求 实体类
     * 搜索产品 列表
     *
     * @param event
     */
    public void onEventMainThread(SerachGoodsBean event) {
        Log.e("TAG", "onEventMainThread");
        if (isFrist) {
//            gif.setVisibility(View.GONE);
//            loadingbg.setVisibility(View.GONE);
            loadingDailog.dismiss();
            goodsBean = event;
            initDate();
            isFrist = false;
        } else {


            load_tv.setVisibility(View.GONE);
            List<SerachGoodsBean.ContentEntity.GoodsEntity.GoodsListEntity> list = event.getContent().getGoods().getGoodsList();
            List<SerachGoodsBean.ContentEntity.GoodsEntity.GoodsListEntity> data = adapter.getData();
            data.addAll(data.size(), list);
            adapter.notifyDataSetChanged();


        }


    }

    private void initDate() {
        goodsList = goodsBean.getContent().getGoods().getGoodsList();
        gdMallBase.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
                    if ((gdMallBase.getAdapter().getCount() - 1) == gdMallBase.getLastVisiblePosition()) {
                        load_tv.setVisibility(View.VISIBLE);

                        loadMore();

                    }
                } else {
                    load_tv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        adapter = new MyBaseAdapter<SerachGoodsBean.ContentEntity.GoodsEntity.GoodsListEntity>(goodsList, this) {


            @Override
            public View MyInstantiateItem(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(MallBaseActivity.this, R.layout.mall_home_topic_sublist_item, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                Glide.with(MallBaseActivity.this)
                        .load(data.get(position).getImageSrc()).fallback(R.drawable.img_default_45x45)
                        .into(holder.topicListImg);


                holder.topicListTitle.setText(data.get(position).getName());
                holder.topicListPrice.setText("￥" + data.get(position).getMinSalePrice() / 100);

                holder.topic_list_marprice.setText("￥" + data.get(position).getMarketPrice() / 100);


                String iconText = data.get(position).getIconText();

                if (TextUtils.isEmpty(iconText.trim())) {
                    holder.topsl.setVisibility(View.GONE);
                } else {
                    holder.topsl.setVisibility(View.VISIBLE);
                }
                holder.topsl.setText(data.get(position).getIconText());
                holder.topic_list_marprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                return convertView;
            }


            class ViewHolder {
                private ImageView topicListImg;
                private TextView topicListTitle;
                private TextView topicListPrice;
                private TextView topic_list_marprice;
                private TextView topsl;

                public ViewHolder(View view) {
                    topicListImg = (ImageView) view.findViewById(R.id.topic_list_img);
                    topicListTitle = (TextView) view.findViewById(R.id.topic_list_title);
                    topicListPrice = (TextView) view.findViewById(R.id.topic_list_price);
                    topic_list_marprice = (TextView) view.findViewById(R.id.topic_list_marprice);
                    topsl = (TextView) view.findViewById(R.id.topsl);
                }
            }
        };
        gdMallBase.setAdapter(adapter);
    }

    private void loadMore() {
        String nUrl="";
        String pageIndex = "pageIndex=1";
        index++;
       // nUrl.replace("pageIndex=", "pageIndex=" + index);

        nUrl=split[0]+"pageIndex=" + index+split[1];
        if (index <= goodsBean.getPageCount()) {
            new OkhttpUtils2<SerachGoodsBean>(nUrl, this, SerachGoodsBean.class);
        } else {
            load_tv.setText("亲，已经没有更多了！");
        }
    }

    private void initView() {
        findViewById(R.id.ib_mall_home_back).setOnClickListener(this);
        tvTitleSearch = (TextView) findViewById(R.id.tv_title_search);
        findViewById(R.id.ib_mall_home_start).setOnClickListener(this);
        gdMallBase = (GridView) findViewById(R.id.gd_mall_base);
        load_tv = (TextView) findViewById(R.id.load_tv);
        loading_failed = (ImageView) findViewById(R.id.loading_failed);
        gdMallBase.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MallBaseActivity.this, WebViewActivity.class);
                String url = goodsList.get(position).getUrl();

                intent.putExtra("URL",url);
                startActivity(intent);
            }
        });


    }


    private ImageView getView() {
        return loading_failed;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_mall_home_back:
                //TODO implement
                break;
            case R.id.ib_mall_home_start:
                //TODO implement
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
