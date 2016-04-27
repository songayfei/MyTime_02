package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.MyApplication;
import com.atguigu.mytime.Utils.PinYinUtils;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.activity.MainActivity;
import com.atguigu.mytime.entity.CityEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 * 城市列表
 */
public class CityListAdapter extends BaseAdapter {
    private List<String> cityInfos;
    private Activity mActivtiy;
    private List<String> pinying;
    public String cityname;
    private boolean value;
    private DbManager db;



    public CityListAdapter(Activity mActivtiy, List<String> cityInfos, List<String> pinying, boolean value) {
        this.mActivtiy=mActivtiy;
        this.cityInfos=cityInfos;
        this.pinying=pinying;
        this.value=value;
        db = x.getDb(((MyApplication) mActivtiy.getApplication()).getDaoConfig());
    }
    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    @Override
    public int getCount() {
        return pinying.size();
    }

    @Override
    public String  getItem(int position) {
        return pinying.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        List<String> citynames=new ArrayList<>();
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(mActivtiy, R.layout.city_classfile,null);
            holder.textView= (TextView) convertView.findViewById(R.id.tv_item_word);
            holder.gridView= (GridView) convertView.findViewById(R.id.gd_city_list);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        String word = pinying.get(position);
        holder.textView.setText(word);
        //遍历城市数据
        for(int i=0;i<cityInfos.size();i++){
            //获取城市首字拼音的第一个字母
            String str = PinYinUtils.getPinYin(cityInfos.get(i)).substring(0, 1);
            if(str!=null&&str.equals(word)){
                //添加首字母相同的城市套集合中
                citynames.add(cityInfos.get(i));
            }
        }
        //装配数据到集合
        holder.gridView.setAdapter(new CityAdapter(mActivtiy,citynames));
        //设置点击监听
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView= (TextView) view;
                cityname= (String) textView.getText();//获取点击的城市名
                if(!value) {
                    mActivtiy.startActivity(new Intent(mActivtiy, MainActivity.class));
                }
                getDBData(cityname);
                mActivtiy.finish();
            }
        });
        return convertView;
    }
    static class ViewHolder{
        TextView textView;
        GridView gridView;
    }
    private void getDBData(String city) {
        try {
            List<CityEntity> all = db.selector(CityEntity.class).where("cityname", "=", city).findAll();
            if (all != null && all.size() > 0) {
                int id = all.get(0).getCityid();
                //保存选中的城市名和id
                SpUtils.getInitialize(mActivtiy.getApplicationContext()).save(SpUtils.CITY_NAME,city);
                SpUtils.getInitialize(mActivtiy.getApplicationContext()).save(SpUtils.CITY_ID, id);
                Intent intent =mActivtiy.getIntent();
                intent.putExtra("city_name_id", new String[]{String.valueOf(id), city});
                mActivtiy.setResult(mActivtiy.RESULT_OK, intent);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
