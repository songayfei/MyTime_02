package com.atguigu.mytime.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.LogUtils;
import com.atguigu.mytime.Utils.MyApplication;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.adapter.CityAdapter;
import com.atguigu.mytime.adapter.Seek_movie_Adapter;
import com.atguigu.mytime.entity.HomeSeekEntity;
import com.atguigu.mytime.entity.SeekCententEntity;
import com.google.gson.Gson;
import com.zf.myzxing.CaptureActivity;
import com.zhy.android.percent.support.PercentLinearLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SeekActivity extends Activity {
    private static final String TAG = SeekActivity.class.getSimpleName();
    private static final int SCANNIN_SEEK_CODE = 202;
    private ArrayList<String> seek_record;
    private DbManager db;
    private ImageButton imSeekBack;
    private ImageButton im_seek_scan;
    private EditText tvSeekFiml;
    private TextView tvSeekStart;

    private TextView tv_seek_shop;//商城搜索
    private ListView lv_seek_content;
    private LinearLayout seek_text_01;

    private PercentLinearLayout default_view;


    private PercentLinearLayout pllSeekContent;
    private GridView gvSeek;
    private ListView lv_record;
    private SearchListTask searchListTask;

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            default_view.setVisibility(View.GONE);
            seek_text_01.setVisibility(View.VISIBLE);
            String content = tvSeekFiml.getText().toString().trim().toUpperCase();
            //获取执行任务的状态
            if (searchListTask != null
                    && searchListTask.getStatus() != AsyncTask.Status.FINISHED) {
                try {
                    searchListTask.cancel(true);//结束当前正在执行的任务
                } catch (Exception e) {
                    Log.e("TAG", "任务终止异常");
                    e.printStackTrace();
                }
            }
            searchListTask = new SearchListTask();
            searchListTask.execute(content);
        }
    };
    private List<SeekCententEntity> all;
    private CityAdapter cityAdapter;
    private ArrayList<HomeSeekEntity.SuggestionsEntity> suggestions;


    class SearchListTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String param = params[0];
            if (param.length() > 0) {
                String url = NetUri.SEEK_CHECK_URL + param;
                OkHttpUtils.get()
                        .url(url)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e) {

                                LogUtils.e(TAG, "请求数据失败");
                            }

                            @Override
                            public void onResponse(String response) {
                                LogUtils.e(TAG, "请求数据成功");
                                HomeSeekEntity seekinfo= new Gson().fromJson(response, HomeSeekEntity.class);
                                suggestions= (ArrayList<HomeSeekEntity.SuggestionsEntity>) seekinfo.getSuggestions();
                            }
                        });
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (suggestions != null&&suggestions.size()>0) {
                lv_seek_content.setAdapter(new Seek_movie_Adapter(getApplicationContext(), suggestions));
            }
        }
    }


    private void findViews() {

        imSeekBack = (ImageButton) findViewById(R.id.im_seek_back);
        tvSeekFiml = (EditText) findViewById(R.id.tv_seek_fiml);
        tvSeekStart = (TextView) findViewById(R.id.tv_seek_start);
        pllSeekContent = (PercentLinearLayout) findViewById(R.id.pll_seek_content);
        gvSeek = (GridView) findViewById(R.id.gv_seek);
        im_seek_scan = (ImageButton) findViewById(R.id.im_seek_scan);
        lv_record = (ListView) findViewById(R.id.lv_record);
        tv_seek_shop = (TextView) findViewById(R.id.tv_seek_shop);
        lv_seek_content = (ListView) findViewById(R.id.lv_seek_content);
        default_view = (PercentLinearLayout) findViewById(R.id.default_view);
        seek_text_01 = (LinearLayout) findViewById(R.id.seek_text_01);
        default_view.setVisibility(View.VISIBLE);
        seek_text_01.setVisibility(View.GONE);

        db = x.getDb(((MyApplication) getApplicationContext()).getDaoConfig());
        seek_record = new ArrayList<>();
        //回滚
        imSeekBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        im_seek_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeekActivity.this, CaptureActivity.class);
                startActivityForResult(intent, SCANNIN_SEEK_CODE);
            }
        });
        tvSeekStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = tvSeekFiml.getText().toString().trim();
                if (!"".equals(trim)) {
                    try {
                        db.save(new SeekCententEntity(trim));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                seek_record.add(trim);
                cityAdapter.notifyDataSetChanged();
            }
        });
        //文本输入框输入监听

        tvSeekFiml.addTextChangedListener(watcher);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        findViews();
        getSeekData();
        getNetData(NetUri.SEEK_URL);
    }

    /**
     * 查询所有搜索记录
     */
    private void getSeekData() {
        try {
            all = db.findAll(SeekCententEntity.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (all != null && all.size() > 0) {
            for (int i = 0; i < all.size(); i++) {
                seek_record.add(all.get(i).getContent());
                cityAdapter = new CityAdapter(getApplicationContext(), seek_record);
                lv_record.setAdapter(cityAdapter);
            }
        }
    }

    private void getNetData(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                        LogUtils.e(TAG, "请求数据失败");
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtils.e(TAG, "请求数据成功");
                        parserJson(response);
                    }
                });
    }


    private ArrayList<String> fimlname;

    private void parserJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray keywords = jsonObject.optJSONArray("keywords");
            fimlname = new ArrayList<>();
            for (int i = 0; i < keywords.length(); i++) {
                fimlname.add((String) keywords.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.item_seek, fimlname);
        gvSeek.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCANNIN_SEEK_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            //显示扫描到的内容
            String result = bundle.getString("result");
            //显示
            Bitmap bitmap = (Bitmap) data.getParcelableExtra("bitmap");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
