package com.example.lxx.myalipay.ui.welcome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.lxx.myalipay.MainActivity;
import com.example.lxx.myalipay.MainFragment;
import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.ui.fragment.a_package.news.DetailContentFragment;

import static com.example.lxx.myalipay.base.BaseMainFragment.TOUCH_TIME;
import static com.example.lxx.myalipay.base.BaseMainFragment.WAIT_TIME;


public class WelcomeActivity extends AppCompatActivity {
    public static final int SKIN_GUIDE = 0x001;
    public static final int SKINP_MAIN = 0x002;
    private SharedPreferences sharedPreferences;

    //该线程用于延迟跳转activity
    private Thread mThread;

    //判断是否第一次打开该应用
    private boolean flag;

    //做一个图片动画缩放的效果
    private ImageView iv;

    //此handler用于处理界面的变换（跳转activity）
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SKIN_GUIDE:
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                    finish();
                    break;
                case SKINP_MAIN:
                    long nowTime = System.currentTimeMillis();
                    if (nowTime - TOUCH_TIME > WAIT_TIME) {
                        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                        TOUCH_TIME = nowTime;
                        finish();
                    } else {
                        //  ToastUtils.getInstance().showToast("不要重复点击，手机会爆的呢");
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
    }

    public void initView() {
        iv = (ImageView) findViewById(R.id.log);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(1000);
        iv.startAnimation(alphaAnimation);

        /**
         * context.getgetSharedPreferences(String name, int mode) 获取sharedPreference对象
         * name是sharedPreference生成的xml文件的名字
         */
        sharedPreferences = getSharedPreferences("FirstIn",MODE_PRIVATE);
        mThread = new Thread(runnable);
        mThread.start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                //getBoolean(String key, boolean defValue) 获取键isFirst的值，若无此键则获取默认值（第一次打开程序的时候没有isFirst这个键）
                flag = sharedPreferences.getBoolean("isFirst", true);
                Message msg = handler.obtainMessage();
                if(flag){
                    //Editor对象用于修改sharedpreference对象,修改完后必须提交事务，才能完成修改（参考数据库的事务处理）
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isFirst", false);
                    editor.commit();
                    msg.what = SKIN_GUIDE;
                }else{
                    msg.what = SKINP_MAIN;
                }
                //休眠2s后，将信息发给handler，由handler来跳转activity
                Thread.sleep(2000);
                handler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
