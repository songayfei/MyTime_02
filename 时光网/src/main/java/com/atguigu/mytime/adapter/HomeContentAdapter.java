package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.DateUtils;
import com.atguigu.mytime.entity.HomeListViewInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public class HomeContentAdapter extends BaseAdapter {
    private static final int ATLAS = 0;//图集
    private static final int NEWS = 1;//简讯
    private static final int FILM_REVIEW = 2;//影评
    private static final int HEADLINE = 3;//头条
    private static final int GUESS_MOVIE = 4;//猜电影
    private static final int NEW_FILM = 5;//欧美新片
    private static final int OVERSEAS = 6;
    private static final int PRIZE_MOVIE = 7;

    private Activity mActivity;
    private List<HomeListViewInfo.DataEntity> data;
    private String time;
    private String timeDisplay;

    public HomeContentAdapter(Activity mActivity,List<HomeListViewInfo.DataEntity> data) {
        this.mActivity = mActivity;
        this.data=data;


    }
    public void setData(List<HomeListViewInfo.DataEntity> datainfo) {
            data.addAll(datainfo);
    }
    @Override
    public int getViewTypeCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 7;
        HomeListViewInfo.DataEntity dataEntity = data.get(position);
        switch (dataEntity.getTag()) {
            case "图集":
                viewType = ATLAS;
                break;
            case "简讯":
                viewType = NEWS;
                break;
            case "影评":
                viewType = FILM_REVIEW;
                break;
            case "头条":
                viewType = HEADLINE;
                break;
            case "猜电影":
                viewType = GUESS_MOVIE;
                break;
            case "欧美新片":
                viewType = NEW_FILM;
                break;
            case "海外佳片":
                viewType = OVERSEAS;
                break;
            case "获奖佳片":
                viewType=PRIZE_MOVIE;
                break;


        }
        return viewType;
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
        int itemViewType = getItemViewType(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = initTypeView(convertView, holder, itemViewType);
            initCommView(convertView, itemViewType, holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        setViewData(position, holder);

        return convertView;
    }

    private void setViewData(int position, ViewHolder holder) {
        //获取该数据的类型
        int itemViewType = getItemViewType(position);
        //获取对应的数据对象
        HomeListViewInfo.DataEntity dataEntity = data.get(position);
        //判断该数据类型
        switch (itemViewType) {
            case ATLAS://图集
                bindData(dataEntity,holder);
                //加载三张图片
                Glide.with(mActivity).load(dataEntity.getImg1()).error(R.drawable.img_default_45x45).placeholder(R.drawable.img_default_45x45).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.im01);
                Glide.with(mActivity).load(dataEntity.getImg2()).error(R.drawable.img_default_45x45).placeholder(R.drawable.img_default_45x45).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.im02);
                Glide.with(mActivity).load(dataEntity.getImg3()).error(R.drawable.img_default_45x45).placeholder(R.drawable.img_default_45x45).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.im03);
                //获取发布的时间
                time = dataEntity.getTime();
                timeDisplay = DateUtils.getTimeDisplay(time);
                holder.tvTime.setText(timeDisplay);
                holder.tvHotcomment.setText("评论 " + dataEntity.getCommentCount());
                break;
            case NEWS://简讯
                Integer type=2;
                if(type.equals(dataEntity.getDataType())){
                    holder.imNewsPlay.setVisibility(View.VISIBLE);
                }else {
                    holder.imNewsPlay.setVisibility(View.GONE);
                }
                bindData(dataEntity,holder);
                Glide.with(mActivity).load(dataEntity.getImg1())
                        .error(R.drawable.img_default_45x45)
                        .placeholder(R.drawable.img_default_45x45)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imNews);
                //获取发布的时间
                time = dataEntity.getTime();
                timeDisplay = DateUtils.getTimeDisplay(time);
                holder.tvTime.setText(timeDisplay);
                holder.tvHotcomment.setText("评论 " + dataEntity.getCommentCount());
                break;
            case FILM_REVIEW://影评
                //图文混排
                SpannableStringBuilder ssb=new SpannableStringBuilder(dataEntity.getSummaryInfo());
                ssb.setSpan(new ImageSpan(mActivity, R.drawable.quote_mark), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.tvTitle.setText(dataEntity.getTitle());
                holder.tvTitlen.setText(ssb);
                Glide.with(mActivity).load(dataEntity.getRelatedObj().getImage())
                        .error(R.drawable.img_default_90x90)
                        .placeholder(R.drawable.img_default_90x90)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imFilm);

                //头像
                Glide.with(mActivity).load(dataEntity.getUserImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imUser);


                String nickname = dataEntity.getNickname();
                String titleCn = dataEntity.getRelatedObj().getName();
                holder.tvCommentCount.setText(nickname + "-评《" + titleCn + "》");
                holder.tv_rating.setText(dataEntity.getRating() + "");
                break;
            case HEADLINE://头条
                bindData(dataEntity,holder);

                Glide.with(mActivity).load(dataEntity.getImg1())
                        .error(R.drawable.img_default_300x200)
                        .placeholder(R.drawable.img_default_300x200)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imHeadline);
                //获取发布的时间
                time = dataEntity.getTime();
                timeDisplay = DateUtils.getTimeDisplay(time);
                holder.tvTime.setText(timeDisplay);
                holder.tvHotcomment.setText("评论 " + dataEntity.getCommentCount());
                break;
            case GUESS_MOVIE://猜电影
                holder.tvTitle.setText(dataEntity.getTitle());
                holder.tvCommentCount.setText(dataEntity.getMemo());
                Glide.with(mActivity).load(dataEntity.getPic1Url())
                        .error(R.drawable.img_default_45x45)
                        .placeholder(R.drawable.img_default_45x45)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imNews);
                break;
            case NEW_FILM://欧美新片
                holder.tvTitle.setText(dataEntity.getTitleCn());
                holder.tvTitlen.setText(dataEntity.getTitleEn());
                holder.tvCommentCount.setText(dataEntity.getContent());
                Glide.with(mActivity).load(dataEntity.getImage())
                        .error(R.drawable.img_default)
                        .placeholder(R.drawable.img_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imNews);
                break;
            case OVERSEAS://海外挂片
                holder.tvTitle.setText(dataEntity.getTitleCn());
                holder.tvTitlen.setText(dataEntity.getTitleEn());
                holder.tvCommentCount.setText(dataEntity.getContent());
                holder.tv_grade.setText(dataEntity.getRating());
                Glide.with(mActivity).load(dataEntity.getImage())
                        .error(R.drawable.img_default)
                        .placeholder(R.drawable.img_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imNews);
                break;
            case PRIZE_MOVIE://获奖佳片

                holder.tvTitle.setText(dataEntity.getTitleCn());
                Log.e(dataEntity.getTitleEn(),dataEntity.getTitleEn());
                holder.tvTitlen.setText(dataEntity.getTitleEn());
                holder.tvCommentCount.setText(dataEntity.getContent());
                Glide.with(mActivity).load(dataEntity.getImage())
                        .error(R.drawable.img_default)
                        .placeholder(R.drawable.img_default)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imNews);
                break;
        }


    }

    private void bindData(HomeListViewInfo.DataEntity dataEntity, ViewHolder holder) {
        //设置标题和 文本
        holder.tvTitle.setText(dataEntity.getTitle());
        holder.tvCommentCount.setText(dataEntity.getContent());
    }

   //初始化不同类型公有的部分
    private void initCommView(View convertView, int itemViewType, ViewHolder holder) {
        switch (itemViewType) {
            case ATLAS:
            case NEWS:
            case HEADLINE:
            case GUESS_MOVIE:
            case NEW_FILM:
            case FILM_REVIEW:
            case OVERSEAS:
            case PRIZE_MOVIE:
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);
                break;
        }
    }

    private View initTypeView(View convertView, ViewHolder holder, int itemViewType) {
        switch (itemViewType) {
            case ATLAS://图集
                convertView = View.inflate(mActivity, R.layout.home_buttom_image, null);
                holder.im01 = (ImageView) convertView.findViewById(R.id.im_01);
                holder.im02 = (ImageView) convertView.findViewById(R.id.im_02);
                holder.im03 = (ImageView) convertView.findViewById(R.id.im_03);
                /*//标题加说明
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);*/
                //发布时间
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvHotcomment = (TextView) convertView.findViewById(R.id.tv_hotcomment);
                break;
            case NEWS://简讯
                convertView = View.inflate(mActivity, R.layout.home_listview_news, null);
                holder.imNews = (ImageView) convertView.findViewById(R.id.im_news);
                holder.imNewsPlay = (ImageView) convertView.findViewById(R.id.im_news_play);
                /*//标题加说明
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);*/
                //发布时间
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvHotcomment = (TextView) convertView.findViewById(R.id.tv_hotcomment);
                break;
            case FILM_REVIEW://影评
                convertView = View.inflate(mActivity, R.layout.film_review_item, null);
                /*//标题加说明
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);*/

                holder.tvTitlen = (TextView) convertView.findViewById(R.id.tv_titlen);//热评论
                holder.imUser = (ImageView) convertView.findViewById(R.id.im_user);//用户头像
                holder.imFilm = (ImageView) convertView.findViewById(R.id.im_film);//图片
                holder.tv_rating = (TextView) convertView.findViewById(R.id.tv_rating);//评分
                break;
            case HEADLINE://头条
                convertView = View.inflate(mActivity, R.layout.home_listview_headline, null);
                holder.imHeadline = (ImageView) convertView.findViewById(R.id.im_headline);
               /* //标题加说明
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);*/
                //发布时间
                holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
                holder.tvHotcomment = (TextView) convertView.findViewById(R.id.tv_hotcomment);
                break;
            case GUESS_MOVIE://猜电影
                convertView = View.inflate(mActivity, R.layout.guess_movie_item, null);
                /*//标题加说明
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);*/
                holder.imNews = (ImageView) convertView.findViewById(R.id.im_news);
                break;
            case NEW_FILM://欧美新片
                convertView = View.inflate(mActivity, R.layout.new_film_item, null);
                /*//标题加说明
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_commentcount);*/

                holder.tvTitlen = (TextView) convertView.findViewById(R.id.tv_titlen);//热评论
                holder.imNews = (ImageView) convertView.findViewById(R.id.im_news);
                break;
            case OVERSEAS://海外挂片
                convertView=View.inflate(mActivity,R.layout.overseas_item,null);
                holder.tvTitlen = (TextView) convertView.findViewById(R.id.tv_titlen);//热评论
                holder.tv_grade= (TextView) convertView.findViewById(R.id.tv_grade);//评分
                holder.imNews = (ImageView) convertView.findViewById(R.id.im_news);
                break;
            case PRIZE_MOVIE:
                convertView=View.inflate(mActivity,R.layout.prize_movie_item,null);
                holder.tvTitlen = (TextView) convertView.findViewById(R.id.tv_titlen);//热评论
                holder.imNews = (ImageView) convertView.findViewById(R.id.im_news);
                break;


        }
        return convertView;
    }

    static class ViewHolder {
        //图集的三张图片
        ImageView im01;
        ImageView im02;
        ImageView im03;
        //简讯
        ImageView imNews;//猜电影
        ImageView imNewsPlay;
        //影评
        TextView tvTitlen;//欧美新片神神回复|海外挂片
        ImageView imUser;
        ImageView imFilm;
        TextView tv_rating;
        //头条
        ImageView imHeadline;

        TextView tv_grade;//海外挂片评分

        /*//猜电影
        ImageView imNews;*/

        //共有部分
        TextView tvTitle;//标题
        TextView tvCommentCount;//说明

        //发布时间+评论
        TextView tvTime;
        TextView tvHotcomment;

    }
}
