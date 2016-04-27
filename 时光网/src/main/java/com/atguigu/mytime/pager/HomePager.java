package com.atguigu.mytime.pager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.Utils.LogUtils;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.activity.AdvListActivity;
import com.atguigu.mytime.activity.MainActivity;
import com.atguigu.mytime.activity.NewsImageActivity;
import com.atguigu.mytime.activity.SeekActivity;
import com.atguigu.mytime.activity.SelecorCityActivity;
import com.atguigu.mytime.activity.homewebview.Share_news_Activity;
import com.atguigu.mytime.activity.homewebview.Shop_WebView_Activity;
import com.atguigu.mytime.adapter.ADViewPagerAdapter;
import com.atguigu.mytime.adapter.HomeContentAdapter;
import com.atguigu.mytime.adapter.HorizontalListViewAdapter;
import com.atguigu.mytime.adapter.TopViewPagerAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.entity.AdvData;
import com.atguigu.mytime.entity.HomeListViewInfo;
import com.atguigu.mytime.entity.HomeShopInfo;
import com.atguigu.mytime.entity.HorizontalListViewInfo;
import com.atguigu.mytime.entity.LocationCityEntity;
import com.atguigu.mytime.entity.OneGetData;
import com.atguigu.mytime.net.InterNetConn;
import com.atguigu.mytime.net.OkhttpUtils2;
import com.atguigu.mytime.service.LocationCityService;
import com.atguigu.mytime.view.HorizontalListView;
import com.atguigu.mytime.view.NoScrollViewPager;
import com.atguigu.refreshlistview.RefreshListView;
import com.bumptech.glide.Glide;
import com.zf.myzxing.CaptureActivity;
import com.zhy.android.percent.support.PercentFrameLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/4/7.
 * 主页面
 */
public class HomePager extends BasePager {
    private static final int WHAT_TOP_PAGER = 0;
    private static final int WHAT_SHOP_PAGER = 1;
    private static final int SCANNIN_GREQUEST_CODE = 666;
    public static final int RESULT_CITY_IDANDNAME = 888;
    private static final String TAG = HomePager.class.getSimpleName();
    private final EventBus eventBus;
    private boolean isrefresh = false;//是否下拉刷新
    private int index = 1;//下拉刷新次数


    //服务
    private NetReceiver receiver;
    private ViewPager vpTitle;
    private LinearLayout llSeek;
    private TextView tvSeek;
    private ImageView imDimension;
    private TextView tvState01;
    private TextView tvSelectorCity01;
    private TextView tvState02;

    private TextView tv_seniority01;
    private TextView tv_seniority02;
    private TextView tv_seniority03;
    private TextView tv_seniority04;

    private HorizontalListView horizonListview;
    private ViewPager vpAd;
    private LinearLayout ll_shop_point;
    private LinearLayout indicator;

    private LinearLayout tv_cinema;//同城影院
    private LinearLayout tv_onshow;//即将上映


    private RelativeLayout tvAllShop;
    private PercentFrameLayout llShop01;
    private ImageView imShop01;
    private TextView tvShopName01;
    private TextView tvNameState01;
    private PercentFrameLayout llShop02;
    private ImageView imShop02;
    private TextView tvShopName02;
    private TextView tvNameState02;
    private PercentFrameLayout llShop03;
    private ImageView imShop03;
    private TextView tvShopName03;
    private TextView tvNameState03;
    private PercentFrameLayout llShop04;
    private ImageView imShop04;
    private TextView tvShopName04;
    private TextView tvNameState04;
    private PercentFrameLayout llShop05;
    private ImageView imShop05;
    private TextView tvShopName05;
    private TextView tvNameState05;
    private PercentFrameLayout llShop06;
    private ImageView imShop06;
    private TextView tvShopName06;
    private TextView tvNameState06;
    private TextView tv_all_shops;
    private TextView tv_all_movie;
    private ArrayList<HomeShopInfo.TopPostersEntity> topPosters;
    private HomeShopInfo.AreaSecondEntity areaSecond;
    //判断是否有网络
    private boolean connected;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_TOP_PAGER:
                    vpTitle.setCurrentItem(vpTitle.getCurrentItem() + 1, true);
                    handler.sendEmptyMessageDelayed(WHAT_TOP_PAGER, 3000);
                    break;
                case WHAT_SHOP_PAGER:
                    vpAd.setCurrentItem(vpAd.getCurrentItem() + 1, true);
                    handler.sendEmptyMessageDelayed(WHAT_SHOP_PAGER, 3000);
                    break;

            }
        }
    };
    private ArrayList<HomeShopInfo.AdvListEntity> advList;
    private ArrayList<HomeListViewInfo.DataEntity> data;
    private HomeContentAdapter listbuttomadapter;
    private Intent intent;
    private int cityId;
    private ImageView loading_failed;
    private RefreshListView lv_home;
    private RelativeLayout gif_loading;

    private void findViews(View view) {
        vpTitle = (NoScrollViewPager) view.findViewById(R.id.vp_title);
        llSeek = (LinearLayout) view.findViewById(R.id.ll_seek);
        indicator = (LinearLayout) view.findViewById(R.id.indicator);
        tvSeek = (TextView) view.findViewById(R.id.tv_seek);
        imDimension = (ImageView) view.findViewById(R.id.im_dimension);
        tvState01 = (TextView) view.findViewById(R.id.tv_state01);
        tvSelectorCity01 = (TextView) view.findViewById(R.id.tv_selector_city01);
        tvState02 = (TextView) view.findViewById(R.id.tv_state02);
        horizonListview = (HorizontalListView) view.findViewById(R.id.horizon_listview);
        vpAd = (NoScrollViewPager) view.findViewById(R.id.vp_ad);

        ll_shop_point = (LinearLayout) view.findViewById(R.id.ll_shop_point);
        tvAllShop = (RelativeLayout) view.findViewById(R.id.tv_All_shop);
        imShop01 = (ImageView) view.findViewById(R.id.im_shop01);
        tvShopName01 = (TextView) view.findViewById(R.id.tv_shop_name01);
        tvNameState01 = (TextView) view.findViewById(R.id.tv_name_state01);
        llShop02 = (PercentFrameLayout) view.findViewById(R.id.ll_shop02);
        imShop02 = (ImageView) view.findViewById(R.id.im_shop02);
        llShop01 = (PercentFrameLayout) view.findViewById(R.id.ll_shop01);
        tvShopName02 = (TextView) view.findViewById(R.id.tv_shop_name02);
        tvNameState02 = (TextView) view.findViewById(R.id.tv_name_state02);
        llShop03 = (PercentFrameLayout) view.findViewById(R.id.ll_shop03);
        imShop03 = (ImageView) view.findViewById(R.id.im_shop03);
        tvShopName03 = (TextView) view.findViewById(R.id.tv_shop_name03);
        tvNameState03 = (TextView) view.findViewById(R.id.tv_name_state03);
        llShop04 = (PercentFrameLayout) view.findViewById(R.id.ll_shop04);
        imShop04 = (ImageView) view.findViewById(R.id.im_shop04);
        tvShopName04 = (TextView) view.findViewById(R.id.tv_shop_name04);
        tvNameState04 = (TextView) view.findViewById(R.id.tv_name_state04);
        llShop05 = (PercentFrameLayout) view.findViewById(R.id.ll_shop05);
        imShop05 = (ImageView) view.findViewById(R.id.im_shop05);
        tvShopName05 = (TextView) view.findViewById(R.id.tv_shop_name05);
        tvNameState05 = (TextView) view.findViewById(R.id.tv_name_state05);
        llShop06 = (PercentFrameLayout) view.findViewById(R.id.ll_shop06);
        imShop06 = (ImageView) view.findViewById(R.id.im_shop06);
        tvShopName06 = (TextView) view.findViewById(R.id.tv_shop_name06);
        tvNameState06 = (TextView) view.findViewById(R.id.tv_name_state06);

        tv_seniority01 = (TextView) view.findViewById(R.id.tv_seniority01);
        tv_seniority02 = (TextView) view.findViewById(R.id.tv_seniority02);
        tv_seniority03 = (TextView) view.findViewById(R.id.tv_seniority03);
        tv_seniority04 = (TextView) view.findViewById(R.id.tv_seniority04);

        tv_cinema = (LinearLayout) view.findViewById(R.id.tv_cinema);
        tv_onshow = (LinearLayout) view.findViewById(R.id.tv_onshow);
        tv_all_shops = (TextView) view.findViewById(R.id.tv_all_shops);
        tv_all_movie = (TextView) view.findViewById(R.id.tv_all_movie);


        String city_name = SpUtils.getInitialize(mactivity.getApplicationContext()).getValue(SpUtils.CITY_NAME, "");
        cityId = SpUtils.getInitialize(mactivity.getApplicationContext()).getValue(SpUtils.CITY_ID, 0);
        LogUtils.d(TAG,cityId+"");

        tv_seniority01.setOnClickListener(new SeniorityOnClickListener());
        tv_seniority02.setOnClickListener(new SeniorityOnClickListener());
        tv_seniority03.setOnClickListener(new SeniorityOnClickListener());
        tv_seniority04.setOnClickListener(new SeniorityOnClickListener());
        //时光精选
        tv_all_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainPager(3);
            }
        });
        //所有商品
        tv_all_shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainPager(2);
            }
        });
        //共多少不电影
        tvState02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainPager(1);
            }
        });

        //即将上线
        tv_onshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainPager(1);
            }
        });
        //同城影院
        tv_cinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainPager(1);
            }
        });
        //电影商城
        tvAllShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMainPager(2);
            }
        });
        if (!TextUtils.isEmpty(city_name)) {
            tvSelectorCity01.setText(city_name);
        }
        tvSelectorCity01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mactivity, SelecorCityActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mactivity.startActivityForResult(intent, RESULT_CITY_IDANDNAME);

            }
        });

        tvSeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mactivity, SeekActivity.class);
                mactivity.startActivity(intent);
            }
        });
        loading_failed.setOnClickListener(new MyImageOnClickListener());
        //点击扫描二维码
        imDimension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mactivity, CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mactivity.startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
            }
        });
    }

    private void setMainPager(int pager) {
        MainActivity mainActivity = (MainActivity) mactivity;
        mainActivity.setPosition(pager);
    }


    public HomePager(Activity mactivity) {
        super(mactivity);

        eventBus = EventBus.getDefault();
        eventBus.register(this);
    }

    @Override
    public View initView() {
        SpUtils.getInitialize(mactivity.getApplicationContext()).save(SpUtils.GUIDE, true);
        setReceicer();
        //检查网络
        View view = View.inflate(mactivity, R.layout.home_pager, null);
        View headView = View.inflate(mactivity, R.layout.home_pager_title, null);
        findViews01(view);
        findViews(headView);
        //查询一启动就启动动画
        getNetConnected();
        //加载头文件
        lv_home.addHeaderView(headView);
        //开启服务定位城市
        startLocationCity();
        return view;
    }

    private void findViews01(View view) {
        loading_failed = (ImageView)view.findViewById(R.id.loading_failed);
        lv_home =(RefreshListView)view.findViewById(R.id.lv_home);
        gif_loading = (RelativeLayout)view.findViewById(R.id.gif_loading);

    }

    private void getNetConnected() {
        connected = MessageUtils.isConnected(mactivity);
        if (connected) {
            loading_failed.setVisibility(View.GONE);
        } else {
            loading_failed.setVisibility(View.VISIBLE);
        }
    }

    private void startLocationCity() {
        intent = new Intent(mactivity, LocationCityService.class);
        mactivity.startService(intent);
    }

    /**
     * 根据经纬度城市定位
     *
     * @param cityname
     */
    public void onEventMainThread(StringBuffer cityname) {
        if (!TextUtils.isEmpty(cityname)) {
            String name = cityname.toString();
            String[] split = name.split(",");
            String cityurl = NetUri.LOCATION_CITY + split[1] + "&latitude=" + split[2] + "&cityName=";
            new InterNetConn(cityurl, mactivity, LocationCityEntity.class);
        }
    }

    public void onEventMainThread(LocationCityEntity cityEntity) {
        if (!TextUtils.isEmpty(cityEntity.getName())) {
            String cityname = tvSelectorCity01.getText().toString().trim();
            if (!cityname.equals(cityEntity.getName())) {
                showSelectorCityDailog(cityEntity);
            }
        }
    }

    private void showSelectorCityDailog(final LocationCityEntity cityEntity) {
        String locationcitymessage = mactivity.getString(R.string.locationcitymessage);
        locationcitymessage += cityEntity.getName() + R.string.cityMessage;
        new AlertDialog.Builder(mactivity)
                .setMessage(locationcitymessage)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSelectorCity01.setText(cityEntity.getName());
                        cityId = cityEntity.getCityId();
                        SpUtils.getInitialize(mactivity.getApplicationContext()).save(SpUtils.CITY_NAME, cityEntity.getName());
                        SpUtils.getInitialize(mactivity.getApplicationContext()).save(SpUtils.CITY_ID, cityId);
                        topPosters=null;
                        advList=null;
                        data=null;
                        getNetData();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }


    /**
     * 没网状态图片的点击监听
     */
    class MyImageOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            gif_loading.setVisibility(View.VISIBLE);
            getNetData();
        }
    }

    /**
     * 联网请求数据
     */
    private void getNetData() {
        new InterNetConn(NetUri.HOME_SHOP, mactivity, HomeShopInfo.class);//ViewPager数据
    }

    public void onEventMainThread(OneGetData isconnected) {
        if (!isconnected.isObtinData()) {
            gif_loading.setVisibility(View.GONE);
            MessageUtils.showMessage(mactivity, "连接数据失败");
        } else {
            gif_loading.setVisibility(View.GONE);
        }
    }

    /**
     * 获取广告数据
     *
     * @param advData
     */
    public void onEventMainThread(AdvData advData) {
        setShowADVDailog(advData);
    }

    /**
     * 显示广告
     *
     * @param advData
     */
    private void setShowADVDailog(AdvData advData) {
        if (!TextUtils.isEmpty(advData.getImg())) {
            Intent intent = new Intent(mactivity, AdvListActivity.class);
            intent.putExtra("advertisementURL", advData.getBigImg());
            mactivity.startActivity(intent);
        }
    }


    /**
     * 横向的ListView信息
     *
     * @param listViewInfo
     */
    public void onEventMainThread(HorizontalListViewInfo listViewInfo) {
        setHorizontalListView(listViewInfo);
        //请求底部的数据
        new InterNetConn(NetUri.HOME_BUTTOM, mactivity, HomeListViewInfo.class);//底部ListView

    }

    /**
     * 电影商品+顶部的ViewPager
     *
     * @param shopInfo
     */
    public void onEventMainThread(HomeShopInfo shopInfo) {
        //数据加载成功隐藏帧动画
        setTopViewPager(shopInfo);
        //请求横向的数据
        new InterNetConn(NetUri.HLISTVIEW + cityId, mactivity, HorizontalListViewInfo.class);//横向LsitView的数据
    }

    /**
     * 底部ListView数据装配
     *
     * @param listViewInfo
     */
    public void onEventMainThread(HomeListViewInfo listViewInfo) {
        if (!isrefresh) {
            //请求广告数据
            new InterNetConn(NetUri.ADV_LIST, mactivity, AdvData.class);
            setButtomContent(listViewInfo);
        } else {
            refreshListData(listViewInfo);
        }
    }

    /**
     * 下拉刷新更新listView数据
     *
     * @param listViewInfo
     */
    private void refreshListData(HomeListViewInfo listViewInfo) {
        ArrayList<HomeListViewInfo.DataEntity> datainfo = (ArrayList<HomeListViewInfo.DataEntity>) listViewInfo.getData();
        if (datainfo.size() > 0 && datainfo != null) {
            data.addAll(datainfo);
            //定位ListView到item的最后一条
            listbuttomadapter.setData(datainfo);
            lv_home.setSelection(lv_home.getLastVisiblePosition() + 1);
            //listbuttomadapter.notifyDataSetChanged();
            isrefresh = true;
        }
    }

    /**
     * 装配底部ListView数据
     *
     * @param listInfo
     */
    private void setButtomContent(HomeListViewInfo listInfo) {
        data = (ArrayList<HomeListViewInfo.DataEntity>) listInfo.getData();
        if (data.size() > 0 && data != null) {
            listbuttomadapter = new HomeContentAdapter(mactivity, data);
            lv_home.setAdapter(listbuttomadapter);
            //设置下拉刷新
            lv_home.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
                @Override
                public void onPullDownRefresh() {

                }


                @Override
                public void onLoadMore() {
                    isrefresh = true;
                    index++;
                    String url = NetUri.HOME_BUTTOM_REF + index;
                    new InterNetConn(url, mactivity, HomeListViewInfo.class);
                }
            });
            lv_home.onFinishRefresh(true);

            lv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    HomeListViewInfo.DataEntity dataEntity = (HomeListViewInfo.DataEntity) listbuttomadapter.getItem(position-2);
                    switch (dataEntity.getTag()) {
                        case "图集":
                            List<HomeListViewInfo.DataEntity.ImagesEntity> images = dataEntity.getImages();
                            intent = new Intent(mactivity, NewsImageActivity.class);
                            intent.putExtra("images", (Serializable) images);
                            intent.putExtra("id", dataEntity.getId());
                            intent.putExtra("title", dataEntity.getTitle());
                            mactivity.startActivity(intent);
                            break;
                        case "简讯":
                            if (dataEntity.getDataType() == 0) {
                                Intent intent = new Intent(mactivity, Share_news_Activity.class);
                                intent.putExtra("id", dataEntity.getId());
                                mactivity.startActivity(intent);
                            } else if (dataEntity.getDataType() == 1) {
                                images = dataEntity.getImages();
                                intent = new Intent(mactivity, NewsImageActivity.class);
                                intent.putExtra("images", (Serializable) images);
                                intent.putExtra("id", dataEntity.getId());
                                intent.putExtra("title", dataEntity.getTitle());
                                mactivity.startActivity(intent);
                            }
                            break;
                        case "影评":

                            break;
                        case "头条":
                            break;
                        case "猜电影":
                            break;
                        case "欧美新片":
                            break;
                        case "海外佳片":
                            break;
                        case "获奖佳片":
                        case "日韩新片":
                        case "精彩短片":
                        case "港台佳片":
                            break;

                    }
                }
            });

        }
    }

    /**
     * 装配顶部的ViewPager
     *
     * @param shopInfo
     */
    private void setTopViewPager(HomeShopInfo shopInfo) {
        indicator.removeAllViews();//清空视图
        ll_shop_point.removeAllViews();

        int li = MessageUtils.dip2px(mactivity, 5);
        topPosters = (ArrayList<HomeShopInfo.TopPostersEntity>) shopInfo.getTopPosters();//顶部图片
        areaSecond = shopInfo.getAreaSecond();//商品

        advList = (ArrayList<HomeShopInfo.AdvListEntity>) shopInfo.getAdvList();
        if (topPosters != null && topPosters.size() > 0) {
            //联网请求成功
            gif_loading.setVisibility(View.GONE);
            for (int i = 0; i < topPosters.size(); i++) {
                ImageView imageView = new ImageView(mactivity);
                imageView.setImageResource(R.drawable.img_point_selector);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(li, li);
                if (i != 0) {
                    params.leftMargin = li;
                    imageView.setEnabled(true);
                } else {
                    imageView.setEnabled(false);
                }
                indicator.addView(imageView, params);
            }
            //装配数据
            vpTitle.setAdapter(new TopViewPagerAdapter(topPosters, mactivity));
            //设置初始显示的下标
            int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % topPosters.size();
            vpTitle.setCurrentItem(item, true);
            vpTitle.addOnPageChangeListener(new MyOnPageChangeListener02());
            handler.sendEmptyMessage(WHAT_TOP_PAGER);

        }else {
            loading_failed.setVisibility(View.VISIBLE);
        }
        if (advList.size() > 0 && advList != null) {
            for (int i = 0; i < advList.size(); i++) {
                ImageView imageView = new ImageView(mactivity);
                imageView.setImageResource(R.drawable.img_point_selector);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(li, li);
                if (i != 0) {
                    params.leftMargin = li;
                    imageView.setEnabled(true);
                } else {
                    imageView.setEnabled(false);
                }
                ll_shop_point.addView(imageView, params);
            }
            vpAd.setAdapter(new ADViewPagerAdapter(advList, mactivity));

            vpAd.addOnPageChangeListener(new MyOnPageChangeListener());
            handler.sendEmptyMessage(WHAT_SHOP_PAGER);

        }
        setShopImage(areaSecond);
    }

    /**
     * ViewPager滚动监听
     */
    class MyOnPageChangeListener02 implements ViewPager.OnPageChangeListener {
        private int setposition;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //得到真实下标
            position = position % topPosters.size();
            indicator.getChildAt(setposition).setEnabled(true);
            indicator.getChildAt(position).setEnabled(false);
            setposition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * ViewPager滚动监听
     */
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        private int setposition;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            //得到真实下标
            position = position % advList.size();
            ll_shop_point.getChildAt(setposition).setEnabled(true);
            ll_shop_point.getChildAt(position).setEnabled(false);
            setposition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    /**
     * 装配横向的ListView
     */
    private void setHorizontalListView(HorizontalListViewInfo listViewInfo) {
        List<HorizontalListViewInfo.MoviesEntity> movies = listViewInfo.getMovies();
        if (movies.size() > 0 && movies != null) {
            tvState02.setText("共" + movies.size() + "部");
            horizonListview.setAdapter(new HorizontalListViewAdapter(movies, mactivity));
        }
    }

    @Override
    public void initData() {
        super.initData();
        getNetData();

    }


    private void setShopImage(HomeShopInfo.AreaSecondEntity areaSecond) {
        //商品一
        final HomeShopInfo.AreaSecondEntity.SubFirstEntity subFirst = areaSecond.getSubFirst();
        tvShopName01.setTextColor(Color.parseColor(subFirst.getTitleColor()));
        tvShopName01.setText(subFirst.getTitle());
        tvNameState01.setText(subFirst.getTitleSmall());
        Glide.with(mactivity).load(subFirst.getImage2()).into(imShop01);

        imShop01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = subFirst.getGotoPage().getUrl();
                Intent intent = new Intent(mactivity, Shop_WebView_Activity.class);
                intent.putExtra("url", url);
                mactivity.startActivity(intent);
            }
        });
        //商品二
        final HomeShopInfo.AreaSecondEntity.SubSecondEntity subSecond = areaSecond.getSubSecond();
        tvShopName02.setTextColor(Color.parseColor(subSecond.getTitleColor()));
        tvShopName02.setText(subSecond.getTitle());
        tvNameState02.setText(subSecond.getTitleSmall());
        Glide.with(mactivity).load(subSecond.getImage2()).into(imShop02);
        imShop02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = subSecond.getGotoPage().getUrl();
                Intent intent = new Intent(mactivity, Shop_WebView_Activity.class);
                intent.putExtra("url", url);
                mactivity.startActivity(intent);
            }
        });
        /*imShop03
        imShop04*/
        //商品五
        final HomeShopInfo.AreaSecondEntity.SubThirdEntity subThird = areaSecond.getSubThird();
        Glide.with(mactivity).load(subThird.getImage2()).into(imShop05);
        tvShopName05.setTextColor(Color.parseColor(subThird.getTitleColor()));
        tvShopName05.setText(subThird.getTitle());
        tvNameState05.setText(subThird.getTitleSmall());
        imShop05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = subThird.getGotoPage().getUrl();
                Intent intent = new Intent(mactivity, Shop_WebView_Activity.class);
                intent.putExtra("url", url);
                mactivity.startActivity(intent);
            }
        });
        //商品六
        final HomeShopInfo.AreaSecondEntity.SubFifthEntity subFifth = areaSecond.getSubFifth();
        Glide.with(mactivity).load(subFifth.getImage2()).into(imShop06);
        tvShopName06.setTextColor(Color.parseColor(subFifth.getTitleColor()));
        tvShopName06.setText(subFifth.getTitle());
        tvNameState06.setText(subFifth.getTitleSmall());

        imShop06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = subFifth.getGotoPage().getUrl();
                Intent intent = new Intent(mactivity, Shop_WebView_Activity.class);
                intent.putExtra("url", url);
                mactivity.startActivity(intent);
            }
        });
    }

    /**
     * 通过广播监听网络状态
     */
    private void setReceicer() {
        receiver = new NetReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mactivity.registerReceiver(receiver, filter);
    }

    @Override
    public void clearEvent() {
        EventBus.getDefault().unregister(this);
        handler.removeCallbacksAndMessages(null);
        mactivity.unregisterReceiver(receiver);
        mactivity.stopService(intent);
        super.clearEvent();
    }

    @Override
    public void showPger(String[] city_name_id) {
        if (city_name_id.length != 0) {
            cityId = Integer.parseInt(city_name_id[0]);
            String city_name = city_name_id[1];
            LogUtils.e(TAG, city_name);
            tvSelectorCity01.setText(city_name);
        }
    }
    class SeniorityOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           MainActivity mainActivity= (MainActivity) mactivity;
            mainActivity.setPosition(3);
        }
    }
    @Override
    public void stopAll(){
        eventBus.unregister(this);
        OkhttpUtils2.stop();
    }
}
