package com.atguigu.mytime.pager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MallURlL;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.activity.MallBaseActivity;
import com.atguigu.mytime.activity.SearchGoodsActivity;
import com.atguigu.mytime.activity.WebViewActivity;
import com.atguigu.mytime.adapter.MyBaseAdapter;
import com.atguigu.mytime.adapter.MyPagerAdapter;
import com.atguigu.mytime.adapter.RecyclerViewAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.entity.MallGoodsInfos;
import com.atguigu.mytime.entity.RecommendGoodsBean;
import com.atguigu.mytime.net.InterNetConn;
import com.atguigu.mytime.view.AutoScrollViewPager;
import com.atguigu.mytime.view.AutoViewPagerIndicator;
import com.atguigu.mytime.view.NoScrollGridView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/4/8.
 * 商城
 */
public class ShopPager extends BasePager implements View.OnClickListener {

    private TextView textView;
    private ListView lvMallPager;
    private TextView tvTitleSearch;
    private AutoScrollViewPager vpMallHead;
    private NoScrollGridView ngvMallHead;
    private ImageView mallShopA;
    private ImageView mallShopC1;
    private ImageView mallShopC2;
    private ImageView mallShopB;

    private RelativeLayout llTop2Bg;
    private RecyclerView hlvMallTop2;
    private TextView tv1;
    private TextView tv2;
    private NoScrollGridView nsgMallTop2;
    private TextView moreGoods;
    private MallGoodsInfos mallGoodsInfos;
    private ImageView imageView;
    private NoScrollGridView mall_foot_item_gv;
    private RecommendGoodsBean recommendGoodsBean;
    private ImageView mall_home_title;
    private int vpMallHeadHeight;
    private int[] location;
    private ImageView top2_bg;
    private ImageView oldView;
    private int oldPosition;
    private AutoViewPagerIndicator Indicator;

    public ShopPager(Activity mactivity) {
        super(mactivity);

        EventBus.getDefault().register(this);

    }

    @Override
    public View initView() {

        View view = View.inflate(mactivity, R.layout.mall_base_pager, null);
        lvMallPager = (ListView) view.findViewById(R.id.lv_mall_pager);
        view.findViewById(R.id.ib_mall_home_scan).setOnClickListener(this);
        tvTitleSearch = (TextView) view.findViewById(R.id.tv_title_search);
        tvTitleSearch.setOnClickListener(this);
        view.findViewById(R.id.ib_mall_home_cart).setOnClickListener(this);
        mall_home_title = (ImageView) view.findViewById(R.id.mall_home_title);

        View headView = View.inflate(mactivity, R.layout.mall_pager_head, null);
        vpMallHead = (AutoScrollViewPager) headView.findViewById(R.id.vp_mall_head);
        ngvMallHead = (NoScrollGridView) headView.findViewById(R.id.ngv_mall_head);
        Indicator = (AutoViewPagerIndicator) headView.findViewById(R.id.Indicator);
        View top_item_view = View.inflate(mactivity, R.layout.mall_top_item, null);
        mallShopA = (ImageView) top_item_view.findViewById(R.id.mall_shopA);
        mallShopC1 = (ImageView) top_item_view.findViewById(R.id.mall_shopC1);
        mallShopC2 = (ImageView) top_item_view.findViewById(R.id.mall_shopC2);
        mallShopB = (ImageView) top_item_view.findViewById(R.id.mall_shopB);

        mallShopA.setOnClickListener(this);
        mallShopC1.setOnClickListener(this);
        mallShopC2.setOnClickListener(this);
        mallShopB.setOnClickListener(this);

        View top2_item_view = View.inflate(mactivity, R.layout.mall_top2_item, null);

        llTop2Bg = (RelativeLayout) top2_item_view.findViewById(R.id.ll_top2_bg);
        top2_bg = (ImageView) top2_item_view.findViewById(R.id.top2_bg);
        hlvMallTop2 = (RecyclerView) top2_item_view.findViewById(R.id.hlv_mall_top2);
        tv1 = (TextView) top2_item_view.findViewById(R.id.tv_1);
        tv2 = (TextView) top2_item_view.findViewById(R.id.tv_2);
        nsgMallTop2 = (NoScrollGridView) top2_item_view.findViewById(R.id.nsg_mall_top2);
        moreGoods = (TextView) top2_item_view.findViewById(R.id.more_goods);


        View mall_foot_item = View.inflate(mactivity, R.layout.mall_foot_item, null);
        mall_foot_item_gv = (NoScrollGridView) mall_foot_item.findViewById(R.id.mall_foot_item_gv);

        lvMallPager.addFooterView(mall_foot_item);
        lvMallPager.addHeaderView(headView);

        lvMallPager.addHeaderView(top_item_view);
        lvMallPager.addHeaderView(top2_item_view);

        vpMallHead.measure(0, 0);//先测量

        vpMallHeadHeight = vpMallHead.getMeasuredHeight();

        setListener();
        vpMallHead.startAutoScroll();
        vpMallHead.setInterval(2000);
        return view;
    }

    private void setListener() {
//        lvMallPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                showTitle();
//            }
//        });
        location = new int[2];
        lvMallPager.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem==0) {
                    showTitle();
                }else{
                    mall_home_title.getBackground().setAlpha(255);
                }

            }
        });

    }

    @Override
    public void initData() {
        super.initData();
        //textView.setText("商城页面");
        String jsonData = SpUtils.getInitialize(mactivity.getApplicationContext()).getJsonData(NetUri.MAll_LIST);
        if (!jsonData.equals("") && jsonData != null) {
            mallGoodsInfos = new Gson().fromJson(jsonData, MallGoodsInfos.class);
            initHead();
        }
        new InterNetConn<MallGoodsInfos>(NetUri.MAll_LIST, mactivity, MallGoodsInfos.class,true);

        new InterNetConn<RecommendGoodsBean>(NetUri.MAll_RECOMMENDGOODS_LIST, mactivity, RecommendGoodsBean.class);

    }


    /**
     * EventBus 接收联网请求 实体类
     * 热销产品 列表
     *
     * @param event
     */
    public void onEventMainThread(RecommendGoodsBean event) {
        Log.e("TAG", "onEventMainThread");
        recommendGoodsBean = event;
        final List<RecommendGoodsBean.GoodsListEntity> goodsList = recommendGoodsBean.getGoodsList();
        mall_foot_item_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mactivity, WebViewActivity.class);
                intent.putExtra("URL", goodsList.get(position).getUrl());
                mactivity.startActivity(intent);
            }
        });
        mall_foot_item_gv.setAdapter(new MyBaseAdapter<RecommendGoodsBean.GoodsListEntity>(goodsList, mactivity) {

            @Override
            public View MyInstantiateItem(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    convertView = View.inflate(mactivity, R.layout.mall_foot_list_goods, null);
                    holder = new ViewHolder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                RecommendGoodsBean.GoodsListEntity item = data.get(position);

                loadImage(holder.footGoodaImg, item.getImage());

                holder.footGoodaTitle.setText(item.getName());
                double minSalePrice = (double) item.getMinSalePrice() / 100;
                holder.footGoodaPrive.setText("￥" + minSalePrice);

                return convertView;
            }


            class ViewHolder {
                ImageView footGoodaImg;
                TextView footGoodaTitle;
                TextView footGoodaPrive;

                public ViewHolder(View view) {
                    footGoodaImg = (ImageView) view.findViewById(R.id.foot_gooda_img);
                    footGoodaTitle = (TextView) view.findViewById(R.id.foot_gooda_title);
                    footGoodaPrive = (TextView) view.findViewById(R.id.foot_gooda_prive);
                }
            }
        });


    }

    /**
     * EventBus 接收联网请求 实体类
     *
     * @param event
     */
    public void onEventMainThread(MallGoodsInfos event) {
        Log.e("TAG", "onEventMainThread");
        mallGoodsInfos = event;
        initHead();
        initTop();
        initTop2();
        initListView();

    }


    /**
     * 判断顶部轮播图是否完全显示
     * 要用ListView和ViewPager部分比较
     *
     * @return 当ListView在屏幕上的Y轴坐标小于或者等于顶部轮播图在Y轴的坐标的时候，顶部轮播图完全显示了
     */
    private boolean isDisplayTopNews() {

        if (vpMallHead != null) {

            //得到ListView在屏幕上的Y轴坐标


            //顶部ViewPager部分在屏幕上的Y轴坐标
            vpMallHead.getLocationOnScreen(location);
            int mtopViewPagerOnScreenY = location[1];

//        if(mListViewOnScreenY <= mtopViewPagerOnScreenY){
//            return  true;
//        }else{
//            return  false;
//        }

            return mtopViewPagerOnScreenY>=0;
        }

        return true;


    }


    /**
     * 标题变色
     *
     * @param
     */
    public void showTitle() {
        Log.e("yag", "ymy");

            //顶部ViewPager部分在屏幕上的Y轴坐标
            vpMallHead.getLocationOnScreen(location);
            int mtopViewPagerOnScreenY = location[1];
            Log.e("vpMallHeadHeight-------", vpMallHeadHeight + "");
            if (mtopViewPagerOnScreenY < -50) {
                mall_home_title.setVisibility(View.VISIBLE);
                float i =-((float)mtopViewPagerOnScreenY)/500;
                if(50-mtopViewPagerOnScreenY<=255) {
                    mall_home_title.getBackground().setAlpha(50-mtopViewPagerOnScreenY);
                }

            } else {
                mall_home_title.setVisibility(View.GONE);

            }



    }


    /**
     * 初始化listvierw
     */
    private void initListView() {

        final List<MallGoodsInfos.CategoryEntity> category = mallGoodsInfos.getCategory();
        lvMallPager.setAdapter(new MyBaseAdapter(category, mactivity) {
            @Override
            public View MyInstantiateItem(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder = null;
                if (convertView == null) {
                    holder = new ViewHolder();


                    convertView = View.inflate(mactivity, R.layout.mall_item, null);


                    holder.textLeftItem = (ImageView) convertView.findViewById(R.id.text_left_item);
                    holder.textItem = (TextView) convertView.findViewById(R.id.text_item);
                    holder.mallItemBigGoods = (ImageView) convertView.findViewById(R.id.mall_item_big_goods);
                    holder.mallItemBigGoods.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mactivity, WebViewActivity.class);
                            intent.putExtra("URL", mallGoodsInfos.getCategory().get(position).getImageUrl());
                            mactivity.startActivity(intent);
                        }
                    });
                    holder.mallItemGv = (NoScrollGridView) convertView.findViewById(R.id.mall_item_gv);

                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                final MallGoodsInfos.CategoryEntity item = category.get(position);

                holder.mallItemGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(mactivity, WebViewActivity.class);
                        intent.putExtra("URL", "http://m.mtime.cn/#!/commerce/item/"+item.getSubList().get(position).getGoodsId()+"/");
                        mactivity.startActivity(intent);
                    }
                });
//                String trim = item.getColorValue().replace("#", "").trim();
//
//                int b = Integer.parseInt(trim,16);
//                holder.textLeftItem.setBackgroundColor(b);
                holder.textItem.setText(item.getName());
                loadImage(holder.mallItemBigGoods, item.getImage());

                holder.mallItemGv.setAdapter(new MyBaseAdapter<MallGoodsInfos.CategoryEntity.SubListEntity>(item.getSubList(), mactivity) {
                    @Override
                    public View MyInstantiateItem(int position, View convertView, ViewGroup parent) {
                        MallGoodsInfos.CategoryEntity.SubListEntity subList = data.get(position);

                        convertView = View.inflate(mactivity, R.layout.mall_list_inner_small_goods, null);

                        ImageView goodaImg = (ImageView) convertView.findViewById(R.id.gooda_img);
                        TextView gooda_title = (TextView) convertView.findViewById(R.id.gooda_title);
                        TextView goodaPrive = (TextView) convertView.findViewById(R.id.gooda_prive);

                        loadImage(goodaImg, subList.getImage());

                        gooda_title.setText(subList.getTitle());
                        return convertView;
                    }
                });


                return convertView;
            }

            class ViewHolder {
                private ImageView textLeftItem;
                private TextView textItem;
                private ImageView mallItemBigGoods;
                private NoScrollGridView mallItemGv;


            }
        });


    }

    /**
     * 初始化头部以下第二条
     */
    private void initTop2() {


        final List<MallGoodsInfos.TopicEntity> topic = mallGoodsInfos.getTopic();
        List<MallGoodsInfos.TopicEntity> myTopic=new ArrayList<MallGoodsInfos.TopicEntity>();

        myTopic.addAll(topic);

        Log.e("myTopic",(myTopic==topic)+"");
        //创建默认的线性LayoutManager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mactivity);
        hlvMallTop2.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        //hlvMallTop2.setHasFixedSize(true);

        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

//创建并设置Adapter
        final RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(myTopic, mactivity);
        mAdapter.setOnItemClickListener(new RecyclerViewAdapter.MyOnItemClickListener() {
            @Override
            public void onItemClick(ImageView view, int position) {
                MyBaseAdapter adapter = (MyBaseAdapter<MallGoodsInfos.TopicEntity.SubListEntity>) nsgMallTop2.getAdapter();
                List<MallGoodsInfos.TopicEntity.SubListEntity> data = adapter.getData();

                data.clear();
                data.addAll(mallGoodsInfos.getTopic().get(position).getSubList());
//                if(position==0) {
//                    data.addAll(mallGoodsInfos.getTopic().get(0).getSubList());
//                }
                //hlvMallTop2.setLayoutDirection();
                Log.e("position","position=="+position+"     mallGoodsInfos.getTopic().get("+position+").getSubList()=="+mallGoodsInfos.getTopic().get(position).getSubList());
                Log.e("position","position=="+position+"     data=="+data.size());
                loadImage(top2_bg, mallGoodsInfos.getTopic().get(position).getBackgroupImage());
                adapter.notifyDataSetChanged();
                loadImage(view, mallGoodsInfos.getTopic().get(position).getCheckedImage());
                if(oldView!=null&&oldPosition!=position) {
                    loadImage(oldView, mallGoodsInfos.getTopic().get(oldPosition).getUncheckImage());
                }
                oldView = view;
                oldPosition = position;
            }
        });

        hlvMallTop2.setAdapter(mAdapter);
        final List<MallGoodsInfos.TopicEntity.SubListEntity> subList2 = mallGoodsInfos.getTopic().get(0).getSubList();
        final List<MallGoodsInfos.TopicEntity.SubListEntity> subList = new ArrayList<>();
        subList.addAll(subList2);
        nsgMallTop2.setAdapter(new MyBaseAdapter<MallGoodsInfos.TopicEntity.SubListEntity>(subList, mactivity) {
            @Override
            public View MyInstantiateItem(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(mactivity, R.layout.mall_list_inner_small_goods, null);

                ImageView goodaImg = (ImageView) convertView.findViewById(R.id.gooda_img);
                TextView gooda_title = (TextView) convertView.findViewById(R.id.gooda_title);
                TextView goodaPrive = (TextView) convertView.findViewById(R.id.gooda_prive);

                loadImage(goodaImg, data.get(position).getImage());

                gooda_title.setText(data.get(position).getTitle());

                return convertView;
            }
        });
        nsgMallTop2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyBaseAdapter<MallGoodsInfos.TopicEntity.SubListEntity> adapter = (MyBaseAdapter<MallGoodsInfos.TopicEntity.SubListEntity>) nsgMallTop2.getAdapter();
                List<MallGoodsInfos.TopicEntity.SubListEntity> data = adapter.getData();

                int goodsId = data.get(position).getGoodsId();
                Intent intent = new Intent(mactivity, WebViewActivity.class);
                intent.putExtra("URL", "http://m.mtime.cn/#!/commerce/item/"+goodsId+"/");
                mactivity.startActivity(intent);
            }
        });

//                imageView = new ImageView(mactivity);
//                String backgroupImage = topic.get(position).getBackgroupImage();
//                loadImage(imageView, backgroupImage);
//                llTop2Bg.setBackground(imageView.getDrawable());

    }

    /**
     * 初始化头部以下第-条
     */
    private void initTop() {
        String imgA = mallGoodsInfos.getCellA().getImg();
        Glide.with(mactivity)
                .load(imgA)
                .into(mallShopA);
        String imgB = mallGoodsInfos.getCellB().getImg();
        Glide.with(mactivity)
                .load(imgB)
                .into(mallShopB);
        String imgC1 = mallGoodsInfos.getCellC().getList().get(0).getImage();
        Glide.with(mactivity)
                .load(imgC1)
                .into(mallShopC1);
        String imgC2 = mallGoodsInfos.getCellC().getList().get(1).getImage();
        Glide.with(mactivity)
                .load(imgC2)
                .into(mallShopC2);


    }

    /**
     * 初始化头部
     */
    private void initHead() {

        final ArrayList<String> Urls = new ArrayList<>();
        String baseUrl = MallURlL.STOREPAGERSERCHER_KEY + MallURlL.STOREPAGERSERCHER_INDEX;
        Urls.add(baseUrl+MallURlL.STOREPAGERTOY_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERDIDITAL_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERDRESS_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERHOME_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERMOVIETIME_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERNEW_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERPRESELL_LAST);
        Urls.add(baseUrl+MallURlL.STOREPAGERLOWPRICE_LAST);

        final List<MallGoodsInfos.ScrollImgEntity> scrollImg = mallGoodsInfos.getScrollImg();

        ngvMallHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mactivity, MallBaseActivity.class);
                intent.putExtra("URL", Urls.get(position));
                mactivity.startActivity(intent);
            }
        });


        vpMallHead.setAdapter(new MyPagerAdapter(scrollImg, mactivity) {
            @Override
            public Object MyInstantiateItem(ViewGroup container, final int position) {

                ImageView imageView = new ImageView(mactivity);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mactivity, WebViewActivity.class);
                        if(position==3) {

                            intent.putExtra("URL","http://mall.wv.mtime.cn/"+ mallGoodsInfos.getScrollImg().get(position).getUrl());
                            Log.e("tag", "我就绿卡看==");
                            mactivity.startActivity(intent);
                            return;
                        }
                        Log.e("tag","我就绿卡看222==");
                        intent.putExtra("URL",mallGoodsInfos.getScrollImg().get(position).getUrl());
                        mactivity.startActivity(intent);
                    }
                });
                Log.e("tag", "scrollImg.size()==" + scrollImg.size());

                Glide.with(mactivity)
                        .load(scrollImg.get(position).getImage())
                        .into(imageView);

                container.addView(imageView);

                return imageView;
            }
        });

        final List<MallGoodsInfos.NavigatorIconEntity> navigatorIcon = mallGoodsInfos.getNavigatorIcon();


        ngvMallHead.setAdapter(new MyBaseAdapter(navigatorIcon, mactivity) {
            @Override
            public View MyInstantiateItem(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(mactivity, R.layout.mall_item_head_item, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_inner);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_inner);
                textView.setText(navigatorIcon.get(position).getIconTitle());
                Glide.with(mactivity)
                        .load(navigatorIcon.get(position).getImage())
                        .into(imageView);
                return convertView;
            }
        });
        Indicator.setViewPager(vpMallHead,scrollImg.size());
    }

    /**
     * @param imageView - 图片容器
     * @param url       -图片地址
     */
    private void loadImage(ImageView imageView, String url) {
        Glide.with(mactivity)
                .load(url)
                .into(imageView);
    }

    /**
     * @param imageView  - 图片容器
     * @param url        -图片地址
     * @param resourceId 默认显示图片
     */
    private void loadImage(ImageView imageView, String url, int resourceId) {
        Glide.with(mactivity)
                .load(url)
                .fallback(resourceId)
                .into(imageView);
    }

    @Override
    public void onClick(View view) {
        Intent intent2 = new Intent(mactivity,WebViewActivity.class);

        switch (view.getId()) {

            case R.id.ib_mall_home_scan:
                //TODO implement
                break;
            case R.id.ib_mall_home_cart:
                //TODO implement
                break;

            case R.id.mall_shopC1:
                intent2.putExtra("URL", mallGoodsInfos.getCellC().getList().get(0).getUrl());
                mactivity.startActivity(intent2);
                break;
            case R.id.mall_shopC2 :
                intent2.putExtra("URL", mallGoodsInfos.getCellC().getList().get(1).getUrl());
                mactivity.startActivity(intent2);
                break;
            case R.id.mall_shopB:
                intent2.putExtra("URL", mallGoodsInfos.getCellB().getUrl());
                mactivity.startActivity(intent2);
                break;
            case R.id.mall_shopA:
                intent2.putExtra("URL", mallGoodsInfos.getCellA().getUrl());
                mactivity.startActivity(intent2);
                break;

            case R.id.tv_title_search:
                Intent intent = new Intent(mactivity,SearchGoodsActivity.class);
                mactivity.startActivity(intent);
                break;
        }


    }


}
