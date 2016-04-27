package com.atguigu.mytime.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * 作者: Administrator on 2016/4/23 16:49
 * 说明:通用的adapter
 */
public abstract class CommonsAdapter<T> extends BaseAdapter {
    protected  Context context;
    protected  ArrayList<T> mDatas;

    public CommonsAdapter(Context context, ArrayList<T> mDatas) {
        this.context=context;
        this.mDatas=mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
