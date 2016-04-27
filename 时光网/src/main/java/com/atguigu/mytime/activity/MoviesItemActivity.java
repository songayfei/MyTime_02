package com.atguigu.mytime.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.adapter.CommItemCommentAdapter;
import com.atguigu.mytime.bean.pingrun;
import com.atguigu.mytime.net.OkhttpUtils2;
import com.atguigu.mytime.view.ExpandableTextView;
import com.atguigu.mytime.view.ObservableScrollView;

import java.util.List;

public class MoviesItemActivity extends Activity {

    private ExpandableTextView expandable_textView;
    private ListView listview_comment;
    private ObservableScrollView observableScrollView;
    private LinearLayout ll_ping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_item);
        listview_comment = (ListView) findViewById(R.id.listview_comment);
        observableScrollView = (ObservableScrollView) findViewById(R.id.ObservableScrollView);
        ll_ping = (LinearLayout) findViewById(R.id.ll_ping);
        new OkhttpUtils2<pingrun>("http://api.m.mtime.cn/Showtime/MovieComments.api?movieId=207872&pageIndex=1", this, pingrun.class,true);
        expandable_textView = (ExpandableTextView)findViewById(R.id.expandable_TextView);
        expandable_textView.setText("冰雪女王弗雷娅（艾米莉·布朗特饰）是邪恶女王拉文纳（查理兹·塞隆饰）的妹妹。拉文纳通过魔镜得知妹妹的女儿将会成为世界上最美的女人。弗雷娅在一次意外中失去了孩子，丧子之痛激发了弗雷娅巨大的魔力，从一个善良温柔的女人变成了冷酷无情的冰雪女王，不仅创建了千里冰封的冰雪王国，更是在王国内制定了不准相爱的规则。猎人（克里斯·海姆斯沃斯饰）与女勇士（杰西卡·查斯坦饰）的爱情之路被迫终止，作为弗雷娅培养的部下，二人经历艰难后将合力对抗两大女王，不仅是一场为爱而展开的斗争，更是一场推翻女王统治的为争取自由而引发的正邪大战[3]  。");


    }

    public void onEventMainThread(pingrun event) {
        List<pingrun.CtsEntity> cts = event.getCts();

        //ll_ping.addView();

        for (pingrun.CtsEntity item : cts) {
            View view = View.inflate(this, R.layout.comm_item_comment, ll_ping);
            TextView item_discuss_tvname = (TextView) view.findViewById(R.id.item_discuss_tv_name);
            item_discuss_tvname.setText(item.getCe());
            ll_ping.addView(view);
        }
        CommItemCommentAdapter<pingrun.CtsEntity> adapter = new CommItemCommentAdapter<pingrun.CtsEntity>(this, cts) {
            @Override
            public void initializeViews(pingrun.CtsEntity object, MyViewHolder holder) {
                holder.itemDiscussTvName.setText(object.getCe());
            }


        };
        listview_comment.setAdapter(adapter);


    }
}
