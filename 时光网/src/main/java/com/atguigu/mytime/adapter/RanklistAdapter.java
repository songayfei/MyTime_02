package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.mytime.R;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 16-4-10.
 */
public class RanklistAdapter extends BaseAdapter{
    private Activity activity;
    private List<JSONObject> lists;
    public RanklistAdapter(Activity mactivity, List<JSONObject> lists) {
        this.activity=mactivity;
        this.lists=lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null) {
            holder=new ViewHolder();
            //加载布局
            convertView = View.inflate(activity, R.layout.layout_item_ranklist, null);
            holder.text_topListNameCn = (TextView) convertView.findViewById(R.id.text_topListNameCn);
            holder.text_summary = (TextView) convertView.findViewById(R.id.text_summary);
       //关联
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //装配数据
        JSONObject itemInfo = lists.get(position);
        holder.text_topListNameCn.setText(itemInfo.optString("topListNameCn"));
        holder.text_summary.setText(itemInfo.optString("summary"));
        return convertView;
    }
    class  ViewHolder{
        TextView text_topListNameCn;
        TextView text_summary;
    }
}
