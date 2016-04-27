package com.atguigu.mytime.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者: Administrator on 2016/4/23 16:15
 * 说明:通用的Viewholder
 */
public class ViewHolder {
    private final SparseArray<View> mViews;//保存各种控件
    private View mConvertView;

    /**
     *
     * @param context 上下文
     * @param parent
     * @param layoutId item对应的视图
     * @param position
     */
    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        mConvertView = View.inflate(context, layoutId, null);
        //setTag
        mConvertView.setTag(this);//绑定Tag
    }

    /**
     * 拿到一个ViewHolder对象
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        //根据tag获去对应的item
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 通过控件的Id获取对应的控件item，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }
}
