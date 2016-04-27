package com.atguigu.mytime.pager;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.mytime.discoverpagers.Commentpager;
import com.atguigu.mytime.discoverpagers.Newspager;
import com.atguigu.mytime.discoverpagers.PrevuePager;
import com.atguigu.mytime.discoverpagers.Ranklistpager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/4/8.
 * 发现
 */
public class DiscoverPager extends BasePager{
    private JSONObject topNews;
    private JSONObject topPrevue;
    private JSONObject topRanklis;
    private JSONObject topComment;
    private static final String TAG = DiscoverPager.class.getSimpleName();
    //发现界面的四个详情界面的集合
    private List<BasePager> pagers;
    private DiscoverAdapter adapter;
    private TabLayout tab_discover;
    private ViewPager viewpager_discover;


    public DiscoverPager(Activity mactivity) {
        super(mactivity);

    }

    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.discover_page, null);
        tab_discover = (TabLayout) view.findViewById(R.id.tab_discover);
        viewpager_discover = (ViewPager) view.findViewById(R.id.viewpager_discover);

        return view;
    }

    /**
     * 初始化四个详情页面
     */
    private void initPager() {
        pagers=new ArrayList<>();
        pagers.add(new Newspager(mactivity,topNews));//新闻
        pagers.add(new PrevuePager(mactivity,topPrevue));//预告片
        pagers.add(new Ranklistpager(mactivity,topRanklis));//排行榜
        pagers.add(new Commentpager(mactivity,topComment));//影评



    }

    @Override
    public void initData(){
        super.initData();
            //在联网之前，先从本地获取数据
        String saveResult = SpUtils.getInitialize(mactivity).getJsonData(NetUri.DISCOVER_TOP);
        if(!TextUtils.isEmpty(saveResult)) {
            //如果存在解析数据
            processJson(saveResult);
        }
        //联网请求四个页面的Top信息
        getTopInfoFromnet();

    }

    @Override
    public void showPger(String[] city_name_id) {

    }

    /**
     * 联网请求四个页面的Top信息
     */
    private void getTopInfoFromnet(){

        //使用okhttp联网请求数据
        String url = NetUri.DISCOVER_TOP;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());

    }
    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e) {
            MessageUtils.showMessage(mactivity,"联网请求数据失败");
        }

        @Override
        public void onResponse(String response) {
            processJson(response);
            //获取数据成功保存json数据
            SpUtils.getInitialize(mactivity).saveJson(NetUri.DISCOVER_TOP,response);
            //
        }
    }

    /**
     * 解析json
     * @param result
     */
    private void processJson(String result){
        parseJsonData(result);
        //初始化详细页面
        initPager();
        adapter=new DiscoverAdapter();
        viewpager_discover.setAdapter(adapter);
        tab_discover.setupWithViewPager(viewpager_discover);
        //设置滚动
        tab_discover.setTabMode(TabLayout.MODE_FIXED);


    }

    private void parseJsonData(String result){
        //获取一个json对象
        try {
//            result=result.replaceAll("\ufeff","");
            JSONObject jsonobject = new JSONObject(result);
            //顶部的新闻信息
            topNews = jsonobject.optJSONObject("news");
            //顶部的预告片信息
            topPrevue = jsonobject.optJSONObject("trailer");
            //顶部的排行榜
            topRanklis = jsonobject.optJSONObject("topList");
            //顶部的影评
            topComment = jsonobject.optJSONObject("review");

        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    class DiscoverAdapter extends PagerAdapter {
        public String[] titles = {"新闻", "预告片", "排行榜", "影评"};


        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }




        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            BasePager itemPage = pagers.get(position);

            itemPage.initData();
            container.addView(itemPage.rootview);
            return itemPage.rootview;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
