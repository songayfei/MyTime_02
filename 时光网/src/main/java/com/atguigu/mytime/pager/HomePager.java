package com.atguigu.mytime.pager;

import android.app.Activity;
import android.app.Dialog;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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
import com.atguigu.mytime.adapter.HomeContentAdapter;
import com.atguigu.mytime.adapter.HorizontalListViewAdapter;
import com.atguigu.mytime.adapter.TopViewPagerAdapter;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.entity.AdvData;
import com.atguigu.mytime.entity.HomeListViewInfo;
import com.atguigu.mytime.entity.HomeShopInfo;
import com.atguigu.mytime.entity.HorizontalListViewInfo;
import com.atguigu.mytime.net.InterNetConn;
import com.atguigu.mytime.view.HorizontalListView;
import com.atguigu.mytime.view.LoadingDailog;
import com.atguigu.mytime.view.NoScrollViewPager;
import com.bumptech.glide.Glide;
import com.zhy.android.percent.support.PercentFrameLayout;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2016/4/7.
 * 主页面
 */
public class HomePager extends BasePager {
    private static final int WHAT_TOP_PAGER = 0;
    private static final int WHAT_SHOP_PAGER = 1;

    private ImageView imClose;
    private ImageView imLink;
    private ImageView imAdv;

    private ImageView im_load;
    private ImageView im_load_anim;
    private ImageView im_backimg;
    private ListView lv_home;
    private AnimationDrawable anim;
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
    private LinearLayout ll_shop_point;
    private LinearLayout indicator;


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
    private List<HomeShopInfo.TopPostersEntity> topPosters;
    private HomeShopInfo.AreaSecondEntity areaSecond;
    //判断是否有网络
    private boolean connected;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case WHAT_TOP_PAGER:
                    vpTitle.setCurrentItem(vpTitle.getCurrentItem()+1,true);
                    handler.sendEmptyMessageDelayed(WHAT_TOP_PAGER,3000);
                break;
                case WHAT_SHOP_PAGER:
                    vpAd.setCurrentItem(vpAd.getCurrentItem()+1,true);
                    handler.sendEmptyMessageDelayed(WHAT_SHOP_PAGER,3000);
                    break;
            }
        }
    };
    private List<HomeShopInfo.AdvListEntity> advList;
    private Dialog dialog;
    private View advLog;

    private void findViews(View view) {
        vpTitle = (NoScrollViewPager) view.findViewById(R.id.vp_title);
        llSeek = (LinearLayout) view.findViewById(R.id.ll_seek);
        indicator=(LinearLayout)view.findViewById(R.id.indicator);
        tvSeek = (TextView) view.findViewById(R.id.tv_seek);
        imDimension = (ImageView) view.findViewById(R.id.im_dimension);
        tvState01 = (TextView) view.findViewById(R.id.tv_state01);
        tvSelectorCity01 = (TextView) view.findViewById(R.id.tv_selector_city01);
        tvState02 = (TextView) view.findViewById(R.id.tv_state02);
        horizonListview = (HorizontalListView) view.findViewById(R.id.horizon_listview);
        vpAd = (NoScrollViewPager) view.findViewById(R.id.vp_ad);
        ll_shop_point= (LinearLayout) view.findViewById(R.id.ll_shop_point);
        tvAllShop = (RelativeLayout)view.findViewById(R.id.tv_All_shop);
        llShop01 = (PercentFrameLayout)view.findViewById(R.id.ll_shop01);
        imShop01 = (ImageView)view.findViewById(R.id.im_shop01);
        tvShopName01 = (TextView)view.findViewById(R.id.tv_shop_name01 );
        tvNameState01 = (TextView)view.findViewById(R.id.tv_name_state01);
        llShop02 = (PercentFrameLayout)view.findViewById(R.id.ll_shop02);
        imShop02 = (ImageView)view.findViewById(R.id.im_shop02);
        tvShopName02 = (TextView)view.findViewById(R.id.tv_shop_name02);
        tvNameState02 = (TextView)view.findViewById(R.id.tv_name_state02);
        llShop03 = (PercentFrameLayout)view.findViewById(R.id.ll_shop03);
        imShop03 = (ImageView)view.findViewById(R.id.im_shop03);
        tvShopName03 = (TextView)view.findViewById(R.id.tv_shop_name03);
        tvNameState03 = (TextView)view.findViewById(R.id.tv_name_state03);
        llShop04 = (PercentFrameLayout)view.findViewById(R.id.ll_shop04);
        imShop04 = (ImageView)view.findViewById(R.id.im_shop04);
        tvShopName04 = (TextView)view.findViewById(R.id.tv_shop_name04);
        tvNameState04 = (TextView)view.findViewById(R.id.tv_name_state04);
        llShop05 = (PercentFrameLayout)view.findViewById(R.id.ll_shop05);
        imShop05 = (ImageView)view.findViewById(R.id.im_shop05);
        tvShopName05 = (TextView)view.findViewById(R.id.tv_shop_name05);
        tvNameState05 = (TextView)view.findViewById(R.id.tv_name_state05);
        llShop06 = (PercentFrameLayout)view.findViewById(R.id.ll_shop06);
        imShop06 = (ImageView)view.findViewById(R.id.im_shop06);
        tvShopName06 = (TextView)view.findViewById(R.id.tv_shop_name06);
        tvNameState06 = (TextView)view.findViewById(R.id.tv_name_state06);
    }
    private void findViews1(View dialog) {
        imClose = (ImageView)dialog.findViewById(R.id.im_close);
        imLink = (ImageView)dialog.findViewById(R.id.im_link);
        imAdv = (ImageView)dialog.findViewById(R.id.im_adv);
    }
    private void findView(View view) {
        im_load = (ImageView) view.findViewById(R.id.im_load);
        im_backimg = (ImageView) view.findViewById(R.id.im_backimg);
        im_load_anim = (ImageView) view.findViewById(R.id.im_load_anim);
        lv_home = (ListView) view.findViewById(R.id.lv_home);
    }

    public HomePager(Activity mactivity) {
        super(mactivity);
        EventBus.getDefault().register(this);
    }

    @Override
    public View initView() {
        setReceicer();
        //检查网络
        connected = MessageUtils.isConnected(mactivity);
        View view = View.inflate(mactivity, R.layout.home_pager, null);
        View headView = View.inflate(mactivity, R.layout.home_pager_title, null);
        advLog = View.inflate(mactivity, R.layout.adv_item, null);
        findView(view);
        findViews(headView);
        findViews1(advLog);
        if (connected) {
            im_load.setVisibility(View.GONE);
        } else {
            im_load.setVisibility(View.VISIBLE);
        }
        //查询一启动就启动动画
        anim = (AnimationDrawable) im_load_anim.getBackground();
        anim.start();
        //加载头文件
        lv_home.addHeaderView(headView);
        im_load.setOnClickListener(new MyImageOnClickListener());
        //显示广告


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
            anim.start();
            getNetData();
        }
    }
    /**
     * 联网请求数据
     */
    private void getNetData() {
        new InterNetConn(NetUri.ADV_LIST,mactivity, AdvData.class,false);
        new InterNetConn(NetUri.HOME_SHOP, mactivity, HomeShopInfo.class, false);//ViewPager数据
        new InterNetConn(NetUri.HLISTVIEW, mactivity, HorizontalListViewInfo.class, false);//横向LsitView的数据
        new InterNetConn(NetUri.HOME_BUTTOM, mactivity, HomeListViewInfo.class, false);
    }
    public void onEventMainThread(boolean isconnected) {
        if (!isconnected) {
            anim.stop();
            im_backimg.setVisibility(View.GONE);
            im_load_anim.setVisibility(View.GONE);
            im_load.setVisibility(View.VISIBLE);
            MessageUtils.showMessage(mactivity, "连接数据失败");
        } else {
            im_backimg.setVisibility(View.GONE);
            im_load_anim.setVisibility(View.GONE);
            im_load.setVisibility(View.GONE);
        }
    }

    /**
     * 获取广告数据
     * @param advData
     */
    public void  onEventMainThread(AdvData advData){
        setShowADVDailog(advData);
    }

    /**
     * 显示广告
     * @param advData
     */
    private void setShowADVDailog(AdvData advData) {
        if(!TextUtils.isEmpty(advData.getImg())){
            if(dialog==null) {
                dialog=new LoadingDailog(mactivity);
            }
            dialog.show();
            //加载广告页面
            Glide.with(mactivity).load(advData.getImg()).into(imAdv);
            //点击广告图片
            imAdv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            imClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }else {
            dialog=null;
        }
    }


    /**
     * 横向的ListView信息
     *
     * @param listViewInfo
     */
    public void onEventMainThread(HorizontalListViewInfo listViewInfo) {
        setHorizontalListView(listViewInfo);

    }

    /**
     * 电影商品+顶部的ViewPager
     *
     * @param shopInfo
     */
    public void onEventMainThread(HomeShopInfo shopInfo) {
        setTopViewPager(shopInfo);
    }

    public void onEventMainThread(HomeListViewInfo listViewInfo) {
        setButtomContent(listViewInfo);
    }

    /**
     * 装配底部ListView数据
     *
     * @param listInfo
     */
    private void setButtomContent(HomeListViewInfo listInfo) {
        List<HomeListViewInfo.DataEntity> data = listInfo.getData();
        if (data.size() > 0 && data != null) {
            lv_home.setAdapter(new HomeContentAdapter(mactivity, data));
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

            int li=MessageUtils.dip2px(mactivity,5);
            topPosters = shopInfo.getTopPosters();//顶部图片
            areaSecond = shopInfo.getAreaSecond();//商品

            advList = shopInfo.getAdvList();
            if (topPosters != null && topPosters.size() > 0) {
                //联网请求成功
                anim.stop();
                im_backimg.setVisibility(View.GONE);
                im_load_anim.setVisibility(View.GONE);
                im_load.setVisibility(View.GONE);
                for(int i=0;i<topPosters.size();i++){
                    ImageView imageView=new ImageView(mactivity);
                    imageView.setImageResource(R.drawable.img_point_selector);
                    LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(li,li);
                    if (i != 0) {
                        params.leftMargin = li;
                        imageView.setEnabled(true);
                    } else {
                        imageView.setEnabled(false);
                    }
                    indicator.addView(imageView,params);
                }
                //装配数据
                vpTitle.setAdapter(new TopViewPagerAdapter(topPosters, mactivity));
                //设置初始显示的下标
                int item = Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%topPosters.size();
                vpTitle.setCurrentItem(item,true);
                vpTitle.addOnPageChangeListener(new MyOnPageChangeListener02());
                handler.sendEmptyMessage(WHAT_TOP_PAGER);

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
                    ll_shop_point.addView(imageView,params);
                }
                vpAd.setAdapter(new ADViewPagerAdapter(advList, mactivity));
                /*//设置初始显示的下标
                int item = Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%topPosters.size();
                vpAd.setCurrentItem(item,true);*/
                vpAd.addOnPageChangeListener(new MyOnPageChangeListener());
                handler.sendEmptyMessage(WHAT_SHOP_PAGER);

            }
            setShopImage(areaSecond);
    }
    class MyOnPageChangeListener02 implements ViewPager.OnPageChangeListener {
        private int setposition;
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //得到真实下标
            position = position%topPosters.size();
            indicator.getChildAt(setposition).setEnabled(true);
            indicator.getChildAt(position).setEnabled(false);
            setposition =position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        private int setposition;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //得到真实下标
            position = position%advList.size();
            ll_shop_point.getChildAt(setposition).setEnabled(true);
            ll_shop_point.getChildAt(position).setEnabled(false);
            setposition =position;
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
        HomeShopInfo.AreaSecondEntity.SubFirstEntity subFirst = areaSecond.getSubFirst();
        tvShopName01.setTextColor(Color.parseColor(subFirst.getTitleColor()));
        tvShopName01.setText(subFirst.getTitle());
        tvNameState01.setText(subFirst.getTitleSmall());
        Glide.with(mactivity).load(subFirst.getImage2()).into(imShop01);
        //商品二
        HomeShopInfo.AreaSecondEntity.SubSecondEntity subSecond = areaSecond.getSubSecond();
        tvShopName02.setTextColor(Color.parseColor(subSecond.getTitleColor()));
        tvShopName02.setText(subSecond.getTitle());
        tvNameState02.setText(subSecond.getTitleSmall());
        Glide.with(mactivity).load(subSecond.getImage2()).into(imShop02);

        /*imShop03
        imShop04*/
        //商品五
        HomeShopInfo.AreaSecondEntity.SubThirdEntity subThird = areaSecond.getSubThird();
        Glide.with(mactivity).load(subThird.getImage2()).into(imShop05);
        tvShopName05.setTextColor(Color.parseColor(subThird.getTitleColor()));
        tvShopName05.setText(subThird.getTitle());
        tvNameState05.setText(subThird.getTitleSmall());
        //商品六
        HomeShopInfo.AreaSecondEntity.SubFifthEntity subFifth = areaSecond.getSubFifth();
        Glide.with(mactivity).load(subFifth.getImage2()).into(imShop06);
        tvShopName06.setTextColor(Color.parseColor(subFifth.getTitleColor()));
        tvShopName06.setText(subFifth.getTitle());
        tvNameState06.setText(subFifth.getTitleSmall());
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
        super.clearEvent();
    }
}
