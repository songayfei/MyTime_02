package com.atguigu.mytime.pager.payticket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.CacheUtils;
import com.atguigu.mytime.Utils.ConstantUtils;
import com.atguigu.mytime.activity.MoviesItemActivity;
import com.atguigu.mytime.adapter.PMListViewAdapte;
import com.atguigu.mytime.base.BaseDiscoverPager;
import com.atguigu.mytime.entity.PlayingMovieBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 上映了
 */
public class PlayingMovie extends BaseDiscoverPager {
    @ViewInject(R.id.lv_playing_movie)
    private ListView lv_playing_movie;

    @ViewInject(R.id.iv_progressbar_buyticket)
    private ImageView iv_progressbar_buyticket;

    /**
     * 数据集合
     */
    private List<PlayingMovieBean.MsEntity> ms;
    /**
     * 动画
     */
    private AnimationDrawable background;
    private PlayingMovieBean movieBean;

    public PlayingMovie(Activity activity) {
        super(activity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.playing_movie_view,null);
        x.view().inject(this, view);
        background = (AnimationDrawable) iv_progressbar_buyticket.getBackground();
        return view;
    }

    @Override
    public void initData() {
        background.start();//启动动画
        //从内存中获取数据
        String result = CacheUtils.getString(mActivity, ConstantUtils.MOVIE_LIST + "?locationId=290");
        if(!TextUtils.isEmpty(result)) {
            processData(result);//处理数据
        }
        getDataFromNet();

    }

    private void getDataFromNet() {
        //联网加载数据
        RequestParams params = new RequestParams(ConstantUtils.MOVIE_LIST+"?locationId=290");//北京地址对应的数据
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("TAG", "result == " + result);
                //保存数据
                CacheUtils.putString(mActivity, ConstantUtils.MOVIE_LIST+"?locationId=290", result);
                processData(result);//处理数据
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void processData(String result) {
        //动画停止
        background.stop();
        iv_progressbar_buyticket.setVisibility(View.GONE);//让动画视图消失

        movieBean = parseData(result);
        //给listview设置适配器
        lv_playing_movie.setAdapter(new PMListViewAdapte(mActivity,movieBean));
        lv_playing_movie.setOnItemClickListener(new MyOnItemClickListener());
    }

    private PlayingMovieBean parseData(String result) {
        return new Gson().fromJson(result,PlayingMovieBean.class);
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PlayingMovieBean.MsEntity msEntity = movieBean.getMs().get(position);
            Toast.makeText(mActivity, "进不去啊", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mActivity, MoviesItemActivity.class);
//            Intent intent = new Intent(mActivity, PlayMovieItemActivity.class);
//           Bundle bundle=new Bundle();
//           bundle.putSerializable(PlayMovieItemActivity.MOVIELISTPOSITION, msEntity);
//           intent.putExtras(bundle);
//            intent.putExtra(PlayMovieItemActivity.MOVIELISTPOSITION,position);
//            intent.putExtra(PlayMovieItemActivity.MOVIEID,msEntity.getId());
           mActivity.startActivity(intent);
        }
    }
}
