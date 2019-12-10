package com.example.lxx.myalipay.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.lxx.myalipay.MainActivity;
import com.example.lxx.myalipay.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.lxx.myalipay.base.BaseMainFragment.TOUCH_TIME;
import static com.example.lxx.myalipay.base.BaseMainFragment.WAIT_TIME;


public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager mViewPager;//声明ViewPager
    private LinearLayout mLinearLayout;//声明将来放置底部小圆点的LinearLayout
    private List<View> mViews;//ViewPager中放置的界面的集合
    private ImageView[] mDots;//底部小圆点的集合
    private GuideViewAdapter mAdapter;//ViewPager适配器
    private Button mButton; //按钮切换

    //引导图片资源
    private static final int[] pics = {R.layout.view1, R.layout.view2, R.layout.view3, R.layout.view4, R.layout.view5};
    //记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initDots() {
        mLinearLayout = (LinearLayout) findViewById(R.id.ll);
        mDots = new ImageView[mViews.size()];
        for (int i = 0; i < mViews.size(); i++) {
            mDots[i] = (ImageView) mLinearLayout.getChildAt(i);
            mDots[i].setEnabled(true);//都设为白色
            mDots[i].setOnClickListener(this);
            mDots[i].setTag(i);//设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0;
        mDots[currentIndex].setEnabled(false);//设置为灰黑色
    }

    /**
     * 设置当前的引导页
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mViewPager.setCurrentItem(position);
    }

    /**
     * 这只当前引导小点的图标
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }
        mDots[position].setEnabled(false);
        mDots[currentIndex].setEnabled(true);
        currentIndex = position;
    }

    //当滑动状态改变时调用
    public void initView() {
        mViews = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        //初始化引导图片列表
        View view1 = inflater.inflate(R.layout.view1, null);
        View view2 = inflater.inflate(R.layout.view2, null);
        View view3 = inflater.inflate(R.layout.view3, null);
        View view4 = inflater.inflate(R.layout.view4, null);
        View view5 = inflater.inflate(R.layout.view5, null);
        mViews.add(view1);
        mViews.add(view2);
        mViews.add(view3);
        mViews.add(view4);
        mViews.add(view5);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        //初始化Adapter
        mAdapter = new GuideViewAdapter(mViews);
        mViewPager.setAdapter(mAdapter);
        //绑定回调
        mViewPager.addOnPageChangeListener(this);
        mButton = (Button) findViewById(R.id.guide_btn);
        initDots();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long nowTime = System.currentTimeMillis();
                if (nowTime - TOUCH_TIME > WAIT_TIME) {
                    startActivity(new Intent(GuideActivity.this,MainActivity.class));
                    TOUCH_TIME = nowTime;
                    finish();
                } else {
                    //  ToastUtils.getInstance().showToast("不要重复点击，手机会爆的呢");
                }


            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置底部小点选中状态
        setCurDot(position);
        if (position == 4) {
            mButton.setVisibility(View.VISIBLE);

        } else {
            mButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class GuideViewAdapter extends PagerAdapter {
        private List<View> mViews;

        public GuideViewAdapter(List<View> views) {
            mViews = views;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }
    }
}

