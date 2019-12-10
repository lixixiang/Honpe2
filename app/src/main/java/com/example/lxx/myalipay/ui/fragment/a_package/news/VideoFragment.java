package com.example.lxx.myalipay.ui.fragment.a_package.news;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.widget.video.SampleCoverVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import butterknife.BindView;

import static com.example.lxx.myalipay.api.FinalClass.video_status;
import static com.example.lxx.myalipay.utils.Utils.getFontSize;
import static com.lcodecore.tkrefreshlayout.utils.DensityUtil.px2dp;


/**
 * created by lxx at 2019/11/27 10:31
 * 描述:Honp视频
 */
public class VideoFragment extends BaseFragment {
    public static final String TAG = VideoFragment.class.getName();

    @BindView(R.id.video_player)
    SampleCoverVideo gsyPlay;
    @BindView(R.id.activity_play)
    LinearLayout llPlay;

    boolean onClick = false;
    String source1 = "http://www.honpe.com:8001/201911/2019112603125462713.mp4";
    private OrientationEventListener orientationEventListener;
    private int screenType = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    private int mIsLand;

    private boolean mClick = false;
    private boolean mClickLand = false;
    private boolean mClickPort;
    private boolean mEnable = true;
    //是否跟随系统
    private boolean mRotateWithSystem = true;
    private float elseHeight;

    public static VideoFragment newInstance(float elseHeight) {
        VideoFragment fragment = new VideoFragment();
        fragment.elseHeight = elseHeight;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video;
    }


    @Override
    protected void initView() {
        LatteLogger.d("elseHeight",elseHeight);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) elseHeight);

        gsyPlay.setLayoutParams(lp);

        playVideo();
    }

    private void playVideo() {
        gsyPlay.setUp(source1, true, "Honp视频");
        ImageView imageView = new ImageView(_mActivity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //增加封面
        imageView.setImageResource(R.mipmap.xxx);
        gsyPlay.setThumbImageView(imageView);
        gsyPlay.getTitleTextView().setVisibility(View.VISIBLE);
        gsyPlay.getBackButton().setVisibility(View.GONE);
        gsyPlay.getTitleTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, getFontSize(40));

        //设置全屏按键功能，这是使用的是选择屏幕，而不是全屏

        gsyPlay.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatteLogger.d("getFullscreenButton", "全屏");
                gsyPlay.startWindowFullscreen(getContext(), false, true);
            }
        });

        //防止错位设置
        gsyPlay.setPlayTag(TAG);
        //是否可以滑动调整
        gsyPlay.setIsTouchWiget(true);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        gsyPlay.setAutoFullWithSize(true);
        //音频焦点冲突时是否释放
        gsyPlay.setReleaseWhenLossAudio(false);
        //全屏动画
        gsyPlay.setShowFullAnimation(true);
        //小屏时不触摸滑动
        gsyPlay.setIsTouchWiget(false);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case video_status:
                int status = (int) event.getData();
                LatteLogger.d("video_status", status);
                switch (status) {
                    case 0:
                        GSYVideoManager.onPause();
                        break;
                    case 1:
                        GSYVideoManager.onPause();
                        break;
                    case 2:
                        GSYVideoManager.onResume();
                        break;
                }
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (GSYVideoManager.backFromWindowFull(_mActivity)) {
            return false;
        }
        return super.onBackPressedSupport();
    }


}


















