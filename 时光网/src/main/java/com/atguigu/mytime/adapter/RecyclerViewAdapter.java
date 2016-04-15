package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.entity.MallGoodsInfos;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author YfSong
 * @Time 19:25
 * Created by YfSong on 2016/4/11.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<MallGoodsInfos.TopicEntity> datas;
    private Activity mActivity;

    public RecyclerViewAdapter(List datas, Activity mActivity) {
        this.datas = datas;
        this.mActivity = mActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mActivity, R.layout.mall_top2_inner_item, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    MyOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(MyOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

     public interface   MyOnItemClickListener {
         void onItemClick(ImageView view, int position);
     }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        loadImage(holder.iv_top2_inner, datas.get(position).getUncheckImage());


        holder.iv_top2_inner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.iv_top2_inner, position);
            }
        });
    }
    private void loadImage(ImageView imageView,String url) {
        Glide.with(mActivity)
                .load(url)
                .into(imageView);
    }
    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public  ImageView iv_top2_inner;

        public ViewHolder(View view) {
            super(view);
            iv_top2_inner = (ImageView) view.findViewById(R.id.iv_top2_inner);
        }
    }


}


