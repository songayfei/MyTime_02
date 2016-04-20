package com.atguigu.mytime.activity;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import com.atguigu.mytime.R;
import com.atguigu.mytime.Utils.DensityUtils;
import com.atguigu.mytime.Utils.MessageUtils;
import com.atguigu.mytime.Utils.SpUtils;
import com.atguigu.mytime.Utils.Utils;
import com.atguigu.mytime.view.VideoView;

import org.xutils.common.util.DensityUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.vov.vitamio.Vitamio;

public class SystemVideoPlayerActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private PopAdapter adapter;
    /**
     * 视频进度的更新
     */
    private static final int PROGRESS = 1;
    /**
     * 隐藏控制面板的消息
     */
    private static final int HIDE_MEDIACONTROLLER = 2;
    /**
     * 默认屏幕
     */
    private static final int SCREEN_DEFAULT = 3;
    /**
     * 全屏播放视频
     */
    private static final int SCREEN_FULL = 4;
    private static final int SHOW_SPEED = 5;
    private VideoView videoview;

    private Uri uri;

    private LinearLayout llTop;
    private TextView tvName;
    private TextView tvSystemTime;
    private Button btnVoice;
    private SeekBar seekbarVoice;
    private LinearLayout llBottom;
    private TextView tvCurrentTime;
    private SeekBar seekbarVideo;
    private TextView tvDuration;
    private Button btnVideoExit;
    private Button btnVideoPre;
    private Button btnVideoPlayPause;
    private Button btnVideoNext;
    private Button btnVideoSwitchScreen;
    private TextView tv_theme;//点击切换标清和高清
//    private ImageView iv_theme;

    private ArrayList<String> moveNames = new ArrayList<>();//视频题目的集合

    private boolean isActivityDestroy = false;

    private Utils utils;
    private ArrayList<String> urlList = new ArrayList<>();//视频地址
    private ArrayList<String> heighturl = new ArrayList<>();//高清视频地址的集合
    private PopupWindow popupWindow;
    private List<String> popList = new ArrayList<>();
    private ListView listView;
    private LinearLayout ll_theme;
    private boolean isClick = false;

    /**
     * 视频列表中播放到哪个视频
     */
    public int position;

    //手势识别器的使用
    //1.定义手势识别器
    private GestureDetector detector;
    //2.实例化手势识别器
    //3.在onTouchEvent()里面接受事件

    /**
     * 是否隐藏控制面板
     */
    private boolean isMediaController = true;


    /**
     * 是否全屏
     * true:全屏；false:非全屏
     */
    private boolean isScreenFull = false;

    /**
     * 屏幕的宽
     */
    private int screenWidth;

    /**
     * 屏幕的高
     */
    private int screenHeight;

    /**
     * 视频的宽
     */
    private int videoWidth;

    /**
     * 视频的高
     */
    private int videoHeight;

    /**
     * 调节系统音量
     */
    private AudioManager am;

    /**
     * 当前音量
     */
    private int currentVolume;

    /**
     * 最大音量0~15之间
     */
    private int maxVolume;

    /**
     * 是否静音
     */
    private boolean isMute = false;

    /**
     * 是否是网络的播放地址
     */
    private boolean isNetUri = false;

    /**
     * 上一秒播放的进度
     */
    private int preCurrrentPosition;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HIDE_MEDIACONTROLLER:
                    hideMediaController();
                    break;
                case PROGRESS://视频进度的更长

                    int currentPosition = (int) videoview.getCurrentPosition();//0

                    //2.SeekBar更新，设置setProgress（）参数
                    seekbarVideo.setProgress(currentPosition);//SeekBar更新进度


                    //设置时间
                    tvCurrentTime.setText(utils.stringForTime(currentPosition));

                    //得到系统时间
                    tvSystemTime.setText(getSystemTime());


                    //设置视频缓存效果
                    if (isNetUri) {
                        //网络资源
                        int bufferPercentage = videoview.getBufferPercentage();//缓存

                        int totalBuffer = bufferPercentage * seekbarVideo.getMax();

                        int secondaryProgress = totalBuffer / 100;

                        System.out.println("bufferPercentage==" + bufferPercentage + ",totalBuffer==" + totalBuffer + ",secondaryProgress==" + secondaryProgress);
                        seekbarVideo.setSecondaryProgress(secondaryProgress);
                    } else {
                        //本地的资源
                        seekbarVideo.setSecondaryProgress(0);
                    }

                    //判断卡的标准currentPosition==1000  2000; 3000,3400;
                    //preCurrrentPosition=0,1000;2000,3000
                    int bufferTime = currentPosition - preCurrrentPosition;//1000,1000,400

                    //preCurrrentPosition==1000; 2000;3000
                    preCurrrentPosition = position;

                    if (!isActivityDestroy) {
                        handler.removeMessages(PROGRESS);
                        handler.sendEmptyMessageDelayed(PROGRESS, 1000);
                    }


                    break;
            }
        }
    };

    /**
     * 得到当前系统时间
     *
     * @return
     */
    private String getSystemTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_videoplayer);
        Vitamio.isInitialized(this);//加载加密器
        initData();
        findViews();


        getData();

        setListener();
        setData();
    }

    private void startAndPause() {
        // Handle clicks for btnVideoPlayPause
        if (videoview.isPlaying()) {
            //暂停
            videoview.pause();
            //设置按钮的状态-播放
            btnVideoPlayPause.setBackgroundResource(R.drawable.btn_video_play_selector);
        } else {
            //播放
            videoview.start();
            //设置按钮状态-暂停
            btnVideoPlayPause.setBackgroundResource(R.drawable.btn_video_pause_selector);
        }
    }

    /**
     *
     */
    private void getData() {
        uri = getIntent().getData();//视频的播放地址,得到可以是文件夹的，或者QQ空间


        String uriStr = getIntent().getStringExtra("uri");
        if (uriStr != null) {
            uri = Uri.parse(uriStr);
        }


        //获取视频列表
        urlList = getIntent().getStringArrayListExtra("videolist");
        moveNames = getIntent().getStringArrayListExtra("moveNames");
        heighturl = getIntent().getStringArrayListExtra("heighturl");
        position = getIntent().getIntExtra("position", 0);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //当执行当前Activity后保持不锁屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//得到音量
        am = (AudioManager) getSystemService(AUDIO_SERVICE);

        maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currentVolume = am.getStreamVolume(AudioManager.STREAM_MUSIC);
//得到屏幕的宽和高
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        utils = new Utils();

        //实例化手势识别器
        detector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {


            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }


            @Override
            public boolean onDoubleTap(MotionEvent e) {
                switchScreen();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (isMediaController) {
                    showMediaController();
                    handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);
                } else {
                    hideMediaController();
                    handler.removeMessages(HIDE_MEDIACONTROLLER);
                }
                return super.onSingleTapConfirmed(e);
            }
        });
    }

    private void setVideoType(int screenDefault) {
        switch (screenDefault) {
            case SCREEN_FULL://全屏播放

                videoview.setVideoSize(screenWidth, screenHeight);
                isScreenFull = true;

                //设置按钮为默认
                btnVideoSwitchScreen.setBackgroundResource(R.drawable.btn_video_switch_defaultscreen_selector);

                break;

            case SCREEN_DEFAULT://默认

                //视频的真正的高和宽
                int mVideoWidth = videoWidth;
                int mVideoHeight = videoHeight;

                //屏幕的高和宽
                int width = screenWidth;
                int height = screenHeight;


                if (mVideoWidth > 0 && mVideoHeight > 0) {
                    // for compatibility, we adjust size based on aspect ratio
                    if (mVideoWidth * height < width * mVideoHeight) {
                        //Log.i("@@@", "image too wide, correcting");
                        width = height * mVideoWidth / mVideoHeight;
                    } else if (mVideoWidth * height > width * mVideoHeight) {
                        //Log.i("@@@", "image too tall, correcting");
                        height = width * mVideoHeight / mVideoWidth;
                    }
                }

                videoview.setVideoSize(width, height);
                isScreenFull = false;

                //设置按钮为默认
                btnVideoSwitchScreen.setBackgroundResource(R.drawable.btn_video_switch_fullscreen_selector);
                break;
        }
    }

    private void setListener() {
        //设置准备监听
        videoview.setOnPreparedListener(new io.vov.vitamio.MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(io.vov.vitamio.MediaPlayer mp) {

                //得到视频的宽和高
                videoWidth = mp.getVideoWidth();
                videoHeight = mp.getVideoHeight();


                videoview.start();//开始播放视频

                //1.SeekBar更新的第一步
                int duration = (int) videoview.getDuration();//得到视频的总时长
                //视频的总长度和SeekBar.setMax();
//                videoview.seekTo(30000);//定位到之前播放的位置
                seekbarVideo.setMax(duration);

                tvDuration.setText(utils.stringForTime(duration));

                //发一个消息
                handler.sendEmptyMessage(PROGRESS);

                //隐藏控制面板
                hideMediaController();

//            ll_videoloading.setVisibility(View.GONE);


                setVideoType(SCREEN_DEFAULT);

            }
        });
        //设置视频播放完成的监听
        videoview.setOnCompletionListener(new MyOnCompletionListener());


        //监听SeekBar拖动的状态
        seekbarVideo.setOnSeekBarChangeListener(new MyOnSeekBarChangeListener());

        //设置拖动音量的监听
        seekbarVoice.setOnSeekBarChangeListener(new VolumeSeekBarChangeListener());

        //设置监听卡有两种方式：第一种是系统自带的：设置视频监听卡
        //人为指定监听卡
        //设置监听卡有两种方式：第一种是系统自带的：设置视频监听卡
        //人为指定监听卡
        //设置监听错误
        videoview.setOnErrorListener(new MyOnErrorListener());

//        videoview.setOnInfoListener(new MyOnInfoListener());//android2.3才开始有MediaPlayer; videoview.setOnInfoListener()在4.2.2才有
    }

    /**
     * 点击listView中某个item的监听
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView item = (TextView) listView.getChildAt(position);
        SpUtils.getInitialize(SystemVideoPlayerActivity.this).save("position", position);
        adapter.notifyDataSetChanged();

        tv_theme.setText(item.getText());
        if ("标清".equals(tv_theme.getText())) {

            videoview.setVideoPath(urlList.get(SystemVideoPlayerActivity.this.position));
        } else if ("高清".equals(tv_theme.getText())) {
            videoview.setVideoPath(heighturl.get(SystemVideoPlayerActivity.this.position));
        }
        popupWindow.dismiss();
        videoview.start();
    }

    class MyOnErrorListener implements io.vov.vitamio.MediaPlayer.OnErrorListener {


        @Override
        public boolean onError(io.vov.vitamio.MediaPlayer mp, int what, int extra) {

            //什么情况下会出错
            //1.播放的格式不支持-跳转到万能播放器继续播放
//            Toast.makeText(VitamioVideoPlayerActivity.this,"播放视频出错了="+extra,Toast.LENGTH_SHORT).show();
            MessageUtils.showMessage(SystemVideoPlayerActivity.this, "播放视频出错了");
            //2.播放视频的过程中网络中断--播放重试
            //3.播放的文件中间缺省--把下载模块解决好，不要用空白
            return true;
        }
    }

    class VolumeSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                updateVolumeProgress(progress);
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            handler.removeMessages(HIDE_MEDIACONTROLLER);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);

        }
    }

    /**
     * 根据传入的值，设置音量
     *
     * @param volume
     */
    private void updateVolume(int volume) {
        //是否静音
        if (isMute) {
            am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
            seekbarVoice.setProgress(0);
        } else {
            am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
            seekbarVoice.setProgress(volume);
        }


        currentVolume = volume;
    }

    private void updateVolumeProgress(int volume) {
        if (currentVolume > 0) {
            isMute = false;
        }

        am.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        seekbarVoice.setProgress(volume);

        currentVolume = volume;
    }

    class MyOnCompletionListener implements io.vov.vitamio.MediaPlayer.OnCompletionListener {


        @Override
        public void onCompletion(io.vov.vitamio.MediaPlayer mp) {
            //播放下一个视频
            playNextVideo();
        }
    }

    /**
     * 播放下一个视频
     */
    private void playNextVideo() {

        if (urlList != null && urlList.size() > 0) {//播放列表

            position++;
            if (position < urlList.size()) {

                String mediaItem = urlList.get(position);
                tvName.setText(moveNames.get(position));
                videoview.setVideoPath(mediaItem);//开始播放
                //设置按钮状态-暂停
                btnVideoPlayPause.setBackgroundResource(R.drawable.btn_video_pause_selector);
                isNetUri = utils.isNetUri(mediaItem);
                /**
                 *
                 */
//                ll_videoloading.setVisibility(View.VISIBLE);

                if (position == urlList.size() - 1) {
                    MessageUtils.showMessage(SystemVideoPlayerActivity.this, "最后一个视频了");
                }

                setButtonStatu();

            } else {
                position = urlList.size() - 1;
                finish();
            }

        } else if (uri != null) {
            //退出播放
            finish();
        }

    }

    /**
     * 播放上一个视频
     */
    private void playPreVideo() {

        if (urlList != null && urlList.size() > 0) {//播放列表

            position--;
            if (position >= 0) {

                String mediaItem = urlList.get(position);
                tvName.setText(moveNames.get(position));
                videoview.setVideoPath(mediaItem);//开始播放
                isNetUri = utils.isNetUri(mediaItem);
                /**
                 *
                 */
//                ll_videoloading.setVisibility(View.VISIBLE);

                if (position == 0) {
                    MessageUtils.showMessage(this, "第一个视频了");
                }

                setButtonStatu();

            } else {
                position = 0;
            }

        } else if (uri != null) {
            //退出播放
            finish();
        }

    }

    private void findViews() {
        videoview = new VideoView(this);
        ll_theme = (LinearLayout) findViewById(R.id.ll_theme);
        llTop = (LinearLayout) findViewById(R.id.ll_top);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvSystemTime = (TextView) findViewById(R.id.tv_system_time);
        btnVoice = (Button) findViewById(R.id.btn_voice);
        seekbarVoice = (SeekBar) findViewById(R.id.seekbar_voice);
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        tvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        seekbarVideo = (SeekBar) findViewById(R.id.seekbar_video);
        tvDuration = (TextView) findViewById(R.id.tv_duration);
        btnVideoExit = (Button) findViewById(R.id.btn_video_exit);
        btnVideoPre = (Button) findViewById(R.id.btn_video_pre);
        btnVideoPlayPause = (Button) findViewById(R.id.btn_video_play_pause);
        btnVideoNext = (Button) findViewById(R.id.btn_video_next);
        btnVideoSwitchScreen = (Button) findViewById(R.id.btn_video_switch_screen);
//        iv_theme = (ImageView) findViewById(R.id.iv_theme);
        videoview = (VideoView) findViewById(R.id.videoview);
        tv_theme = (TextView) findViewById(R.id.tv_theme);
        btnVoice.setOnClickListener(this);
        btnVideoExit.setOnClickListener(this);
        btnVideoPre.setOnClickListener(this);
        btnVideoPlayPause.setOnClickListener(this);
        btnVideoNext.setOnClickListener(this);
        btnVideoSwitchScreen.setOnClickListener(this);
        ll_theme.setOnClickListener(this);


        //设置SeekBar.Max()
        seekbarVoice.setMax(maxVolume);
        seekbarVoice.setProgress(currentVolume);//设置默认值
    }

    /**
     * 点击事件的回调方法
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == btnVoice) {
            // Handle clicks for btnVoice
            isMute = !isMute;
            updateVolume(currentVolume);
        } else if (v == btnVideoExit) {
            // Handle clicks for btnVideoExit
            finish();
        } else if (v == btnVideoPre) {
            // Handle clicks for btnVideoPre
            playPreVideo();
        } else if (v == btnVideoPlayPause) {
            startAndPause();

        } else if (v == btnVideoNext) {
            playNextVideo();
        } else if (v == btnVideoSwitchScreen) {
            switchScreen();
        } else if (v == ll_theme) {

            //显示下拉列表
            if (popupWindow == null) {
                popupWindow = new PopupWindow(SystemVideoPlayerActivity.this);
                initListView();
                popupWindow.setContentView(listView);
                popupWindow.setWidth(DensityUtils.dip2px(this, 80));
                //在代码中尺寸的单位是px
                popupWindow.setHeight(DensityUtils.dip2px(this, 50));//将dp-->px
                // 设置pw可以获得焦点, 这样才能响应Item点击事件和点击外部自动dismiss
                popupWindow.setFocusable(true);
            }

            popupWindow.showAsDropDown(tv_theme);
            isClick = !isClick;
            tv_theme.setEnabled(isClick);


        }

        handler.removeMessages(HIDE_MEDIACONTROLLER);
        handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);
    }

    /**
     * 初始化listview
     */
    private void initListView() {
        listView = new ListView(SystemVideoPlayerActivity.this);
        listView.setBackgroundResource(R.drawable.flashsale);

        listView.setDividerHeight(DensityUtil.dip2px(1));
        //初始化数据
        popList.add("标清");
        popList.add("高清");
        adapter = new PopAdapter();
        listView.setAdapter(adapter);
        //默认选中标清

        //设置点击某个item的监听
        listView.setOnItemClickListener(this);
    }

    class PopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 2;
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

            TextView textView = new TextView(SystemVideoPlayerActivity.this);
            textView.setTextColor(Color.WHITE);//默认是白色
            textView.setText(popList.get(position));
            int p = SpUtils.getInitialize(SystemVideoPlayerActivity.this).getValue("position", 0);
            if (p == position) {
                textView.setTextColor(Color.BLUE);
            } else {
                textView.setTextColor(Color.WHITE);
            }


            return textView;
        }
    }

    /**
     * 更新上一个和下一个按钮的状态
     */
    private void setButtonStatu() {

        if (urlList != null && urlList.size() > 0) {
            if (position == 0) {
                btnVideoPre.setEnabled(false);
                btnVideoPre.setBackgroundResource(R.drawable.btn_pre_gray);
            } else if (position == urlList.size() - 1) {
                btnVideoNext.setEnabled(false);
                btnVideoNext.setBackgroundResource(R.drawable.btn_next_gray);
            } else {
                btnVideoPre.setEnabled(true);
                btnVideoPre.setBackgroundResource(R.drawable.btn_video_pre_selector);
                btnVideoNext.setEnabled(true);
                btnVideoNext.setBackgroundResource(R.drawable.btn_video_next_selector);
            }
        } else if (uri != null) {
            btnVideoPre.setEnabled(false);
            btnVideoPre.setBackgroundResource(R.drawable.btn_pre_gray);
            btnVideoNext.setEnabled(false);
            btnVideoNext.setBackgroundResource(R.drawable.btn_next_gray);
        }

    }

    private void switchScreen() {
        if (isScreenFull) {
            //默认的屏幕播放
            setVideoType(SCREEN_DEFAULT);
        } else {
            //设置全屏播放
            setVideoType(SCREEN_FULL);
        }
    }

    private void setData() {

        if (urlList != null && urlList.size() > 0) {

            String mediaItem = urlList.get(position);
            tvName.setText(moveNames.get(position));//设置标题

            videoview.setVideoPath(mediaItem);

            isNetUri = utils.isNetUri(mediaItem);

        } else if (uri != null) {
            //设置播放地址
            videoview.setVideoURI(uri);
            isNetUri = utils.isNetUri(uri.toString());
            tvName.setText(uri.toString());//设置标题
        }

        setButtonStatu();
        //发一个消息
        handler.sendEmptyMessage(SHOW_SPEED);

    }

    class MyOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        /**
         * 当Seekbar的进度发生变化的时候回调这个方法
         *
         * @param seekBar
         * @param progress 当前的进度，视频的进度
         * @param fromUser 如果是认为改变的就是true,否则false
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {//一定不能少
                videoview.seekTo(progress);
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            handler.removeMessages(HIDE_MEDIACONTROLLER);
        }

        /**
         * 当手指离开的时候回调这个方法
         *
         * @param seekBar
         */
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(PROGRESS);
        isActivityDestroy = true;

        super.onDestroy();//调用父类的onDestroy
    }

    private float startY;//起始坐标
    private int touchRang;//屏幕的高
    private int mVol;//原理的音量

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);//把事件让手势识别器解析
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://手指按下屏幕
                //1.在手指按下屏幕时候，记录三个值
                startY = event.getY();
                touchRang = Math.min(screenHeight, screenWidth);//screenHeight
                mVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                handler.removeMessages(HIDE_MEDIACONTROLLER);
                break;

            case MotionEvent.ACTION_MOVE://手指在屏幕上移动
                //2.手指在屏幕上滑动的时候   记录偏移量
                float endY = event.getY();
                float distanceY = startY - endY;
                //改变的声音 = （屏幕滑动的距离/总距离）*总音量
                float changVolume = (distanceY / touchRang) * maxVolume;

                //真正改变的声音 = 原来的音量+改变的声音
                int volume = (int) Math.min(Math.max(mVol + changVolume, 0), maxVolume);

                updateVolumeProgress(volume);

//                startY = event.getY();//不能加

                break;

            case MotionEvent.ACTION_UP://手指离开屏幕
                handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);
                break;

        }
        return super.onTouchEvent(event);
    }


    /**
     * 隐藏控制面板
     */

    private void hideMediaController() {

        llTop.setVisibility(View.GONE);
        llBottom.setVisibility(View.GONE);
        isMediaController = true;

    }


    /**
     * 显示控制面板
     */

    private void showMediaController() {

        llTop.setVisibility(View.VISIBLE);
        llBottom.setVisibility(View.VISIBLE);
        isMediaController = false;
    }

    /**
     * 监听物理健改变声音
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            currentVolume--;
            updateVolumeProgress(currentVolume);
            handler.removeMessages(HIDE_MEDIACONTROLLER);
            handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            currentVolume++;
            updateVolumeProgress(currentVolume);
            handler.removeMessages(HIDE_MEDIACONTROLLER);
            handler.sendEmptyMessageDelayed(HIDE_MEDIACONTROLLER, 3000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

}
