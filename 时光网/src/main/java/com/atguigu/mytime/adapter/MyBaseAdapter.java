package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @Author YfSong
 * @Time 15:51
 * Created by YfSong on 2016/4/11.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    public List<T> data;

    public Activity mActivity;

    public MyBaseAdapter(List data, Activity mActivity) {
        this.data = data;
        this.mActivity = mActivity;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {




        return MyInstantiateItem(position, convertView, parent);
    }

    public abstract View MyInstantiateItem(int position, View convertView, ViewGroup parent);
}


