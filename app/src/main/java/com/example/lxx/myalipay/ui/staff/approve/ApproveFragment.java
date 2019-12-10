package com.example.lxx.myalipay.ui.staff.approve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lxx.myalipay.R;
import com.example.lxx.myalipay.api.FinalClass;
import com.example.lxx.myalipay.base.BaseFragment;
import com.example.lxx.myalipay.ui.staff.adapter.MyPagerAdapter;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.CarFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment1.add_order.AddSendCarApplyActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.BuyFragment;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment2.add_order.AddShoppingApplyActivity;
import com.example.lxx.myalipay.ui.staff.apply.ui.fragment4.OrderFoodFragment;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment3.SubContractFragment;
import com.example.lxx.myalipay.ui.staff.approve.ui.fragment4.SendCarChildFragment04;
import com.example.lxx.myalipay.ui.staff.query.ui.position6.CardQueryFragment;
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
import me.yokeyword.fragmentation.SupportFragment;

import static com.example.lxx.myalipay.ui.staff.apply.ApplyActivity.ORDER;


/**
 * created by lxx at 2019/11/27 13:47
 * 描述:
 */
public class ApproveFragment extends BaseFragment {
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private Calendar calendar;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    private String StartDay, EndDate;
    private String[] vpContent = {"派车审批", "采购审批", "委外加工审批", "补卡申请"};//,"自定义查询"
    private int[] unSelected = {R.mipmap.ic_approve01, R.mipmap.ic_approve02,
            R.mipmap.ic_approve04, R.mipmap.ic_approve05};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    public static final int CHANGE = 1;
    private MyPagerAdapter adapter;
    private Message message;
    private Bundle bundle = new Bundle();
    public int flag = 0;
    public String[] mDatas;

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            flag = bundle.getInt("position");

            tvEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag == 0) {
                        Intent intent = new Intent(_mActivity, AddSendCarApplyActivity.class);
                        startActivityForResult(intent, CHANGE);
                    }else if (flag == 1){
                        startActivity(new Intent(_mActivity, AddShoppingApplyActivity.class));
                    } else if (flag == 3) {

                    }
                }
            });
        }
    };

    public static ApproveFragment newInstance(){
        ApproveFragment fragment = new ApproveFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_apply;
    }

    @Override
    public void initView() {
        initToolbarNav(llBack);
        tvTitle.setText("审批");
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
        mFragments.add(SubContractFragment.newInstance());
        mFragments.add(SendCarChildFragment04.newInstance());
        adapter = new MyPagerAdapter(getChildFragmentManager(),mFragments,vpContent);
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
                mFragments.add(SubContractFragment.newInstance());
                mFragments.add(SendCarChildFragment04.newInstance());
                adapter.notifyDataSetChanged();
                break;
        }
    }

    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}
















