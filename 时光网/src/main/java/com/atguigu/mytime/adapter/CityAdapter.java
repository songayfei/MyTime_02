package com.atguigu.mytime.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.mytime.R;

import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */
public class CityAdapter extends BaseAdapter {

    private List<String> cityInfos;
    private Context context;

    public CityAdapter(Context context,List<String> cityInfos) {
        this.context=context;
        this.cityInfos=cityInfos;
    }


    @Override
    public int getCount() {
        return cityInfos.size();
    }

    @Override
    public String  getItem(int position) {
        return cityInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context,R.layout.city_name,null);
            holder.textView= (TextView) convertView.findViewById(R.id.tv_city_name);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(cityInfos.get(position));

        return convertView;
    }
    static class ViewHolder{
        TextView textView;
    }
}
