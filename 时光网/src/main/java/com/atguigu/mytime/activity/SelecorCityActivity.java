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
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Receiver.NetReceiver;
import com.atguigu.mytime.Utils.LogUtils;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.MyApplication;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.Utils.PinYinUtils;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.adapter.CityAdapter;
import com.atguigu.mytime.adapter.CityListAdapter;
import com.atguigu.mytime.entity.CityEntity;
import com.atguigu.mytime.entity.SelectorCityInfo;
import com.atguigu.mytime.net.InterNetConn;
import com.atguigu.mytime.service.LocationCityService;
import com.atguigu.mytime.view.NoScrollGridView;

import org.xutils.DbManager;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SelecorCityActivity extends Activity {

    private static final java.lang.String TAG = SelecorCityActivity.class.getSimpleName();
    private ArrayList<String> hotcitys;//热门城市
    private ArrayList<String> pinying;
    private ArrayList<String> cityInfos;
    private ArrayList<String> citysData;
    private Object searchLock = new Object();
    private boolean isinto = false;
    private EditText tvCity;
    private TextView tvCancel;

    private ImageView im_load;
    private ImageView im_load_anim;
    private ImageView im_backimg;

    private TextView tv_currentcity;
    private NoScrollGridView gv_hotcity;

    private ListView lvClassifiedCity;
    private NetReceiver receiver;
    private ImageView im_rollback;

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
            //移除头视图
            lvClassifiedCity.removeHeaderView(headview);
            //设置数据
            lvClassifiedCity.setAdapter(new CityAdapter(getApplicationContext(), cityInfos));
            tvCancel.setClickable(true);
            String searchString = tvCity.getText().toString().trim().toUpperCase();
            //获取执行任务的状态
            if (curSearchTask != null
                    && curSearchTask.getStatus() != AsyncTask.Status.FINISHED) {
                try {
                    curSearchTask.cancel(true);//结束当前正在执行的任务
                } catch (Exception e) {
                    LogUtils.e(TAG,"终止任务异常");
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

    private Intent intent;
    private DbManager db;
    private CityAdapter cityAdapter;
    private boolean value;


    class SearchListTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            citysData.clear();
            //获取参数的元素的第一个元素
            String keyword = params[0];
            //判断该元素的超大户是否大于0
            inSearchMode = (keyword.length() > 0);
            if (inSearchMode) {
                for (int i = 0; i < cityInfos.size(); i++) {
                    //判断是否包含输入的字符串
                    boolean contains = cityInfos.get(i).contains(keyword);
                    //若用户输入的拼音
                    boolean contains1 = PinYinUtils.getPinYin(cityInfos.get(i)).contains(PinYinUtils.getPinYin(keyword));
                    if (contains || contains1) {
                        citysData.add(cityInfos.get(i));
                    }
                }
            } else {
                citysData.addAll(cityInfos);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            synchronized (searchLock) {
                cityAdapter.setCityInfos(citysData);
                lvClassifiedCity.setAdapter(cityAdapter);

            }

            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    citysData=null;
                    cityAdapter.setCityInfos(hotcitys);
                    gv_hotcity.setAdapter(cityAdapter);
                    lvClassifiedCity.addHeaderView(headview);
                    CityListAdapter adapter = new CityListAdapter(SelecorCityActivity.this, cityInfos, pinying, value);
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
        //获取数据库
        db = x.getDb(((MyApplication)getApplication()).getDaoConfig());
        headview = View.inflate(this, R.layout.hot_ciyts, null);
        tv_currentcity = (TextView)headview.findViewById(R.id.tv_currentcity);
        gv_hotcity = (NoScrollGridView)headview.findViewById(R.id.gv_hotcity);





        im_rollback = (ImageView) findViewById(R.id.im_rollback);
        tvCity = (EditText) findViewById(R.id.tv_city);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        lvClassifiedCity = (ListView) findViewById(R.id.lv_classified_city);

        im_load = (ImageView) findViewById(R.id.im_load);
        im_backimg = (ImageView) findViewById(R.id.im_backimg);
        im_load_anim = (ImageView) findViewById(R.id.im_load_anim);

        anim = (AnimationDrawable) im_load_anim.getBackground();

        lvClassifiedCity.addHeaderView(headview);

        citysData = new ArrayList<>();

        lvClassifiedCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (inSearchMode) {
                    //获取城市名
                    String cityname = citysData.get(position);
                    //保存选中的城市名
                    if (!value) {
                        Intent intent = new Intent(SelecorCityActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    getDBData(cityname);
                    finish();
                }
            }
        });
        im_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im_backimg.setVisibility(View.VISIBLE);
                im_load_anim.setVisibility(View.VISIBLE);
                anim.start();
                loadData();
            }
        });
        im_rollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = tv_currentcity.getText().toString();
                if (!value) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                getDBData(city);
                finish();
            }
        });
        //item的点击监听
        gv_hotcity.setOnItemClickListener(new HotOnItemClickListener());

    }

    /**
     * 保存点击的城市数据保存到全局变量
     *
     * @param city
     */
    private void getDBData(String city) {
        try {
            List<CityEntity> all = db.selector(CityEntity.class).where("cityname", "=", city).findAll();
            if (all != null && all.size() > 0) {
                int id = all.get(0).getId();
                //保存选中的城市名和id
                SpUtils.getInitialize(getApplicationContext()).save(SpUtils.CITY_NAME,city);
                SpUtils.getInitialize(getApplicationContext()).save(SpUtils.CITY_ID, id);
                Intent intent = getIntent();
                intent.putExtra("city_name_id", new String[]{String.valueOf(id), city});
                setResult(RESULT_OK, intent);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
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

    /**
     * 启动城市定位服务
     */
    private void startLocationCity() {
        intent = new Intent(this, LocationCityService.class);
        startService(intent);
    }

    /**
     * 判断网络连接
     */
    private void getNetWork() {
        //判断是否有网络
        isNetwork = MessageUtils.isConnected(this);
        if (isNetwork) {
            im_load.setVisibility(View.GONE);
        } else {
            im_load.setVisibility(View.VISIBLE);

        }
    }

    public void onEventMainThread(SelectorCityInfo cityInfo) {
        getCityAllData(cityInfo);
    }

    public void onEventMainThread(StringBuffer cityname) {
        if (!TextUtils.isEmpty(cityname)&&!"".equals(cityname)) {
            String name = cityname.toString();
            String[] split = name.split(",");
            String city_name=null;
            for(int i=0;i<cityInfos.size();i++){
                if(cityInfos.get(i).contains(split[0])){
                    city_name=cityInfos.get(i);
                }
            }
            tv_currentcity.setText(city_name);
        } else {
            tv_currentcity.setText("北京");
        }
    }

    /**
     * 加载数据
     */
    private List<CityEntity> cityall;

    private void loadData() {
        //查询是否进入过主页面
        value = SpUtils.getInitialize(this).getValue(SpUtils.GUIDE, false);
        //查询数据是否有相关数据
        try {
            cityall = db.findAll(CityEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (cityall != null && cityall.size() > 0) {
            //获取城市列表数据
            getAdapterData(cityall);
        } else {
            if (isNetwork) {
                //联网请求数据
                new InterNetConn(NetUri.CITY_URL, getApplicationContext(), SelectorCityInfo.class);
            } else {
                im_load.setVisibility(View.VISIBLE);
                im_backimg.setVisibility(View.GONE);
                im_load_anim.setVisibility(View.GONE);
                anim.stop();
                MessageUtils.showMessage(getApplicationContext(), "加载数据失败");
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            String text = tv_currentcity.getText().toString();
            getDBData(text);
            if(!value) {
                startActivity(new Intent(this, MainActivity.class));
            }
            finish();
        }
        return super.onKeyDown(keyCode, event);
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
    private CityEntity cityEntity;

    private void getCityAllData(SelectorCityInfo cityDataP) {
        ArrayList<SelectorCityInfo.PEntity> cityallnetdata = (ArrayList<SelectorCityInfo.PEntity>) cityDataP.getP();
        cityall = new ArrayList<>();
        if (cityallnetdata.size() > 0 && cityallnetdata != null) {
            im_load.setVisibility(View.GONE);
            im_backimg.setVisibility(View.GONE);
            im_load_anim.setVisibility(View.GONE);
            anim.stop();
            for (int i = 0; i < cityallnetdata.size(); i++) {
                //创建城市实体对象
                cityEntity = new CityEntity();
                cityEntity.setCount(cityallnetdata.get(i).getCount());
                cityEntity.setCityname(cityallnetdata.get(i).getN());
                cityEntity.setId(cityallnetdata.get(i).getId());
                cityEntity.setPinyinFull(cityallnetdata.get(i).getPinyinFull());
                cityEntity.setPinyinShort(cityallnetdata.get(i).getPinyinShort());
                cityall.add(cityEntity);
                //保存城市对象到数据库
                try {
                    db.save(cityEntity);
                } catch (DbException e) {
                    LogUtil.e("保存数据失败");
                    e.printStackTrace();
                }
            }
        }
        getAdapterData(cityall);
    }

    /**
     * 对城市数据进行排序操作
     *
     * @param cityall
     */
    private void getAdapterData(List<CityEntity> cityall) {
        hotcitys = new ArrayList<>();//热门城市数据
        pinying = new ArrayList<>();//拼音
        cityInfos = new ArrayList<>();//所有城市
        for (int i = 0; i < this.cityall.size(); i++) {
            if (i <= 11) {
                hotcitys.add(cityall.get(i).getCityname());//前12个放入热门城市集合中
            }
            cityInfos.add(cityall.get(i).getCityname());
            String word = cityall.get(i).getPinyinFull().substring(0, 1);//获取城市首字字母拼音
            if (!pinying.contains(word)) {//判断是否包含在该集合中
                pinying.add(word);
            }
        }
        //城市排序
        Collections.sort(cityInfos, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                String str1 = PinYinUtils.getPinYin(lhs).substring(0, 1);
                String str2 = PinYinUtils.getPinYin(rhs).substring(0, 1);
                return str1.compareTo(str2);
            }
        });
        //拼音排序排序
        Collections.sort(pinying, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        //热门城市
        cityAdapter = new CityAdapter(getApplicationContext(), hotcitys);
        //装配热门数据
        gv_hotcity.setAdapter(cityAdapter);

        CityListAdapter adapter = new CityListAdapter(this, cityInfos, pinying, value);
        //城市分类装配
        lvClassifiedCity.setAdapter(adapter);
        //获取点击的城市名
        tv_currentcity.setText(adapter.getCityname());
        //文本输入框输入监听
        tvCity.addTextChangedListener(watcher);
    }


    /**
     * 最热城市item点击监听
     */
    class HotOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String cityname = hotcitys.get(position);
            getDBData(cityname);
            if (!value) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        unregisterReceiver(receiver);//广播解注册
        EventBus.getDefault().unregister(this);//解注册
        super.onDestroy();
    }
}
