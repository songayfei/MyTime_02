package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.entity.HorizontalListViewInfo;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2016/4/10.
 */
public class HorizontalListViewAdapter extends BaseAdapter {
    private List<HorizontalListViewInfo.MoviesEntity> movies;
    private Activity activity;
    public HorizontalListViewAdapter(List<HorizontalListViewInfo.MoviesEntity> movies,Activity activity) {
        this.movies=movies;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
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
            convertView=View.inflate(activity, R.layout.horizontallistview_item,null);
            holder.imMovie= (ImageView) convertView.findViewById(R.id.im_movie);
            holder.tvMovieName= (TextView) convertView.findViewById(R.id.tv_movie_name);
            holder.tvRating= (TextView) convertView.findViewById(R.id.tv_rating);
            holder.btnBuy= (Button) convertView.findViewById(R.id.btn_buy);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        HorizontalListViewInfo.MoviesEntity moviesEntity = movies.get(position);
        Glide.with(activity).load(moviesEntity.getImg()).into(holder.imMovie);//图片
        holder.tvMovieName.setText(moviesEntity.getTitleCn());//电影名称
        holder.tvRating.setText(moviesEntity.getRatingFinal() + "");//评分
        //图片点击事件
        holder.imMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new InterNetConn(NetUri.HLISTVIEW,activity,HomeShopInfo.class);
            }
        });
        //购票Buttom 到购票页面
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
    static class ViewHolder{
        private ImageView imMovie;
        private TextView tvRating;
        private TextView tvMovieName;
        private Button btnBuy;
    }

}
