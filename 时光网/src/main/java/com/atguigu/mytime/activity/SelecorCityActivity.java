package com.atguigu.mytime.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.PinYinUtils;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.Utils.UrlPath;
import com.atguigu.mytime.adapter.CityAdapter;
import com.atguigu.mytime.adapter.CityListAdapter;
import com.atguigu.mytime.entity.SelectorCityInfo;
import com.atguigu.mytime.view.NoScrollGridView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SelecorCityActivity extends Activity {
    private static final String TAG =SelecorCityActivity.class.getSimpleName() ;
    private List<String> hotcitys;//热门城市
    private List<String> cityInfos;
    private List<String> pinying;
    private TextView tvCity;
    private TextView tvCancel;
    @ViewInject(R.id.tv_currentcity)
    private TextView tv_currentcity;

    @ViewInject(R.id.gv_hotcity)
    private NoScrollGridView  gv_hotcity;

    private ListView lvClassifiedCity;


    /**
     * 初始化界面布局
     */
    private void findViews() {
        setContentView(R.layout.activity_selecor_city);

        View view = View.inflate(this, R.layout.hot_ciyts, null);//头文件
        x.view().inject(this, view);

        tvCity = (TextView)findViewById( R.id.tv_city );
        tvCancel = (TextView)findViewById( R.id.tv_cancel );
        lvClassifiedCity = (ListView)findViewById( R.id.lv_classified_city );

        lvClassifiedCity.addHeaderView(view);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        String  value = SpUtils.getInitialize(this).getValue(SpUtils.CITY, null);
        if(!TextUtils.isEmpty(value)) {
            parseJson(value);
        }else {
            //联网请求数据
            getCityData();
        }


    }

    /**
     * 联网请求城市数据
     */
    private void getCityData() {
        RequestParams params=new RequestParams(UrlPath.CITY_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "联网请求成功");
                //保存请求数据
                SpUtils.getInitialize(SelecorCityActivity.this).save(SpUtils.CITY, result);
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "联网请求失败");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e(TAG, "联网请求完成");
            }

            @Override
            public void onFinished() {
                Log.e(TAG, "联网请求取消");
            }
        });
    }

    /**
     * 解析json
     * @param json
     */
    private void parseJson(String json) {
        hotcitys=new ArrayList<>();
        cityInfos=new ArrayList<>();
        pinying=new ArrayList<>();
        if(json!=null) {
            SelectorCityInfo citydata = new Gson().fromJson(json, SelectorCityInfo.class);
            List<SelectorCityInfo.PEntity> p = citydata.getP();
            if(p.size()>0&& p !=null) {
                for (int i = 0; i < p.size(); i++) {
                    if (i <= 11) {
                        hotcitys.add(p.get(i).getN());//前12个放入热门城市集合中
                    } else {
                        cityInfos.add(p.get(i).getN());
                    }
                }
            }
        }
        //装配热门数据
        gv_hotcity.setAdapter(new CityAdapter(this, hotcitys));
        gv_hotcity.setOnItemClickListener(new HotOnItemClickListener());
        /**
         * 装配数据城市列表
         */
        setCityData();




    }
    /**
     * 装配数据
     */
    private void setCityData() {

        //排序
        Collections.sort(cityInfos, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                String lhs1 = PinYinUtils.getPinYin(lhs).substring(0, 1);
                String rhs1 = PinYinUtils.getPinYin(rhs).substring(0, 1);
                return lhs1.compareTo(rhs1);
            }
        });
        //将城市首字母拼音添加到set集合
        if (cityInfos != null && cityInfos.size() > 0) {
            for (int i = 0; i < cityInfos.size(); i++) {
                String word = PinYinUtils.getPinYin(cityInfos.get(i)).substring(0, 1);//获取城市首字字母拼音
                if (!pinying.contains(word)) {//判断是否包含在该集合中
                    pinying.add(word);
                }
            }
        }
        CityListAdapter adapter=new CityListAdapter(this, cityInfos, pinying);
        //城市分类装配
        lvClassifiedCity.setAdapter(adapter);
        //获取点击的城市名
        tv_currentcity.setText(adapter.getCityname());
    }

    /**
     * 最热城市item点击监听
     */
    class HotOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            tv_currentcity.setText(hotcitys.get(position));
            //点击后就到主页面
            Intent intent = new Intent(SelecorCityActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
