package com.atguigu.mytime.discoverpagers;

import android.app.Activity;
import android.view.View;

import com.atguigu.mytime.R;
import com.atguigu.mytime.base.BasePager;
import com.atguigu.refreshlistview.RefreshListView;

import org.json.JSONObject;

/**
 * 排行榜
 * Created by Administrator on 16-4-8.
 */
public class Ranklistpager extends BasePager {
private RefreshListView listview_ranklist;
    private JSONObject topList ;
    public Ranklistpager(Activity mactivity, JSONObject topList) {
        super(mactivity);
        this.topList=topList;
    }

    @Override
    public View initView() {
        View view = View.inflate(mactivity, R.layout.listview_ranklist, null);
        listview_ranklist = (RefreshListView) view.findViewById(R.id.listview_ranklist);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
