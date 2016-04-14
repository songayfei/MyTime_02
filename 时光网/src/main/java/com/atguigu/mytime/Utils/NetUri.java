package com.atguigu.mytime.Utils;

/**
<<<<<<< HEAD
 * Created by Administrator on 2016/4/8.
 */


public class NetUri {


    public static final String CITY_URL="http://api.m.mtime.cn/Showtime/HotCitiesByCinema.api";
    public static final String DISCOVER_LIST = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
    public static final String DISCOVER_TOP = "http://api.m.mtime.cn/PageSubArea/GetRecommendationIndexInfo.api";
    public static final String COMMENT_LIST = "http://api.m.mtime.cn/MobileMovie/Review.api?needTop=false";


    public static final String MAll_LIST="http://api.m.mtime.cn/PageSubArea/MarketFirstPageNew.api?lastTime={0}";
    public static int pageIndex=1;
    public static final String MAll_RECOMMENDGOODS_LIST="http://api.m.mtime.cn/ECommerce/RecommendProducts.api?pageIndex="+pageIndex+"&goodsIds=102315%2C102306%2C102486";
}
