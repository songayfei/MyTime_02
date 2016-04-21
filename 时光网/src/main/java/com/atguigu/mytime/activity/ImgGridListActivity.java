package com.atguigu.mytime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImgGridListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
private ImageView image_back;
    private TextView imgs_title;
    private GridView pic_grid;
    private ArrayList<String> imagesUrls;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_grid_list);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        imagesUrls=getIntent().getStringArrayListExtra("imagesUrls");
        title=getIntent().getStringExtra("title");
        imgs_title.setText(title);
        pic_grid.setAdapter(new GridAdapter());
    }

    /**
     * 初始化视图
     */
    private void initView() {
        image_back = (ImageView)findViewById(R.id.image_back);
        imgs_title = (TextView)findViewById(R.id.imgs_title);
        pic_grid = (GridView)findViewById(R.id.pic_grid);
        image_back.setOnClickListener(this);
        //给GridView设置监听
        pic_grid.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v){

        finish();
    }

    /**
     * 点击某个item的回调
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        setResult(2, intent);
        finish();
    }

    class GridAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return imagesUrls.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            String uri = imagesUrls.get(position);
            ImageView imageView = new ImageView(ImgGridListActivity.this);
            Glide.with(ImgGridListActivity.this).load(uri).into(imageView);
            return imageView;
        }
    }
}
