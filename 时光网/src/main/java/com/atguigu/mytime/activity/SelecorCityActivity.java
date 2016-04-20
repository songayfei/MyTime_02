package com.atguigu.mytime.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.PinYinUtils;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.adapter.CityAdapter;
import com.atguigu.mytime.adapter.CityListAdapter;
import com.atguigu.mytime.entity.SelectorCityInfo;
import com.atguigu.mytime.net.InterNetConn;
import com.atguigu.mytime.service.LocationCityService;
import com.atguigu.mytime.view.NoScrollGridView;
import com.google.gson.Gson;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.greenrobot.event.EventBus;

public class SelecorCityActivity extends Activity {
    private ArrayList<String> hotcitys;//热门城市
    private ArrayList<String> cityInfos;
    private ArrayList<String> pinying;
    private ArrayList<String> citysData;
    private EditText tvCity;
    private TextView tvCancel;

    private ImageView im_load;
    private ImageView im_load_anim;
    private ImageView im_backimg;

    @ViewInject(R.id.tv_currentcity)
    private TextView tv_currentcity;

    @ViewInject(R.id.gv_hotcity)
    private NoScrollGridView gv_hotcity;

    private ListView lvClassifiedCity;
    private NetReceiver receiver;

    private boolean isNetwork;
    private AnimationDrawable anim;
    private SelectorCityInfo selectorCityInfo;
    private ArrayList<SelectorCityInfo.PEntity> cityDataP;
    boolean inSearchMode = false;//根据状态装配不同的数据
    //文本输入框的监听
    private TextWatcher watcher = new TextWatcher() {


        //文本变化前
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        //文本变化中
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        //文本变化后
        @Override
        public void afterTextChanged(Editable s) {
            String searchString = tvCity.getText().toString().trim().toUpperCase();
            //移除头视图
            lvClassifiedCity.removeHeaderView(headview);
            //设置数据
            lvClassifiedCity.setAdapter(new CityAdapter(getApplicationContext(),cityInfos));
            tvCancel.setClickable(true);
            //获取执行任务的状态
            if(curSearchTask != null
                    && curSearchTask.getStatus() != AsyncTask.Status.FINISHED){
                try {
                    curSearchTask.cancel(true);//结束当前正在执行的任务
                } catch (Exception e) {
                    Log.e("TAG","任务中指异常");
                    e.printStackTrace();
                }
            }
            curSearchTask = new SearchListTask();
            //将输入内容传递
            curSearchTask.execute(searchString);
        }

    };



    private SearchListTask curSearchTask;
    private View headview;

    private String locationcity;
    private Intent intent;

    class SearchListTask extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            citysData.clear();
            //获取参数的元素的第一个元素
            String keyword=params[0];
            //判断该元素的超大户是否大于0
            inSearchMode = (keyword.length() > 0);
            if(inSearchMode){
                for(int i=0;i<cityInfos.size();i++){
                    //判断是否包含输入的字符串
                    boolean contains = cityInfos.get(i).contains(keyword);
                    //若用户输入的拼音
                    boolean contains1 = PinYinUtils.getPinYin(cityInfos.get(i)).contains(keyword);
                    if(contains||contains1){
                        citysData.add(cityInfos.get(i));
                    }
                }
            }else {
                citysData=cityInfos;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            lvClassifiedCity.setAdapter(new CityAdapter(getApplicationContext(),citysData));
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lvClassifiedCity.addHeaderView(headview);
                    CityListAdapter adapter = new CityListAdapter(SelecorCityActivity.this, cityInfos, pinying);
                    //城市分类装配
                    lvClassifiedCity.setAdapter(adapter);
                }
            });
        }
    }
    /**
     * 初始化界面布局
     */
    private void findViews() {

        setContentView(R.layout.activity_selecor_city);
        EventBus.getDefault().register(this);

        headview = View.inflate(this, R.layout.hot_ciyts, null);
        x.view().inject(this, headview);

        tvCity = (EditText) findViewById(R.id.tv_city);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        lvClassifiedCity = (ListView) findViewById(R.id.lv_classified_city);

        im_load = (ImageView) findViewById(R.id.im_load);
        im_backimg = (ImageView) findViewById(R.id.im_backimg);
        im_load_anim = (ImageView) findViewById(R.id.im_load_anim);

        anim = (AnimationDrawable) im_load_anim.getBackground();

        lvClassifiedCity.addHeaderView(headview);
        //判断是否有网络
        isNetwork = MessageUtils.isConnected(this);
        citysData=new ArrayList<>();
        lvClassifiedCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (inSearchMode) {
                    //获取城市名
                    String cityname = citysData.get(position);
                    Intent intent = new Intent(SelecorCityActivity.this, MainActivity.class);
                    intent.putExtra("cityname", cityname);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setReceicer();
        getNetWork();
        loadData();
        startLocationCity();

    }

    private void startLocationCity() {
        intent = new Intent(this, LocationCityService.class);
        startService(intent);
    }

    /**
     * 判断网络连接
     */
    private void getNetWork() {
        if (isNetwork) {
            im_load.setVisibility(View.VISIBLE);
            im_load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    im_backimg.setVisibility(View.VISIBLE);
                    im_load_anim.setVisibility(View.VISIBLE);
                    anim.start();
                    loadData();
                }
            });
        } else {
            im_load.setVisibility(View.GONE);
        }
    }

    public void onEventMainThread(SelectorCityInfo cityInfo) {
        setCityData(cityInfo);
    }
    public void onEventMainThread(StringBuffer citynam){
        if(!TextUtils.isEmpty(citynam)){
            tv_currentcity.setText(citynam);
        }else {
            tv_currentcity.setText("定位失败");
        }
    }
    /**
     * 加载数据
     */
    private void loadData() {
        String jsonData = SpUtils.getInitialize(this).getJsonData(NetUri.CITY_URL);
        if (!TextUtils.isEmpty(jsonData)) {
            //获取城市列表数据
            selectorCityInfo = new Gson().fromJson(jsonData, SelectorCityInfo.class);
            setCityData(selectorCityInfo);
        } else {
            if (isNetwork) {
                //联网请求数据
                new InterNetConn(true, NetUri.CITY_URL, getApplicationContext(), SelectorCityInfo.class);
            } else {
                im_load.setVisibility(View.VISIBLE);
                im_backimg.setVisibility(View.GONE);
                im_load_anim.setVisibility(View.GONE);
                anim.stop();
                MessageUtils.showMessage(getApplicationContext(), "加载数据失败");
            }
        }
    }

    /**
     * 通过广播监听网络状态
     */
    private void setReceicer() {
        receiver = new NetReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);
    }


    /**
     * 装配数据
     *
     * @param citys
     */
    private void setCityData(SelectorCityInfo citys) {
        ArrayList<SelectorCityInfo.PEntity> cityDataP = (ArrayList<SelectorCityInfo.PEntity>) citys.getP();
        hotcitys = new ArrayList<>();//热门城市数据
        cityInfos = new ArrayList<>();//一般城市数据
        pinying = new ArrayList<>();//拼音
        if (cityDataP.size() > 0 && cityDataP != null) {
            im_load.setVisibility(View.GONE);
            im_backimg.setVisibility(View.GONE);
            im_load_anim.setVisibility(View.GONE);
            anim.stop();
            for (int i = 0; i < cityDataP.size(); i++) {
                if (i <= 11) {
                    hotcitys.add(cityDataP.get(i).getN());//前12个放入热门城市集合中
                    cityInfos.add(cityDataP.get(i).getN());
                } else {
                    cityInfos.add(cityDataP.get(i).getN());
                }
            }
        }
        //装配热门数据
        gv_hotcity.setAdapter(new CityAdapter(getApplicationContext(),hotcitys));

        gv_hotcity.setOnItemClickListener(new HotOnItemClickListener());

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
        CityListAdapter adapter = new CityListAdapter(this, cityInfos, pinying);
        //城市分类装配
        lvClassifiedCity.setAdapter(adapter);
        //获取点击的城市名
        tv_currentcity.setText(adapter.getCityname());
        //城市列表输入狂点击输入监听
        tvCity.addTextChangedListener(watcher);
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

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);//广播解注册
        EventBus.getDefault().unregister(this);//解注册
        stopService(intent);
        super.onDestroy();
    }
}
