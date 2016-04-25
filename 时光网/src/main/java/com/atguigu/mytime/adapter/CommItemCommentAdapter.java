package com.atguigu.mytime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mytime.R;

import java.util.ArrayList;
import java.util.List;

public abstract class CommItemCommentAdapter<T> extends BaseAdapter {

    private List<T> data = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CommItemCommentAdapter(Context context,List<T> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.comm_item_comment, null);
            convertView.setTag(new MyViewHolder(convertView));
        }
        initializeViews((T)getItem(position), (MyViewHolder) convertView.getTag());
        return convertView;
    }

    public abstract void initializeViews(T object, MyViewHolder holder) ;

    protected class MyViewHolder {
        public LinearLayout itemDiscussLlContainer;
        public ImageView itemDiscussIvIcon;
        public TextView itemDiscussTvName;
        public LinearLayout itemDiscussLlScoreContainer;
        public TextView itemDiscussScorePing;
        public TextView itemDiscussTvReply;

        public MyViewHolder(View view) {
            itemDiscussLlContainer = (LinearLayout) view.findViewById(R.id.item_discuss_ll_container);
            itemDiscussIvIcon = (ImageView) view.findViewById(R.id.item_discuss_iv_icon);
            itemDiscussTvName = (TextView) view.findViewById(R.id.item_discuss_tv_name);
            itemDiscussLlScoreContainer = (LinearLayout) view.findViewById(R.id.item_discuss_ll_score_container);
            itemDiscussScorePing = (TextView) view.findViewById(R.id.item_discuss_score_ping);
            itemDiscussTvReply = (TextView) view.findViewById(R.id.item_discuss_tv_reply);
        }
    }
}
