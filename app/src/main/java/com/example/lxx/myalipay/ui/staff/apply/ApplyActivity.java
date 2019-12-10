package com.example.lxx.myalipay.ui.staff.apply;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseActivity;
import com.example.lxx.myalipay.ui.staff.adapter.MyPagerAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.CarFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.add_order.AddSendCarApplyActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.BuyFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.add_order.AddShoppingApplyActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.MaintainFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment3.add_order.AddRepairListActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.OrderFoodFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.add_order.ApplyOrderActivity;
import com.example.lxx.myalipay.utils.LatteLogger;
import com.example.lxx.myalipay.utils.TabEntity;
import com.example.lxx.myalipay.utils.event.Event;
import com.example.lxx.myalipay.utils.event.EventBusUtil;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;

/**
 * created by lxx at 2019/11/27 13:44
 * 描述:我的申请 fragment_common_apply
 */
public class ApplyActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout homeBack;
    @BindView(R.id.tv_title)
    TextView title;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;

    private int[] unSelected = {R.mipmap.ic_approve01, R.mipmap.ic_approve02,
            R.mipmap.ic_approve03, R.mipmap.ic_approve04};
    private MyPagerAdapter adapter;

    private String[] vpContent = {"派车申请", "采购申请", "维修申请", "订餐申请"};
    private String[] strTab1 = {"派车申请", "滴滴打车"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public int flag = 0;
    private Calendar calendar = Calendar.getInstance();

    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    public String param = null;

    public static final int CHANGE = 1;
    public static final int REPAIR = 2;
    public static final int ORDER = 4;

    private int status = -1;

    private Message message;
    private Bundle bundle;

    public static ApplyActivity newInstance(){
        return new ApplyActivity();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            final Bundle bundle = msg.getData();
            flag = bundle.getInt("position");
            tvEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag == 0) {
                        Intent intent = new Intent(_mActivity, AddSendCarApplyActivity.class);
                        startActivityForResult(intent, CHANGE);
                    } else if (flag == 1) {
                        startActivity(new Intent(_mActivity, AddShoppingApplyActivity.class));
                    } else if (flag == 2) {
                        Intent intent = new Intent(_mActivity, AddRepairListActivity.class);
                        startActivityForResult(intent, REPAIR);
                    } else if (flag == 3) {
                        Intent intent = new Intent(mContext, ApplyOrderActivity.class);
                        bundle.putString("style","1");
                        intent.putExtras(bundle);
                        startActivityForResult(intent, ORDER);
                    }
                }
            });
        }
    };

    @Override
    public int getLayoutId() {
        return  R.layout.fragment_common_apply;
    }

    @Override
    public void initView() {
        initToolbarNav(homeBack);
        title.setText("申请");
        //这是右边两个图标
        tvEnd.setVisibility(View.VISIBLE);
        tvEnd.setText(getString(R.string.apply));
        setViewPager(); //设置界面
        tl();//消息提醒

        viewpager.setCurrentItem(flag);
        message = handler.obtainMessage();
        bundle = new Bundle();
        bundle.putInt("position", flag);
        message.setData(bundle);
        message.sendToTarget();
    }

    private void tl() {
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void setListFragment() {
        mFragments.add(CarFragment.newInstance());
        mFragments.add(BuyFragment.newInstance());
        mFragments.add(MaintainFragment.newInstance());
        mFragments.add(OrderFoodFragment.newInstance());
        adapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,vpContent);
        viewpager.removeAllViews();
        viewpager.removeAllViewsInLayout();
        viewpager.setAdapter(adapter);
    }

    private void setViewPager() {
        setListFragment();
        for (int i = 0; i < vpContent.length; i++) {
            mTabEntities.add(new TabEntity(vpContent[i], unSelected[i], unSelected[i]));
        }

        viewpager.setCurrentItem(0);
        adapter.notifyDataSetChanged();
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
                flag = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 请求码，返回添加订单消息
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected String mMsg;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE && resultCode == RESULT_OK && data != null) {
            mMsg = data.getStringExtra("result");
            EventBusUtil.sendEvent(new Event(FinalClass.A));//派车申请单成功发送到子fragment中
        } else if (requestCode == ORDER && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            LatteLogger.d("ddd", result);
            EventBusUtil.sendEvent(new Event(FinalClass.A));
        } else if (resultCode == 100) {
           LatteLogger.d("myMsg===",data.getStringExtra("msg"));
            EventBusUtil.sendEvent(new Event(FinalClass.A));
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    public void onEventBusCome(Event event) {
        switch (event.getCode()) {
            case FinalClass.D:
                mFragments.clear();
                mFragments.add(CarFragment.newInstance());
                mFragments.add(BuyFragment.newInstance());
                mFragments.add(MaintainFragment.newInstance());
                mFragments.add(OrderFoodFragment.newInstance());
                adapter.notifyDataSetChanged();
                break;
        }
    }
}























