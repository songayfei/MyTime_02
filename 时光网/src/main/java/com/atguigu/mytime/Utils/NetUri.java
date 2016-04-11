package com.atguigu.mytime.Utils;

/**
 * Created by Administrator on 2016/4/8.
 *
 * 联网请求地址
 * Created by Administrator on 16-4-8.
 */
public class NetUri {
    //城市
    public static final String CITY_URL="http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";
    //首页Viewpager
    public static final String HOME_SHOP="http://api.m.mtime.cn/PageSubArea/GetFirstPageAdvAndNews.api";
    //横向ListView
    public static final String HLISTVIEW="http://api.m.mtime.cn/PageSubArea/HotPlayMovies.api?locationId=290";
    //横向ListView imageView的点击连接地址
    public static final String HLISTVIEW_ITEM="http://api.m.mtime.cn/Showtime/MovieComments.api?";

    public static final String DISCOVER_LIST = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    public static final String DISCOVER_TOP = "http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    public static final String COMMENT_LIST = "http://api.m.mtime.cn/MobileMovie/Review.api?needTop=false";

}
