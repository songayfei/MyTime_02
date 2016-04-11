package com.atguigu.mytime.pager;

import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.adapter.ADViewPagerAdapter;
import com.atguigu.mytime.adapter.HorizontalListViewAdapter;
import com.atguigu.mytime.adapter.TopViewPagerAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.entity.HomeShopInfo;
import com.atguigu.mytime.entity.HorizontalListViewInfo;
import com.atguigu.mytime.net.InterNetConn;
import com.atguigu.mytime.view.HorizontalListView;
import com.bumptech.glide.Glide;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/4/7.
 * 主页面
 */
public class HomePager extends BasePager {
    private ImageView im_load;
    private ImageView im_load_anim;
    private ImageView im_backimg;
    private ListView lv_home;
    //服务
    private NetReceiver receiver;
    private ViewPager vpTitle;
    private LinearLayout llSeek;
    private TextView tvSeek;
    private ImageView imDimension;
    private TextView tvState01;
    private TextView tvSelectorCity01;
    private TextView tvState02;
    private HorizontalListView horizonListview;
    private ViewPager vpAd;

    private RelativeLayout tvAllShop;
    private ImageView imShop01;
    private ImageView imShop02;
    private ImageView imShop03;
    private ImageView imShop04;
    private ImageView imShop05;
    private ImageView imShop06;
    private List<HomeShopInfo.TopPostersEntity> topPosters;
    private HomeShopInfo.AreaSecondEntity areaSecond;
    //判断是否有网络
    private boolean connected;

    private void findViews(View view) {
        vpTitle = (ViewPager)view.findViewById(R.id.vp_title);
        llSeek = (LinearLayout)view.findViewById(R.id.ll_seek);
        tvSeek = (TextView)view.findViewById(R.id.tv_seek);
        imDimension = (ImageView)view.findViewById(R.id.im_dimension);
        tvState01 = (TextView)view.findViewById(R.id.tv_state01);
        tvSelectorCity01 = (TextView)view.findViewById(R.id.tv_selector_city01);
        tvState02 = (TextView)view.findViewById(R.id.tv_state02);
        horizonListview = (HorizontalListView)view.findViewById(R.id.horizon_listview);
        vpAd = (ViewPager)view.findViewById( R.id.vp_ad );

        tvAllShop = (RelativeLayout)view.findViewById(R.id.tv_All_shop);
        imShop01 = (ImageView)view.findViewById(R.id.im_shop01);
        imShop02 = (ImageView)view.findViewById(R.id.im_shop02);
        imShop03 = (ImageView)view.findViewById(R.id.im_shop03);
        imShop04 = (ImageView)view.findViewById(R.id.im_shop04);
        imShop05 = (ImageView)view.findViewById(R.id.im_shop05);
        imShop06 = (ImageView)view.findViewById(R.id.im_shop06);
    }
    public HomePager(Activity mactivity) {
        super(mactivity);
        EventBus.getDefault().register(this);
    }

    @Override
    public View initView() {
        setReceicer();
        View view=View.inflate(mactivity, R.layout.home_pager,null);
        im_load= (ImageView) view.findViewById(R.id.im_load);
        im_backimg= (ImageView) view.findViewById(R.id.im_backimg);
        im_load_anim= (ImageView) view.findViewById(R.id.im_load_anim);
        lv_home= (ListView) view.findViewById(R.id.lv_home);
        //加载头文件
        View headView=View.inflate(mactivity,R.layout.home_pager_title,null);
        findViews(headView);
        lv_home.addHeaderView(headView);
        //检查网络
        connected = MessageUtils.isConnected(mactivity);
        im_load.setOnClickListener(new MyImageOnClickListener());
        return view;
    }

    /**
     * 没网状态图片的点击监听
     */
    class MyImageOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            im_backimg.setVisibility(View.VISIBLE);
            im_load_anim.setVisibility(View.VISIBLE);
            AnimationDrawable anim = (AnimationDrawable) im_load_anim.getBackground();
            anim.start();
            boolean connected = MessageUtils.isConnected(mactivity);
            if(connected){
                anim.stop();
                initData();
            }


        }
    }
    /**
     * 横向的ListView信息
     * @param listViewInfo
     */
    public void onEventMainThread(HorizontalListViewInfo listViewInfo){
        setHorizontalListView(listViewInfo);

    }

    /**
     * 电影商品+顶部的ViewPager
     * @param shopInfo
     */
    public void onEventMainThread(HomeShopInfo shopInfo){
        setTopViewPager(shopInfo);
    }

    /**
     * 装配顶部的ViewPager
     * @param shopInfo
     */
    private void setTopViewPager(HomeShopInfo shopInfo) {
        topPosters = shopInfo.getTopPosters();//顶部图片
        areaSecond = shopInfo.getAreaSecond();//商品
        List<HomeShopInfo.AdvListEntity> advList = shopInfo.getAdvList();
        if(topPosters!=null&& topPosters.size()>0){
            //装配数据
            vpTitle.setAdapter(new TopViewPagerAdapter(topPosters,mactivity));

        }
        if(advList.size()>0&&advList!=null){
            vpAd.setAdapter(new ADViewPagerAdapter(advList,mactivity));
        }
        setShopImage(areaSecond);
    }

    /**
     * 装配横向的ListView
     */
    private void setHorizontalListView(HorizontalListViewInfo listViewInfo){
        List<HorizontalListViewInfo.MoviesEntity> movies = listViewInfo.getMovies();
        if(movies.size()>0&&movies!=null){
            horizonListview.setAdapter(new HorizontalListViewAdapter(movies,mactivity));
        }
    }
    @Override
    public void initData() {
        super.initData();
        if(connected) {
            im_load.setVisibility(View.GONE);
            im_load_anim.setVisibility(View.GONE);
            im_load_anim.setVisibility(View.GONE);
            new InterNetConn(NetUri.HOME_SHOP, mactivity, HomeShopInfo.class);//ViewPager数据
            new InterNetConn(NetUri.HLISTVIEW, mactivity, HorizontalListViewInfo.class);//横向LsitView的数据
        }else {
            im_load.setVisibility(View.VISIBLE);
        }

    }

    private void setShopImage(HomeShopInfo.AreaSecondEntity areaSecond) {
        HomeShopInfo.AreaSecondEntity.SubFirstEntity subFirst = areaSecond.getSubFirst();
        Glide.with(mactivity).load(subFirst.getImage()).into(imShop01);//商品一
        HomeShopInfo.AreaSecondEntity.SubSecondEntity subSecond = areaSecond.getSubSecond();
        Glide.with(mactivity).load(subSecond.getImage()).into(imShop02);

        /*imShop03
        imShop04*/
        HomeShopInfo.AreaSecondEntity.SubThirdEntity subThird = areaSecond.getSubThird();
        Glide.with(mactivity).load(subThird.getImage()).into(imShop05);
        HomeShopInfo.AreaSecondEntity.SubFifthEntity subFifth = areaSecond.getSubFifth();
        Glide.with(mactivity).load(subFifth.getImage()).into(imShop06);
    }

    /**
     * 通过广播监听网络状态
     */
    private void setReceicer() {
        receiver=new NetReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mactivity.registerReceiver(receiver, filter);
    }

    @Override
    public void clearEvent() {
        EventBus.getDefault().unregister(this);
        super.clearEvent();
    }
}
