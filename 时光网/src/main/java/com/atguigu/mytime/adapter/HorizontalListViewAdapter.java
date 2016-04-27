package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.NetUri;
import com.atguigu.mytime.activity.MainActivity;
import com.atguigu.mytime.activity.homewebview.Home_movie_Activity;
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
            holder.im_3d= (TextView) convertView.findViewById(R.id.im_3d);
            holder.tvMovieName= (TextView) convertView.findViewById(R.id.tv_movie_name);
            holder.tvRating= (TextView) convertView.findViewById(R.id.tv_rating);
            holder.btnBuy= (Button) convertView.findViewById(R.id.btn_buy);
            holder.btnBuy.setOnClickListener(new TicketButtomOnClickListener());
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        final HorizontalListViewInfo.MoviesEntity moviesEntity = movies.get(position);
        final int movieId = moviesEntity.getMovieId();
        Glide.with(activity).load(moviesEntity.getImg()).into(holder.imMovie);//图片
        if(moviesEntity.isIs3D()&&moviesEntity.isIsIMAX3D()){
            holder.im_3d.setVisibility(View.VISIBLE);
            holder.im_3d.setText("IMAX 3D");
        }else if(!moviesEntity.isIsIMAX3D()&&moviesEntity.isIs3D()){
            holder.im_3d.setVisibility(View.VISIBLE);
            holder.im_3d.setText("3D");
        }else if(moviesEntity.isIsIMAX()&&!moviesEntity.isIs3D()){
            holder.im_3d.setBackgroundColor(Color.rgb(72,209,204));
            holder.im_3d.setVisibility(View.VISIBLE);
            holder.im_3d.setText("IMAX 2D");
        }else {
            holder.im_3d.setVisibility(View.GONE);
        }
        String titleCn = moviesEntity.getTitleCn();
        if(titleCn.length()>8){
            holder.tvMovieName.setText(titleCn.substring(0,9)+"...");//电影名称
        }
        holder.tvMovieName.setText(titleCn);
        holder.tvRating.setText(moviesEntity.getRatingFinal() + "");//评分
        
        //图片点击事件
        holder.imMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,Home_movie_Activity.class);
                intent.putExtra("url",NetUri.HOME_MOVIE+movieId);
                activity.startActivity(intent);
            }
        });

        //购票Buttom 到购票页面
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity= (MainActivity) activity;
                mainActivity.setPosition(1);
            }
        });
        return convertView;
    }
    static class ViewHolder{
        private ImageView imMovie;
        private TextView im_3d;
        private TextView tvRating;
        private TextView tvMovieName;
        private Button btnBuy;
    }
    class TicketButtomOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            MainActivity mainActivity= (MainActivity) activity;
            mainActivity.setPosition(1);
            activity.finish();
        }
    }
}
