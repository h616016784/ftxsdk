package com.ftxad.ftxsdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.ftxad.ftxsdk.R;
import com.ftxad.ftxsdk.bean.response.RspAdvert;
import com.ftxad.ftxsdk.interf.FtxCallback;
import com.ftxad.ftxsdk.net.API;
import com.ftxad.ftxsdk.utils.ScreenUtil;

import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/**
 * Created by hanhuailong on 2018/6/26.
 */

public class FtxView extends FrameLayout {
    private LinearLayout ftx_title_up_ll,ftx_title_down_ll;
    private RelativeLayout ftx_content_rl,ftx_bottom_ll;
    private TextView ftx_title_up_tv,ftx_title_down_tv,ftx_bottom_name_tv,ftx_bottom_download_tv,ftx_bottom_advert_tv,ftx_content_time_tv,ftx_content_transparent_tv;
    private ImageView ftx_content_iv;
    private VideoView ftx_content_vv;
    private ProgressBar ftx_content_pb;
    private Context mContext;
    private int TIME = 1000;  //每隔1s执行一次.

    Handler handler = new Handler(Looper.getMainLooper());
    private String totalTime;
    private int video=0;//0是初始状态，1是正在播放中，2是播放完毕
    private boolean isFirstClick=true;// 是否是第一次点击
    private boolean isFirstPlay=true;// 是否是第一次播放视频

    private RspAdvert.DataBean myAdvert;
    public FtxView(Context context) {
        super(context);
        initView(context);
    }

    public FtxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        TypedArray td=context.obtainStyledAttributes(attrs,R.styleable.View);
//        float width=td.getDimension(R.styleable.ConstraintSet_android_layout_width,300);
//        float height=td.getDimension(R.styleable.ConstraintSet_android_layout_height,200);
        initView(context);
    }

    @SuppressLint("NewApi")
    private void initView(Context context) {
        mContext=context;
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_perform, this);

        ftx_title_up_ll=view.findViewById(R.id.ftx_title_up_ll);
        ftx_title_down_ll=view.findViewById(R.id.ftx_title_down_ll);
        ftx_bottom_ll=view.findViewById(R.id.ftx_bottom_ll);
        ftx_title_up_tv=view.findViewById(R.id.ftx_title_up_tv);
        ftx_title_down_tv=view.findViewById(R.id.ftx_title_down_tv);
        ftx_bottom_name_tv=view.findViewById(R.id.ftx_bottom_name_tv);
        ftx_bottom_download_tv=view.findViewById(R.id.ftx_bottom_download_tv);
        ftx_bottom_advert_tv=view.findViewById(R.id.ftx_bottom_advert_tv);
        ftx_content_iv=view.findViewById(R.id.ftx_content_iv);
        ftx_content_vv=view.findViewById(R.id.ftx_content_vv);
        ftx_content_time_tv=view.findViewById(R.id.ftx_content_time_tv);
        ftx_content_rl=view.findViewById(R.id.ftx_content_rl);
        ftx_content_pb=view.findViewById(R.id.ftx_content_pb);
        ftx_content_transparent_tv=view.findViewById(R.id.ftx_content_transparent_tv);

//        Uri rawUri=Uri.parse("android.resource://" + getPackageName(view.getContext()) + "/" + R.raw.shuai_dan_ge);

        ftx_content_vv.setMediaController(new MediaController(view.getContext()));
//        ftx_content_vv.start();
        ftx_content_vv.setOnErrorListener(myErrorListener);
        ftx_content_vv.setOnPreparedListener(mPreparedListener);
        ftx_content_vv.setOnCompletionListener(onCompletionListener);
        ftx_content_vv.setOnInfoListener(onInfoListener);
        ftx_content_vv.requestFocus();
        addListener();
    }

    private void getAdvertInfo() {
        //显示 Loading 图
        ftx_content_pb.setVisibility(View.VISIBLE);
        API.getInstance(mContext).fantasyWfjs("2", new FtxCallback<RspAdvert.DataBean>() {
            @Override
            public void ftxCallback(RspAdvert.DataBean dataBean) {
                //隐藏 Loading 图
                ftx_content_pb.setVisibility(View.GONE);
                if (dataBean!=null){
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
                        ftx_content_vv.setVideoURI(rawUri);
                    }
                    String imageUrl="";
                    if (listCoverStr!=null){
                        imageUrl=listCoverStr.get(0).getInfo();
                        Glide.with(mContext)
                                .load(imageUrl)
                                .error(R.drawable.img_default)
                                .into(ftx_content_iv);
                    }
                }
            }

            @Override
            public void ftxFailed(String msg) {
                //隐藏 Loading 图
                ftx_content_pb.setVisibility(View.GONE);
            }
        });
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
            openUrl();
            API.getInstance(mContext).addJsCNum(myAdvert);
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

    /**
     * 开始获取广告
     */
    public void startAdvert(){
        getAdvertInfo();
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
    private void startVideo(MediaPlayer mp){
        mp.start();
        video=1;
        handler.postDelayed(runnable,TIME);
    }

    public void onPause(){
        ftx_content_vv.pause();
    }
    public void onContinue(){
        ftx_content_vv.start();
        video=1;
        handler.postDelayed(runnable,TIME);
    }
    public boolean isRunning(){
        return ftx_content_vv.isPlaying();
    }
    //经常会碰到视频编码格式不支持的情况,这里还是处理一下,若不想弹出提示框就返回true;
    private MediaPlayer.OnErrorListener myErrorListener=new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return true;
        }
    };
    private MediaPlayer.OnPreparedListener mPreparedListener=new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            ftx_content_iv.setVisibility(View.GONE);
            ftx_content_pb.setVisibility(View.GONE);

            int width=mp.getVideoWidth();
//            int height=mp.getVideoHeight();

            int androidWidth= ScreenUtil.getScreenWidth(mContext);

            ViewGroup.LayoutParams layoutParams=ftx_content_rl.getLayoutParams();
            if (width>androidWidth){
                layoutParams.width=androidWidth;
            }else {
                layoutParams.width=width+8;
            }
//            layoutParams.height=height;
            ftx_content_rl.setLayoutParams(layoutParams);

            totalTime=stringForTime(mp.getDuration());
            ftx_content_time_tv.setText(totalTime);
            startVideo(mp);
        }
    };
    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(final MediaPlayer mp) {
            video=2;
            isFirstPlay=false;
            ftx_content_iv.setVisibility(View.VISIBLE);
            ftx_content_pb.setVisibility(View.GONE);

            ftx_content_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ftx_content_iv.setVisibility(View.GONE);
                    ftx_content_time_tv.setText(totalTime);
                    startVideo(mp);
                }
            });
//            mp.stop();
        }
    };
    private MediaPlayer.OnInfoListener onInfoListener=new MediaPlayer.OnInfoListener() {
        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            switch (what) {
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    //显示 Loading 图
                    ftx_content_pb.setVisibility(View.VISIBLE);
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    //隐藏 Loading 图
                    ftx_content_pb.setVisibility(View.GONE);
                    break;
            }
            return false;
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (video==1){
                    int currentPos=ftx_content_vv.getCurrentPosition();
                    int pos=ftx_content_vv.getDuration()-currentPos;
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
}
