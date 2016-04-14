package com.atguigu.mytime.Utils;

/**
 * Created by Administrator on 2016/4/8.
 * <p/>
 * 联网请求地址
 * Created by Administrator on 16-4-8.
 */
public class NetUri {
    //城市
    public static final String CITY_URL = "http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";
    //广告页
    public static final String AD_LIST="http://api.m.mtime.cn/Advertisement/MobileAdvertisementInfo.api?locationId=290";
    //首页Viewpager
    public static final String HOME_SHOP = "http://api.m.mtime.cn/PageSubArea/GetFirstPageAdvAndNews.api";
    //横向ListView
    public static final String HLISTVIEW = "http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
    //横向ListView imageView的点击连接地址
    public static final String HLISTVIEW_ITEM = "http://api.m.mtime.cn/Showtime/MovieComments.api?";
    //homeListView下半部分
    public static final String HOME_BUTTOM = "http://api.m.mtime.cn/PageSubArea/GetHomeFeed.api?pageIndes";
    public static final String DISCOVER_LIST = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    public static final String DISCOVER_TOP = "http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    public static final String COMMENT_LIST = "http://api.m.mtime.cn/MobileMovie/Review.api?needTop=false";
    //弹窗广告
    public static final String ADV_LIST = "http://api.m.mtime.cn/Advertisement/MainPagePopup.api?locationId=290&lastTime=0&thirdAdvId=0&thirdOperate=0&thirdTime=0&thirdShowCount=0&ownAdvId=0&ownOperate=0&ownTime=0&ownShowCount=0";
}
