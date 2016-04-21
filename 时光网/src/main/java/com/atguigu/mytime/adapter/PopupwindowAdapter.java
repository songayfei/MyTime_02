package com.atguigu.mytime.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 16-4-20.
 */
public class PopupwindowAdapter extends BaseAdapter {
    private Context context;
    private int year;
    private ArrayList<String> popListimageName;
    private ArrayList<String> popListimageUrls;
    private int rating;
    public PopupwindowAdapter(Context context, int year, int rating, ArrayList<String> popListimageName, ArrayList<String> popListimageUrls) {
        this.context=context;
        this.popListimageName=popListimageName;
        this.popListimageUrls=popListimageUrls;
        this.year=year;
        this.rating=rating;
    }

    @Override
    public int getCount() {
        return popListimageUrls.size();
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
            convertView=View.inflate(context, R.layout.news_pop_item,null);

            holder.iv_movie= (ImageView) convertView.findViewById(R.id.iv_movie);
            holder.tv_moive_rate = (TextView) convertView.findViewById(R.id.tv_moive_rate);
            holder.movie_title = (TextView) convertView.findViewById(R.id.movie_title);
            holder.movie_time = (TextView) convertView.findViewById(R.id.movie_time);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //装配数据
        String popimageUrl = popListimageUrls.get(position);
        String popimageName = popListimageName.get(position);
        if(position==0) {
           holder.tv_moive_rate.setVisibility(View.VISIBLE);
            holder.movie_time.setVisibility(View.VISIBLE);
            holder.movie_time.setText(""+year);
            holder.tv_moive_rate.setText(""+rating);
        }else{
            holder.tv_moive_rate.setVisibility(View.GONE);
            holder.movie_time.setVisibility(View.GONE);
        }
        Glide.with(context).load(popimageUrl).into(holder.iv_movie);
        holder.movie_title.setText(popimageName);
        return convertView;
    }
    class ViewHolder{
        ImageView iv_movie;
        TextView tv_moive_rate;
        TextView movie_title;
        TextView movie_time;
    }
}
