package com.ftxad.ftxsdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.ftxad.ftxsdk.R;
import com.ftxad.ftxsdk.bean.response.RspAdvert;
import com.ftxad.ftxsdk.interf.FtxCallback;
import com.ftxad.ftxsdk.net.API;
import com.ftxad.ftxsdk.utils.ScreenUtil;

import java.io.IOException;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * Created by hanhuailong on 2018/6/26.
 */

public class FtxViewX extends FrameLayout {
    private LinearLayout ftx_title_up_ll,ftx_title_down_ll;
    private RelativeLayout ftx_content_rl,ftx_bottom_ll,ftx_content_image_rl;
    private TextView ftx_title_up_tv,ftx_title_down_tv,ftx_bottom_name_tv,ftx_bottom_download_tv,ftx_bottom_advert_tv,ftx_content_time_tv,ftx_content_transparent_tv;
    private ImageView ftx_content_iv;
    private SurfaceView ftx_content_sv;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    private ProgressBar ftx_content_pb;
    private Context mContext;
    private int TIME = 1000;  //每隔1s执行一次.

    Handler handler = new Handler(Looper.getMainLooper());
    private String totalTime;
    private int video=0;//0是初始状态，1是正在播放中，2是播放完毕
    private boolean isFirstClick=true;// 是否是第一次点击
    private boolean isFirstPlay=true;// 是否是第一次播放视频
    private int getAdavertTimes=1;

    private RspAdvert.DataBean myAdvert;
    private FtxCompleteListener mFtxCompleteListener;
    public FtxViewX(Context context) {
        super(context);
        initView(context);
    }

    public FtxViewX(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @SuppressLint("NewApi")
    private void initView(Context context) {
        mContext=context;
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_perform_x, this);

        ftx_title_up_ll=view.findViewById(R.id.ftx_title_up_ll);
        ftx_title_down_ll=view.findViewById(R.id.ftx_title_down_ll);
        ftx_bottom_ll=view.findViewById(R.id.ftx_bottom_ll);
        ftx_title_up_tv=view.findViewById(R.id.ftx_title_up_tv);
        ftx_title_down_tv=view.findViewById(R.id.ftx_title_down_tv);
        ftx_bottom_name_tv=view.findViewById(R.id.ftx_bottom_name_tv);
        ftx_bottom_download_tv=view.findViewById(R.id.ftx_bottom_download_tv);
        ftx_bottom_advert_tv=view.findViewById(R.id.ftx_bottom_advert_tv);
        ftx_content_iv=view.findViewById(R.id.ftx_content_iv);
        ftx_content_sv=view.findViewById(R.id.ftx_content_sv);
        ftx_content_time_tv=view.findViewById(R.id.ftx_content_time_tv);
        ftx_content_rl=view.findViewById(R.id.ftx_content_rl);
        ftx_content_pb=view.findViewById(R.id.ftx_content_pb);
        ftx_content_transparent_tv=view.findViewById(R.id.ftx_content_transparent_tv);
        ftx_content_image_rl=view.findViewById(R.id.ftx_content_image_rl);

//        Uri rawUri=Uri.parse("android.resource://" + getPackageName(view.getContext()) + "/" + R.raw.shuai_dan_ge);
        prepareSurface();
        addListener();
    }

    private void getAdvertInfo() {
        if (getAdavertTimes<=3){
            getAdavertTimes++;
            //显示 Loading 图
            ftx_content_pb.setVisibility(View.VISIBLE);
            API.getInstance(mContext).fantasyWfjs("2", new FtxCallback<RspAdvert.DataBean>() {
                @Override
                public void ftxCallback(RspAdvert.DataBean dataBean) {
                    //隐藏 Loading 图
                    ftx_content_pb.setVisibility(View.GONE);
                    if (dataBean!=null){
                        Log.e("ftxCallback","ftxCallback");
                        myAdvert=dataBean;
                        List<RspAdvert.DataBean.RelsMapBean.VideoPathBean> list=myAdvert.getRelsMap().getVideoPath();
                        List<RspAdvert.DataBean.RelsMapBean.IntroduceStrBean> listIntroduce=myAdvert.getRelsMap().getIntroduceStr();
                        List<RspAdvert.DataBean.RelsMapBean.CoverStrBean> listCoverStr=myAdvert.getRelsMap().getCoverStr();

                        ftx_bottom_name_tv.setText(myAdvert.getAdStuff().getPName());
                        String title="";
                        if (listIntroduce!=null){
                            for (RspAdvert.DataBean.RelsMapBean.IntroduceStrBean bean:listIntroduce){
                                title+=bean.getInfo();
                            }
                        }
                        ftx_title_down_tv.setText(title);
                        String url="";
                        if (list!=null){
                            url =list.get(0).getInfo();
                            Uri rawUri=Uri.parse(url);
                            startPlayVideo(rawUri);
//                        ftx_content_vv.setVideoURI(rawUri);
                        }
                        String imageUrl="";
                        if (listCoverStr!=null){
                            imageUrl=listCoverStr.get(0).getInfo();
                            Glide.with(mContext)
                                    .load(imageUrl)
                                    .error(R.drawable.img_default)
                                    .into(ftx_content_iv);
                        }
                    }else {
                        Log.e("ftxCallback","null");
                        getAdvertInfo();
                    }
                }

                @Override
                public void ftxFailed(String msg) {
                    //隐藏 Loading 图
                    ftx_content_pb.setVisibility(View.GONE);
                }
            });
        }
    }


    private void prepareSurface() {
        //初始化surfaceview
        // 设置surfaceHolder
        surfaceHolder = ftx_content_sv.getHolder();
        // 设置Holder类型,该类型表示surfaceView自己不管理缓存区,虽然提示过时，但最好还是要设置
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 设置surface回调
        surfaceHolder.addCallback(new SurfaceCallback());
    }

    private void addListener() {
        ftx_content_transparent_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstClick){
                    isFirstClick=false;
                }else {
                    jumpToClick();
                }
            }
        });
        ftx_title_up_ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToClick();
            }
        });
        ftx_bottom_ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToClick();
            }
        });

    }

    /**
     * 跳转网页并调用点击广告接口
     */
    private void jumpToClick() {
        if (myAdvert!=null){
            API.getInstance(mContext).addJsCNum(myAdvert);
            openUrl();
        }
    }
    private void openUrl(){
        String url=myAdvert.getAdStuff().getUrl();
        if (!TextUtils.isEmpty(url)){
            Uri uri=Uri.parse(url);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            mContext.startActivity(intent);
        }

    }
    /*
     根据位置转换成时间
     */
    private String stringForTime(int timeMs){
        //将长度转换为时间
        StringBuilder mFormatBuilder;
        Formatter mFormatter;
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

        int totalSeconds = timeMs / 1000;

        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (video==1){
                    int currentPos=mediaPlayer.getCurrentPosition();
                    int pos=mediaPlayer.getDuration()-currentPos;
                    String time=stringForTime(pos);//当前播放时间
                    ftx_content_time_tv.setText(time);

                    if (isFirstPlay){
                        if (stringForTime(currentPos).equals("00:01")){//播放1秒后
                            API.getInstance(mContext).addJsENum(myAdvert);
                        }
                    }
                    handler.postDelayed(this, TIME);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 播放视频
     */
    public void startPlayVideo(Uri uri) {
        // 初始化MediaPlayer
        mediaPlayer = new MediaPlayer();
        // 重置mediaPaly,建议在初始滑mediaplay立即调用。
        mediaPlayer.reset();
        // 设置声音效果
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置播放完成监听
        mediaPlayer.setOnCompletionListener(onCompletionListener);
        // 设置媒体加载完成以后回调函数。
        mediaPlayer.setOnPreparedListener(onPreparedListener);
        // 错误监听回调函数
        mediaPlayer.setOnErrorListener(onErrorListener);
        // 设置缓存变化监听
        mediaPlayer.setOnBufferingUpdateListener(onBufferingUpdateListener);
        try {
            // mediaPlayer.reset();
            mediaPlayer.setDataSource(mContext, uri);
            // 设置异步加载视频，包括两种方式 prepare()同步，prepareAsync()异步
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        mediaPlayer.start();
    }
    public void pause(){
        mediaPlayer.pause();
    }
    public void release(){
        // surfaceView销毁
        // 如果MediaPlayer没被销毁，则销毁mediaPlayer
        if (null != mediaPlayer) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    public void setOnFtxCompleteListener(FtxCompleteListener listener){
        this.mFtxCompleteListener=listener;
    }

    private void startVideo() {
        mediaPlayer.start();
        video=1;
        handler.postDelayed(runnable,TIME);
    }
    // SurfaceView的callBack
    public class SurfaceCallback implements SurfaceHolder.Callback {
        public SurfaceCallback(){
        }
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // SurfaceView的大小改变

        }

        public void surfaceCreated(SurfaceHolder holder) {
            // surfaceView被创建
            // 设置播放资源
            Log.e("surfaceCreated",video+"");
//            if (video==1){
//                mediaPlayer.start();
//            }else {
//
//            }
            getAdavertTimes=1;
            getAdvertInfo();
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            Log.e("surfaceDestroyed",video+"");
        }
    }
    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(final MediaPlayer mediaPlayer) {
            Log.e("OnCompletionListener","OnCompletionListener");
            video=2;
            ftx_content_image_rl.setVisibility(View.VISIBLE);
            ftx_content_image_rl.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("onClick","ftx_content_image_rl.setOnClickListener");
                    ftx_content_image_rl.setVisibility(View.GONE);
                    // 播放视频
                    startVideo();
                }
            });
//            ftx_content_iv.setVisibility(View.VISIBLE);
            if (mFtxCompleteListener!=null)
                mFtxCompleteListener.OnFtxCompleteListener();
        }
    };
    private MediaPlayer.OnPreparedListener onPreparedListener=new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            //隐藏默认头像
            ftx_content_image_rl.setVisibility(View.GONE);
//            ftx_content_iv.setVisibility(View.GONE);
            // 设置显示到屏幕
            mediaPlayer.setDisplay(surfaceHolder);
            // 设置surfaceView保持在屏幕上
            mediaPlayer.setScreenOnWhilePlaying(true);
            surfaceHolder.setKeepScreenOn(true);
            // 设置播放时间
            totalTime = stringForTime(mediaPlayer.getDuration());
            ftx_content_time_tv.setText(totalTime);

            // 播放视频
            startVideo();

        }
    };


    private MediaPlayer.OnErrorListener onErrorListener=new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            return false;
        }
    };
    private MediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener=new MediaPlayer.OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

        }
    };
    public interface FtxCompleteListener{
        void OnFtxCompleteListener();
    }
}
