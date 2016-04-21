package com.atguigu.mytime.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.mytime.R;
import com.atguigu.mytime.entity.WillShowMovieBean;
import com.atguigu.mytime.view.SectionedBaseAdapter;

import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Administrator on 2016/3/1.
 */
public class MyWillShowMovieListAdapter extends SectionedBaseAdapter {

    private Activity activity;
    private WillShowMovieBean willShowMovieBean;

    private final List<WillShowMovieBean.MoviecomingsEntity> moviecomings;
    private final LinkedHashSet<Integer> testSet;
    private final ArrayList<Integer> list;
    private final ArrayList<Integer> names;
    private final List<ArrayList<WillShowMovieBean.MoviecomingsEntity>> sectionItems;

    public MyWillShowMovieListAdapter(Activity activity, WillShowMovieBean willShowMovieBean) {
        this.activity = activity;
        this.willShowMovieBean = willShowMovieBean;
        moviecomings = willShowMovieBean.getMoviecomings();
        testSet = new LinkedHashSet<Integer>();
        for (WillShowMovieBean.MoviecomingsEntity entity : moviecomings) {
            testSet.add(entity.getRMonth());

        }

        names = new ArrayList<>();
        names.addAll(testSet);
        sectionItems = new ArrayList<>();
        int count=0;
       Iterator<Integer> iterator = testSet.iterator();
        list = new ArrayList<>();


       while(iterator.hasNext()) {
           Integer next = iterator.next();
           ArrayList<WillShowMovieBean.MoviecomingsEntity> objects = new ArrayList<>();
           for(int j=0;j<moviecomings.size();j++){

               if(next.equals(moviecomings.get(j).getRMonth())){
                   objects.add(moviecomings.get(j));
                   count++;
               }
           }
           sectionItems.add(objects);
           list.add(count);
           count=0;


       }









    }


    @Override
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {


        convertView = initHolder(section,position, convertView);
        return convertView;
    }

    @NonNull
    private View initHolder(int section,final int position, View convertView) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.willshowmovie_listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.month_view = (LinearLayout) convertView.findViewById(R.id.month_view);
            viewHolder.iv_icon_lvwillshow_item = (ImageView) convertView.findViewById(R.id.iv_icon_lvwillshow_item);
            viewHolder.tv_name_lvwillshow_item = (TextView) convertView.findViewById(R.id.tv_name_lvwillshow_item);
            viewHolder.tv_month_willshow_list_item = (TextView) convertView.findViewById(R.id.tv_month_willshow_list_item);
            viewHolder.tv_day_willshow_list_item = (TextView) convertView.findViewById(R.id.tv_day_willshow_list_item);
            viewHolder.tv_type_lvwillshow_item = (TextView) convertView.findViewById(R.id.tv_type_lvwillshow_item);
            viewHolder.tv_director_lvwillshow_item = (TextView) convertView.findViewById(R.id.tv_director_lvwillshow_item);
            viewHolder.btn_prebuy_lv_willshow_item = (Button) convertView.findViewById(R.id.btn_prebuy_lv_willshow_item);
            viewHolder.btn_movie_lv_willshow_item = (Button) convertView.findViewById(R.id.btn_movie_lv_willshow_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final WillShowMovieBean.MoviecomingsEntity moviecomingsEntity = sectionItems.get(section).get(position);
        //获取时间月
        int rMonth = moviecomingsEntity.getRMonth();
        viewHolder.tv_month_willshow_list_item.setText(rMonth + "月");
        //获取时间日
        int rDay = moviecomingsEntity.getRDay();
        viewHolder.tv_day_willshow_list_item.setText(rDay + "日");
        //获取标签
        String title = moviecomingsEntity.getTitle();
        viewHolder.tv_name_lvwillshow_item.setText(title);
        //获取多少人想看+类型和产地
        int wantedCount = moviecomingsEntity.getWantedCount();
        String type = moviecomingsEntity.getType();
        String locationName = moviecomingsEntity.getLocationName();
        viewHolder.tv_type_lvwillshow_item.setText(wantedCount + "人想看-" + type + "/" + locationName);
        //导演
        String director = moviecomingsEntity.getDirector();
        viewHolder.tv_director_lvwillshow_item.setText(director);

        //是否有门票
        boolean isTicket = moviecomingsEntity.isIsTicket();

        if (isTicket) {
            viewHolder.btn_prebuy_lv_willshow_item.setVisibility(View.VISIBLE);
            viewHolder.btn_prebuy_lv_willshow_item.setBackgroundResource(R.drawable.home_button_movie);
            viewHolder.btn_prebuy_lv_willshow_item.setText("超前预售");
            viewHolder.btn_prebuy_lv_willshow_item.setTextColor(0XFFFFFFFF);
            viewHolder.btn_prebuy_lv_willshow_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(activity, BuyMovieActivity.class);
//                    intent.putExtra("name", moviecomingsEntity.getTitle());
//                    activity.startActivity(intent);

                    Toast.makeText(activity, "售票页面", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Drawable background = viewHolder.btn_prebuy_lv_willshow_item.getBackground();
            viewHolder.btn_prebuy_lv_willshow_item.setBackgroundResource(R.drawable.actor_detail_unlike_background_on);

            viewHolder.btn_prebuy_lv_willshow_item.setText("上映提醒");
            viewHolder.btn_prebuy_lv_willshow_item.setTextColor(0XFF669E10);
        }

        //是否有预告片
        boolean isVideo = moviecomingsEntity.isIsVideo();
        if (isVideo) {//有票也有预告片
            viewHolder.btn_movie_lv_willshow_item.setVisibility(View.VISIBLE);
            viewHolder.btn_movie_lv_willshow_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(activity, VidoListActivity.class);
//                    intent.putExtra("id", moviecomingsEntity.getId());
//                    activity.startActivity(intent);
                    Toast.makeText(activity, "有票也有预告片", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            viewHolder.btn_movie_lv_willshow_item.setVisibility(View.GONE);
        }
        //获取图片的照片
        String image = moviecomingsEntity.getImage();
        x.image().bind(viewHolder.iv_icon_lvwillshow_item, image);

        //判断是否显示月份
        if (position != 0) {
            WillShowMovieBean.MoviecomingsEntity moviecomingsEntity1 = moviecomings.get(position - 1);
            int rMonth1 = moviecomingsEntity1.getRMonth();

            if (rMonth == rMonth1) {//和上一个相同则隐藏
                viewHolder.month_view.setVisibility(View.GONE);
            } else {
                viewHolder.month_view.setVisibility(View.GONE);
            }
        } else {
            viewHolder.month_view.setVisibility(View.GONE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = moviecomingsEntity.getId();
//                Intent intent = new Intent(activity, PlayMovieItemActivity.class);
//                intent.putExtra(PlayMovieItemActivity.MOVIELISTPOSITION,position);
//                intent.putExtra(PlayMovieItemActivity.MOVIEID,moviecomingsEntity.getId());
//                activity.startActivity(intent);
                Toast.makeText(activity, "PlayMovieItemActivity", Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }


    @Override
    public Object getItem(int section, int position) {
        return null;
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getSectionCount() {
        return testSet.size();
    }

    @Override
    public int getCountForSection(int section) {
        return list.get(section);
    }


    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        TextView tv_month_willshow_list_item = null;
        if (convertView == null) {
            convertView = View.inflate(activity, R.layout.month_bar, null);

            tv_month_willshow_list_item = (TextView) convertView.findViewById(R.id.tv_month_willshow_list_item);
            convertView.setTag(tv_month_willshow_list_item);
        } else {
            tv_month_willshow_list_item = (TextView) convertView.getTag();
        }

        tv_month_willshow_list_item.setText(names.get(section)+"月");

        return convertView;
    }


    class ViewHolder {
        private LinearLayout month_view;
        private ImageView iv_icon_lvwillshow_item;
        private TextView tv_month_willshow_list_item;
        private TextView tv_day_willshow_list_item;
        private TextView tv_name_lvwillshow_item;
        private TextView tv_type_lvwillshow_item;
        private TextView tv_director_lvwillshow_item;
        private Button btn_prebuy_lv_willshow_item;
        private Button btn_movie_lv_willshow_item;
    }
}
