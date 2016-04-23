package com.atguigu.mytime.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.entity.HomeSeekEntity;
import com.bumptech.glide.Glide;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;

/**
 * 作者: Administrator on 2016/4/21 16:36
 * 说明:
 */
public class Seek_movie_Adapter extends BaseAdapter {
    private  Context context;
    private ArrayList<HomeSeekEntity.SuggestionsEntity> suggestions;
    public Seek_movie_Adapter(Context context, ArrayList<HomeSeekEntity.SuggestionsEntity> suggestions) {
        this.context=context;
        this.suggestions=suggestions;
    }

    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public HomeSeekEntity.SuggestionsEntity getItem(int position) {
        return suggestions.get(position);
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
            convertView=View.inflate(context,R.layout.item_seek_two,null);
            holder.imSeekImg = (ImageView)convertView.findViewById(R.id.im_seek_img);
            holder.tvMatchingTitle = (TextView)convertView.findViewById(R.id.tv_matching_title);
            holder.tvProvider = (TextView)convertView.findViewById(R.id.tv_provider);
            holder.tvProperty = (TextView)convertView.findViewById(R.id.tv_property );
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        HomeSeekEntity.SuggestionsEntity suggestionsEntity = getItem(position);
        LogUtil.e(suggestionsEntity.getCover());
        Glide.with(context).load(suggestionsEntity.getCover()).into(holder.imSeekImg);
        if(suggestionsEntity.getType()==1) {
            String titlecn = suggestionsEntity.getTitlecn();
            String year = suggestionsEntity.getYear();
            String contentType = suggestionsEntity.getContentType();
            String movieType = suggestionsEntity.getMovieType();
            String titleen = suggestionsEntity.getTitleen();

            String buttomStr=contentType+movieType+suggestionsEntity.getRLocation();
            String title=titlecn+"("+year+")";
            SpannableStringBuilder ssb=new SpannableStringBuilder(title);
            ssb.append("[影片]");
            ssb.setSpan(new AbsoluteSizeSpan(20), title.length(), title.length() + 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.setSpan(new ForegroundColorSpan(Color.GRAY), title.length(), title.length() + 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tvMatchingTitle.setText(ssb);
            holder.tvProvider.setVisibility(View.VISIBLE);
            holder.tvProvider.setText(titleen);
            holder.tvProperty.setText(buttomStr);
        }else if(suggestionsEntity.getType()==2){
            holder.tvMatchingTitle.setText(suggestionsEntity.getName());
            holder.tvProvider.setVisibility(View.INVISIBLE);
            holder.tvProperty.setText(suggestionsEntity.getAddress());
        }
        return convertView;
    }

    static class ViewHolder{
         ImageView imSeekImg;
         TextView tvMatchingTitle;
         TextView tvProvider;
         TextView tvProperty;
    }
}
